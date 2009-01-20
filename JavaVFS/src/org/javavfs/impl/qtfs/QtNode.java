/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.javavfs.impl.qtfs;

import com.trolltech.qt.core.QDir;
import com.trolltech.qt.core.QFile;
import com.trolltech.qt.core.QFileInfo;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.javavfs.Directory;
import org.javavfs.FileSystemSession;
import org.javavfs.Node;
import org.javavfs.Path;

/**
 *
 * @author michael
 */
public class QtNode implements Node{
    public QtNode(QtFileSystemSession session, QFileInfo file) {
        this.session=session;
        this.file=file;
    }
    
    protected QtFileSystemSession session;
    protected QFileInfo file;

    protected Principal getPrincipal(){
        return session.getPrincipal();
    }
    private void deleteNonRecursive(QFileInfo file) throws IOException{
        session.getFileSystem().getSecurity().checkWrite(getPrincipal(), this);
        
        QDir parentDir = file.dir();
        boolean deleted = parentDir.remove(file.fileName());
        if(!deleted && file.isDir())
            throw new IOException("Could not delete directory. Maybe because it is not empty.[path="+file.toString()+"]");
        else if(!deleted)
            throw new IOException("Could not delete file. [path="+file.toString()+"]");
    }
    
    private void deleteRecursive(QFileInfo file) throws IOException{
        
        if(!file.isDir())
            throw new IOException("A file cannot be deleted recursively.");
        
        if(file.exists()){
            
            QDir dir = file.absoluteDir();
            List<QFileInfo> files = dir.entryInfoList();
            for(QFileInfo currentFile:files){
                if(currentFile.isDir())
                    deleteRecursive(currentFile);
                else
                    deleteNonRecursive(currentFile);
            }
            deleteNonRecursive(file);
        }
    }
    
    public boolean isRoot(){
        try{
            return session.getRoot().equals(this);
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
        moveTo(newParent,file.fileName());
        session.getFileSystem().getSecurity().checkWrite(getPrincipal(), this);
        session.getFileSystem().getSecurity().checkWrite(getPrincipal(), newParent);
        
        Path destPath = newParent.getPath();
        destPath.addLevel(file.fileName());
        
        boolean moved = file.dir().rename(file.fileName(), destPath.toString());
        if(!moved)
            throw new IOException("Could not move the "+(file.isDir()?"directory":"file")+".");
    }

    public void moveTo(Directory newParent, String newName) throws IOException {
        session.getFileSystem().getSecurity().checkWrite(getPrincipal(), this);
        session.getFileSystem().getSecurity().checkWrite(getPrincipal(), newParent);
        
        Path destPath = newParent.getPath();
        destPath.addLevel(newName);
        
        boolean moved = file.dir().rename(file.fileName(), destPath.toString());
        if(!moved)
            throw new IOException("Could not move the "+(file.isDir()?"directory":"file")+".");
   }

    public void setName(String name) throws IOException {
        session.getFileSystem().getSecurity().checkWrite(getPrincipal(), this);
        
        boolean moved =  file.dir().rename(file.fileName(), name);
        if(!moved)
            throw new IOException("Could not rename the "+(file.isDir()?"directory":"file")+".");
    }

    public String getName() {
        return file.fileName();
    }

    public Directory getParent() throws FileNotFoundException {
        if(isRoot())
            return null;
        return new QtDirectory(session, new QFileInfo(file.dir().absolutePath()));
    }

    public FileSystemSession getFileSystem() {
        return session;
    }
    
 public boolean isDirectory() {
        return file.isDir();
    }

    public boolean isHidden() {
        return file.isHidden();
    }

    public Date getLastModified() {
        //QDateTime's toTime_t gives seconds since 1970 - makes it millis and create a Date object
        return new Date(file.lastModified().toTime_t()*1000);
    }

    public void setLastModified(Date date) {
        session.getFileSystem().getSecurity().checkWrite(getPrincipal(), this);
        /* noop */
    }

    public int compareTo(Node node) {
        return this.getName().compareTo(node.getName());
    }

    public void delete() throws IOException {
        delete(false);
    }

    public URI toUri() {
        
        try {
            return new URI("file://" + file.absolutePath());
        } catch (URISyntaxException ex) {
            Logger.getLogger(QtNode.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }

    public int compareTo(Object o) {
        if(o instanceof Node)
            return compareTo((Node)o);
        return 0;
    }

    public boolean equals(Node node) {
        return this.file.equals(((QtNode)node).file);
    }
    
    public boolean equals(Object object){
        if(object instanceof QtNode){
            return equals((QtNode)object);
        }
        return false;
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
        return file.isReadable();
    }

    public boolean canWrite() {
        return file.isWritable();
    }
}
