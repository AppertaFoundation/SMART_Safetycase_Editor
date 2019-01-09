/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.nhs.digital.safetycase.ui;

import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import uk.nhs.digital.projectuiframework.smart.SmartProject;
import uk.nhs.digital.projectuiframework.ui.EditorComponent;
import uk.nhs.digital.safetycase.data.IssuesLog;
import uk.nhs.digital.safetycase.data.MetaFactory;
import uk.nhs.digital.safetycase.data.Persistable;
import uk.nhs.digital.safetycase.data.Relationship;

/**
 *
 * @author damian
 */
public class IssuesLogEditor 
        extends javax.swing.JPanel 
        implements PersistableEditor
{
    private static final String[] ISSUESCOLUMNS = {"Name", "Reference", "Created", "Type", "Reolution Type"};
    private static final String[] LINKSCOLUMNS = {"Type", "Name", "Comment"};
    private EditorComponent editorComponent = null;
    private int newObjectProjectId = -1;
    private ArrayList<IssuesLog> logEntries = new ArrayList<>();
    private ArrayList<IssuesLog> displayedEntries = new ArrayList<>();
    
    private IssuesLog currentIssue = null;
    private boolean modified = false;
    
    private static final SimpleDateFormat DATEFORMAT = new SimpleDateFormat("yyyy-MM-dd");
    /**
     * Creates new form IssuesLogEditor
     */
    public IssuesLogEditor() {
        initComponents();
        SmartProject.getProject().addNotificationSubscriber(this);
        issuesTable.setRowHeight(SmartProject.getProject().getTableRowHeight());
        linksTable.setRowHeight(SmartProject.getProject().getTableRowHeight());
        ListSelectionModel lsm = issuesTable.getSelectionModel();
        lsm.addListSelectionListener((ListSelectionEvent evt) -> {
            int sel = issuesTable.getSelectedRow();
            if (sel == -1)
                return;
            IssuesLog issue = displayedEntries.get(sel);
            populateIssuesDisplay(issue);
        });
        try {
            ArrayList<String> issueTypes = MetaFactory.getInstance().getFactory("IssuesLog").getDistinctSet("GroupingType");
            if (issueTypes.isEmpty()) {
                typeComboBox.addItem("Generic");
            } else {
                for (String s : issueTypes)
                    typeComboBox.addItem(s);
            }
            issueTypes = MetaFactory.getInstance().getFactory("IssuesLog").getDistinctSet("ResolutionType");
            resolutionTypeComboBox.addItem("");
            for (String s : issueTypes) {
                resolutionTypeComboBox.addItem(s);
            }
            DefaultTableModel dtm = new DefaultTableModel(ISSUESCOLUMNS, 0);
            issuesTable.setModel(dtm);
            dtm = new DefaultTableModel(LINKSCOLUMNS, 0);
            linksTable.setModel(dtm);
            linksTable.setDefaultRenderer(Object.class, new LinkTableCellRenderer());
            Collection<Persistable> log = MetaFactory.getInstance().getFactory("IssuesLog").getEntries();
            if (log == null)
                return;
            for (Persistable l : log) {
                IssuesLog issue = (IssuesLog)l;
                logEntries.add(issue);
            }
            populateIssuesTable();
            clearIssuesDisplay();
            
        }
        catch (Exception e) {
            SmartProject.getProject().log("Failed to initialise IssuesLogEditor", e);
        }
        descriptionTextArea.setFont(nameTextField.getFont());
        resolutionTextArea.setFont(nameTextField.getFont());
    }

    @Override
    public boolean wantsScrollPane() { return true; }
    
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

        controlsPanel = new javax.swing.JPanel();
        closedIssuesCheckbox = new javax.swing.JCheckBox();
        currentProjectOnlyCheckbox = new javax.swing.JCheckBox();
        newButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        issuesTable = new javax.swing.JTable();
        detailPanel = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        linksTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        editLinksButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        nameTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        descriptionTextArea = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        resolutionTypeComboBox = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        resolutionTextArea = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        typeComboBox = new javax.swing.JComboBox<>();
        discardButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        resolutionDateTextField = new javax.swing.JTextField();
        unresolveButton = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        externalIdentifierTextField = new javax.swing.JTextField();

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.PAGE_AXIS));

        closedIssuesCheckbox.setText("Also show closed issues");
        closedIssuesCheckbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closedIssuesCheckboxActionPerformed(evt);
            }
        });

        currentProjectOnlyCheckbox.setSelected(true);
        currentProjectOnlyCheckbox.setText("Show issues for current project only");

        newButton.setText("Create new");
        newButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout controlsPanelLayout = new javax.swing.GroupLayout(controlsPanel);
        controlsPanel.setLayout(controlsPanelLayout);
        controlsPanelLayout.setHorizontalGroup(
            controlsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlsPanelLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(closedIssuesCheckbox, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(currentProjectOnlyCheckbox, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 130, Short.MAX_VALUE)
                .addComponent(newButton)
                .addContainerGap())
        );
        controlsPanelLayout.setVerticalGroup(
            controlsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(controlsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(closedIssuesCheckbox)
                    .addComponent(currentProjectOnlyCheckbox)
                    .addComponent(newButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(controlsPanel);

        jScrollPane2.setMinimumSize(new java.awt.Dimension(22, 200));

        issuesTable.setModel(new javax.swing.table.DefaultTableModel(
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
        issuesTable.setMinimumSize(new java.awt.Dimension(60, 200));
        jScrollPane2.setViewportView(issuesTable);

        add(jScrollPane2);

        detailPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

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

        jLabel1.setText("Linked to");

        editLinksButton.setText("Edit links");
        editLinksButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editLinksButtonActionPerformed(evt);
            }
        });

        jLabel2.setText("Name");

        nameTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nameTextFieldKeyTyped(evt);
            }
        });

        jLabel3.setText("Description");

        descriptionTextArea.setColumns(20);
        descriptionTextArea.setLineWrap(true);
        descriptionTextArea.setRows(5);
        descriptionTextArea.setWrapStyleWord(true);
        descriptionTextArea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                descriptionTextAreaKeyTyped(evt);
            }
        });
        jScrollPane4.setViewportView(descriptionTextArea);

        jLabel4.setText("Resolution type");

        resolutionTypeComboBox.setEditable(true);
        resolutionTypeComboBox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                resolutionTypeComboBoxMouseClicked(evt);
            }
        });
        resolutionTypeComboBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                resolutionTypeComboBoxKeyPressed(evt);
            }
        });

        jLabel5.setText("Resolution");

        resolutionTextArea.setColumns(20);
        resolutionTextArea.setLineWrap(true);
        resolutionTextArea.setRows(5);
        resolutionTextArea.setWrapStyleWord(true);
        resolutionTextArea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                resolutionTextAreaKeyTyped(evt);
            }
        });
        jScrollPane5.setViewportView(resolutionTextArea);

        jLabel6.setText("Type");

        typeComboBox.setEditable(true);
        typeComboBox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                typeComboBoxMouseClicked(evt);
            }
        });
        typeComboBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                typeComboBoxKeyTyped(evt);
            }
        });

        discardButton.setText("Cancel");
        discardButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                discardButtonActionPerformed(evt);
            }
        });

        saveButton.setText("Save");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        jLabel7.setText("Resolution date: ");

        resolutionDateTextField.setEditable(false);

        unresolveButton.setText("Unresolve");
        unresolveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unresolveButtonActionPerformed(evt);
            }
        });

        jLabel8.setText("Reference");

        externalIdentifierTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                externalIdentifierTextFieldKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout detailPanelLayout = new javax.swing.GroupLayout(detailPanel);
        detailPanel.setLayout(detailPanelLayout);
        detailPanelLayout.setHorizontalGroup(
            detailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(detailPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(detailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 769, Short.MAX_VALUE)
                    .addGroup(detailPanelLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(editLinksButton))
                    .addComponent(jScrollPane4)
                    .addGroup(detailPanelLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(resolutionTypeComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, detailPanelLayout.createSequentialGroup()
                        .addComponent(saveButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(resolutionDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(unresolveButton)
                        .addGap(129, 129, 129)
                        .addComponent(discardButton))
                    .addGroup(detailPanelLayout.createSequentialGroup()
                        .addGroup(detailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(detailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nameTextField)
                            .addComponent(externalIdentifierTextField)))
                    .addGroup(detailPanelLayout.createSequentialGroup()
                        .addGroup(detailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel3))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(detailPanelLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(50, 50, 50)
                        .addComponent(typeComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        detailPanelLayout.setVerticalGroup(
            detailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, detailPanelLayout.createSequentialGroup()
                .addGroup(detailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(7, 7, 7)
                .addGroup(detailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(externalIdentifierTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(detailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(typeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(detailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(resolutionTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(4, 4, 4)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(detailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(discardButton)
                    .addComponent(saveButton)
                    .addComponent(unresolveButton)
                    .addComponent(resolutionDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(detailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(editLinksButton, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        add(detailPanel);
    }// </editor-fold>//GEN-END:initComponents

    private void newButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newButtonActionPerformed
        clearIssuesDisplay();
        currentIssue = null;
    }//GEN-LAST:event_newButtonActionPerformed

    private void discardButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_discardButtonActionPerformed
        
        if (issuesTable.getSelectedRow() == -1) {
            clearIssuesDisplay();
        } else {
            if (currentIssue == null)
                clearIssuesDisplay();
            else
                populateIssuesDisplay(currentIssue);
        }
        
    }//GEN-LAST:event_discardButtonActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        boolean created = false;
        if (currentIssue == null) {
            currentIssue = new IssuesLog();
            created = true;
        }
        currentIssue.setAttribute("Name", nameTextField.getText());
        currentIssue.setAttribute("ExternalIdentifier", externalIdentifierTextField.getText());
        currentIssue.setAttribute("GroupingType", (String)typeComboBox.getSelectedItem());
        currentIssue.setAttribute("ResolutionType", (String)resolutionTypeComboBox.getSelectedItem());
        currentIssue.setAttribute("Description", descriptionTextArea.getText());
        currentIssue.setAttribute("Resolution", resolutionTextArea.getText());
        currentIssue.setAttribute("ProjectID", SmartProject.getProject().getCurrentProjectID());
        if (currentIssue.getAttributeValue("ResolvedDate").length() == 0) {
            if (((String)resolutionTypeComboBox.getSelectedItem()).length() != 0) {
                currentIssue.setAttribute("ResolvedDate", DATEFORMAT.format(new Date()));
            }
        }
        currentIssue.getAttribute("ResolvedDate").setIsDate(true);
        try {
            MetaFactory.getInstance().getFactory("IssuesLog").put(currentIssue);
            if (created)
                logEntries.add(currentIssue);
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Failed to save Issue. Send logs to support", "Save failed", JOptionPane.ERROR_MESSAGE);
            SmartProject.getProject().log("Failed to save in IssuessLogEditor", e);
        }
        modified = false;
        populateIssuesTable();
    }//GEN-LAST:event_saveButtonActionPerformed

    private void unresolveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unresolveButtonActionPerformed
        currentIssue.setAttribute("ResolutionType", "");
        currentIssue.setAttribute("ResolvedDate", "");
        resolutionDateTextField.setText("");
        modified = true;
    }//GEN-LAST:event_unresolveButtonActionPerformed

    private void closedIssuesCheckboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closedIssuesCheckboxActionPerformed
        populateIssuesTable();
    }//GEN-LAST:event_closedIssuesCheckboxActionPerformed


    
    private void editLinksButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editLinksButtonActionPerformed
        if (currentIssue == null) {
            JOptionPane.showMessageDialog(this, "Save this Issue first, before adding links", "Save first", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        JDialog linkEditor = new JDialog(JOptionPane.getFrameForComponent(this), true);
        currentIssue.getAttribute("ResolvedDate").setIsDate(true);
        linkEditor.add(new LinkEditor(currentIssue).setParent(linkEditor));
        linkEditor.pack();
        linkEditor.setVisible(true);
        populateIssuesDisplay(currentIssue);
    }//GEN-LAST:event_editLinksButtonActionPerformed

    private void nameTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nameTextFieldKeyTyped
        modified = true;
    }//GEN-LAST:event_nameTextFieldKeyTyped

    private void externalIdentifierTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_externalIdentifierTextFieldKeyTyped
        modified = true;
    }//GEN-LAST:event_externalIdentifierTextFieldKeyTyped

    private void descriptionTextAreaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_descriptionTextAreaKeyTyped
        modified = true;
    }//GEN-LAST:event_descriptionTextAreaKeyTyped

    private void typeComboBoxKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_typeComboBoxKeyTyped
        modified = true;
    }//GEN-LAST:event_typeComboBoxKeyTyped

    private void typeComboBoxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_typeComboBoxMouseClicked
        modified = true;
    }//GEN-LAST:event_typeComboBoxMouseClicked

    private void resolutionTypeComboBoxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_resolutionTypeComboBoxMouseClicked
       modified = true;
    }//GEN-LAST:event_resolutionTypeComboBoxMouseClicked

    private void resolutionTypeComboBoxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_resolutionTypeComboBoxKeyPressed
        modified = true;
    }//GEN-LAST:event_resolutionTypeComboBoxKeyPressed

    private void resolutionTextAreaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_resolutionTextAreaKeyTyped
       modified = true;
    }//GEN-LAST:event_resolutionTextAreaKeyTyped

    @Override
    public void setPersistableObject(Persistable p) {}

    private void clearIssuesDisplay() {
        nameTextField.setText("");
        externalIdentifierTextField.setText("");
        descriptionTextArea.setText("");
        resolutionTextArea.setText("");
        resolutionDateTextField.setText("");
        DefaultTableModel dtm = new DefaultTableModel(LINKSCOLUMNS, 0);
        linksTable.setModel(dtm);        
    }
    
    private void populateIssuesDisplay(IssuesLog issue) {
        currentIssue = issue;
        currentIssue.getAttribute("ResolvedDate").setIsDate(true);
        nameTextField.setText(issue.getAttributeValue("Name"));
        externalIdentifierTextField.setText(issue.getAttributeValue("ExternalIdentifier"));
        typeComboBox.setSelectedItem(issue.getAttributeValue("GroupingType"));
        descriptionTextArea.setText(issue.getAttributeValue("Description"));
        resolutionTextArea.setText(issue.getAttributeValue("Resolution"));
        resolutionTypeComboBox.setSelectedItem(issue.getAttributeValue("ResolutionType"));
        resolutionDateTextField.setText(issue.getAttributeValue("ResolvedDate"));
        try {
            DefaultTableModel dtm = new DefaultTableModel(LINKSCOLUMNS, 0);
            HashMap<String,ArrayList<Relationship>> rels = issue.getRelationshipsForLoad();
            if (rels != null) {
                for (String t : rels.keySet()) {
                    ArrayList<Relationship> a = rels.get(t);
                    if (a != null) {
                        for (Relationship r : a) {
                            Object[] row = new Object[LINKSCOLUMNS.length];
                            for (int i = 0; i < LINKSCOLUMNS.length; i++)
                                row[i] = r;
                            dtm.addRow(row);
                        }
                    }
                }
            }
            linksTable.setModel(dtm);
        }
        catch (Exception e) {
            SmartProject.getProject().log("Failed to populate display in IssuesLogEditor", e);
        }
    }
    
    private void populateIssuesTable() {
        DefaultTableModel dtm = new DefaultTableModel(ISSUESCOLUMNS, 0);
        displayedEntries.clear();
        for (IssuesLog issue : logEntries) {
            if (!closedIssuesCheckbox.isSelected()) {
                if (issue.isDeleted())
                    continue;
                if (issue.getAttributeValue("ResolvedDate").length() != 0)
                    continue;
            }
            if (!currentProjectOnlyCheckbox.isSelected() || 
                    (SmartProject.getProject().getCurrentProjectID() == issue.getAttribute("ProjectID").getIntValue())) {
                String[] row = new String[ISSUESCOLUMNS.length];
                row[0] = issue.getAttributeValue("Name");
                row[1] = issue.getAttributeValue("ExternalIdentifier");
                row[2] = issue.getAttributeValue("CreatedDate");
                if ((row[2] == null) || (row[2].trim().length() == 0))
                        row[2] = "Today";
                row[3] = issue.getAttributeValue("GroupingType");
                row[4] = issue.getAttributeValue("ResolutionType");
                dtm.addRow(row);
                displayedEntries.add(issue);
            }
        }
        issuesTable.setModel(dtm);
    }
    
    @Override
    public void setEditorComponent(EditorComponent ed) {
        editorComponent = ed;
    }

    @Override
    public Component getComponent() {
        return this;
    }

    @Override
    public void setNewObjectProjectId(int i) {
        newObjectProjectId = i;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox closedIssuesCheckbox;
    private javax.swing.JPanel controlsPanel;
    private javax.swing.JCheckBox currentProjectOnlyCheckbox;
    private javax.swing.JTextArea descriptionTextArea;
    private javax.swing.JPanel detailPanel;
    private javax.swing.JButton discardButton;
    private javax.swing.JButton editLinksButton;
    private javax.swing.JTextField externalIdentifierTextField;
    private javax.swing.JTable issuesTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable linksTable;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JButton newButton;
    private javax.swing.JTextField resolutionDateTextField;
    private javax.swing.JTextArea resolutionTextArea;
    private javax.swing.JComboBox<String> resolutionTypeComboBox;
    private javax.swing.JButton saveButton;
    private javax.swing.JComboBox<String> typeComboBox;
    private javax.swing.JButton unresolveButton;
    // End of variables declaration//GEN-END:variables


    @Override
    public boolean notification(int evtype, Object o) {
        return true;
    }

    @Override
    public JPanel getEditor(Object o) {
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
