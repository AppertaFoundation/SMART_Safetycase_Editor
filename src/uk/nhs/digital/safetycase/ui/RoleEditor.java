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
import uk.nhs.digital.safetycase.data.MetaFactory;
import uk.nhs.digital.safetycase.data.Persistable;
import uk.nhs.digital.safetycase.data.Relationship;
import uk.nhs.digital.safetycase.data.Role;

/**
 *
 * @author damian
 */
public class RoleEditor extends javax.swing.JPanel
        implements uk.nhs.digital.safetycase.ui.PersistableEditor
{
    private final String[] linkcolumns = {"Type", "Name", "Comment"};
    private Role role = null;
    private EditorComponent editorComponent = null;
    private int newObjectProjectId = -1;

    /**
     * Creates new form RoleEditor
     */
    public RoleEditor() {
        initComponents();
        DefaultTableModel dtm = new DefaultTableModel(linkcolumns, 0);
        linksTable.setRowHeight(SmartProject.getProject().getTableRowHeight());
        linksTable.setDefaultEditor(Object.class, null);
        linksTable.setDefaultRenderer(Object.class, new LinkTableCellRenderer());        
        linksTable.setModel(dtm);
        SmartProject.getProject().addNotificationSubscriber(this);
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
        jLabel2 = new javax.swing.JLabel();
        nameTextField = new javax.swing.JTextField();
        categoryTextField = new javax.swing.JTextField();
        descriptionPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        descriptionTextArea = new javax.swing.JTextArea();
        linksPanel = new javax.swing.JPanel();
        editLinksButtonButtonPanel = new javax.swing.JPanel();
        editLinksButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        linksTable = new javax.swing.JTable();
        buttonsPanel = new javax.swing.JPanel();
        saveButton = new javax.swing.JButton();
        discardButton = new javax.swing.JButton();

        editorPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        editorPanel.setLayout(new javax.swing.BoxLayout(editorPanel, javax.swing.BoxLayout.PAGE_AXIS));

        jLabel1.setText("Name");

        jLabel2.setText("Category");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(58, 58, 58)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nameTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)
                    .addComponent(categoryTextField))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(categoryTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap())
        );

        editorPanel.add(jPanel1);

        descriptionPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Description"));

        descriptionTextArea.setColumns(20);
        descriptionTextArea.setLineWrap(true);
        descriptionTextArea.setRows(5);
        descriptionTextArea.setWrapStyleWord(true);
        jScrollPane2.setViewportView(descriptionTextArea);

        javax.swing.GroupLayout descriptionPanelLayout = new javax.swing.GroupLayout(descriptionPanel);
        descriptionPanel.setLayout(descriptionPanelLayout);
        descriptionPanelLayout.setHorizontalGroup(
            descriptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 641, Short.MAX_VALUE)
            .addGroup(descriptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(descriptionPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 617, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        descriptionPanelLayout.setVerticalGroup(
            descriptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 102, Short.MAX_VALUE)
            .addGroup(descriptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(descriptionPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        editorPanel.add(descriptionPanel);

        linksPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Linked to"));
        linksPanel.setLayout(new javax.swing.BoxLayout(linksPanel, javax.swing.BoxLayout.PAGE_AXIS));

        editLinksButton.setText("Edit links");
        editLinksButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editLinksButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout editLinksButtonButtonPanelLayout = new javax.swing.GroupLayout(editLinksButtonButtonPanel);
        editLinksButtonButtonPanel.setLayout(editLinksButtonButtonPanelLayout);
        editLinksButtonButtonPanelLayout.setHorizontalGroup(
            editLinksButtonButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editLinksButtonButtonPanelLayout.createSequentialGroup()
                .addContainerGap(531, Short.MAX_VALUE)
                .addComponent(editLinksButton)
                .addContainerGap())
        );
        editLinksButtonButtonPanelLayout.setVerticalGroup(
            editLinksButtonButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editLinksButtonButtonPanelLayout.createSequentialGroup()
                .addComponent(editLinksButton)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        linksPanel.add(editLinksButtonButtonPanel);

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
        jScrollPane1.setViewportView(linksTable);

        linksPanel.add(jScrollPane1);

        editorPanel.add(linksPanel);

        saveButton.setText("Save");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        discardButton.setText("Delete");
        discardButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                discardButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout buttonsPanelLayout = new javax.swing.GroupLayout(buttonsPanel);
        buttonsPanel.setLayout(buttonsPanelLayout);
        buttonsPanelLayout.setHorizontalGroup(
            buttonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, buttonsPanelLayout.createSequentialGroup()
                .addContainerGap(472, Short.MAX_VALUE)
                .addComponent(saveButton)
                .addGap(18, 18, 18)
                .addComponent(discardButton)
                .addContainerGap())
        );
        buttonsPanelLayout.setVerticalGroup(
            buttonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(buttonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(discardButton)
                    .addComponent(saveButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        editorPanel.add(buttonsPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(editorPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(editorPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void discardButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_discardButtonActionPerformed

        if (role == null)
            return;
        
        int r = JOptionPane.showConfirmDialog(this, "Really delete this Role ?", "Confirm delete", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);        
        if (r == JOptionPane.CANCEL_OPTION)
            return;
        
        try {
            MetaFactory.getInstance().getFactory("Role").delete(role);
            SmartProject.getProject().editorEvent(Project.DELETE, role);
            SmartProject.getProject().getProjectWindow().closeContainer(this);
        }
        catch(Exception e) {
            JOptionPane.showMessageDialog(editorPanel, "Failed to delete Role", "Delete failed", JOptionPane.ERROR_MESSAGE);
            SmartProject.getProject().log("Failed to delete in RoleEditor", e);
        }
        
    }//GEN-LAST:event_discardButtonActionPerformed

    
    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
     
       
        try {
            String duplicateWarning = MetaFactory.getInstance().getDuplicateCheckMessage("Role", "Role", nameTextField.getText(), SmartProject.getProject().getCurrentProjectID(), role);
            if (duplicateWarning != null) {
                JOptionPane.showMessageDialog(this, duplicateWarning, "Duplicate role name", JOptionPane.ERROR_MESSAGE);
                saveButton.setEnabled(true);
                return;
            }
        } catch (Exception e) {
        }
         
        boolean created = false;
        if (role == null) {
                role = new Role();
                created = true;
        }
        role.setAttribute("Name", nameTextField.getText());
        role.setAttribute("Category", categoryTextField.getText());
        role.setAttribute("Description", descriptionTextArea.getText());
        if (newObjectProjectId == -1)
            role.setAttribute("ProjectID", Integer.parseInt(role.getAttributeValue("ProjectID")));
        else 
            role.setAttribute("ProjectID",newObjectProjectId);
        try {
            MetaFactory.getInstance().getFactory(role.getDatabaseObjectName()).put(role);
            if (created) {
                SmartProject.getProject().editorEvent(Project.ADD, role);
            } else {
                SmartProject.getProject().editorEvent(Project.UPDATE, role);
            }                
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(editorPanel, "Failed to save Role", "Save failed", JOptionPane.ERROR_MESSAGE);
            SmartProject.getProject().log("Failed to save in RoleEditor", e);
        }
        // Check to see if the Role has at least one link to a Location, if not
        // display a warning that one needs to be added.
        SmartProject.getProject().getProjectWindow().setViewTitle(this, "Role:"  + role.getAttributeValue("Name"));
//        ArrayList<Relationship> r = role.getRelationships("Location");
//        if ((r == null) || (r.isEmpty())) {
//            JOptionPane.showMessageDialog(this, "Role has no links to Care Settings, at least one must be added before this Role is valid for a report", "Add link to Care Setting", JOptionPane.INFORMATION_MESSAGE);            
//        }
    }//GEN-LAST:event_saveButtonActionPerformed

    private void editLinksButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editLinksButtonActionPerformed
        if (role == null) {
            JOptionPane.showMessageDialog(this, "Save this Role first, before adding links", "Save first", JOptionPane.INFORMATION_MESSAGE);
            return;            
        }
        JDialog linkEditor = new JDialog(JOptionPane.getFrameForComponent(this), true);
        linkEditor.add(new LinkEditor(role).setParent(linkEditor));
        linkEditor.pack();
        linkEditor.setVisible(true);
        setPersistableObject(role);
    }//GEN-LAST:event_editLinksButtonActionPerformed

    @Override
    public void setEditorComponent(EditorComponent ed) {
        editorComponent = ed;
    }
    
    @Override
    public void setPersistableObject(Persistable p) {
        if (p == null) {
           discardButtonActionPerformed(null);
           return;
        }
        try {
            role = (Role)p;
            descriptionTextArea.setText(role.getAttributeValue("Description"));
            nameTextField.setText(role.getAttributeValue("Name"));
            categoryTextField.setText(role.getAttributeValue("Category"));
            DefaultTableModel dtm = new DefaultTableModel(linkcolumns, 0);
            HashMap<String,ArrayList<Relationship>> rels = role.getRelationshipsForLoad();
            if (rels != null) {
                for (String t : rels.keySet()) {
                    ArrayList<Relationship> a = rels.get(t);
                    if (a == null)
                        continue;
                    for (Relationship r : a) {
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
            JOptionPane.showMessageDialog(editorPanel, "Failed to load Role for editing", "Load failed", JOptionPane.ERROR_MESSAGE);
            SmartProject.getProject().log("Failed to set persistable object in RoleEditor", e);
        }
    }

    @Override
    public Component getComponent() {
        return this;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel buttonsPanel;
    private javax.swing.JTextField categoryTextField;
    private javax.swing.JPanel descriptionPanel;
    private javax.swing.JTextArea descriptionTextArea;
    private javax.swing.JButton discardButton;
    private javax.swing.JButton editLinksButton;
    private javax.swing.JPanel editLinksButtonButtonPanel;
    private javax.swing.JPanel editorPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel linksPanel;
    private javax.swing.JTable linksTable;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JButton saveButton;
    // End of variables declaration//GEN-END:variables

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
        
        if (role == null)
            return false;
        if (o instanceof uk.nhs.digital.safetycase.data.Role) {
            Role c = (Role)o;
            if (c == role) {
                if (evtype == Project.DELETE) {
                    // Close this form and its container... 
                    SmartProject.getProject().getProjectWindow().closeContainer(this);
                    // then return true so that this form can be removed from the
                    // notifications list
                    return true;
                }
                SmartProject.getProject().getProjectWindow().setViewTitle(this, "Role:" + c.getTitle());
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
            uk.nhs.digital.safetycase.data.Role c = (uk.nhs.digital.safetycase.data.Role)o;
            if (c.getTitle().equals(role.getTitle()))
                return this;
        }
        catch (Exception e) {}
        return null;
    }    
    
    @Override
    public void unsubscribe() {
        SmartProject.getProject().removeNotificationSubscriber(this);
    }
    
}
