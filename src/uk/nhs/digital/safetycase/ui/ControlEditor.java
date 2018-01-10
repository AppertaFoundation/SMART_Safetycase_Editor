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
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import uk.nhs.digital.projectuiframework.Project;
import uk.nhs.digital.projectuiframework.ui.EditorComponent;
import uk.nhs.digital.safetycase.data.Condition;
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
    private final String[] controlcolumns = {"Name", "Type", "State"};
    private final String[] linkcolumns = {"Type", "ID", "Name", "Comment"};

    private EditorComponent editorComponent = null;
    private Control control = null;
    private ArrayList<Control> controls = new ArrayList<>();
    private ArrayList<Condition> conditions = new ArrayList<>();
    private int newObjectProjectId = -1;
    /**
     * Creates new form ControlEditor
     */
    public ControlEditor() {
        initComponents();
        DefaultTableModel effectModel = new DefaultTableModel(controlcolumns, 0);
        DefaultTableModel linkModel = new DefaultTableModel(linkcolumns, 0);
        controlsTable.setModel(effectModel);
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
            Collection<Persistable> conds = MetaFactory.getInstance().getFactory("Condition").getEntries();
            for (Persistable p : conds) {
                Condition c = (Condition)p;
                conditions.add(c);
                conditionComboBox.addItem(c.getTitle());
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
        controlsPanel = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        newButton = new javax.swing.JButton();
        editButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        controlsTable = new javax.swing.JTable();
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
        conditionComboBox = new javax.swing.JComboBox<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        descriptionTextArea = new javax.swing.JTextArea();
        saveButton = new javax.swing.JButton();
        discardButton = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        clinicalJustificationTextArea = new javax.swing.JTextArea();

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

        controlsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Controls"));

        jToolBar1.setRollover(true);

        newButton.setText("New");
        newButton.setFocusable(false);
        newButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        newButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        newButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(newButton);

        editButton.setText("Edit");
        editButton.setFocusable(false);
        editButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        editButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(editButton);

        deleteButton.setText("Delete");
        deleteButton.setFocusable(false);
        deleteButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        deleteButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(deleteButton);

        controlsTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(controlsTable);

        javax.swing.GroupLayout controlsPanelLayout = new javax.swing.GroupLayout(controlsPanel);
        controlsPanel.setLayout(controlsPanelLayout);
        controlsPanelLayout.setHorizontalGroup(
            controlsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        controlsPanelLayout.setVerticalGroup(
            controlsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlsPanelLayout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE))
        );

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

        discardButton.setText("Discard");
        discardButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                discardButtonActionPerformed(evt);
            }
        });

        jLabel6.setText("Clinical justification");

        clinicalJustificationTextArea.setColumns(20);
        clinicalJustificationTextArea.setLineWrap(true);
        clinicalJustificationTextArea.setRows(5);
        jScrollPane5.setViewportView(clinicalJustificationTextArea);

        javax.swing.GroupLayout editorPanelLayout = new javax.swing.GroupLayout(editorPanel);
        editorPanel.setLayout(editorPanelLayout);
        editorPanelLayout.setHorizontalGroup(
            editorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editorPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(editorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                            .addComponent(conditionComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editorPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(discardButton))
                    .addGroup(editorPanelLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(274, 274, 274)
                        .addComponent(jLabel6)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(editorPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE)))
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
                    .addComponent(conditionComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(editorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(editorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(editorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(discardButton)
                    .addComponent(saveButton))
                .addGap(0, 18, Short.MAX_VALUE)
                .addComponent(linksPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(controlsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(editorPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(controlsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editorPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void newButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newButtonActionPerformed
        editorPanel.setEnabled(true);
        controlsTable.clearSelection();
        typeComboBox.setSelectedIndex(-1);
        conditionComboBox.setSelectedIndex(-1);
        stateComboBox.setSelectedIndex(-1);
        nameTextField.setText("");
        descriptionTextArea.setText("");
        clinicalJustificationTextArea.setText("");
        DefaultTableModel linkModel = new DefaultTableModel(linkcolumns, 0);
        linksTable.setModel(linkModel);
    }//GEN-LAST:event_newButtonActionPerformed

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        int selected = controlsTable.getSelectedRow();
        if (selected == -1)
            return;
        editorPanel.setEnabled(true);
        Control c = controls.get(selected);
        nameTextField.setText(c.getAttributeValue("Name"));
        int d = Integer.parseInt(c.getAttributeValue("ConditionID"));
        for (int i = 0; i < conditions.size(); i++) {
            if (conditions.get(i).getId() == d) {
                conditionComboBox.setSelectedIndex(i);
                break;
            }
        }
        String s = c.getAttributeValue("Type");
        for (int j = 0; j < typeComboBox.getItemCount(); j++) {
            if (s.contentEquals(typeComboBox.getItemAt(j))) {
                typeComboBox.setSelectedIndex(j);
                break;
            }
        }
        s = c.getAttributeValue("State");
        for (int j = 0; j < stateComboBox.getItemCount(); j++) {
            if (s.contentEquals(stateComboBox.getItemAt(j))) {
                stateComboBox.setSelectedIndex(j);
                break;
            }
        }
        descriptionTextArea.setText(c.getAttributeValue("Description"));
        clinicalJustificationTextArea.setText(c.getAttributeValue("ClinicalJustification"));
        try {
            HashMap<String,ArrayList<Relationship>> rels = c.getRelationshipsForLoad();
            DefaultTableModel dtm = new DefaultTableModel(linkcolumns, 0);
            for (String t : rels.keySet()) {
                ArrayList<Relationship> a = rels.get(t);
                for (Relationship r : a) {
                    String[] row = new String[linkcolumns.length];
                    row[0] = t;
                    row[1] = Integer.toString(r.getTarget());
                    row[2] = MetaFactory.getInstance().getFactory(r.getTargetType()).get(r.getTarget()).getAttributeValue("Name");
                    row[3] = r.getComment();
                    dtm.addRow(row);
                }
            }
            linksTable.setModel(dtm);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_editButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
         int current = controlsTable.getSelectedRow();
        if (current == -1)
            return;
        int dialogResult = JOptionPane.showConfirmDialog(null, "Delete this Control ?", "Warning", JOptionPane.YES_NO_OPTION);
        if (dialogResult == JOptionPane.NO_OPTION) {
            return;
        }
        
        try {
            Control c = controls.get(current);
            MetaFactory.getInstance().getFactory(c.getDatabaseObjectName()).delete(c);
            controls.remove(current);
            ((DefaultTableModel)controlsTable.getModel()).removeRow(current);
            editorComponent.notifyEditorEvent(Project.DELETE, c);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_deleteButtonActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        if (conditionComboBox.getSelectedIndex() == -1)
            conditionComboBox.setSelectedIndex(0);
        if (typeComboBox.getSelectedIndex() == -1)
            typeComboBox.setSelectedIndex(0);
        Control c = null;
        int selected = controlsTable.getSelectedRow();
        if (selected == -1)
            c = new Control();
        else
            c = controls.get(selected);
        c.setAttribute("Name", nameTextField.getText());
        c.setAttribute("Description", descriptionTextArea.getText());
        c.setAttribute("ClinicalJustification", clinicalJustificationTextArea.getText());
        c.setAttribute("Type", (String)typeComboBox.getSelectedItem());
        c.setAttribute("State", (String)stateComboBox.getSelectedItem());
        c.setAttribute("ConditionID", conditions.get(conditionComboBox.getSelectedIndex()).getId());
        if (newObjectProjectId == -1)
            c.setAttribute("ProjectID", Integer.parseInt(control.getAttributeValue("ProjectID")));
        else 
            c.setAttribute("ProjectID",newObjectProjectId);
        try {
            MetaFactory.getInstance().getFactory(c.getDatabaseObjectName()).put(c);
            String[] row = new String[controlcolumns.length];
            row[0] = c.getAttributeValue("Name");
            row[1] = c.getAttributeValue("Type");
            row[2] = c.getAttributeValue("State");
            DefaultTableModel dtm = ((DefaultTableModel)controlsTable.getModel());
            if (selected == -1) {
                controls.add(c);
                dtm.addRow(row);
                controlsTable.setRowSelectionInterval(controls.size() - 1, controls.size() - 1);
                editorComponent.notifyEditorEvent(Project.ADD, c);
            } else {
                dtm.removeRow(selected);
                dtm.insertRow(selected, row);                
                editorComponent.notifyEditorEvent(Project.UPDATE, c);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_saveButtonActionPerformed

    private void discardButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_discardButtonActionPerformed
        typeComboBox.setSelectedIndex(-1);
        conditionComboBox.setSelectedIndex(-1);
        stateComboBox.setSelectedIndex(-1);
        nameTextField.setText("");
        descriptionTextArea.setText("");
        clinicalJustificationTextArea.setText("");
    }//GEN-LAST:event_discardButtonActionPerformed

    private void editLinksButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editLinksButtonActionPerformed
       int selected = controlsTable.getSelectedRow();
        if (selected == -1)
            return;
        Control c = controls.get(selected);
        JDialog linkEditor = new JDialog(JOptionPane.getFrameForComponent(this), true);
        linkEditor.add(new LinkEditor(c).setParent(linkEditor));
        linkEditor.pack();
        linkEditor.setVisible(true);

        try {
            HashMap<String,ArrayList<Relationship>> rels = c.getRelationshipsForLoad();
            DefaultTableModel dtm = new DefaultTableModel(linkcolumns, 0);
            for (String t : rels.keySet()) {
                ArrayList<Relationship> a = rels.get(t);
                for (Relationship r : a) {
                    String[] row = new String[linkcolumns.length];
                    row[0] = t;
                    row[1] = Integer.toString(r.getTarget());
                    row[2] = MetaFactory.getInstance().getFactory(r.getTargetType()).get(r.getTarget()).getAttributeValue("Name");
                    row[3] = r.getComment();
                    dtm.addRow(row);
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
    private javax.swing.JComboBox<String> conditionComboBox;
    private javax.swing.JPanel controlsPanel;
    private javax.swing.JTable controlsTable;
    private javax.swing.JButton deleteButton;
    private javax.swing.JTextArea descriptionTextArea;
    private javax.swing.JButton discardButton;
    private javax.swing.JButton editButton;
    private javax.swing.JButton editLinksButton;
    private javax.swing.JPanel editorPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable jTable1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JPanel linksPanel;
    private javax.swing.JTable linksTable;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JButton newButton;
    private javax.swing.JButton saveButton;
    private javax.swing.JComboBox<String> stateComboBox;
    private javax.swing.JComboBox<String> typeComboBox;
    // End of variables declaration//GEN-END:variables

    @Override
    public void setPersistableObject(Persistable p) {
        if (p == null) {
            newButtonActionPerformed(null);
            return;
        }
      int selected = -1;
      try {
          control = (Control)p;
            DefaultTableModel dtm = new DefaultTableModel(controlcolumns, 0);
            DefaultTableModel ld = new DefaultTableModel(linkcolumns, 0);
            linksTable.setModel(ld);
            ArrayList<Persistable> ctrl = MetaFactory.getInstance().getChildren(control.getDatabaseObjectName(), "ProjectID", Integer.parseInt(control.getAttributeValue("ProjectID")));
            for (int i = 0; i < ctrl.size(); i++) {
                Control c = (Control)ctrl.get(i);
                if (c.isDeleted())
                    continue;
                controls.add(c);
                String[] row = new String[controlcolumns.length];
                row[0] = c.getAttributeValue("Name");
                row[1] = c.getAttributeValue("Type");
                row[2] = c.getAttributeValue("State");
                if (p.getId() == c.getId()) {
                    selected = i;
                }
                dtm.addRow(row);
            }    
            controlsTable.setModel(dtm);
            if (selected != -1)
                controlsTable.setRowSelectionInterval(selected, selected);
          
      }
      catch (Exception e) {
          e.printStackTrace();
      }
        try {
            HashMap<String,ArrayList<Relationship>> rels = control.getRelationshipsForLoad();
            DefaultTableModel dtm = new DefaultTableModel(linkcolumns, 0);
            for (String t : rels.keySet()) {
                ArrayList<Relationship> a = rels.get(t);
                for (Relationship r : a) {
                    String[] row = new String[linkcolumns.length];
                    row[0] = t;
                    row[1] = Integer.toString(r.getTarget());
                    row[2] = MetaFactory.getInstance().getFactory(r.getTargetType()).get(r.getTarget()).getAttributeValue("Name");
                    row[3] = r.getComment();
                    dtm.addRow(row);
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
