/*
 * 
 *   Copyright 2018  NHS Digital
 * 
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 * 
 *        http://www.apache.org/licenses/LICENSE-2.0
 * 
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *  
 */
package uk.nhs.digital.safetycase.ui;

import com.mxgraph.examples.swing.editor.BasicGraphEditor;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import uk.nhs.digital.projectuiframework.Project;
import uk.nhs.digital.projectuiframework.smart.SmartProject;
import uk.nhs.digital.projectuiframework.ui.SaveRejectedException;
import uk.nhs.digital.safetycase.data.System;
import uk.nhs.digital.safetycase.data.SystemFunction;
import uk.nhs.digital.safetycase.data.MetaFactory;
import uk.nhs.digital.safetycase.data.Persistable;
import uk.nhs.digital.safetycase.data.PersistableFactory;
import uk.nhs.digital.safetycase.data.Relationship;
import uk.nhs.digital.safetycase.ui.systemeditor.SystemGraphEditor;

/**
 *
 * @author damian
 */
public class SystemSaveHandler
        extends AbstractSaveHandler {

//    private final ArrayList<Persistable> added = new ArrayList<>();
//    private final ArrayList<Persistable> updated = new ArrayList<>();
    private final ArrayList<Persistable> removed = new ArrayList<>();

    private final ArrayList<String> processedElements = new ArrayList<>();
    private System system = null;
    SystemGraphEditor sge = null;

    @Override
    public void handle(BasicGraphEditor ge)
            throws Exception {
        boolean systemExists = false;
        try {
            // int systemStepId = -1;
            sge = (SystemGraphEditor) ge;
            //SystemStep ss = null;
            MetaFactory mf = MetaFactory.getInstance();
            PersistableFactory<System> sf = mf.getFactory("System");
            // PersistableFactory<uk.nhs.digital.safetycase.data.Process> pf = mf.getFactory("Process");
            // Process system = null;
            // System system = null;
            if (sge.getSystemId() != -1) {
                system = sf.get(sge.getSystemId());
            }
            String xml = getXml(ge);

            HashMap<String, DiagramEditorElement> existingSystem = sge.getExistingGraph();
            if (checkNames(xml, existingSystem))
                return;
            
            int projectid = -1;
            if (system == null) {
                system = new System();

                SmartProject sp = SmartProject.getProject();
                projectid = sp.getCurrentProjectID();
                system.setAttribute("ProjectID", projectid);
                //added.add(system);
            } else {
                systemExists = true;
                projectid = system.getAttribute("ProjectID").getIntValue();
               // updated.add(system);
            }
            //when parsing XMl make sure that we find the root system and not the subsystem.
            // this can be done using mxCell id value . if this id value is not be used as target in any of the edsge cell i.e having no target value of the system. that cell is root system.
            // e.g mxCell edge="1" id="6" parent="1" source="2" style="straight" target="3" value=""> value 2 is not used at target in the whole xml string
            NodeList cells = parseSystem(xml, system);
            HashMap<String, DiagramEditorElement> systemElements = parseSystem(cells, projectid);
            
            // See if the user has done anything dumb, like multiply-linked anything
            //
            for (DiagramEditorElement d : systemElements.values()) {
                if (d.connections.contains(system.getAttributeValue("GraphCellId"))) {
                    throw new BrokenConnectionException("The root system cannot have anything pointing to it");
                } else {
                    int linkCount = 0;
                    for (DiagramEditorElement other : systemElements.values()) {
                        if (other.connections.contains(Integer.toString(d.cellId))) {
                            linkCount++;
                            if (linkCount == 2)
                                throw new BrokenConnectionException(d.type + " " + d.name + " has more than one link to it. System diagram elements can only have one parent");
                        }
                    }
                    if ((d.cellId != system.getAttribute("GraphCellId").getIntValue()) && (linkCount == 0)) {
                        throw new BrokenConnectionException(d.type + " " + d.name + " is not the root system and has nothing pointing to it.");
                    }
                }
            }
            sf.put(system);
            //systemID = system.getId();

            if (existingSystem != null) {

                for (String cid : existingSystem.keySet()) {
                    DiagramEditorElement bt = existingSystem.get(cid);
                    ArrayList<Relationship> d = bt.object.deleteAutomaticRelationships();
                    MetaFactory.getInstance().getFactory(bt.object.getDatabaseObjectName()).put(bt.object);
//                    MetaFactory.getInstance().getFactory(bt.object.getDatabaseObjectName()).refresh(bt.object.getId());
                    bt.object.purgeAutomaticRelationships(d);
                    if (systemElements.containsKey(cid)) {
                        DiagramEditorElement btupdate = systemElements.get(cid);
                        if (btupdate != null) {
                            btupdate.object = bt.object;
                            if (!btupdate.object.getAttributeValue("Name").contentEquals(btupdate.name)) {
                                btupdate.object.setAttribute("Name", btupdate.name);
//                                updated.add(btupdate.object);
                                bt.object.setAttribute("Name", btupdate.name); // for updating name in the  database
                            }
                        }
                    } else {
                        removed.add(bt.object);
                    }
                }
            }
                else {
                 java.lang.System.out.println("Existing System is null ");
                //createSystemSystemFunctionRelationship(system, system.getId());
            }
            saveRootSystem(systemElements, projectid);
            sge.setExistingGraph(systemElements);
            sge.setSystemId(system.getId(), xml, false);
            java.lang.System.out.println(xml);
            SmartProject sp = SmartProject.getProject();
            if (systemExists) {
                sp.editorEvent(Project.UPDATE, system);
            } else {
                sp.editorEvent(Project.ADD, system);
            }
            deleteRemovedNodes();
        } catch (BrokenConnectionException bce) {
            JOptionPane.showMessageDialog(sge, "The diagram has a broken link and has not been saved: " + bce.getMessage(), "Diagram incomplete", JOptionPane.ERROR_MESSAGE);
            throw new SaveRejectedException();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(sge, "Failed to save. Send logs to support", "Save failed", JOptionPane.ERROR_MESSAGE);
            SmartProject.getProject().log("Failed to save in SystemSaveEditor", ex);
        }
        sge.setModified(false);
    }
    
    private void deleteRemovedNodes()
    {
        if (removed == null)
            return;
        for (Persistable p : removed) {
            try {
                MetaFactory.getInstance().getFactory(p.getDatabaseObjectName()).delete(p);
            }
            catch (Exception e) {
                SmartProject.getProject().log("Removing deleted node failed: " + p.getTitle(), e);
            }
        }
    }
    


    private HashMap<String, DiagramEditorElement> parseSystem(NodeList nl, int projectid)
            throws Exception {
        // Get the list of subsystems, functions and subFunctions in the bowtie...

        HashMap<String, DiagramEditorElement> systemElements = new HashMap<>();

        for (int i = 0; i < nl.getLength(); i++) {
            Element cell = (Element) nl.item(i);
            if (!cell.hasAttribute("edge")) { // Vertices only
                if (cell.hasAttribute("style")) { // Ignore the couple of "virtual" cells at the start of the model
                    DiagramEditorElement se = new DiagramEditorElement(cell.getAttribute("style"), cell.getAttribute("value"), cell.getAttribute("id"));
                    systemElements.put(cell.getAttribute("id"), se);
                }
            }
        }
        // create elements connections
        for (int i = 0; i < nl.getLength(); i++) {
            Element cell = (Element) nl.item(i);
            if (cell.hasAttribute("edge")) {
                String s = cell.getAttribute("source");
                String t = cell.getAttribute("target");
                if (s.length() == 0) {
                    if (t.length() == 0) {
                        throw new BrokenConnectionException("Unconnected system/function link");
                    } else {
                        String c = getCellName(nl, t);
                        throw new BrokenConnectionException("Link to " + c + " has no source system/function element");
                    }
                }
                if (t.length() == 0) {
                    String c = cell.getAttribute("value");
                    throw new BrokenConnectionException("Link from " + c + " has no target system/function element");
                }
                DiagramEditorElement se = systemElements.get(s);
                // bt.fromCell = Integer.parseInt(s);
                // bt.toCell = Integer.parseInt(t);
                if (se.type.contentEquals("SystemFunction")) {
                    DiagramEditorElement te = systemElements.get(t);
                    if (te.type.contentEquals("System")) {
                        throw new BrokenConnectionException("Function " + se.name + " cannot contain a system (" + te.name + ")");
                    }
                }
                se.connections.add(t);
            }
        }
        return systemElements;
    }

    private String getCellName(NodeList nl, String id) {
        for (int i = 0; i < nl.getLength(); i++) {
            Element cell = (Element) nl.item(i);
            if (cell.getAttribute("id").contentEquals(id)) {
                return cell.getAttribute("value");
            }
        }
        return null;
    }
     private void saveRootSystem(HashMap<String, DiagramEditorElement> systemElements, int projectid)
            throws Exception {
       
        DiagramEditorElement rootelement = systemElements.get(system.getAttributeValue("GraphCellId"));

        rootelement.object = system;
        system.setAttribute("Name", rootelement.name);
        MetaFactory.getInstance().getFactory(rootelement.type).put(rootelement.object);
//        MetaFactory.getInstance().getFactory("System").put(system);
         
        processedElements.add(String.valueOf(rootelement.cellId));
        for (String t : rootelement.connections) {
            DiagramEditorElement target = systemElements.get(t);
            if (target.type.equals("System")) { //main methid
                savesubsystem(rootelement, target, systemElements, projectid);
                
            } else if (target.type.equals("SystemFunction")) {
                savesystemfunction(rootelement, target, systemElements, projectid);

            }
        }

        for (DiagramEditorElement bt : systemElements.values()) {
            if (!processedElements.contains(String.valueOf(bt.cellId))) {

                java.lang.System.out.println("Unprocessed Element : " + bt.name + " , Cell ID : " + bt.cellId + " , No of Connections : " + bt.connections.toString());
            }
        }
        
        MetaFactory.getInstance().getFactory(rootelement.type).put(rootelement.object);
        //if any relations been added to system
        MetaFactory.getInstance().getFactory("System").put(system);
       // MetaFactory.getInstance().initialise(projectid); //work around for loading the new relationships.
       
       
    }
    private void savesystemfunction(DiagramEditorElement parent, DiagramEditorElement target, HashMap<String, DiagramEditorElement> systemelements, int projectid) throws Exception {
        if (target.object == null) {
            if (!processedElements.contains(String.valueOf(target.cellId))) {
                Persistable p = createPersistable(target.type);
                if (p == null) {
                    throw new Exception("Unknown Bowtie Element type " + target.type);
                }

                p.setAttribute("Name", target.name);
                p.setAttribute("GraphCellId", target.cellId);
                p.setAttribute("ProjectID", projectid);
                target.object = p;

                MetaFactory.getInstance().getFactory(target.type).put(p);
                processedElements.add(String.valueOf(target.cellId));

                Relationship rel = new Relationship(parent.object.getId(), target.object.getId(), target.type);
                rel.setComment("system diagram");
                rel.setManagementClass("Diagram");
                if (parent.type.equals("SystemFunction")) {
                    p.setAttribute("ParentSystemFunctionID", parent.object.getId());
                    MetaFactory.getInstance().getFactory(target.type).put(p);
                    //target.object.addRelationship(rel);
                   // parent.object.addRelationship(rel);
                   // MetaFactory.getInstance().getFactory(target.type).put(p);
                } //else {
                    //system.addRelationship(rel); // if the request is not from systemfunction then we need to add relation to system object.
                    parent.object.addRelationship(rel);
                    MetaFactory.getInstance().getFactory(parent.type).put(parent.object);
                //}
                
                //parent.object.addRelationship(rel);
               // MetaFactory.getInstance().getFactory(parent.type).put(parent.object);
                
            }
        } else {
            Relationship rel = new Relationship(parent.object.getId(), target.object.getId(), target.type);
            //Relationship rel = new Relationship(parentid, target.object.getId(), requestsenderobject);
            rel.setComment("system diagram");
            rel.setManagementClass("Diagram");
//            if (parent.type.equals("SystemFunction")) {
//                target.object.addRelationship(rel);
//            } else {
//                //system.addRelationship(rel);
//                parent.object.addRelationship(rel);
//                MetaFactory.getInstance().getFactory(parent.type).put(parent.object);
//            }
//            MetaFactory.getInstance().getFactory(target.type).put(target.object);
            
            //Replaced above if condition.
            parent.object.addRelationship(rel);
            MetaFactory.getInstance().getFactory(parent.type).put(parent.object);
            processedElements.add(String.valueOf(target.cellId));
           
        }
        for (String t : target.connections) {
            DiagramEditorElement targetchild = systemelements.get(t);
            if (targetchild.object == null) {
                if (!processedElements.contains(String.valueOf(targetchild.cellId))) { 
                    if (targetchild.type.equals("SystemFunction")) {
                        savesystemfunction(target, targetchild, systemelements, projectid);
                        //processedElements.add(t);
                    } else if (targetchild.type.equals("System")) { 
                        java.lang.System.out.println("Wrong connection from " + target.name + " to  " + targetchild.name);
                        //savesystem(target, targetchild, bowtieelements, projectid);
                    }
                }
            } else {
                Relationship rel = new Relationship(target.object.getId(), targetchild.object.getId(), targetchild.type);
                //Relationship rel = new Relationship(parentid, target.object.getId(), requestsenderobject);
                rel.setComment("system diagram");
                rel.setManagementClass("Diagram");
                target.object.addRelationship(rel);
                MetaFactory.getInstance().getFactory(target.type).put(target.object);
//                if (target.type.equals("SystemFunction")) { 
//                    target.object.addRelationship(rel);
//                } else {
//                   // parent.object.addRelationship(rel);
//                   // MetaFactory.getInstance().getFactory(parent.type).put(parent.object);
//                   // parent.object.addRelationship(rel);
//                   // MetaFactory.getInstance().getFactory(parent.type).put(parent.object);
//                }
//                MetaFactory.getInstance().getFactory(target.type).put(target.object);
                processedElements.add(t);
                
                //check for subchild
                for (String st : targetchild.connections) {
                    DiagramEditorElement targetsubchild = systemelements.get(st);
                    if (targetsubchild.type.equals("SystemFunction")) {
                        savesystemfunction(targetchild, targetsubchild, systemelements, projectid);
                        //processedElements.add(st);
                    }else if (targetsubchild.type.equals("System")) {
                        // To do throw exception as systemfunction and systemfunction shouldn't have a cinnection to system/subsystem.
                        java.lang.System.err.println("Wrong connection from " + targetchild.name + " to  " + targetsubchild.name);
                        //savesystem(targetchild, targetsubchild, bowtieelements, projectid);
                        processedElements.add(st);
                    }
                }
            }
            MetaFactory.getInstance().getFactory(parent.type).put(parent.object);
        }
    }

    private void savesubsystem(DiagramEditorElement parent, DiagramEditorElement target, HashMap<String, DiagramEditorElement> systemelements, int projectid) throws Exception {
        //System s = new System();
        if (target.object == null) {
            if (!processedElements.contains(String.valueOf(target.cellId))) {
                Persistable p = createPersistable(target.type);
                if (p == null) {
                    throw new Exception("Unknown Bowtie Element type " + target.type);
                }
                p.setAttribute("Name", target.name);
                p.setAttribute("GraphCellId", target.cellId);
                p.setAttribute("ParentSystemID", parent.object.getId());
                p.setAttribute("ProjectID", projectid);
                target.object = p;
                MetaFactory.getInstance().getFactory(target.type).put(p);
                Relationship rel = new Relationship(parent.object.getId(), target.object.getId(), target.type);
                //  Relationship rel = new Relationship(parent.object.getId(), target.object.getId(), parent.type);
                // Relationship rel = new Relationship(parentid, target.object.getId(), "System");
                rel.setComment("system diagram");
                rel.setManagementClass("Diagram");
                parent.object.addRelationship(rel);
                //system.addRelationship(rel); //temp comment
                MetaFactory.getInstance().getFactory(target.type).put(p);
                processedElements.add(String.valueOf(target.cellId));
            }
        } else {
            Relationship rel = new Relationship(parent.object.getId(), target.object.getId(), target.type);
            //Relationship rel = new Relationship(parent.object.getId(), target.object.getId(), parent.type);
            rel.setComment("system diagram");
            rel.setManagementClass("Diagram");
            //target.object.addRelationship(rel);
            parent.object.addRelationship(rel);
            MetaFactory.getInstance().getFactory(parent.type).put(parent.object);
            processedElements.add(String.valueOf(target.cellId));
        }
        for (String t : target.connections) {
            DiagramEditorElement targetchild = systemelements.get(t);
            if (targetchild.object == null) {
                if (!processedElements.contains(String.valueOf(targetchild.cellId))) {
                    if (targetchild.type.equals("System")) {
                        savesubsystem(target, targetchild, systemelements, projectid);
                    } else if (targetchild.type.equals("SystemFunction")) {
                        savesystemfunction(target, targetchild, systemelements, projectid);
                    }
                }
            } else {
                Relationship rel = new Relationship(target.object.getId(), targetchild.object.getId(), targetchild.type);
                rel.setComment("system diagram");
                rel.setManagementClass("Diagram");
                target.object.addRelationship(rel);
                MetaFactory.getInstance().getFactory(target.type).put(target.object);
                processedElements.add(t);
                //check for subchild
                for (String st : targetchild.connections) {
                    DiagramEditorElement targetsubchild = systemelements.get(st);
                    if (targetsubchild.type.equals("System")) {
                        savesubsystem(targetchild, targetsubchild, systemelements, projectid);
                        //processedElements.add(st);
                    } else if (targetsubchild.type.equals("SystemFunction")) {
                        savesystemfunction(targetchild, targetsubchild, systemelements, projectid);
                        //processedElements.add(st);
                    }
                }
            }
            MetaFactory.getInstance().getFactory(parent.type).put(parent.object);
        }
    }
    Persistable createPersistable(String t) {
        Persistable p = null;
        if (t.contentEquals("System")) {
            p = new System();
        }
        if (t.contentEquals("SystemFunction")) {
            p = new SystemFunction();
        }        
        if (t.contentEquals("Function")) {
            p = new SystemFunction();
        }
//        if (t.contentEquals("SubFunction")) {
//            p = new SystemFunction();
//        }
//        if (p != null) {
//            added.add(p);
 //       }
        return p;
    }

    private boolean checkNames(String xml, HashMap<String,DiagramEditorElement> existingSystem) {
        try {
            int pid = SmartProject.getProject().getCurrentProjectID();
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setNamespaceAware(true);
            DocumentBuilder db = dbf.newDocumentBuilder();
            StringReader sr = new StringReader(xml);
            InputSource is = new InputSource(sr);
            Element d = db.parse(is).getDocumentElement();
            NodeList nl = d.getElementsByTagName("mxCell");
            for (int i = 0; i < nl.getLength(); i++) {
                Element cell = (Element)nl.item(i);
                if (cell.hasAttribute("style")) {
                    String id = cell.getAttribute("id");
                    String name = cell.getAttribute("value");
                   
                    // Get any existing object of this sort
                    Persistable p = null;
                    if (existingSystem != null) {
                        DiagramEditorElement dee = existingSystem.get(id);
                        if (dee != null)
                            p = dee.object;
                    }    
                    
                    if (cell.getAttribute("style").contains("image=/uk/nhs/digital/safetycase/ui/systemeditor/system.png")) {
                        try {
//                            String duplicateWarning = MetaFactory.getInstance().getDuplicateCheckMessage("System", "System", name,pid, system);
                            String duplicateWarning = MetaFactory.getInstance().getDuplicateCheckMessage("System", "System", name,pid, p);

                            if (duplicateWarning != null) {
                                JOptionPane.showMessageDialog(sge, duplicateWarning, "Duplicate system name", JOptionPane.ERROR_MESSAGE);
                                return true;
                            }
                        }
                        catch (Exception e) {}                        
                    }
                }
            }
        }
        catch (IOException | ParserConfigurationException | SAXException e) {
            JOptionPane.showMessageDialog(sge, "Error checking for duplicate names, send logs to support", "Error", JOptionPane.ERROR_MESSAGE);
            SmartProject.getProject().log("Error in system duplicate name check", e);
        }
        return false;
    }
    
    private NodeList parseSystem(String xml, System s)
            throws Exception {
        // Find the System, get the name, and store it.
        //when parsing XMl make sure that we find the root system and not the subsystem.
        // this can be done using mxCell id value . this will not be used as target in any of the edsge cell having no target value of the system
        // e.g mxCell edge="1" id="6" parent="1" source="2" style="straight" target="3" value=""> if value 2 is not used at target in the whole xml string then 2 is root element

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        HashMap<String, Element> dic = new HashMap<>();
        dbf.setNamespaceAware(true);
        DocumentBuilder db = dbf.newDocumentBuilder();
        StringReader sr = new StringReader(xml);
        InputSource is = new InputSource(sr);
        Element d = db.parse(is).getDocumentElement();
        NodeList nl = d.getElementsByTagName("mxCell");
        for (int i = 0; i < nl.getLength(); i++) {
            Element cell = (Element) nl.item(i);
            if (cell.hasAttribute("style")
                    && cell.getAttribute("style").contains("image=/uk/nhs/digital/safetycase/ui/systemeditor/system.png")) {
                String id = cell.getAttribute("id");
                dic.put(id, cell);
            }
        }
        for (int i = 0; i < nl.getLength(); i++) {
            Element cell = (Element) nl.item(i);
            if (cell.hasAttribute("edge")) {
                String id = cell.getAttribute("target");
                if (dic.containsKey(id)) {
                    dic.remove(id);// reomve as it not the root node
                }
            }
        }
        if (dic.entrySet().isEmpty()) {
            throw new BrokenConnectionException("Root system not found. This is probably because there is something pointing to the root system, and it cannot have anything pointing to it");    
        }
        if (dic.entrySet().size() > 1) {
            throw new BrokenConnectionException("More than one root system found. This is probably because there are more than one systems with nothing pointing to them. Make a new system from the project instead.");    
        }
        HashMap.Entry<String, Element> entry = dic.entrySet().iterator().next();
        Element cell = entry.getValue();
        String id = cell.getAttribute("id"); //entry.getKey();
        String name = cell.getAttribute("value");
        s.setAttribute("Name", name);
        s.setAttribute("GraphCellId", Integer.parseInt(id));
        s.setAttribute("GraphXml", xml);

        return nl;
    }

}
