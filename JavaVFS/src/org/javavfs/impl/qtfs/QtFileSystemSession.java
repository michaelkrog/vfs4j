/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.javavfs.impl.qtfs;

import java.io.FileNotFoundException;
import java.security.Principal;
import org.javavfs.Directory;
import org.javavfs.FileSystem;
import org.javavfs.FileSystemSession;
import org.javavfs.Node;

/**
 *
 * @author michael
 */
public class QtFileSystemSession implements FileSystemSession{

    public Directory getRoot() throws FileNotFoundException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public FileSystem getFileSystem() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public long getSize() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public long getFreeSpace() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Principal getPrincipal() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Node getNode(String path) throws FileNotFoundException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
