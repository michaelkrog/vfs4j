/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.javavfs.impl.nativefs;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URI;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import org.javavfs.Directory;
import org.javavfs.FileSystem;
import org.javavfs.FileSystemSession;
import org.javavfs.Node;
import org.javavfs.Path;
import org.javavfs.security.NoSecurity;
import org.javavfs.security.Security;

/**
 *
 * @author michael
 */
public class NativeFileSystem implements FileSystem {
    public NativeFileSystem(URI uri) throws IllegalArgumentException {
        if(!uri.getScheme().equals("file"))
            throw new IllegalArgumentException("Scheme not valid. Must be 'file' (fx: file:/mydir).");
        
        //Add info to infomap
        infomap.put(FileSystem.FSInfo_Name, "NativeFS");
        infomap.put(FileSystem.FSInfo_Description, "A filesystem based upon native files via the java.io package.");
        infomap.put(FileSystem.FSInfo_Version, "0.1.0");
        
        //Java version < 1.6
        infomap.put(FileSystem.FSInfo_HasFreeSpaceInformation, "false");
        infomap.put(FileSystem.FSInfo_HasSizeInformation, "false");
        
        
        root = new File(uri);
        if(!root.exists() || !root.isDirectory())
            throw new IllegalArgumentException("uri must point at a valid directory. [uri="+root.toString()+"]");
    }
    
    File root;
    HashMap infomap = new HashMap();
    Security security = new NoSecurity();

    /*public File getRoot() {
        return root;
    }*/
    
    
    public Map getInfo() {
        return infomap;
    }

    /*public Security getSecurity() {
        return security;
    }

    public void setSecurity(Security security) {
        this.security=security;
    }*/

    public String getName() {
        return "NativeFS("+root.toURI()+")";
    }



    /*public FileSystemSession createSession(Principal principal) {
        return new NativeFileSystemSession(this, principal);
    }*/

    public Directory getRoot() throws FileNotFoundException {
        return new NativeDirectory(this, root);
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
