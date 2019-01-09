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
import uk.nhs.digital.safetycase.data.ProjectLink;
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
 
    private final String[] linkcolumns = {"Name", "Type", "Comment", "Via"};
    private int newObjectProjectId = -1;
    /**
     * Creates new form HazardEditor
     */
    @SuppressWarnings("LeakingThisInConstructor")
    public CauseEditor() {
        initComponents();
        linksTable.setDefaultEditor(Object.class, null);
//        linksTable.setDefaultRenderer(Object.class, new LinkTableCellRenderer());        
        linksTable.setDefaultRenderer(Object.class, new LinkExplorerTableCellRenderer());        
        DefaultTableModel dtm = new DefaultTableModel(linkcolumns, 0);
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
        conditionsComboBox.setVisible(false);
        jLabel2.setVisible(false);
        descriptionTextArea.setFont(nameTextField.getFont());
        SmartProject.getProject().addNotificationSubscriber(this);
    }

    private void populateLinks() {
        try {
            
//            HashMap<String,ArrayList<Relationship>> rels = hazard.getRelationshipsForLoad();
            DefaultTableModel dtm = new DefaultTableModel(linkcolumns, 0);
            ArrayList<ProjectLink> pls = new ArrayList<>();
            pls = MetaFactory.getInstance().exploreLinks(cause, cause, pls, false);
            for (ProjectLink pl : pls) {
                if (!directLinksOnlyCheckBox.isSelected() || (pl.getRemotePath().length() == 0)) {
                    Object[] row = new Object[linkcolumns.length];
                    for (int i = 0; i < linkcolumns.length; i++) {
                        row[i] = pl;
                    }
                    dtm.addRow(row);
                }
            }
              
            linksTable.setModel(dtm);
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(editorPanel, "Failed to load Cause relationshis for editing", "Load failed", JOptionPane.ERROR_MESSAGE);
            SmartProject.getProject().log("Failed to load cause relationships", e);
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
        linksPanel = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        linksTable = new javax.swing.JTable();
        directLinksOnlyCheckBox = new javax.swing.JCheckBox();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        descriptionTextArea = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        nameTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        conditionsComboBox = new javax.swing.JComboBox<>();
        commonToolBar = new javax.swing.JToolBar();
        saveButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        editLinksButton = new javax.swing.JButton();

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.PAGE_AXIS));

        editorPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        editorPanel.setLayout(new javax.swing.BoxLayout(editorPanel, javax.swing.BoxLayout.PAGE_AXIS));

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
        jScrollPane4.setViewportView(linksTable);

        directLinksOnlyCheckBox.setSelected(true);
        directLinksOnlyCheckBox.setText("Show direct links only");
        directLinksOnlyCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                directLinksOnlyCheckBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout linksPanelLayout = new javax.swing.GroupLayout(linksPanel);
        linksPanel.setLayout(linksPanelLayout);
        linksPanelLayout.setHorizontalGroup(
            linksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 894, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, linksPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(directLinksOnlyCheckBox)
                .addContainerGap())
        );
        linksPanelLayout.setVerticalGroup(
            linksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(linksPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(directLinksOnlyCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 3, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Description"));

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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Name");
        jPanel4.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 14, -1, -1));

        nameTextField.setEditable(false);
        jPanel4.add(nameTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(92, 12, 788, -1));

        jLabel2.setText("Condition");
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

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
        jPanel4.add(conditionsComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 40, 788, -1));

        commonToolBar.setRollover(true);

        saveButton.setText("Save");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });
        commonToolBar.add(saveButton);
        commonToolBar.add(jSeparator1);

        editLinksButton.setText("Links ...");
        editLinksButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editLinksButtonActionPerformed(evt);
            }
        });
        commonToolBar.add(editLinksButton);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(linksPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(commonToolBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(commonToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(linksPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        editorPanel.add(jPanel1);

        add(editorPanel);
    }// </editor-fold>//GEN-END:initComponents

    private void editLinksButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editLinksButtonActionPerformed
        
        JDialog linkEditor = new JDialog(JOptionPane.getFrameForComponent(this), true);
        linkEditor.add(new LinkEditor(cause).setParent(linkEditor));
        linkEditor.pack();
        linkEditor.setVisible(true);

        populateLinks();
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
        String cos = System.getProperty("SMART.closeonsave");
        if ((cos != null) && (cos.contains("Cause"))) {
           unsubscribe();
           SmartProject.getProject().getProjectWindow().closeContainer(this);
        }
        
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

    private void directLinksOnlyCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_directLinksOnlyCheckBoxActionPerformed
        populateLinks();
    }//GEN-LAST:event_directLinksOnlyCheckBoxActionPerformed
    
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
    private javax.swing.JToolBar commonToolBar;
    private javax.swing.JComboBox<String> conditionsComboBox;
    private javax.swing.JTextArea descriptionTextArea;
    private javax.swing.JCheckBox directLinksOnlyCheckBox;
    private javax.swing.JButton editLinksButton;
    private javax.swing.JPanel editorPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JToolBar.Separator jSeparator1;
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
        populateLinks();
        modified = false;
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
