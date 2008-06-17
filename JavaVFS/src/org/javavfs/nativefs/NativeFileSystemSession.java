/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.javavfs.nativefs;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URI;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import org.javavfs.Directory;
import org.javavfs.FileSystem;
import org.javavfs.FileSystemSession;
import org.javavfs.security.NoSecurity;
import org.javavfs.security.Security;

/**
 *
 * @author mzk
 */
public class NativeFileSystemSession implements FileSystemSession{

    public NativeFileSystemSession(NativeFileSystem filesystem, Principal principal) {
        this.filesystem=filesystem;
        this.principal=principal;
    }

    NativeFileSystem filesystem;
    Principal principal;
    
    public Directory getRoot() throws FileNotFoundException {
        return new NativeDirectory(this, filesystem.getRoot());
    }

    public long getSize() {
        //Java version < 1.6
        return -1;
        
        //Java version 1.6+
        //return root.innerFile.getTotalSpace();
    }

    public long getFreeSpace() {
        //Java version < 1.6
        return -1;
        
        //Java version 1.6+
        //return root.innerFile.getFreeSpace();
    }

    public FileSystem getFileSystem() {
        return filesystem;
    }

    public Principal getPrincipal() {
        return principal;
    }
    
    

}
