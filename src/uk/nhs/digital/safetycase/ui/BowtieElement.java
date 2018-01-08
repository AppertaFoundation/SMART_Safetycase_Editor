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

import uk.nhs.digital.safetycase.data.Persistable;

/**
 *
 * @author damian
 */
public class BowtieElement {

    int cellId = -1;
    
    // TODO NEXT:
    // This doesn't support real "bowties" where for example a hazard has more than one
    // effect, or multiple causes. So... replace the "fromCell" and "toCell" with an
    // ArrayList<BowtieElement> which is populated with *targets* by the parseBowtie()
    // method. Processing those from the targets will allow bi-directional relationships
    // to be made when a bowtie is saved, and the overall hazard/component "BowtieDiagram"
    // relationsips.

    
    public int fromCell = -1;
    public int toCell = -1;
    public String type = null;
    public String name = null;
    public Persistable object = null;
    public boolean updateDone = false;
    
    BowtieElement(String s, String n, String c) {
        cellId = Integer.parseInt(c);
        name = n;
        int lastslash = s.lastIndexOf("/");
        char f = s.charAt(lastslash + 1);
        Character uf = Character.toUpperCase(f);
        type = uf + s.substring(lastslash + 2, s.indexOf("."));
    }
    
    public BowtieElement(Persistable p) {
        name = p.getAttributeValue("Name");
        type = p.getDatabaseObjectName();
        cellId = p.getAttribute("GraphCellId").getIntValue();
        object = p;
    }
}
