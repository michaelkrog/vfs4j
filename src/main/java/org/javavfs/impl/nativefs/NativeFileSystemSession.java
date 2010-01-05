/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.javavfs.impl.nativefs;

import java.io.FileNotFoundException;
import java.security.Principal;
import org.javavfs.Directory;
import org.javavfs.FileSystem;
import org.javavfs.FileSystemSession;
import org.javavfs.Node;
import org.javavfs.Path;

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

    public Node getNode(String path) throws FileNotFoundException {
        Path pathObject = new Path(path);
        Node currentNode = getRoot();
        
        for(int i=0;i<pathObject.getLevels();i++){
            if(currentNode.isDirectory()){
                currentNode = ((Directory)currentNode).getChild(pathObject.getLevel(i));
            } else
                throw new FileNotFoundException("The path '"+path+"' does not exist.");
        }
        return currentNode;
    }
     
    

}
