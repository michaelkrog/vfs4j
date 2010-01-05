/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.javavfs.impl.subfs;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.javavfs.Directory;
import org.javavfs.File;
import org.javavfs.FileSystem;
import org.javavfs.Node;
import org.javavfs.NodeFilter;

/**
 *
 * @author michael
 */
public class SubDirectory extends SubNode implements Directory{

    public SubDirectory(FileSystem fs, Directory subbedDirectory) {
        super(fs, subbedDirectory);
        this.subbedDirectory=subbedDirectory;
    }

    private Directory subbedDirectory;

    public Directory createDirectory(String name) throws IOException {
        return subbedDirectory.createDirectory(name);
    }

    public File createFile(String name) throws IOException {
        return subbedDirectory.createFile(name);
    }

    public boolean isRoot() {
        try {
            return this.equals(fs.getRoot());
        } catch (FileNotFoundException ex) {
            return false;
        }
    }

    public boolean hasChild(String name) {
        return subbedDirectory.hasChild(name);
    }

    public boolean hasFile(String name) {
        return subbedDirectory.hasFile(name);
    }

    public boolean hasDirectory(String name) {
        return subbedDirectory.hasDirectory(name);
    }

    public Node getChild(String name) throws FileNotFoundException {
        Node node = subbedDirectory.getChild(name);
        if(node.isDirectory())
            return new SubDirectory(fs, (Directory)node);
        else
            return new SubFile(fs, (File)node);
    }

    public File getFile(String name) throws FileNotFoundException {
        return new SubFile(fs, subbedDirectory.getFile(name));
    }

    public Directory getDirectory(String name) throws FileNotFoundException {
        return new SubDirectory(fs, subbedDirectory.getDirectory(name));
    }

    public File getFile(String name, boolean createIfNeeded) throws FileNotFoundException, IOException {
            return new SubFile(fs, subbedDirectory.getFile(name,createIfNeeded));
    }

    public Directory getDirectory(String name, boolean createIfNeeded) throws FileNotFoundException, IOException {
        return new SubDirectory(fs, subbedDirectory.getDirectory(name,createIfNeeded));
    }

    public List<Node> getChildren() {
        return getChildren(null);
    }

    public List<Directory> getDirectories() {
        return getDirectories(null);
    }

    public List<File> getFiles() {
        return getFiles(null);
    }

    public List<Node> getChildren(NodeFilter filter) {
        List<Node> childList = subbedDirectory.getChildren(filter);
        List<Node> subbedList = new ArrayList<Node>();

        for(Node node:childList){
            subbedList.add(node.isDirectory()?new SubDirectory(fs, (Directory)node):new SubFile(fs,(File)node));
        }
        return subbedList;
    }

    public List<Directory> getDirectories(NodeFilter filter) {
        List<Directory> childList = subbedDirectory.getDirectories(filter);
        List<Directory> subbedList = new ArrayList<Directory>();

        for(Directory node:childList){
            subbedList.add(new SubDirectory(fs, node));
        }
        return subbedList;
    }

    public List<File> getFiles(NodeFilter filter) {
        List<File> childList = subbedDirectory.getFiles(filter);
        List<File> subbedList = new ArrayList<File>();

        for(File node:childList){
            subbedList.add(new SubFile(fs, node));
        }
        return subbedList;
    }

    public void delete(boolean recursive) throws IOException {
        subbedDirectory.delete(recursive);
    }

    public boolean isBundle() {
        return subbedDirectory.isBundle();
    }

}
