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
package uk.nhs.digital.projectuiframework;
import javax.swing.UIManager;
import uk.nhs.digital.projectuiframework.ui.ProjectWindow;
import uk.nhs.digital.safetycase.data.Database;
import uk.nhs.digital.safetycase.data.MetaFactory;
/**
 *
 * @author damian
 */
public class ProjectUIFramework {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.setProperty(ProjectHelper.PROJECTCLASSPROPERTY, "uk.nhs.digital.projectuiframework.smart.SmartProject");
        Project smart = null;
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        java.lang.System.setProperty("user.minimum.wrap.width", "132");
        java.lang.System.setProperty(Database.CONNECTIONURLPROPERTY, args[0]);
        java.lang.System.setProperty("user", "SA");
        java.lang.System.setProperty("password", "");
        java.lang.System.setProperty("uk.nhs.digital.safetycase.applicationidentity", "NHS Digital SMART Safety Case Editor DEVELOPMENT 20180521");
//        java.lang.System.setProperty("uk.nhs.digital.safetycase.ui.Process","uk.nhs.digital.safetycase.ui.processeditor.ProcessEditor");
        java.lang.System.setProperty("uk.nhs.digital.safetycase.ui.Process","uk.nhs.digital.safetycase.ui.processeditor.SingleProcessEditorForm");
        java.lang.System.setProperty("uk.nhs.digital.safetycase.ui.Location","uk.nhs.digital.safetycase.ui.LocationEditor");
        java.lang.System.setProperty("uk.nhs.digital.safetycase.ui.Role","uk.nhs.digital.safetycase.ui.RoleEditor");
        java.lang.System.setProperty("uk.nhs.digital.safetycase.ui.Hazard","uk.nhs.digital.safetycase.ui.HazardEditor");
        java.lang.System.setProperty("uk.nhs.digital.safetycase.ui.Effect","uk.nhs.digital.safetycase.ui.EffectEditor");
        java.lang.System.setProperty("uk.nhs.digital.safetycase.ui.Control","uk.nhs.digital.safetycase.ui.ControlEditor");
        java.lang.System.setProperty("uk.nhs.digital.safetycase.ui.Cause","uk.nhs.digital.safetycase.ui.CauseEditor");
        java.lang.System.setProperty("uk.nhs.digital.safetycase.ui.System","uk.nhs.digital.safetycase.ui.systemeditor.SystemEditorDetails");
        java.lang.System.setProperty("uk.nhs.digital.safetycase.ui.Report","uk.nhs.digital.safetycase.ui.SafetyReportEditor");
        java.lang.System.setProperty("uk.nhs.digital.safetycase.ui.Project","uk.nhs.digital.safetycase.ui.ProjectEditor");
        java.lang.System.setProperty("uk.nhs.digital.projectuiframework.initialtab", "internal:/uk/nhs/digital/projectuiframework/smart/frontpage.pdf");
        java.lang.System.setProperty("uk.nhs.digital.projectuiframework.initialtabtitle", "SMART");
        java.lang.System.setProperty("uk.nhs.digial.projectuiframework.appicon", "/uk/nhs/digital/projectuiframework/smart/nhsd-16x16.png");
        System.out.println("Running on " + System.getProperty("os.name"));
        ProjectWindow p = new ProjectWindow();
        try {
            smart = ProjectHelper.createProject();
            smart.setProjectWindow(p);
            p.setTitle(smart.getApplicationIdentifier());
            MetaFactory.getInstance().setUIProject(smart);
//            p.addProject(smart.getName(), smart);
            p.setTreeModel(smart.getTreeModel(), smart.getName(), smart);
        }
        catch (Exception e) {
            System.err.println("Fatal error initialising: " + e.toString());
            System.exit(1);
        }
        p.setVisible(true);
    }
    
}
