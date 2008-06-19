/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.javavfs;

import java.io.FileNotFoundException;
import java.security.Principal;
import java.util.Map;
import org.javavfs.security.Security;

/**
 *
 * @author mzk
 */
public interface FileSystemSession {
    
    /**
     * Retrieves the root directory of the filesystem.
     * @return The root directory.
     * @throws java.io.FileNotFoundException Thrown if the root directory could not be found.
     */
    public Directory getRoot() throws FileNotFoundException;
    
    public FileSystem getFileSystem();
    
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
    
    public Principal getPrincipal();
    
    public Node getNode(String path) throws FileNotFoundException;
    
    
}
