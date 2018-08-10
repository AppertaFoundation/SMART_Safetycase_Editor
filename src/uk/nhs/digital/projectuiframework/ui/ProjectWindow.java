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
package uk.nhs.digital.projectuiframework.ui;
import com.sun.glass.events.MouseEvent;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Enumeration;
import uk.nhs.digital.projectuiframework.Project;
import java.util.HashMap;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreePath;
import uk.nhs.digital.projectuiframework.InitialTab;
import uk.nhs.digital.projectuiframework.smart.RiskMatrix;
import uk.nhs.digital.projectuiframework.smart.SmartProject;
import uk.nhs.digital.safetycase.ui.Housekeeper;
import uk.nhs.digital.safetycase.ui.LibraryEditorDialog;
import uk.nhs.digital.safetycase.ui.LinkExplorer;
import uk.nhs.digital.safetycase.ui.ProjectEditor;
/**
 *
 * @author damian
 */
public class ProjectWindow extends javax.swing.JFrame {
    
    private final HashMap<String,Project> projects = new HashMap<>();
    private uk.nhs.digital.projectuiframework.Project lastProjectAdded = null;
    /**
     * Creates new form ProjectWindow
     */
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public ProjectWindow() {
        initComponents();
        String s = System.getProperty("uk.nhs.digital.projectuiframework.initialtabtitle");
        mainWindowTabbedPane.add(s, new InitialTab());
        mainWindowTabbedPane.setTabComponentAt(mainWindowTabbedPane.getSelectedIndex(), new UndockTabComponent(mainWindowTabbedPane));
        // Stop Netbeans' UI builder screwing up the scrolling behaviour of the tree
        projectTree.setPreferredSize(null);
        setSize(new Dimension(1620,950));

        String uf = System.getProperty("SMART.userfont");
        if (uf != null) {
           float fs = Float.parseFloat(uf);
           if ((fs >= 12.0) && (fs <= 30.0)) {
               Font f = projectTree.getFont();
               Font f2 = f.deriveFont(fs);
               setUIFont(f2, null);               
           }
        }
        
    }

    public JTree getProjectTree() { return projectTree; }
    public Font getDisplayFont() { return projectTree.getFont(); }
    
