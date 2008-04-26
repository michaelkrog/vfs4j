/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.javavfs;

import java.io.FileNotFoundException;
import java.util.Map;

/**
 *
 * @author mzk
 */
public interface FileSystem {
    public static final String FSInfo_Name="FSInfo_Name";
    public static final String FSInfo_Description="FSInfo_Description";
    public static final String FSInfo_Version="FSInfo_Version";
    
    public String getName();
    public Directory getRoot() throws FileNotFoundException;
    public Map getInfo();
    
}
