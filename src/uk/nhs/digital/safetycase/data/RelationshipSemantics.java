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
package uk.nhs.digital.safetycase.data;

import uk.nhs.digital.projectuiframework.smart.SmartProject;

/**
 *
 * @author damian
 */
public class RelationshipSemantics {
    
    private int semanticsId = -1;
    private String sourceType = null;
    private String targetType = null;
    private String summary = null;
    private String description = null;
    private String displayName = null;
    
    static final String sqlAll = "select * from RelationshipSemantics";
    static final String sqlActive = "select * from RelationshipSemantics where DeprecatedDate is null";

    RelationshipSemantics(int i) { semanticsId = i; }
    
    void setSourceType(String s) { sourceType = s; }
    void setTargetType(String t) { 
        targetType = t; 
        try {
            String cname = "uk.nhs.digital.safetycase.data." + targetType;
            Persistable p = (Persistable)Class.forName(cname).newInstance();
            displayName = p.getDisplayName();
        }
        catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            SmartProject.getProject().log("Could not identify displayName for relationship target type " + t, e);
        }
    }
    void setSummary(String s) { summary = s; }
    void setDescription(String d) { description = d; }
    
    public String getSourceType() { return sourceType; }
    public String getTargetType() { return targetType; }
    public String getSummary() { return summary; }
    public String getDescription() { return description; }
    public String getDisplayName() { return displayName; }
}