    public void setTreeCellRenderer(TreeCellRenderer r) {
        projectTree.setCellRenderer(r);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is alwathrow new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.ys
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fontsizes = new javax.swing.ButtonGroup();
        mainWindowSplitPane = new javax.swing.JSplitPane();
        projectTreeScrollPane = new javax.swing.JScrollPane();
        projectTree = new javax.swing.JTree();
        mainWindowTabbedPane = new javax.swing.JTabbedPane();
        mainMenu = new javax.swing.JMenuBar();
        saveAllMenu = new javax.swing.JMenu();
        mainFileMenu = new javax.swing.JMenu();
        mainMenuNew = new javax.swing.JMenuItem();
        changeDatabaseMenuItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        mainMenuExit = new javax.swing.JMenuItem();
        toolsMenu = new javax.swing.JMenu();
        textSizeMenu = new javax.swing.JMenu();
        smallFontMenu = new javax.swing.JRadioButtonMenuItem();
        mediumFontMenu = new javax.swing.JRadioButtonMenuItem();
        largeFontMenu = new javax.swing.JRadioButtonMenuItem();
        surfaceMediumMenu = new javax.swing.JRadioButtonMenuItem();
        surfaceLargeMenu = new javax.swing.JRadioButtonMenuItem();
        libraryMenuItem = new javax.swing.JMenuItem();
        undeleteMenuItem = new javax.swing.JMenuItem();
        importMenuItem = new javax.swing.JMenuItem();
        linkExplorerMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        showProcessMenuItem = new javax.swing.JMenuItem();
        riskMatrixMenuItem = new javax.swing.JMenuItem();
        helpAboutMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setSize(new java.awt.Dimension(902, 892));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        projectTreeScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        projectTreeScrollPane.setAutoscrolls(true);
        projectTreeScrollPane.setPreferredSize(new java.awt.Dimension(400, 600));

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("root");
        projectTree.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        projectTree.setAutoscrolls(true);
        projectTree.setLargeModel(true);
        projectTree.setPreferredSize(new java.awt.Dimension(400, 600));
        projectTree.setRootVisible(false);
        projectTree.setToggleClickCount(3);
        projectTree.setVisibleRowCount(4000);
        projectTree.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                projectTreeMouseClicked(evt);
            }
        });
        projectTreeScrollPane.setViewportView(projectTree);

        mainWindowSplitPane.setLeftComponent(projectTreeScrollPane);

        mainWindowTabbedPane.setAutoscrolls(true);
        mainWindowTabbedPane.setPreferredSize(new java.awt.Dimension(600, 600));
        mainWindowSplitPane.setRightComponent(mainWindowTabbedPane);

        getContentPane().add(mainWindowSplitPane);

        mainMenu.setName("mainMenu"); // NOI18N

        saveAllMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/uk/nhs/digital/projectuiframework/ui/resources/media-floppy.png"))); // NOI18N
        saveAllMenu.addMenuListener(new javax.swing.event.MenuListener() {
            public void menuSelected(javax.swing.event.MenuEvent evt) {
                saveAllMenuMenuSelected(evt);
            }
            public void menuDeselected(javax.swing.event.MenuEvent evt) {
            }
            public void menuCanceled(javax.swing.event.MenuEvent evt) {
            }
        });
        mainMenu.add(saveAllMenu);

        mainFileMenu.setText("File");

        mainMenuNew.setText("New project ...");
        mainMenuNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mainMenuNewActionPerformed(evt);
            }
        });
        mainFileMenu.add(mainMenuNew);

        changeDatabaseMenuItem.setText("Change database");
        changeDatabaseMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeDatabaseMenuItemActionPerformed(evt);
            }
        });
        mainFileMenu.add(changeDatabaseMenuItem);
        mainFileMenu.add(jSeparator1);

        mainMenuExit.setText("Exit");
        mainMenuExit.setToolTipText("");
        mainMenuExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mainMenuExitActionPerformed(evt);
            }
        });
        mainFileMenu.add(mainMenuExit);

        mainMenu.add(mainFileMenu);

        toolsMenu.setText("Tools");

        textSizeMenu.setText("Text sizes");

        fontsizes.add(smallFontMenu);
        smallFontMenu.setSelected(true);
        smallFontMenu.setText("Small");
        smallFontMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                smallFontMenuActionPerformed(evt);
            }
        });
        textSizeMenu.add(smallFontMenu);

        fontsizes.add(mediumFontMenu);
        mediumFontMenu.setText("Medium");
        mediumFontMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mediumFontMenuActionPerformed(evt);
            }
        });
        textSizeMenu.add(mediumFontMenu);

        fontsizes.add(largeFontMenu);
        largeFontMenu.setText("Large/Surface small");
        largeFontMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                largeFontMenuActionPerformed(evt);
            }
        });
        textSizeMenu.add(largeFontMenu);

        fontsizes.add(surfaceMediumMenu);
        surfaceMediumMenu.setText("Surface medium");
        surfaceMediumMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                surfaceMediumMenuActionPerformed(evt);
            }
        });
        textSizeMenu.add(surfaceMediumMenu);

        fontsizes.add(surfaceLargeMenu);
        surfaceLargeMenu.setText("Surface large");
        surfaceLargeMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                surfaceLargeMenuActionPerformed(evt);
            }
        });
        textSizeMenu.add(surfaceLargeMenu);

        toolsMenu.add(textSizeMenu);

        libraryMenuItem.setText("Library");
        libraryMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                libraryMenuItemActionPerformed(evt);
            }
        });
        toolsMenu.add(libraryMenuItem);

        undeleteMenuItem.setText("Undelete and purge");
        undeleteMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                undeleteMenuItemActionPerformed(evt);
            }
        });
        toolsMenu.add(undeleteMenuItem);

        importMenuItem.setText("Import");
        toolsMenu.add(importMenuItem);

        linkExplorerMenuItem.setText("Link explorer");
        linkExplorerMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linkExplorerMenuItemActionPerformed(evt);
            }
        });
        toolsMenu.add(linkExplorerMenuItem);

        mainMenu.add(toolsMenu);

        helpMenu.setText("Help");

        showProcessMenuItem.setText("Show process");
        showProcessMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showProcessMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(showProcessMenuItem);

        riskMatrixMenuItem.setText("Show risk matrix");
        riskMatrixMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                riskMatrixMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(riskMatrixMenuItem);

        helpAboutMenuItem.setText("About");
        helpAboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpAboutMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(helpAboutMenuItem);

        mainMenu.add(helpMenu);

        setJMenuBar(mainMenu);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mainMenuNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mainMenuNewActionPerformed
        try {
//            Project p = ProjectHelper.createProject();
//            projects.put(p.getName(), p);
//            DefaultMutableTreeNode dtmn = (DefaultMutableTreeNode)projectTree.getModel().getRoot();
//            dtmn.add(p.getProjectRoot());
//            ((DefaultTreeModel)projectTree.getModel()).reload();
            JDialog projectEditor = new JDialog(JOptionPane.getFrameForComponent(this), true);

            projectEditor.add(new ProjectEditor(lastProjectAdded).setParent(projectEditor));
            projectEditor.pack();
            projectEditor.setVisible(true);
              
        }
        catch (Exception e) {
            SmartProject.getProject().log("Failed to load new project editor", e);
            JOptionPane.showMessageDialog(rootPane, "Error " + e.getMessage() + " details have been saved to the log file", "Error whilst creating", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_mainMenuNewActionPerformed

    public javax.swing.JTabbedPane getMainWindowTabbedPane() { return mainWindowTabbedPane; }
    
    public void newObjectRequested(TreePath p) 
    {
        String n = p.getPathComponent(1).toString();
        Project proj = null;
        for (Project ap : projects.values()) {
            if (n.contentEquals(ap.getName())) {
                proj = ap;
                break;
            }
        }
        if (proj == null) {
            return;
        }
        int id = proj.getProjectID((DefaultMutableTreeNode) p.getLastPathComponent());
        if (id == -1) {
            return;
        }
        proj.setCurrentProjectID(id);
        String check = proj.checkNewFromPopupMenu(p); 
        if (check != null) {
            JOptionPane.showMessageDialog(rootPane, check, "Can't make a new one of these here", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        EditorComponent ec = proj.getEditorComponent(p);
        if (ec == null)
            return;
        mainWindowTabbedPane.setSelectedComponent(mainWindowTabbedPane.add(ec.getTitle(), ec.getComponent()));
        mainWindowTabbedPane.setTabComponentAt(mainWindowTabbedPane.getSelectedIndex(), new UndockTabComponent(mainWindowTabbedPane));
    }
    
    public boolean isDocked(JPanel p) {
        Container parent = p;
        while ((parent = parent.getParent()) != null) {
            if (parent instanceof javax.swing.JTabbedPane) {
                JTabbedPane jtp = (JTabbedPane)parent;
                int i = jtp.indexOfComponent(p);
                if (i != -1) {
                    return true;
                }
            }
        } 
        return false;
    }

    public boolean isUnDocked(JPanel p) {
        Container parent = p;
        while ((parent = parent.getParent()) != null) {
            if (parent instanceof javax.swing.JTabbedPane) {
                JTabbedPane jtp = (JTabbedPane)parent;
                int i = jtp.indexOfComponent(p);
                if (i != -1) {
                    return false;
                }
            }
            if (parent instanceof uk.nhs.digital.projectuiframework.ui.ExternalEditorView) {
                return true;
            }
        } 
        return false;
    }
    
    public void setViewTitle(JPanel p, String t) {
        Container parent = p;
        while ((parent = parent.getParent()) != null) {
            if (parent instanceof javax.swing.JTabbedPane) {
                JTabbedPane jtp = (JTabbedPane)parent;
                int i = jtp.indexOfComponent(p);
                if (i != -1) {
                    jtp.setTitleAt(i, t);
                    return;
                }
            }
            if (parent instanceof uk.nhs.digital.projectuiframework.ui.ExternalEditorView) {
                ((ExternalEditorView)parent).setTitle(t);
                return;
            }
        } 
    }
    
    public void selectPanel(JPanel p) {
        if (isDocked(p)) {
            int i = mainWindowTabbedPane.indexOfComponent(p);
            if (i != -1) {
                mainWindowTabbedPane.setSelectedIndex(i);
                return;
            }
        }
        ExternalEditorView eev = getUndockedWindow(p);
        if (eev != null) {
            if (eev.getState() == Frame.ICONIFIED)
                eev.setState(Frame.NORMAL);
            eev.setVisible(true);
            eev.toFront();
        }
    }
    
    public ExternalEditorView getUndockedWindow(JPanel p) {
        Container parent = p;
        while ((parent = parent.getParent()) != null) {
            if (parent instanceof javax.swing.JTabbedPane) {
                JTabbedPane jtp = (JTabbedPane)parent;
                int i = jtp.indexOfComponent(p);
                if (i != -1) {
                    return null;
                }
            }
            if (parent instanceof uk.nhs.digital.projectuiframework.ui.ExternalEditorView) {
                return (ExternalEditorView)parent;
            }
        } 
        return null;
        
    }
    
    public void closeContainer(JPanel p) {
        Container parent = p;
        while ((parent = parent.getParent()) != null) {
            if (parent instanceof javax.swing.JTabbedPane) {
                JTabbedPane jtp = (JTabbedPane)parent;
                int i = jtp.indexOfComponent(p);
                if (i != -1) {
                    jtp.remove(i);
                    return;
                }
            }
            if (parent instanceof uk.nhs.digital.projectuiframework.ui.ExternalEditorView) {
                ((ExternalEditorView) parent).dispose();
                return;
            }
        }
    }
    
    private void projectTreeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_projectTreeMouseClicked
        String p = null;
        try {
            p = (String)projectTree.getSelectionPath().getPathComponent(1).toString();
        }
        catch (NullPointerException e) {
            TreePath tp = projectTree.getPathForLocation(evt.getX(), evt.getY());
            if (tp == null)
                return;
            p = tp.getPathComponent(1).toString();
        }
        Project proj = null;
        for (Project ap : projects.values()) {
            if (p.contentEquals(ap.getName())) {
                proj = ap;
                break;
            }
        }
        if (proj == null) {
            return;
        }
        
        if ((evt.getButton() == MouseEvent.BUTTON_RIGHT) || (evt.getButton() == java.awt.event.MouseEvent.BUTTON3)) {
            if (evt.getClickCount() == 1) {
                TreePath pmp = projectTree.getPathForLocation(evt.getX(), evt.getY());
                if (pmp != null) {
                    if (!proj.checkShowPopup(pmp))
                        return;
                }
                (new ProjectTreePopupMenu(this)).show(projectTree, evt.getX(), evt.getY());
                return;
            }
        }
        if (evt.getClickCount() == 2 && !evt.isConsumed()) {
            evt.consume();
            EditorComponent ec = null;
            if (projectTree.getSelectionPath().getPathCount() == 2) // root and the one we can see
                return;
            int id = proj.getProjectID((DefaultMutableTreeNode)projectTree.getSelectionPath().getLastPathComponent());
            proj.setCurrentProjectID(id);
            TreePath t = projectTree.getSelectionPath();
            try {
                Object selected = ((DefaultMutableTreeNode)t.getLastPathComponent()).getUserObject();
                JPanel pnl = proj.getExistingEditor(selected, this);
                if (pnl != null) {
                    this.selectPanel(pnl);
                    return;
                }
            }
            catch (Exception eIgnore) {}
            ec = proj.getEditorComponent(t);
            if (ec == null) {
                // See if we have a ViewComponent instead... add getViewComponent(TreePath t) to Project
                // and implement it... only if *that* returns null do we to the "isCollapsed()" code.
                //
                ViewComponent view = proj.getViewComponent(t);
                if (view == null) {
                    if (projectTree.isCollapsed(t))
                        projectTree.expandPath(t);
                    else
                        projectTree.collapsePath(t);
                    return;
                }
               mainWindowTabbedPane.setSelectedComponent(mainWindowTabbedPane.add(view.getTitle(), view.getComponent()));
               mainWindowTabbedPane.setTabComponentAt(mainWindowTabbedPane.getSelectedIndex(), new UndockTabComponent(mainWindowTabbedPane));                    
               return;
            }
            for (int i = 0; i < mainWindowTabbedPane.getTabCount(); i++) {
                if (ec.getTitle().contentEquals((mainWindowTabbedPane.getTitleAt(i)))) {
                    mainWindowTabbedPane.setSelectedIndex(i);
                    return;
                }
            }
            if (evt.getButton() == java.awt.event.MouseEvent.BUTTON1) {
               mainWindowTabbedPane.setSelectedComponent(mainWindowTabbedPane.add(ec.getTitle(), ec.getComponent()));
               mainWindowTabbedPane.setTabComponentAt(mainWindowTabbedPane.getSelectedIndex(), new UndockTabComponent(mainWindowTabbedPane));                    
            } else {
               ExternalEditorView.start(ec.getComponent(), ec.getTitle(), mainWindowTabbedPane);
            }  
        }        
    }//GEN-LAST:event_projectTreeMouseClicked

    private void mainMenuExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mainMenuExitActionPerformed
        
        boolean outstandingsaves = false;
        for (Project p : projects.values()) {
            if (p.hasChanged()) {
                outstandingsaves = true;
                break;
            }
        }
        if (outstandingsaves) {
            int r = JOptionPane.showConfirmDialog(rootPane, "There are changed projects. Exit without saving ?", "Unsaved projects", JOptionPane.YES_NO_OPTION);
            if (r == JOptionPane.NO_OPTION)
                return;
        }
        SmartProject.getProject().shutdown();
        System.exit(0);
    }//GEN-LAST:event_mainMenuExitActionPerformed

    private void libraryMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_libraryMenuItemActionPerformed
        LibraryEditorDialog lde = new LibraryEditorDialog(JOptionPane.getFrameForComponent(this), true);
        lde.setVisible(true);
    }//GEN-LAST:event_libraryMenuItemActionPerformed

    private void helpAboutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpAboutMenuItemActionPerformed
        HelpAboutDialog had = new HelpAboutDialog(this, true, lastProjectAdded.getHelpAboutIcon());
        had.setVisible(true);
    }//GEN-LAST:event_helpAboutMenuItemActionPerformed

    private void saveAllMenuMenuSelected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_saveAllMenuMenuSelected
       lastProjectAdded.saveAll();
    }//GEN-LAST:event_saveAllMenuMenuSelected

    private void showProcessMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showProcessMenuItemActionPerformed
        String s = System.getProperty("uk.nhs.digital.projectuiframework.initialtabtitle");
        for (int i = 0; i < mainWindowTabbedPane.getTabCount(); i++) {
            if ("SMART".contentEquals((mainWindowTabbedPane.getTitleAt(i)))) {
                mainWindowTabbedPane.setSelectedIndex(i);
                return;
            }
        }
        
        mainWindowTabbedPane.setSelectedComponent(mainWindowTabbedPane.add(s, new InitialTab()));
        mainWindowTabbedPane.setTabComponentAt(mainWindowTabbedPane.getSelectedIndex(), new UndockTabComponent(mainWindowTabbedPane));
    }//GEN-LAST:event_showProcessMenuItemActionPerformed

    private void riskMatrixMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_riskMatrixMenuItemActionPerformed
        new RiskMatrix(this, false).setVisible(true);
    }//GEN-LAST:event_riskMatrixMenuItemActionPerformed

    private void setUIFont(Font f, SmartProject sp) {
        Enumeration keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object k = keys.nextElement();
            Object v = UIManager.get(k);
            if ((v != null) && (v instanceof java.awt.Font)) {
                UIManager.put(k, f);
            }
        }
        projectTree.setFont(f);
        projectTree.repaint();
        java.lang.System.setProperty("SMART.userfont", Float.toString(f.getSize2D()));
        if (sp != null)
            sp.saveUserProperties();
  //      pack();
    }
    
    private void smallFontMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_smallFontMenuActionPerformed
        SmartProject sp = SmartProject.getProject();
        Font f1 = sp.getDisplayFont();
        Font f2 = f1.deriveFont((float)12.0);
        sp.setDisplayFont(f2);
        setUIFont(f2, sp);
    }//GEN-LAST:event_smallFontMenuActionPerformed

    private void mediumFontMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mediumFontMenuActionPerformed
        SmartProject sp = SmartProject.getProject();
        Font f1 = sp.getDisplayFont();
        Font f2 = f1.deriveFont((float)14.0);
        sp.setDisplayFont(f2);
        setUIFont(f2, sp);
    }//GEN-LAST:event_mediumFontMenuActionPerformed

    private void largeFontMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_largeFontMenuActionPerformed
        SmartProject sp = SmartProject.getProject();
        Font f1 = sp.getDisplayFont();
        Font f2 = f1.deriveFont((float)18.0);
        sp.setDisplayFont(f2);
        setUIFont(f2, sp);
    }//GEN-LAST:event_largeFontMenuActionPerformed

    private void surfaceMediumMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_surfaceMediumMenuActionPerformed
        SmartProject sp = SmartProject.getProject();
        Font f1 = sp.getDisplayFont();
        Font f2 = f1.deriveFont((float)22.0);
        sp.setDisplayFont(f2);
        setUIFont(f2, sp);
        if (System.getProperty("os.name").contains("Windows")) {
            projectTree.setRowHeight((int)(f2.getSize() * 1.3));
        }
    }//GEN-LAST:event_surfaceMediumMenuActionPerformed

    private void surfaceLargeMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_surfaceLargeMenuActionPerformed
        SmartProject sp = SmartProject.getProject();
        Font f1 = sp.getDisplayFont();
        Font f2 = f1.deriveFont((float)24.0);
        sp.setDisplayFont(f2);
        setUIFont(f2, sp);
        if (System.getProperty("os.name").contains("Windows")) {
            projectTree.setRowHeight((int)(f2.getSize() * 1.3));
        }
        
    }//GEN-LAST:event_surfaceLargeMenuActionPerformed

    private void undeleteMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_undeleteMenuItemActionPerformed
        (new Housekeeper(this, true)).setVisible(true);
    }//GEN-LAST:event_undeleteMenuItemActionPerformed

    private void linkExplorerMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linkExplorerMenuItemActionPerformed
        (new LinkExplorer(this, false)).setVisible(true);
    }//GEN-LAST:event_linkExplorerMenuItemActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        boolean outstandingsaves = false;
        for (Project p : projects.values()) {
            if (p.hasChanged()) {
                outstandingsaves = true;
                break;
            }
        }
        if (outstandingsaves) {
            int r = JOptionPane.showConfirmDialog(rootPane, "There are changed projects. Exit without saving ?", "Unsaved projects", JOptionPane.YES_NO_OPTION);
            if (r == JOptionPane.NO_OPTION) {
                JOptionPane.showMessageDialog(rootPane, "Save your work, then exit", "Save work", JOptionPane.OK_OPTION);
                return;
            }
        }
        SmartProject.getProject().shutdown();
        this.dispose();
    }//GEN-LAST:event_formWindowClosing

    private void changeDatabaseMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeDatabaseMenuItemActionPerformed

        String c = System.getProperty("SMART.allowdatabasechange"); 
        if ((c == null) || !c.toLowerCase().startsWith("y")) {
            JOptionPane.showMessageDialog(rootPane, "This feature is not available.", "Not available", JOptionPane.OK_OPTION);
            return;
        }
        
        String dbLocation = null;
        JFileChooser jfc = new JFileChooser();
        jfc.setDialogType(JFileChooser.OPEN_DIALOG);
        jfc.setDialogTitle("SMART database directory");
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        jfc.setMultiSelectionEnabled(false);
        int result = jfc.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File f = jfc.getSelectedFile();
            if (f.getAbsolutePath().contains(" ") || f.getName().contains(" ")) {
                JOptionPane.showMessageDialog(this, "Due to the database URL requirements, installation locations cannot contain spaces", "Invalid location", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (f.exists() && f.canWrite()) {
                File dbcheckinstall = new File(f, "Database");
                if (dbcheckinstall.exists()) {
                    File olddb = new File(dbcheckinstall, "safety.script");
                    if (olddb.exists()) {
                        dbLocation = olddb.getAbsolutePath();
                    }
                }
                if (dbLocation == null) {
                    JOptionPane.showMessageDialog(this, "No SMART database found in " + f.getAbsolutePath(), "No database", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
            } else {
                JOptionPane.showMessageDialog(this, "The selected location either does not exist or cannot be accessed", "Invalid location", JOptionPane.INFORMATION_MESSAGE);
            }
            String databaseUrl = "jdbc:hsqldb:file:" + f.getAbsolutePath() + "/Database/safety;shutdown=true";
//            System.out.println(databaseUrl);
            System.setProperty("SMART.dburl", databaseUrl);
                        
            try {
                SmartProject sp = SmartProject.getProject();     
                sp.resetDatabase(false);
                setTreeModel(sp.getTreeModel(), sp.getName(), sp);
                int permanent = JOptionPane.showConfirmDialog(this, "Make this database URL:\n" + databaseUrl + "\nthe default ?", "Make database default", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);                
                if (permanent == JOptionPane.YES_OPTION) {                                    
                    sp.saveUserProperties();
                }
            }
            catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Resetting database failed:\n" + e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        
    }//GEN-LAST:event_changeDatabaseMenuItemActionPerformed

    public void newProject(String n, Project p) {
        lastProjectAdded = p;
        projects.put(n, p);        
    }
    
    public void addProject(String n, Project p) {
        lastProjectAdded = p;
        projects.put(n, p);
        DefaultMutableTreeNode d = p.getProjectRoot();
        DefaultTreeModel tm = (DefaultTreeModel)projectTree.getModel();
        ((DefaultMutableTreeNode)tm.getRoot()).add(p.getProjectRoot());
        tm.reload();
        projectTree.expandRow(0);
    }
    
    public void resetTreeModel(DefaultTreeModel m) {
        projectTree.setModel(m);
        projectTree.expandRow(0);        
    }
    
    public void setTreeModel(DefaultTreeModel m, String n, Project p) {
        lastProjectAdded = p;
        projects.put(n, p);
        projectTree.setModel(m);
        projectTree.expandRow(0);
    }
    /**
     * @param args the command line arguments
     */
    public void start(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ProjectWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProjectWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProjectWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProjectWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

       
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new ProjectWindow().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem changeDatabaseMenuItem;
    private javax.swing.ButtonGroup fontsizes;
    private javax.swing.JMenuItem helpAboutMenuItem;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JMenuItem importMenuItem;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JRadioButtonMenuItem largeFontMenu;
    private javax.swing.JMenuItem libraryMenuItem;
    private javax.swing.JMenuItem linkExplorerMenuItem;
    private javax.swing.JMenu mainFileMenu;
    private javax.swing.JMenuBar mainMenu;
    private javax.swing.JMenuItem mainMenuExit;
    private javax.swing.JMenuItem mainMenuNew;
    private javax.swing.JSplitPane mainWindowSplitPane;
    private javax.swing.JTabbedPane mainWindowTabbedPane;
    private javax.swing.JRadioButtonMenuItem mediumFontMenu;
    private javax.swing.JTree projectTree;
    private javax.swing.JScrollPane projectTreeScrollPane;
    private javax.swing.JMenuItem riskMatrixMenuItem;
    private javax.swing.JMenu saveAllMenu;
    private javax.swing.JMenuItem showProcessMenuItem;
    private javax.swing.JRadioButtonMenuItem smallFontMenu;
    private javax.swing.JRadioButtonMenuItem surfaceLargeMenu;
    private javax.swing.JRadioButtonMenuItem surfaceMediumMenu;
    private javax.swing.JMenu textSizeMenu;
    private javax.swing.JMenu toolsMenu;
    private javax.swing.JMenuItem undeleteMenuItem;
    // End of variables declaration//GEN-END:variables
}
