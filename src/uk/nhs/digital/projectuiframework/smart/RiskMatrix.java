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
package uk.nhs.digital.projectuiframework.smart;

import java.awt.Image;
import javax.swing.ImageIcon;
import uk.nhs.digital.projectuiframework.ui.resources.ResourceUtils;

/**
 * Container for a static image of the risk matrix. Called from the Risk matrix menu item in the 
 * main window. This should probably be replaced by the RiskMatrixPanel in the uk.nhs.digital.safetycase.ui
 * package which is re-sizeable, aware of the selected text size, and in principle dynamic for different
 * risk matrices.
 * 
 * @author damian
 */
public class RiskMatrix extends javax.swing.JDialog {

    private static final String RISK_MATRIX_IMAGE = "/uk/nhs/digital/safetycase/ui/risk_matrix_image.jpg";
    private static final int RISK_MATRIX_X = 722;
    private static final int RISK_MATRIX_Y = 186;
    
    private static ImageIcon riskMatrixImageIcon = null;

    static {
        try {
            riskMatrixImageIcon = ResourceUtils.getImageIcon(RISK_MATRIX_IMAGE);
            riskMatrixImageIcon = new ImageIcon(riskMatrixImageIcon.getImage().getScaledInstance(RISK_MATRIX_X, RISK_MATRIX_Y, Image.SCALE_DEFAULT));
        }
        catch (Exception e) {
            SmartProject.getProject().log("Error loading Risk Matrix graphic", e);
        }
    }
    
    /**
     * Creates new form RiskMatrix
     */
    public RiskMatrix(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
//        riskMatrixImageLabel.setText("");
//        riskMatrixImageLabel.setIcon(riskMatrixImageIcon);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        riskMatrixPanel1 = new uk.nhs.digital.safetycase.ui.RiskMatrixPanel();
        jPanel1 = new javax.swing.JPanel();
        closeButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Risk matrix");
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.PAGE_AXIS));
        getContentPane().add(riskMatrixPanel1);

        closeButton.setText("Close");
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(725, Short.MAX_VALUE)
                .addComponent(closeButton)
                .addGap(26, 26, 26))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(40, Short.MAX_VALUE)
                .addComponent(closeButton)
                .addGap(35, 35, 35))
        );

        getContentPane().add(jPanel1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        this.dispose();
    }//GEN-LAST:event_closeButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton closeButton;
    private javax.swing.JPanel jPanel1;
    private uk.nhs.digital.safetycase.ui.RiskMatrixPanel riskMatrixPanel1;
    // End of variables declaration//GEN-END:variables
}
