/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.javavfs.impl.qtfs;

import com.trolltech.qt.core.QDir;
import com.trolltech.qt.core.QFile;
import java.io.FileNotFoundException;
import java.security.Principal;
import org.javavfs.Directory;
import org.javavfs.FileSystem;
import org.javavfs.FileSystemSession;
import org.javavfs.Node;
import org.javavfs.Path;

/**
 *
 * @author michael
 */
public class QtFileSystemSession implements FileSystemSession{

    public QtFileSystemSession(QtFileSystem filesystem, Principal principal) {
        this.filesystem=filesystem;
        this.principal=principal;
    }

    private Principal principal;
    private QtFileSystem filesystem;

    public Directory getRoot() throws FileNotFoundException {
        return new QtDirectory(this, filesystem.getRoot());
    }

    public FileSystem getFileSystem() {
        return filesystem;
    }

    public long getSize() {
        return 0;
    }

    public long getFreeSpace() {
        return 0;
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
