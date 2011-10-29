package dk.apaq.vfs.impl.subfs;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.util.Date;
import dk.apaq.vfs.Directory;
import dk.apaq.vfs.FileSystem;
import dk.apaq.vfs.Node;
import dk.apaq.vfs.Path;

/**
 *
 * @author michael
 */
public class SubNode implements Node{

    public SubNode(FileSystem fs, SubDirectory parent, Node subbedNode) {
        this.fs=fs;
        this.subbedNode=subbedNode;
        this.parent = parent;
    }

    protected FileSystem fs;
    private Node subbedNode;
    private SubDirectory parent;

    public void moveTo(Directory newParent) throws IOException {
        subbedNode.moveTo(newParent);
    }

    public void moveTo(Directory newParent, String newName) throws IOException {
        subbedNode.moveTo(newParent, newName);
    }

    public void setName(String name) throws IOException {
        subbedNode.setName(name);
    }

    public String getName() {
        return subbedNode.getName();
    }

    public String getBaseName() {
        return subbedNode.getBaseName();
    }

    public String getSuffix() {
        return subbedNode.getSuffix();
    }

    public Directory getParent() throws FileNotFoundException {
        return parent;
    }

    public FileSystem getFileSystem() {
        return fs;
    }

    public boolean isDirectory() {
        return subbedNode.isDirectory();
    }

    public boolean isFile() {
        return subbedNode.isFile();
    }

    public boolean isHidden() {
        return subbedNode.isHidden();
    }

    public Date getLastModified() {
        return subbedNode.getLastModified();
    }

    public void setLastModified(Date date) {
        subbedNode.setLastModified(date);
    }

    public int compareTo(Node node) {
        return subbedNode.compareTo(node);
    }

    public void delete() throws IOException {
        if(this instanceof SubDirectory && ((SubDirectory)this).isRoot()) {
            throw new IOException("Cannot delete root.");
        }
        subbedNode.delete();
    }

    public URI toUri() {
        return subbedNode.toUri();
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Node) {
            return equals((Node)o);
        }
        return super.equals(o);
    }

    
    public boolean equals(Node node) {
        return subbedNode.equals(((SubNode)node).subbedNode);
    }

    public Path getPath() {
        Path path = new Path();

        Node node = this;
        while(!node.isDirectory() || !((Directory)node).isRoot()){
            path.addLevel(0, node.getName());
            try{
                node=node.getParent();
            } catch(FileNotFoundException ex){
                //if this happens, the parent directy has been removed.
                //Actually - this file does not exist anymore.
                throw new RuntimeException("Cannot retrieve parent directory.");
            }
        }
        return path;
    }

    public boolean canRead() {
        return subbedNode.canRead();
    }

    public boolean canWrite() {
        return subbedNode.canWrite();
    }

    public int compareTo(Object o) {
        return subbedNode.compareTo(o);
    }

}
