/*
 * 
 *   Copyright 2017  NHS Digital
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

import java.awt.Component;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import uk.nhs.digital.projectuiframework.Project;
import uk.nhs.digital.projectuiframework.smart.SmartProject;
import uk.nhs.digital.projectuiframework.ui.EditorComponent;
import uk.nhs.digital.safetycase.data.Control;
import uk.nhs.digital.safetycase.data.MetaFactory;
import uk.nhs.digital.safetycase.data.Persistable;
import uk.nhs.digital.safetycase.data.Relationship;
import uk.nhs.digital.safetycase.data.ValueSet;

/**
 *
 * @author damian
 */
public class ControlEditor extends javax.swing.JPanel 
         implements uk.nhs.digital.safetycase.ui.PersistableEditor
{
    private final String[] linkcolumns = {"Type", "Name", "Comment"};

    private EditorComponent editorComponent = null;
    private Control control = null;
    private int newObjectProjectId = -1;
    /**
     * Creates new form ControlEditor
     */
    public ControlEditor() {
        initComponents();
        DefaultTableModel linkModel = new DefaultTableModel(linkcolumns, 0);
        linksTable.setModel(linkModel);
        try {
            ValueSet controlType = MetaFactory.getInstance().getValueSet("ControlType");
            Iterator<String> ctypes = controlType.iterator();
            while(ctypes.hasNext()) {
                String s = ctypes.next();
                typeComboBox.addItem(s);
            }
            ValueSet controlState = MetaFactory.getInstance().getValueSet("ControlState");
            Iterator<String> cstates = controlState.iterator();
            while(cstates.hasNext()) {
                String s = cstates.next();
                stateComboBox.addItem(s);
            }
            ArrayList<String> conds = MetaFactory.getInstance().getFactory("Control").getDistinctSet("GroupingType");
            if (conds.isEmpty()) {
                conditionsComboBox.addItem("Generic");
            } else {
                for (String s : conds) {
                    conditionsComboBox.addItem(s);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        editorPanel = new javax.swing.JPanel();
        linksPanel = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        linksTable = new javax.swing.JTable();
        editLinksButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        nameTextField = new javax.swing.JTextField();
        typeComboBox = new javax.swing.JComboBox<>();
        stateComboBox = new javax.swing.JComboBox<>();
        conditionsComboBox = new javax.swing.JComboBox<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        descriptionTextArea = new javax.swing.JTextArea();
        saveButton = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        clinicalJustificationTextArea = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        evidenceTextArea = new javax.swing.JTextArea();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        editorPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        editorPanel.setEnabled(false);

        linksPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Links"));

        linksTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(linksTable);

        editLinksButton.setText("Links...");
        editLinksButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editLinksButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout linksPanelLayout = new javax.swing.GroupLayout(linksPanel);
        linksPanel.setLayout(linksPanelLayout);
        linksPanelLayout.setHorizontalGroup(
            linksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
            .addGroup(linksPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(editLinksButton))
        );
        linksPanelLayout.setVerticalGroup(
            linksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(linksPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editLinksButton))
        );

        jLabel1.setText("Name");

        jLabel2.setText("Type");

        jLabel3.setText("State");

        jLabel4.setText("Condition");

        jLabel5.setText("Description");

        conditionsComboBox.setEditable(true);

        descriptionTextArea.setColumns(20);
        descriptionTextArea.setLineWrap(true);
        descriptionTextArea.setRows(5);
        jScrollPane4.setViewportView(descriptionTextArea);

        saveButton.setText("Save");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        jLabel6.setText("Clinical justification");

        clinicalJustificationTextArea.setColumns(20);
        clinicalJustificationTextArea.setLineWrap(true);
        clinicalJustificationTextArea.setRows(5);
        jScrollPane5.setViewportView(clinicalJustificationTextArea);

        jLabel7.setText("Evidence");

        evidenceTextArea.setColumns(20);
        evidenceTextArea.setLineWrap(true);
        evidenceTextArea.setRows(5);
        evidenceTextArea.setWrapStyleWord(true);
        jScrollPane1.setViewportView(evidenceTextArea);

        javax.swing.GroupLayout editorPanelLayout = new javax.swing.GroupLayout(editorPanel);
        editorPanel.setLayout(editorPanelLayout);
        editorPanelLayout.setHorizontalGroup(
            editorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editorPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(editorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(linksPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(editorPanelLayout.createSequentialGroup()
                        .addGroup(editorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(editorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(stateComboBox, 0, 584, Short.MAX_VALUE)
                            .addComponent(typeComboBox, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(nameTextField, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(conditionsComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(editorPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 356, Short.MAX_VALUE))
                    .addGroup(editorPanelLayout.createSequentialGroup()
                        .addGroup(editorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(editorPanelLayout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(274, 274, 274)
                                .addComponent(jLabel6))
                            .addComponent(jLabel7))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editorPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        editorPanelLayout.setVerticalGroup(
            editorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editorPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(editorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(editorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(typeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(editorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(stateComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(editorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(conditionsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(editorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(editorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(linksPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(saveButton)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(editorPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(editorPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void doDelete() {
                
        if (control == null)
            return;
        
        for (Relationship r : control.getRelationships("Effect")) {
            if (r.getManagementClass() != null) {
                JOptionPane.showMessageDialog(this, "Cause still connected to a Bowtie diagram. Remove from the diagram then delete.", "Cause in use", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        for (Relationship r : control.getRelationships("Hazard")) {
            if (r.getManagementClass() != null) {
                JOptionPane.showMessageDialog(this, "Control still connected to a Bowtie diagram. Remove from the diagram then delete.", "Control in use", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        int dialogResult = JOptionPane.showConfirmDialog(null, "Would You Like to Delete this Control?", "Warning", JOptionPane.YES_NO_OPTION);
        if (dialogResult == JOptionPane.NO_OPTION) {
            return;
        }
        try {
            MetaFactory.getInstance().getFactory(control.getDatabaseObjectName()).delete(control);
            editorComponent.notifyEditorEvent(Project.DELETE, control);
        }
        catch (Exception e) {
            e.printStackTrace();
        }        
    }
    
    
    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        if (typeComboBox.getSelectedIndex() == -1)
            typeComboBox.setSelectedIndex(0);
        control.setAttribute("Name", nameTextField.getText());
        control.setAttribute("Description", descriptionTextArea.getText());
        control.setAttribute("ClinicalJustification", clinicalJustificationTextArea.getText());
        control.setAttribute("Type", (String)typeComboBox.getSelectedItem());
        control.setAttribute("State", (String)stateComboBox.getSelectedItem());
        control.setAttribute("GroupingType", (String)conditionsComboBox.getSelectedItem());
        control.setAttribute("Evidence", evidenceTextArea.getText());
//        if (newObjectProjectId == -1)
//            control.setAttribute("ProjectID", Integer.parseInt(control.getAttributeValue("ProjectID")));
//        else 
            control.setAttribute("ProjectID",SmartProject.getProject().getCurrentProjectID());
        try {
            MetaFactory.getInstance().getFactory(control.getDatabaseObjectName()).put(control);
            editorComponent.notifyEditorEvent(Project.UPDATE, control);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_saveButtonActionPerformed

    private void editLinksButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editLinksButtonActionPerformed
        JDialog linkEditor = new JDialog(JOptionPane.getFrameForComponent(this), true);
        linkEditor.add(new LinkEditor(control).setParent(linkEditor));
        linkEditor.pack();
        linkEditor.setVisible(true);

        try {
            HashMap<String,ArrayList<Relationship>> rels = control.getRelationshipsForLoad();
            DefaultTableModel dtm = new DefaultTableModel(linkcolumns, 0);
            for (String t : rels.keySet()) {
                ArrayList<Relationship> a = rels.get(t);
                for (Relationship r : a) {
                    String m = r.getManagementClass();
                    if ((m == null) || (!m.contentEquals("Diagram"))) {                    
                        Persistable tgt = MetaFactory.getInstance().getFactory(r.getTargetType()).get(r.getTarget());
                        String[] row = new String[linkcolumns.length];
                        row[0] = tgt.getDisplayName();
                        row[1] = tgt.getAttributeValue("Name");
                        row[2] = r.getComment();
                        dtm.addRow(row);
                    }
                }
            }
            linksTable.setModel(dtm);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_editLinksButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea clinicalJustificationTextArea;
    private javax.swing.JComboBox<String> conditionsComboBox;
    private javax.swing.JTextArea descriptionTextArea;
    private javax.swing.JButton editLinksButton;
    private javax.swing.JPanel editorPanel;
    private javax.swing.JTextArea evidenceTextArea;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable jTable1;
    private javax.swing.JPanel linksPanel;
    private javax.swing.JTable linksTable;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JButton saveButton;
    private javax.swing.JComboBox<String> stateComboBox;
    private javax.swing.JComboBox<String> typeComboBox;
    // End of variables declaration//GEN-END:variables

    private void newControl() {
        JOptionPane.showMessageDialog(this, "Controls should be created via the Hazard Bowtie editor.", "Context!", JOptionPane.ERROR_MESSAGE);
        
        
//         DefaultTableModel dtm = new DefaultTableModel(linkcolumns, 0);
//         linksTable.setModel(dtm);
//        editorPanel.setEnabled(true);
//        conditionsComboBox.setSelectedIndex(-1);
//       nameTextField.setText("");
//        descriptionTextArea.setText("");        
        
    }
    
    @Override
    public void setPersistableObject(Persistable p) {
        if (p == null) {
            JOptionPane.showMessageDialog(this, "Controls should be created via the Hazard Bowtie editor.", "Context!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        control = (Control)p;
        nameTextField.setText(control.getAttributeValue("Name"));
        clinicalJustificationTextArea.setText(control.getAttributeValue("ClinicalJustification"));
        descriptionTextArea.setText(control.getAttributeValue("Description"));
        evidenceTextArea.setText(control.getAttributeValue("Evidence"));
        for (int i = 0; i < conditionsComboBox.getModel().getSize(); i++) {
            if (conditionsComboBox.getItemAt(i).contentEquals(control.getAttributeValue("GroupingType"))) {
                conditionsComboBox.setSelectedIndex(i);
                break;
            }
        }
        String s = control.getAttributeValue("Type");
        for (int j = 0; j < typeComboBox.getItemCount(); j++) {
            if (s.contentEquals(typeComboBox.getItemAt(j))) {
                typeComboBox.setSelectedIndex(j);
                break;
            }
        }
        s = control.getAttributeValue("State");
        for (int j = 0; j < stateComboBox.getItemCount(); j++) {
            if (s.contentEquals(stateComboBox.getItemAt(j))) {
                stateComboBox.setSelectedIndex(j);
                break;
            }
        }        
        try {
            HashMap<String,ArrayList<Relationship>> rels = control.getRelationshipsForLoad();
            DefaultTableModel dtm = new DefaultTableModel(linkcolumns, 0);
            for (String t : rels.keySet()) {
                ArrayList<Relationship> a = rels.get(t);
                for (Relationship r : a) {
                    String m = r.getManagementClass();
                    if ((m == null) || (!m.contentEquals("Diagram"))) {                    
                        Persistable tgt = MetaFactory.getInstance().getFactory(r.getTargetType()).get(r.getTarget());
                        String[] row = new String[linkcolumns.length];
                        row[0] = tgt.getDisplayName();
                        row[1] = tgt.getAttributeValue("Name");
                        row[2] = r.getComment();
                        dtm.addRow(row);
                    }
                }
            }
            linksTable.setModel(dtm);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
      
    }

    @Override
    public Component getComponent() {
        return this;
    }

    @Override
    public void setEditorComponent(EditorComponent ed) {
        editorComponent = ed;
    }

    @Override
    public void setNewObjectProjectId(int i) {
        newObjectProjectId = i;
    }
}
