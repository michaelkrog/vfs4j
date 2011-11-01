package dk.apaq.vfs.impl.layered;

import dk.apaq.vfs.Directory;
import dk.apaq.vfs.File;
import dk.apaq.vfs.FileSystem;
import dk.apaq.vfs.Node;
import dk.apaq.vfs.NodeFilter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author michael
 */
public class LayeredDirectory extends LayeredNode implements Directory {

    private final List<Directory> layers;

    public LayeredDirectory(LayeredFileSystem fileSystem, LayeredDirectory parent, Directory wrappedNode) {
        super(fileSystem, parent, wrappedNode);
        layers = fileSystem.getLayers(getPath());
    }

    @Override
    public Directory createDirectory(String name) throws IOException {
        return new LayeredDirectory(fileSystem, this, ((Directory) wrappedNode).createDirectory(name));
    }

    @Override
    public File createFile(String name) throws IOException {
        return new LayeredFile(fileSystem, this, ((Directory) wrappedNode).createFile(name));
    }

    @Override
    public boolean isRoot() {
        return parent == null;
    }

    @Override
    public boolean hasChild(String name) {
        if (((Directory) wrappedNode).hasChild(name)) {
            return true;
        }

        for (Directory dir : layers) {
            if (dir.hasChild(name)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean hasFile(String name) {
        if (((Directory) wrappedNode).hasFile(name)) {
            return true;
        }

        for (Directory dir : layers) {
            if (dir.hasFile(name)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean hasDirectory(String name) {
        if (((Directory) wrappedNode).hasDirectory(name)) {
            return true;
        }

        for (Directory dir : layers) {
            if (dir.hasDirectory(name)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Node getChild(String name) throws FileNotFoundException {
        if (((Directory) wrappedNode).hasChild(name)) {
            Node node = ((Directory) wrappedNode).getChild(name);
            if (node.isDirectory()) {
                return new LayeredDirectory(fileSystem, this, (Directory) node);
            } else {
                return new LayeredFile(fileSystem, this, (File) node);
            }
        }

        for (Directory dir : layers) {
            if (dir.hasChild(name)) {
                Node node = dir.getChild(name);
                if (node.isDirectory()) {
                    return new LayeredDirectory(fileSystem, this, (Directory) node);
                } else {
                    return new LayeredFile(fileSystem, this, (File) node);
                }
            }
        }
        throw new FileNotFoundException("Node not found. [parent=" + this.getPath() + "; name=" + name + "]");
    }

    @Override
    public File getFile(String name) throws FileNotFoundException {
        if (((Directory) wrappedNode).hasFile(name)) {
            return new LayeredFile(fileSystem, this, ((Directory) wrappedNode).getFile(name));
        }

        for (Directory dir : layers) {
            if (dir.hasFile(name)) {
                return new LayeredFile(fileSystem, this, dir.getFile(name));
            }
        }
        throw new FileNotFoundException("File not found. [parent=" + this.getPath() + "; name=" + name + "]");
    }

    @Override
    public Directory getDirectory(String name) throws FileNotFoundException {
        if (((Directory) wrappedNode).hasDirectory(name)) {
            return new LayeredDirectory(fileSystem, this, ((Directory) wrappedNode).getDirectory(name));
        }

        for (Directory dir : layers) {
            if (dir.hasDirectory(name)) {
                return new LayeredDirectory(fileSystem, this, dir.getDirectory(name));
            }
        }
        throw new FileNotFoundException("Directory not found. [parent=" + this.getPath() + "; name=" + name + "]");
    }

    @Override
    public File getFile(String name, boolean createIfNeeded) throws FileNotFoundException, IOException {
        try {
            return getFile(name);
        } catch (FileNotFoundException ex) {
            if (createIfNeeded) {
                return createFile(name);
            } else {
                throw ex;
            }
        }
    }

    @Override
    public Directory getDirectory(String name, boolean createIfNeeded) throws FileNotFoundException, IOException {
        try {
            return getDirectory(name);
        } catch (FileNotFoundException ex) {
            if (createIfNeeded) {
                return createDirectory(name);
            } else {
                throw ex;
            }
        }
    }

    @Override
    public List<Node> getChildren() {
        return getChildren(null);

    }

    @Override
    public List<Directory> getDirectories() {
        return getDirectories(null);
    }

    @Override
    public List<File> getFiles() {
        return getFiles(null);
    }

    @Override
    public List<Node> getChildren(NodeFilter filter) {

        List<Node> children = ((Directory) wrappedNode).getChildren(filter);
        for (Directory dir : layers) {
            for (Node node : dir.getChildren(filter)) {
                for (Node existingNode : children) {
                    if (existingNode.getName().equals(node.getName())) {
                        break;
                    }
                }
                children.add(node);
                
            }
        }

        List<Node> layeredList = new ArrayList();
        for (Node current : children) {
            if (current.isDirectory()) {
                layeredList.add(new LayeredDirectory(fileSystem, this, (Directory) current));
            } else {
                layeredList.add(new LayeredFile(fileSystem, this, (File) current));
            }
        }
        return layeredList;
    }

    @Override
    public List<Directory> getDirectories(NodeFilter filter) {
        List<Directory> children = ((Directory) wrappedNode).getDirectories(filter);
        for (Directory dir : layers) {
            for (Directory node : dir.getDirectories(filter)) {
                for (Directory existingNode : children) {
                    if (existingNode.getName().equals(node.getName())) {
                        break;
                    }
                }
                children.add(node);
                
            }
        }

        List<Directory> layeredList = new ArrayList();
        for (Node current : children) {
            layeredList.add(new LayeredDirectory(fileSystem, this, (Directory) current));
        }
        return layeredList;
    }

    @Override
    public List<File> getFiles(NodeFilter filter) {
        List<File> children = ((Directory) wrappedNode).getFiles(filter);
        for (Directory dir : layers) {
            for (File node : dir.getFiles(filter)) {
                for (File existingNode : children) {
                    if (existingNode.getName().equals(node.getName())) {
                        break;
                    }
                }
                children.add(node);
                
            }
        }

        List<File> layeredList = new ArrayList();
        for (Node current : children) {
            layeredList.add(new LayeredFile(fileSystem, this, (File) current));
        }
        return layeredList;
    }

    @Override
    public void delete(boolean recursive) throws IOException {
        ((Directory)wrappedNode).delete(recursive);
    }

    @Override
    public boolean isBundle() {
        return ((Directory) wrappedNode).isBundle();
    }
}
