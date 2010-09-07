/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.javavfs.impl.subfs;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.Principal;
import java.util.Map;
import org.javavfs.Directory;
import org.javavfs.FileSystem;
import org.javavfs.FileSystemSession;
import org.javavfs.Node;
import org.javavfs.Path;
import org.javavfs.security.Security;

/**
 *
 * @author michael
 */
public class SubFs implements FileSystem{

    public SubFs(FileSystem fs, Directory root) {
        this.root = root;
    }

    private Directory root;
    private FileSystem fs;

    public String getName() {
        return fs.getName();
    }

    public Map getInfo() {
        return fs.getInfo();
    }

    public Directory getRoot() throws FileNotFoundException {
        return root;
    }

    public long getSize() {
        return fs.getSize();
    }

    public long getFreeSpace() {
        return fs.getFreeSpace();
    }

    public Node getNode(String path) throws FileNotFoundException {
        Path pathObject = new Path(path);
        Node currentNode = root;

        for(int i=0;i<pathObject.getLevels();i++){
            if(currentNode.isDirectory()){
                currentNode = ((Directory)currentNode).getChild(pathObject.getLevel(i));
            } else
                throw new FileNotFoundException("The path '"+path+"' does not exist.");
        }
        return currentNode;
    }

    public void close() throws IOException {
        fs.close();
    }




}
