/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.nhs.digital.projectuiframework.ui;

import javax.swing.ImageIcon;

/**
 *
 * @author damian
 */
public class HelpAboutDialog extends javax.swing.JDialog {

    /**
     * Creates new form HalpAboutDialog
     */
    public HelpAboutDialog(java.awt.Frame parent, boolean modal, ImageIcon image) {
        super(parent, modal);
        initComponents();
        imageLabel.setText("");
        imageLabel.setIcon(image);
        pack();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        imageLabel = new javax.swing.JLabel();
        closeButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SMART");
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        imageLabel.setText("Image not found");
        getContentPane().add(imageLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 12, 720, 420));

        closeButton.setText("Close");
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });
        getContentPane().add(closeButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 670, -1, -1));

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setText("Copyright 2018 NHS Digital. Licensed under the Apache License, Version 2.0.\n\nMade in Yorkshire by: Hannah McCann (UX Lead, Safety Engineering SME), Damian Murphy and \nSharif Ullah (development team).\n\nAided and abetted by Sean White (NHS Digital Safety Team) and Ibrahim Habli (University of York)\n\nGraphical editors powered by mxGraph (http://www.jgraph.com), licenced under Apache Licence, version 2.0\nHTML editors powered by Ekit (http://www.hexidec.com) licenced under LGPL, copyright Howard Kistler.\nPDF SMART methodology display powered by Apache PDFbox, copyright Apache Software Foundation.\n\n");
        jScrollPane1.setViewportView(jTextArea1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 470, 730, 190));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        this.dispose();
    }//GEN-LAST:event_closeButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton closeButton;
    private javax.swing.JLabel imageLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
