/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.nhs.digital.safetycase.ui;

/**
 *
 * @author damian
 */
public class ObjectLinkReporter extends javax.swing.JPanel {

    /**
     * Creates new form ObjectLinkReporter
     */
    public ObjectLinkReporter() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        systemsCheckBox = new javax.swing.JCheckBox();
        systemFunctionsCheckBox = new javax.swing.JCheckBox();
        rolesCheckBox = new javax.swing.JCheckBox();
        locationsCheckBox = new javax.swing.JCheckBox();
        processesCheckBox = new javax.swing.JCheckBox();
        processStepsCheckBox = new javax.swing.JCheckBox();
        hazardsCheckBox = new javax.swing.JCheckBox();
        causesCheckBox = new javax.swing.JCheckBox();
        controlsCheckBox = new javax.swing.JCheckBox();
        effectsCheckBox = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Include links to/from"));

        systemsCheckBox.setSelected(true);
        systemsCheckBox.setText("Systems");

        systemFunctionsCheckBox.setSelected(true);
        systemFunctionsCheckBox.setText("System functions");

        rolesCheckBox.setSelected(true);
        rolesCheckBox.setText("Roles");

        locationsCheckBox.setSelected(true);
        locationsCheckBox.setText("Care settings");

        processesCheckBox.setSelected(true);
        processesCheckBox.setText("Processes");

        processStepsCheckBox.setSelected(true);
        processStepsCheckBox.setText("Process steps");

        hazardsCheckBox.setSelected(true);
        hazardsCheckBox.setText("Hazards");

        causesCheckBox.setSelected(true);
        causesCheckBox.setText("Causes");

        controlsCheckBox.setSelected(true);
        controlsCheckBox.setText("Controls");

        effectsCheckBox.setSelected(true);
        effectsCheckBox.setText("Effects");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(systemFunctionsCheckBox)
                    .addComponent(rolesCheckBox)
                    .addComponent(systemsCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(113, 113, 113)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(locationsCheckBox)
                    .addComponent(processesCheckBox)
                    .addComponent(processStepsCheckBox))
                .addGap(139, 139, 139)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(causesCheckBox)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(hazardsCheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 116, Short.MAX_VALUE)
                        .addComponent(effectsCheckBox))
                    .addComponent(controlsCheckBox))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(systemsCheckBox)
                    .addComponent(locationsCheckBox)
                    .addComponent(hazardsCheckBox)
                    .addComponent(effectsCheckBox))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(systemFunctionsCheckBox)
                    .addComponent(processesCheckBox)
                    .addComponent(causesCheckBox))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rolesCheckBox)
                    .addComponent(processStepsCheckBox)
                    .addComponent(controlsCheckBox))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(jPanel1);

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
        jScrollPane1.setViewportView(jTable1);

        add(jScrollPane1);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox causesCheckBox;
    private javax.swing.JCheckBox controlsCheckBox;
    private javax.swing.JCheckBox effectsCheckBox;
    private javax.swing.JCheckBox hazardsCheckBox;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JCheckBox locationsCheckBox;
    private javax.swing.JCheckBox processStepsCheckBox;
    private javax.swing.JCheckBox processesCheckBox;
    private javax.swing.JCheckBox rolesCheckBox;
    private javax.swing.JCheckBox systemFunctionsCheckBox;
    private javax.swing.JCheckBox systemsCheckBox;
    // End of variables declaration//GEN-END:variables
}