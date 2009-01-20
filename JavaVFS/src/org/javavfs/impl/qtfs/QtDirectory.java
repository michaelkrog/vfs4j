/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.javavfs.impl.qtfs;

import com.trolltech.qt.core.QFileInfo;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import org.javavfs.Directory;
import org.javavfs.File;
import org.javavfs.FileSystemSession;
import org.javavfs.Node;
import org.javavfs.NodeFilter;

/**
 *
 * @author michael
 */
public class QtDirectory extends QtNode implements Directory {

    public QtDirectory(QtFileSystemSession session, QFileInfo file) {
        super(session, file);
    }

    public Directory createDirectory(String name) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public File createFile(String name) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean hasChild(String name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean hasFile(String name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean hasDirectory(String name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Node getChild(String name) throws FileNotFoundException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public File getFile(String name) throws FileNotFoundException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Directory getDirectory(String name) throws FileNotFoundException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public File getFile(String name, boolean createIfNeeded) throws FileNotFoundException, IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Directory getDirectory(String name, boolean createIfNeeded) throws FileNotFoundException, IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Node> getChildren() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Directory> getDirectories() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<File> getFiles() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Node> getChildren(NodeFilter filter) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Directory> getDirectories(NodeFilter filter) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<File> getFiles(NodeFilter filter) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
}
