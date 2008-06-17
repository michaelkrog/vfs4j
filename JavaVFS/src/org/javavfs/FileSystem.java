/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.javavfs;

import java.io.FileNotFoundException;
import java.util.Map;
import org.javavfs.security.Security;

/**
 *
 * @author mzk
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
    
    /**
     * Retrieves the root directory of the filesystem.
     * @return The root directory.
     * @throws java.io.FileNotFoundException Thrown if the root directory could not be found.
     */
    public Directory getRoot() throws FileNotFoundException;
    
    /**
     * Retrieves a list of information about the filesystem.
     * @return A map hold key/pair information about the filesystem. Default keys are available as static variables on the FileSystem interface.
     */
    public Map getInfo();
    
    /**
     * Retrieves the size of the filesystem in bytes.
     * @return The size of the filesystem or -1 if not supported. The filesystem should report if it supports this call via the infomap retrieveable by <code>getInfo()</code>. The key to use for retrieveal is <code>FSInfo_HasSizeInformation</code>
     */
    public long getSize();
    
    /**
     * Retrieves the free space available.
     * @return The free space avaliable in bytes or -1 if not supported. The filesystem should report if it supports this call via the infomap retrieveable by <code>getInfo()</code>. The key to use for retrieveal is <code>FSInfo_HasFreeSpaceInformation</code>.
     */
    public long getFreeSpace();
    
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
