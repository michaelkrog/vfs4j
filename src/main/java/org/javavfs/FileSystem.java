/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.javavfs;

import java.security.Principal;
import java.util.Map;
import org.javavfs.security.Security;

/**
 *
 * @author michael
 */
public interface FileSystem {
    public static final String FSInfo_Name="FSInfo_Name";
    public static final String FSInfo_Description="FSInfo_Description";
    public static final String FSInfo_Version="FSInfo_Version";
    public static final String FSInfo_HasSizeInformation="FSInfo_HasSizeInformation";
    public static final String FSInfo_HasFreeSpaceInformation="FSInfo_HasFreeSpaceInformation";
    
    
    /**
     * Retrieves the name of the filesystem.
     * @return The name.
     */
    public String getName();
    
    public FileSystemSession createSession(Principal principal);
    
    /**
     * Retrieves a list of information about the filesystem.
     * @return A map hold key/pair information about the filesystem. Default keys are available as static variables on the FileSystem interface.
     */
    public Map getInfo();
    
    
    /**
     * Retrieves the security used by the filesystem.
     * @return The Security object.
     */
    public Security getSecurity();
    
    /**
     * Sets the security object used by the filesystem.
     * @param security The new security to use for the filesystem.
     */
    public void setSecurity(Security security);
    
}
