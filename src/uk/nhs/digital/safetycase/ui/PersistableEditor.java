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
import uk.nhs.digital.projectuiframework.DataNotificationSubscriber;
import uk.nhs.digital.projectuiframework.ui.EditorComponent;
import uk.nhs.digital.safetycase.data.Persistable;

/**
 *
 * @author damian
 */
public interface PersistableEditor 
        extends DataNotificationSubscriber
        
{
    public void setPersistableObject(Persistable p);
    public void setEditorComponent(EditorComponent ed);
    public Component getComponent();
    public void setNewObjectProjectId(int i);
}
