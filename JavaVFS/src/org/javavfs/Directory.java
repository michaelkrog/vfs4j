/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.javavfs;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author mzk
 */
public interface Directory extends Node {
    public Directory createDirectory(String name) throws IOException;
    public File createFile(String name) throws IOException;
    
    public boolean isRoot();
    public boolean hasChild(String name);
    public boolean hasFile(String name);
    public boolean hasDirectory(String name);
    
    public Node getChild(String name) throws FileNotFoundException;
    public File getFile(String name) throws FileNotFoundException;
    public Directory getDirectory(String name) throws FileNotFoundException;
    public File getFile(String name, boolean createIfNeeded) throws FileNotFoundException, IOException;
    public Directory getDirectory(String name, boolean createIfNeeded) throws FileNotFoundException, IOException;
    
    public List<Node> getChildren();
    public List<Directory> getDirectories();
    public List<File> getFiles();
    
    public List<Node> getChildren(NodeFilter filter);
    public List<Directory> getDirectories(NodeFilter filter);
    public List<File> getFiles(NodeFilter filter);
    
    public void delete(boolean recursive) throws IOException;
    
}
