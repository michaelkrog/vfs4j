package dk.apaq.vfs.impl.subfs;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import dk.apaq.vfs.Directory;
import dk.apaq.vfs.File;
import dk.apaq.vfs.FileSystem;
import dk.apaq.vfs.Node;
import dk.apaq.vfs.NodeFilter;

/**
 *
 * @author michael
 */
public class SubDirectory extends SubNode implements Directory{

    public SubDirectory(FileSystem fs, SubDirectory parent, Directory subbedDirectory) {
        super(fs, parent, subbedDirectory);
        this.subbedDirectory=subbedDirectory;
    }

    private Directory subbedDirectory;

    public Directory createDirectory(String name) throws IOException {
        return new SubDirectory(fs, this, subbedDirectory.createDirectory(name));
    }

    public File createFile(String name) throws IOException {
        return new SubFile(fs, this, subbedDirectory.createFile(name));
    }

    public boolean isRoot() {
        try {
            return getParent() == null;
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
            return new SubDirectory(fs, this, (Directory)node);
        else
            return new SubFile(fs, this, (File)node);
    }

    public File getFile(String name) throws FileNotFoundException {
        return new SubFile(fs, this, subbedDirectory.getFile(name));
    }

    public Directory getDirectory(String name) throws FileNotFoundException {
        return new SubDirectory(fs, this, subbedDirectory.getDirectory(name));
    }

    public File getFile(String name, boolean createIfNeeded) throws FileNotFoundException, IOException {
            return new SubFile(fs, this, subbedDirectory.getFile(name,createIfNeeded));
    }

    public Directory getDirectory(String name, boolean createIfNeeded) throws FileNotFoundException, IOException {
        return new SubDirectory(fs, this, subbedDirectory.getDirectory(name,createIfNeeded));
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
            subbedList.add(node.isDirectory()?new SubDirectory(fs, this, (Directory)node):new SubFile(fs, this,(File)node));
        }
        return subbedList;
    }

    public List<Directory> getDirectories(NodeFilter filter) {
        List<Directory> childList = subbedDirectory.getDirectories(filter);
        List<Directory> subbedList = new ArrayList<Directory>();

        for(Directory node:childList){
            subbedList.add(new SubDirectory(fs, this, node));
        }
        return subbedList;
    }

    public List<File> getFiles(NodeFilter filter) {
        List<File> childList = subbedDirectory.getFiles(filter);
        List<File> subbedList = new ArrayList<File>();

        for(File node:childList){
            subbedList.add(new SubFile(fs, this, node));
        }
        return subbedList;
    }

    public void delete(boolean recursive) throws IOException {
        if(isRoot()) {
            throw new IOException("Cannot delete root.");
        }
        subbedDirectory.delete(recursive);
    }

    public boolean isBundle() {
        return subbedDirectory.isBundle();
    }

}
