package dk.apaq.vfs.impl.layered;

import dk.apaq.vfs.Directory;
import dk.apaq.vfs.FileSystem;
import dk.apaq.vfs.Node;
import dk.apaq.vfs.Path;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.util.Date;

/**
 *
 * @author michael
 */
public abstract class LayeredNode implements Node {

    protected final LayeredFileSystem fileSystem;
    protected final LayeredDirectory parent;
    protected final Node wrappedNode;

    public LayeredNode(LayeredFileSystem fileSystem, LayeredDirectory parent, Node wrappedNode) {
        this.fileSystem = fileSystem;
        this.wrappedNode = wrappedNode;
        this.parent = parent;
    }
    
    @Override
    public void moveTo(Directory newParent) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void moveTo(Directory newParent, String newName) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setName(String name) throws IOException {
        wrappedNode.setName(name);
    }

    @Override
    public String getName() {
        return wrappedNode.getName();
    }

    @Override
    public String getBaseName() {
        return wrappedNode.getBaseName();
    }

    @Override
    public String getSuffix() {
        return wrappedNode.getSuffix();
    }

    @Override
    public Directory getParent() throws FileNotFoundException {
        return parent;
    }

    @Override
    public FileSystem getFileSystem() {
        return fileSystem;
    }

    @Override
    public boolean isDirectory() {
        return wrappedNode.isDirectory();
    }

    @Override
    public boolean isFile() {
        return wrappedNode.isFile();
    }

    @Override
    public boolean isHidden() {
        return wrappedNode.isHidden();
    }

    @Override
    public Date getLastModified() {
        return wrappedNode.getLastModified();
    }

    @Override
    public void setLastModified(Date date) {
        wrappedNode.setLastModified(date);
    }

    @Override
    public int compareTo(Node node) {
        return wrappedNode.compareTo(node);
    }

    @Override
    public void delete() throws IOException {
        wrappedNode.delete();
    }

    @Override
    public URI toUri() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean equals(Node node) {
        if(node instanceof LayeredNode) {
            return wrappedNode.equals(((LayeredNode)node).wrappedNode);
        }
        return false;
    }
    
    public boolean equals(Object object){
        if(object instanceof LayeredNode){
            return equals((LayeredNode)object);
        }
        return false;
    }

    @Override
    public Path getPath() {
        Path path = new Path();

        Node node = this;
        while(!node.isDirectory() || !((Directory)node).isRoot()){
            path.addLevel(0, node.getName());
            try{
                node=node.getParent();
            } catch(FileNotFoundException ex){
                //if this happens, the parent directy has been removed.
                //Actually - this file does not exist anymote.
                throw new RuntimeException("Cannot retrieve parent directory.");
            }
        }
        return path;
    }

    @Override
    public boolean canRead() {
        return wrappedNode.canRead();
    }

    @Override
    public boolean canWrite() {
        return wrappedNode.canWrite();
    }

    @Override
    public int compareTo(Object t) {
        return wrappedNode.compareTo(t);
    }
    
}
