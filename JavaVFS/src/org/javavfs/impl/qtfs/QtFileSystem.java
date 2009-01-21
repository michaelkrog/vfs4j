/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.javavfs.impl.qtfs;

import com.trolltech.qt.core.QDir;
import com.trolltech.qt.core.QFileInfo;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import org.javavfs.FileSystem;
import org.javavfs.FileSystemSession;
import org.javavfs.security.Security;

/**
 *
 * @author michael
 */
public class QtFileSystem implements FileSystem {

    public QtFileSystem(String path) {
        
        //Add info to infomap
        infomap.put(FileSystem.FSInfo_Name, "QtFS");
        infomap.put(FileSystem.FSInfo_Description, "A filesystem based upon Qt's file implementation.");
        infomap.put(FileSystem.FSInfo_Version, "0.1.0");
        
        //Java version < 1.6
        infomap.put(FileSystem.FSInfo_HasFreeSpaceInformation, "false");
        infomap.put(FileSystem.FSInfo_HasSizeInformation, "false");
        
        
        root = new QFileInfo(path);
        if(!root.exists() || !root.isDir())
            throw new IllegalArgumentException("path must point at a valid directory. [uri="+root.toString()+"]");

        
    }

    private QFileInfo root;
    private HashMap infomap = new HashMap();
    private String name="QtFileSystem";
    private Security security = new QtFileSecurity();

    public QFileInfo getRoot() {
        return root;
    }

    
    public String getName() {
        return name;
    }

    public FileSystemSession createSession(Principal principal) {
        return new QtFileSystemSession(this, principal);
    }

    public Map getInfo() {
        return infomap;
    }

    public Security getSecurity() {
        return security;
    }

    public void setSecurity(Security security) {
        this.security=security;
    }

}
