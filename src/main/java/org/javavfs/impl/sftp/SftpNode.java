/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.javavfs.impl.sftp;

import ch.ethz.ssh2.SFTPv3DirectoryEntry;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.util.Date;
import java.util.Vector;
import org.javavfs.Directory;
import org.javavfs.FileSystem;
import org.javavfs.Node;
import org.javavfs.Path;

/**
 *
 * @author krog
 */
public class SftpNode implements Node {

    public SftpNode(SftpFileSystem fileSystem, Path path, SFTPv3DirectoryEntry entry) throws IOException {
        this.fileSystem=fileSystem;
        this.path=path;
        this.entry=entry;
    }

    protected SftpFileSystem fileSystem;
    protected Path path;
    protected SFTPv3DirectoryEntry entry;

    protected SFTPv3DirectoryEntry resolveChildEntry(String name) throws IOException{
        Path childPath = new Path(path,name);
        Vector<SFTPv3DirectoryEntry> entries = fileSystem.sftpc.ls(childPath.toString());
        if(entries.isEmpty())
            throw new FileNotFoundException("Unable to resolve child.");
        return entries.elementAt(0);
    }

    public void moveTo(Directory newParent) throws IOException {
        moveTo(newParent,getName());
    }

    public void moveTo(Directory newParent, String newName) throws IOException {
        fileSystem.sftpc.mv(path.toString(), new Path(newParent.getPath(),newName).toString());
    }

    public void setName(String name) throws IOException {
        Path parentPath = path.clone();
        parentPath.removeLevel(parentPath.getLevels()-1);
        fileSystem.sftpc.mv(path.toString(),new Path(parentPath,name).toString());
    }

    public String getName() {
        return path.getLevels()==0?"":path.getLeaf();
    }

    public String getBaseName() {
        String name = getName();
        if(name.contains("."))
            return name.substring(0,name.lastIndexOf("."));
        else
            return name;
    }

    public String getSuffix() {
        String name = getName();
        if(name.contains("."))
            return name.substring(name.lastIndexOf(".")+1);
        else
            return null;
    }

    public Directory getParent() throws FileNotFoundException {
        if(path.getLevels()==0)
            return null;

        try{
            Path parentPath = path.clone();
            parentPath.removeLevel(path.getLevels()-1);
            //TODO FIX
            return new SftpDirectory(fileSystem, parentPath,null);
        } catch(IOException ex){
            throw new FileNotFoundException("Unable to get parent. "+ex.getMessage());
        }
    }

    public FileSystem getFileSystem() {
        return fileSystem;
    }

    public boolean isRoot() {
        return path.getLevels()==0;
    }
    
    public boolean isDirectory() {
        return isRoot()?true:entry.attributes.isDirectory();
    }

    public boolean isFile() {
        return isRoot()?false:entry.attributes.isRegularFile();
    }

    public boolean isHidden() {
        return getName().startsWith(".");
    }

    public Date getLastModified() {
        return isRoot()?new Date(0):new Date(entry.attributes.mtime);
    }

    public void setLastModified(Date date) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int compareTo(Node node) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void delete() throws IOException {
        fileSystem.sftpc.rm(path.toString());
    }

    public URI toUri() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean equals(Node node) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Path getPath() {
        return path;
    }

    public boolean canRead() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean canWrite() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int compareTo(Object o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
