/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.javavfs.nativefs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.util.Date;
import org.javavfs.Directory;
import org.javavfs.FileSystem;
import org.javavfs.Node;

/**
 *
 * @author mzk
 */
public abstract class NativeNode implements Node{

    public NativeNode(NativeFileSystem filesystem, java.io.File file) {
        this.filesystem=filesystem;
        this.file=file;
    }
    
    protected NativeFileSystem filesystem;
    protected java.io.File file;

    private void deleteNonRecursive(File file) throws IOException{
        filesystem.getSecurity().checkWrite(this);
        
        boolean deleted = file.delete();
        if(!deleted && file.isDirectory())
            throw new IOException("Could not delete directory. Maybe because it is not empty.[path="+file.toString()+"]");
        else if(!deleted)
            throw new IOException("Could not delete file. [path="+file.toString()+"]");
    }
    
    private void deleteRecursive(File file) throws IOException{
        if(!file.isDirectory())
            throw new IOException("A file cannot be deleted recursively.");
        
        if(file.exists()){
            File[] files = file.listFiles();
            for(File currentFile:files){
                if(currentFile.isDirectory())
                    deleteRecursive(currentFile);
                else
                    deleteNonRecursive(currentFile);
            }
            deleteNonRecursive(file);
        }
    }
    
    public boolean isRoot(){
        try{
            return filesystem.getRoot().equals(this);
        } catch(FileNotFoundException ex){
            
        }
        return false;
    }
    
    public void delete(boolean recursive) throws IOException {
        if(isRoot())
            throw new IOException("Cannot delete root folder.");
        
        if(recursive)
            deleteRecursive(file);
        else
            deleteNonRecursive(file);
        
    }

    public void moveTo(Directory newParent) throws IOException {
        filesystem.getSecurity().checkWrite(this);
        filesystem.getSecurity().checkWrite(newParent);
        
        java.io.File newParentFile = new java.io.File(newParent.toUri());
        java.io.File newDestination = new java.io.File(newParentFile,file.getName());
        boolean moved = file.renameTo(newDestination);
        if(!moved)
            throw new IOException("Could not move the "+(file.isDirectory()?"directory":"file")+".");
    }

    public void moveTo(Directory newParent, String newName) throws IOException {
        filesystem.getSecurity().checkWrite(this);
        filesystem.getSecurity().checkWrite(newParent);
        
        java.io.File newParentFile = new java.io.File(newParent.toUri());
        java.io.File newDestination = new java.io.File(newParentFile,newName);
        boolean moved = file.renameTo(newDestination);
        if(!moved)
            throw new IOException("Could not move the "+(file.isDirectory()?"directory":"file")+".");
    }

    public void setName(String name) throws IOException {
        filesystem.getSecurity().checkWrite(this);
        
        java.io.File newDestination = new java.io.File(file.getParentFile(),name);
        boolean moved = file.renameTo(newDestination);
        if(!moved)
            throw new IOException("Could not rename the "+(file.isDirectory()?"directory":"file")+".");
    }

    public String getName() {
        return file.getName();
    }

    public Directory getParent() throws FileNotFoundException {
        if(isRoot())
            return null;
        return new NativeDirectory(filesystem, file.getParentFile());
    }

    public FileSystem getFileSystem() {
        return filesystem;
    }
    
 public boolean isDirectory() {
        return file.isDirectory();
    }

    public boolean isHidden() {
        return file.isHidden();
    }

    public Date getLastModified() {
        return new Date(file.lastModified());
    }

    public void setLastModified(Date date) {
        filesystem.getSecurity().checkWrite(this);
        file.setLastModified(date.getTime());
    }

    public int compareTo(Node node) {
        return this.getName().compareTo(node.getName());
    }

    public void delete() throws IOException {
        delete(false);
    }

    public URI toUri() {
        return file.toURI();
    }

    public int compareTo(Object o) {
        if(o instanceof Node)
            return compareTo((Node)o);
        return 0;
    }

    public boolean equals(Node node) {
        return this.file.equals(((NativeNode)node).file);
    }
    
    public boolean equals(Object object){
        if(object instanceof NativeNode){
            return equals((NativeNode)object);
        }
        return false;
    }

    public String getPath() {
        return this.file.getPath();
    }

    public boolean canRead() {
        return filesystem.getSecurity().canRead(this) && file.canRead();
    }

    public boolean canWrite() {
        return filesystem.getSecurity().canWrite(this) && file.canWrite();
    }

    

}
