package dk.apaq.vfs.impl.ram;

import dk.apaq.vfs.Directory;
import dk.apaq.vfs.File;
import dk.apaq.vfs.Node;
import dk.apaq.vfs.NodeFilter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class RamDirectory extends RamNode implements Directory {

    private final List<RamNode> children = new ArrayList<RamNode>();

    public RamDirectory(RamDirectory parent, String name) {
        super(parent, name);
    }


    public Directory createDirectory(String name) throws IOException {
        if(hasChild(name)) {
            throw new IOException("A child with then name '"+name+"' already exists.");
        }

        RamDirectory file = new RamDirectory(this, name);
        children.add(file);
        return file;
    }

    public File createFile(String name) throws IOException {
        if(hasChild(name)) {
            throw new IOException("A child with then name '"+name+"' already exists.");
        }

        RamFile file = new RamFile(this, name);
        children.add(file);
        return file;
    }

    public boolean isRoot() {
        return parent==null;
    }

    public boolean hasChild(String name) {
        try{
            getChild(name);
            return true;
        } catch(FileNotFoundException ex) {
            return false;
        }
    }

    public boolean hasFile(String name) {
        try{
            getFile(name);
            return true;
        } catch(FileNotFoundException ex) {
            return false;
        }
    }

    public boolean hasDirectory(String name) {
        try{
            getDirectory(name);
            return true;
        } catch(FileNotFoundException ex) {
            return false;
        }
    }

    public Node getChild(String name) throws FileNotFoundException {
        for(RamNode node : children) {
            if(name.equals(node.getName())) {
                return node;
            }
        }

        throw new FileNotFoundException(name + " not found.");
    }

    public File getFile(String name) throws FileNotFoundException {
        Node node = getChild(name);

        if(!node.isFile()) {
            throw new FileNotFoundException("Node found but was not a file.");
        }

        return (File) node;
    }

    public Directory getDirectory(String name) throws FileNotFoundException {
        Node node = getChild(name);

        if(!node.isDirectory()) {
            throw new FileNotFoundException("Node found but was not a directory.");
        }

        return (Directory) node;
    }

    public File getFile(String name, boolean createIfNeeded) throws FileNotFoundException, IOException {
        try{
            return getFile(name);
        } catch(FileNotFoundException ex) {
             if(createIfNeeded) {
                return createFile(name);
            } else {
                 throw ex;
            }
        }
    }

    public Directory getDirectory(String name, boolean createIfNeeded) throws FileNotFoundException, IOException {
        try{
            return getDirectory(name);
        } catch(FileNotFoundException ex) {
             if(createIfNeeded) {
                return createDirectory(name);
            } else {
                 throw ex;
            }
        }
    }

    public List<Node> getChildren() {
        List<Node> nodes = new ArrayList<Node>(children);
        return nodes;
    }

    protected List<RamNode> getChildrenUnwrapped() {
        return children;
    }

    public List<Directory> getDirectories() {
        return getDirectories(null);
    }

    public List<Directory> getDirectories(NodeFilter filter) {
        List<Directory> nodes = new ArrayList<Directory>();
        for(Node node : children) {
            if(node.isDirectory() && (filter==null || filter.accept(node))) {
                nodes.add((Directory) node);
            }
        }
        return nodes;
    }

    public List<File> getFiles() {
        List<File> nodes = new ArrayList<File>();
        for(Node node : children) {
            if(node.isFile()) {
                nodes.add((File) node);
            }
        }
        return nodes;
    }

    public List<Node> getChildren(NodeFilter filter) {
        List<Node> nodes = new ArrayList<Node>();
        for(Node node : children) {
            if(filter==null || filter.accept(node)) {
                nodes.add(node);
            }
        }
        return nodes;
    }



    public List<File> getFiles(NodeFilter filter) {
        List<File> nodes = new ArrayList<File>();
        for(Node node : children) {
            if(node.isFile() && (filter==null || filter.accept(node))) {
                nodes.add((File) node);
            }
        }
        return nodes;
    }

    public void delete(boolean recursive) throws IOException {
        if(isRoot()) {
            throw new IOException("Cannot delete root.");
        }
        
        if(!recursive && !getChildren().isEmpty()) {
            throw new IOException("Cannot delete directory beause it has children and deletion was not recursive.");
        }
        parent.getChildrenUnwrapped().remove(this);
    }

    public boolean isBundle() {
        return getName().contains(".");
    }

}
