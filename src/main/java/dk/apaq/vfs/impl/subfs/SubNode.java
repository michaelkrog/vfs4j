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

    public SubNode(FileSystem fs, Node subbedNode) {
        this.fs=fs;
        this.subbedNode=subbedNode;
    }

    protected FileSystem fs;
    private Node subbedNode;

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
        throw new UnsupportedOperationException("Not supported yet.");
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
        subbedNode.delete();
    }

    public URI toUri() {
        return subbedNode.toUri();
    }

    public boolean equals(Node node) {
        return subbedNode.equals(node);
    }

    public Path getPath() {
        Path path = new Path();

        Node node = this;
        while(!node.isDirectory() || !((Directory)node).isRoot()){
            path.addLevel(0, node.getName());
            try{
                node=getParent();
            } catch(FileNotFoundException ex){
                //if this happens, the parent directy has been removed.
                //Actually - this file does not exist anymote.
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
