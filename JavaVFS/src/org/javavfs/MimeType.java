/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.javavfs;

/**
 *
 * @author michael
 */
public class MimeType {
    /**
     * Retrieves the mimetype for a specified filename. The name does not need to be 
     * related to an exisintg file. The methond simply looks at the name, and detects 
     * from its suffix(or some other way) what mimetype it is.
     * @param filename The filename.
     * @return The mimetype.
     */
    public String retrieve(String filename){
        return "Uuknown/unknown";
    }
}
