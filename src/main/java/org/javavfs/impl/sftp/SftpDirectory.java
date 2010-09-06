/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.javavfs.impl.sftp;

import ch.ethz.ssh2.SFTPv3DirectoryEntry;
import ch.ethz.ssh2.SFTPv3FileHandle;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import org.javavfs.Directory;
import org.javavfs.File;
import org.javavfs.Node;
import org.javavfs.NodeFilter;
import org.javavfs.Path;

/**
 *
 * @author krog
 */
public class SftpDirectory extends SftpNode implements Directory{

    public SftpDirectory(SftpFileSystem fileSystem, Path path, SFTPv3DirectoryEntry entry) throws IOException {
        super(fileSystem,path, entry);
    }


    public Directory createDirectory(String name) throws IOException {
        fileSystem.sftpc.mkdir(name, 0x0700);
        SFTPv3DirectoryEntry entry = resolveChildEntry(name);
        return new SftpDirectory(fileSystem, new Path(path,name), entry);
    }

    public File createFile(String name) throws IOException {
        SFTPv3FileHandle fh = fileSystem.sftpc.createFile(name);
        fileSystem.sftpc.closeFile(fh);

        SFTPv3DirectoryEntry entry = resolveChildEntry(name);
        return new SftpFile(fileSystem, new Path(path,name), entry);
    }

    public boolean isRoot() {
        return path.getLevels()==0;
    }

    public boolean hasChild(String name) {
        try {
            getChild(name);
            return true;
        } catch (FileNotFoundException ex) {
            return false;
        }

    }

    public boolean hasFile(String name) {
        try {
            getFile(name);
            return true;
        } catch (FileNotFoundException ex) {
            return false;
        }
    }

    public boolean hasDirectory(String name) {
        try {
            getDirectory(name);
            return true;
        } catch (FileNotFoundException ex) {
            return false;
        }
    }

    public Node getChild(String name) throws FileNotFoundException {
        //TODO
        return null;
    }

    public File getFile(String name) throws FileNotFoundException {
        Node node = getChild(name);
        if(!node.isFile())
            throw new FileNotFoundException("Node found but it was not a file.");
        return (File)node;
    }

    public Directory getDirectory(String name) throws FileNotFoundException {
        Node node = getChild(name);
        if(!node.isDirectory())
            throw new FileNotFoundException("Node found but it was not a directory.");
        return (Directory)node;
    }

    public File getFile(String name, boolean createIfNeeded) throws FileNotFoundException, IOException {
        File file;
        try{
            file = getFile(name);
        } catch(FileNotFoundException ex){
            if(createIfNeeded)
                file= createFile(name);
            else
                throw ex;
        }
        return file;
    }

    public Directory getDirectory(String name, boolean createIfNeeded) throws FileNotFoundException, IOException {
        Directory dir;
        try{
            dir = getDirectory(name);
        } catch(FileNotFoundException ex){
            if(createIfNeeded)
                dir= createDirectory(name);
            else
                throw ex;
        }
        return dir;
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
        //TODO
        return null;
        
    }

    public List<Directory> getDirectories(NodeFilter filter) {
        //TODO
        return null;
        
    }

    public List<File> getFiles(NodeFilter filter) {
        //TODO
        return null;
        
    }

    public void delete(boolean recursive) throws IOException {
        fileSystem.sftpc.rmdir(path.toString());
    }

    public boolean isBundle() {
        return getName().contains(".");
    }

}
