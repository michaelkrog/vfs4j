/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.javavfs.nativefs;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import org.javavfs.Directory;
import org.javavfs.FileSystem;

/**
 *
 * @author mzk
 */
public class NativeFileSystem implements FileSystem{

    public NativeFileSystem(URI uri) throws IllegalArgumentException {
        if(!uri.getScheme().equals("file"))
            throw new IllegalArgumentException("Scheme not valid. Must be 'file' (fx: file:/mydir).");
        
        //Add info to infomap
        infomap.put(FileSystem.FSInfo_Name, "NativeFS");
        infomap.put(FileSystem.FSInfo_Description, "A filesystem based upon native files via the java.io package.");
        infomap.put(FileSystem.FSInfo_Version, "0.1.0");
        
        
        root = new File(uri);
        if(!root.exists() || !root.isDirectory())
            throw new IllegalArgumentException("uri must point at a valid directory. [uri="+root.toString()+"]");
    }

    File root;
    HashMap infomap = new HashMap();
    
    public String getName() {
        return "NativeFS("+root.toURI()+")";
    }

    public Directory getRoot() throws FileNotFoundException {
        return new NativeDirectory(this, root);
    }

    public Map getInfo() {
        return infomap;
    }

}
