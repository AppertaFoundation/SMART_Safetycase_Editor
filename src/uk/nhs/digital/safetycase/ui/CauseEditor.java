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
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import uk.nhs.digital.projectuiframework.Project;
import uk.nhs.digital.projectuiframework.smart.SmartProject;
import uk.nhs.digital.projectuiframework.ui.EditorComponent;
import uk.nhs.digital.safetycase.data.Cause;
import uk.nhs.digital.safetycase.data.MetaFactory;
import uk.nhs.digital.safetycase.data.Persistable;
import uk.nhs.digital.safetycase.data.Relationship;

/**
 *
 * @author sharif
 */
public class CauseEditor extends javax.swing.JPanel 
    implements uk.nhs.digital.safetycase.ui.PersistableEditor
{
    private EditorComponent editorComponent = null;
    private Cause cause = null;
    private boolean modified = false;
 
    private final String[] linkcolumns = {"Type", "Name", "Comment"};
    private int newObjectProjectId = -1;
    /**
     * Creates new form HazardEditor
     */
    public CauseEditor() {
        initComponents();
        linksTable.setDefaultEditor(Object.class, null);
        linksTable.setDefaultRenderer(Object.class, new LinkTableCellRenderer());        
        DefaultTableModel dtm = new DefaultTableModel(linkcolumns, 0);
        SmartProject.getProject().addNotificationSubscriber(this);
        linksTable.setModel(dtm);
        linksTable.setRowHeight(SmartProject.getProject().getTableRowHeight());

        try {
            ArrayList<String> conds = MetaFactory.getInstance().getFactory("Cause").getDistinctSet("GroupingType");
            if (conds.isEmpty()) {
                conditionsComboBox.addItem("Generic");
            } else {
                for (String s : conds) {
                    conditionsComboBox.addItem(s);
                }
            }
        }
        catch (Exception e) {
            SmartProject.getProject().log("Failed to initialise CauseEditor", e);
        }
    }

    @Override
    public boolean wantsScrollPane() { return false; }
    
//    @Override
//    public void addNotify() {
//        super.addNotify();
//        SmartProject.getProject().addNotificationSubscriber(this);
//    }
    
//    @Override
//    public void removeNotify() {
//        super.removeNotify();
//        SmartProject.getProject().removeNotificationSubscriber(this);
//    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        editorPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        nameTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        conditionsComboBox = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        descriptionTextArea = new javax.swing.JTextArea();
        linksPanel = new javax.swing.JPanel();
        editLinksButton = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        linksTable = new javax.swing.JTable();
        saveButton = new javax.swing.JButton();

        editorPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        editorPanel.setLayout(new javax.swing.BoxLayout(editorPanel, javax.swing.BoxLayout.PAGE_AXIS));

        jLabel1.setText("Name");

        nameTextField.setEditable(false);

        jLabel2.setText("Condition");

        conditionsComboBox.setEditable(true);
        conditionsComboBox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                conditionsComboBoxMousePressed(evt);
            }
        });
        conditionsComboBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                conditionsComboBoxKeyTyped(evt);
            }
        });

        jLabel4.setText("Description");

        descriptionTextArea.setColumns(20);
        descriptionTextArea.setLineWrap(true);
        descriptionTextArea.setRows(5);
        descriptionTextArea.setWrapStyleWord(true);
        descriptionTextArea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                descriptionTextAreaKeyTyped(evt);
            }
        });
        jScrollPane2.setViewportView(descriptionTextArea);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addContainerGap(518, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane2)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addGap(123, 123, 123)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(nameTextField)
                        .addComponent(conditionsComboBox, 0, 476, Short.MAX_VALUE))
                    .addContainerGap()))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addContainerGap(109, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(15, 15, 15)
                    .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(15, 15, 15)
                    .addComponent(conditionsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(33, 33, 33)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        editorPanel.add(jPanel1);

        linksPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Links"));

        editLinksButton.setText("Links ...");
        editLinksButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editLinksButtonActionPerformed(evt);
            }
        });

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
        jScrollPane4.setViewportView(linksTable);

        javax.swing.GroupLayout linksPanelLayout = new javax.swing.GroupLayout(linksPanel);
        linksPanel.setLayout(linksPanelLayout);
        linksPanelLayout.setHorizontalGroup(
            linksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 601, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, linksPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(editLinksButton)
                .addContainerGap())
        );
        linksPanelLayout.setVerticalGroup(
            linksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(linksPanelLayout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editLinksButton)
                .addContainerGap())
        );

        editorPanel.add(linksPanel);

        saveButton.setText("Save");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(editorPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(saveButton, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(editorPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(saveButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void editLinksButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editLinksButtonActionPerformed
        
        JDialog linkEditor = new JDialog(JOptionPane.getFrameForComponent(this), true);
        linkEditor.add(new LinkEditor(cause).setParent(linkEditor));
        linkEditor.pack();
        linkEditor.setVisible(true);

        try {
            HashMap<String,ArrayList<Relationship>> rels = cause.getRelationshipsForLoad();
            DefaultTableModel dtm = new DefaultTableModel(linkcolumns, 0);
            for (String t : rels.keySet()) {
                ArrayList<Relationship> a = rels.get(t);
                for (Relationship r : a) {
                    String m = r.getManagementClass();
                    if ((m == null) || (!m.contentEquals("Diagram"))) {                    
                        Object[] row = new Object[linkcolumns.length];
                        for (int i = 0; i < linkcolumns.length; i++)
                            row[i] = r;
                        dtm.addRow(row);
                    }
                }
            }
            linksTable.setModel(dtm);
        }
        catch (Exception e) {
            SmartProject.getProject().log("Failed to process editLinks action in CauseEditor", e);
        }

    }//GEN-LAST:event_editLinksButtonActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        boolean created = false;
        if (cause == null) {
            JOptionPane.showMessageDialog(this, "Causes should be created via the Hazard Bowtie editor.", "Context!", JOptionPane.ERROR_MESSAGE);
            return;
//            cause = new Cause();
//            created = true;
        }
        // Only set the name if we're creating the cause. Otherwise do it via the
        // bowtie editor
        if (created)
            cause.setAttribute("Name", nameTextField.getText());
        cause.setAttribute("Description", descriptionTextArea.getText());
        cause.setAttribute("GroupingType", (String)conditionsComboBox.getSelectedItem());
        
//        if (newObjectProjectId == -1)
//            cause.setAttribute("ProjectID", Integer.parseInt(cause.getAttributeValue("ProjectID")));
//        else 
            cause.setAttribute("ProjectID",SmartProject.getProject().getCurrentProjectID());
        try {
            MetaFactory.getInstance().getFactory(cause.getDatabaseObjectName()).put(cause);
            SmartProject.getProject().editorEvent((created) ? Project.ADD : Project.UPDATE, cause);
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(editorPanel, "Failed to save Cause. Send logs to support", "Save failed", JOptionPane.ERROR_MESSAGE);
            SmartProject.getProject().log("Failed to save in CauseEditor", e);
        }
        modified = false;
    }//GEN-LAST:event_saveButtonActionPerformed

    private void descriptionTextAreaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_descriptionTextAreaKeyTyped
        modified = true;
    }//GEN-LAST:event_descriptionTextAreaKeyTyped

    private void conditionsComboBoxKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_conditionsComboBoxKeyTyped
        modified = true;
    }//GEN-LAST:event_conditionsComboBoxKeyTyped

    private void conditionsComboBoxMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_conditionsComboBoxMousePressed
        modified = true;
    }//GEN-LAST:event_conditionsComboBoxMousePressed
    
    private void doDelete() {
                
        if (cause == null)
            return;
        
        for (Relationship r : cause.getRelationships("Control")) {
            if (r.getManagementClass() != null) {
                JOptionPane.showMessageDialog(this, "Cause still connected to a Bowtie diagram. Remove from the diagram then delete.", "Cause in use", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        for (Relationship r : cause.getRelationships("Hazard")) {
            if (r.getManagementClass() != null) {
                JOptionPane.showMessageDialog(this, "Cause still connected to a Bowtie diagram. Remove from the diagram then delete.", "Cause in use", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        int dialogResult = JOptionPane.showConfirmDialog(null, "Would You Like to Delete this Cause?", "Warning", JOptionPane.YES_NO_OPTION);
        if (dialogResult == JOptionPane.NO_OPTION) {
            return;
        }
        try {
            MetaFactory.getInstance().getFactory(cause.getDatabaseObjectName()).delete(cause);
            editorComponent.notifyEditorEvent(Project.DELETE, cause);
        }
        catch (Exception e) {
            SmartProject.getProject().log("Failed to delete in CauseEditor", e);
        }        
    }
    
    private void newCause() {
        JOptionPane.showMessageDialog(this, "Causes should be created via the Hazard Bowtie editor.", "Context!", JOptionPane.ERROR_MESSAGE);
        
        
//         DefaultTableModel dtm = new DefaultTableModel(linkcolumns, 0);
//         linksTable.setModel(dtm);
//        editorPanel.setEnabled(true);
//        conditionsComboBox.setSelectedIndex(-1);
//       nameTextField.setText("");
//        descriptionTextArea.setText("");        
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> conditionsComboBox;
    private javax.swing.JTextArea descriptionTextArea;
    private javax.swing.JButton editLinksButton;
    private javax.swing.JPanel editorPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPanel linksPanel;
    private javax.swing.JTable linksTable;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JButton saveButton;
    // End of variables declaration//GEN-END:variables

    @Override
    public void setPersistableObject(Persistable p) 
    {
        if (p == null) {
            JOptionPane.showMessageDialog(this, "Causes should be created via the Hazard Bowtie editor.", "Context!", JOptionPane.ERROR_MESSAGE);
            return;            
        }
        cause = (Cause)p;
        nameTextField.setText(cause.getAttributeValue("Name"));
        
        for (int i = 0; i < conditionsComboBox.getModel().getSize(); i++) {
            if (conditionsComboBox.getItemAt(i).contentEquals(cause.getAttributeValue("GroupingType"))) {
                conditionsComboBox.setSelectedIndex(i);
                break;
            }
        }
        descriptionTextArea.setText(cause.getAttributeValue("Description"));
        try {
            HashMap<String,ArrayList<Relationship>> rels = cause.getRelationshipsForLoad();
            DefaultTableModel dtm = new DefaultTableModel(linkcolumns, 0);
            for (String t : rels.keySet()) {
                ArrayList<Relationship> a = rels.get(t);
                for (Relationship r : a) {
                    if (r.isDeleted())
                        continue;

                    String m = r.getManagementClass();
                    if ((m == null) || (!m.contentEquals("Diagram"))) {    
                        Object[] row = new Object[linkcolumns.length];
                        for (int i = 0; i < linkcolumns.length; i++)
                            row[i] = r;
                        dtm.addRow(row);
                    }
                }
            }
            linksTable.setModel(dtm);
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(editorPanel, "Failed to load Cause for editing", "Load failed", JOptionPane.ERROR_MESSAGE);
            SmartProject.getProject().log("Failed to set persistable object in CauseEditor", e);
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

    @Override
    public boolean notification(int evtype, Object o) {
        if (evtype == Project.SAVE) {
            saveButtonActionPerformed(null);
            return false;
        }
        
        if (cause == null)
            return false;
        if (o instanceof uk.nhs.digital.safetycase.data.Cause) {
            Cause c = (Cause)o;
            if (c == cause) {
                if (evtype == Project.DELETE) {
                    // Close this form and its container... 
                    SmartProject.getProject().getProjectWindow().closeContainer(this);
                    // then return true so that this form can be removed from the
                    // notifications list
                    return true;
                }
                SmartProject.getProject().getProjectWindow().setViewTitle(this, "Cause:" + c.getTitle());
                setPersistableObject(c);
                if (evtype == Project.SAVE) {
                    saveButtonActionPerformed(null);
                }
            }
        }
        return false;
    }

    @Override
    public JPanel getEditor(Object o) {
        try {            
            Cause c = (Cause)o;
            if (c.getTitle().equals(cause.getTitle()))
                return this;
        }
        catch (Exception e) {}
        return null;
    }

    @Override
    public void unsubscribe() {
        SmartProject.getProject().removeNotificationSubscriber(this);
    }

    @Override
    public boolean isModified() {
        return modified;
    }
}
