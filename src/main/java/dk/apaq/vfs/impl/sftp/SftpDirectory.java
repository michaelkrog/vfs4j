package dk.apaq.vfs.impl.sftp;

import ch.ethz.ssh2.SFTPv3DirectoryEntry;
import ch.ethz.ssh2.SFTPv3FileHandle;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import dk.apaq.vfs.Directory;
import dk.apaq.vfs.File;
import dk.apaq.vfs.Node;
import dk.apaq.vfs.NodeFilter;
import dk.apaq.vfs.Path;

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

    public Node getChild(final String name) throws FileNotFoundException {
        try {
            /*** THIS WOULD BE THE FASTEST METHOD BUT IT DOES NOT WORK ???? ***
            SFTPv3DirectoryEntry childEntry = resolveChildEntry(name);
            Path childPath = new Path(path,name);
            if (childEntry.attributes.isDirectory()) {
                return new SftpDirectory(fileSystem, childPath,childEntry);
            }
            if (childEntry.attributes.isRegularFile()) {
                return new SftpFile(fileSystem, childPath, childEntry);
            }
            throw new IOException("Unable to find a file or directory with the given name.");
             * */

            List<Node> nodes = getChildren(new NodeFilter(){

                public boolean accept(Node node) {
                    return name.equals(node.getName());
                }

            });

            if(nodes.isEmpty())
                throw new FileNotFoundException("Unable to find node.[name="+name+"]");
            return nodes.get(0);
        } catch (IOException ex) {
            throw new FileNotFoundException(ex.getMessage() + " (path="+new Path(path,name)+")");
        }
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
        List<Node> nodes = new ArrayList<Node>();
        try {
            Vector<SFTPv3DirectoryEntry> entries = fileSystem.sftpc.ls(path.toString());
            for(SFTPv3DirectoryEntry entry:entries){
                Node node = null;
                Path childPath = path.clone();
                childPath.addLevel(entry.filename);
                if(entry.attributes.isDirectory()){
                    node = new SftpDirectory(fileSystem, childPath, entry);
                }
                if(entry.attributes.isRegularFile()){
                    node = new SftpFile(fileSystem, childPath, entry);
                }
                if(filter==null || filter.accept(node))
                    nodes.add(node);
            }
            return nodes;
        } catch (IOException ex) {
            return nodes;
        }
        
    }

    public List<Directory> getDirectories(NodeFilter filter) {
        List<Directory> nodes = new ArrayList<Directory>();
        try {
            Vector<SFTPv3DirectoryEntry> entries = fileSystem.sftpc.ls(path.toString());
            for(SFTPv3DirectoryEntry entry:entries){
                Path childPath = path.clone();
                childPath.addLevel(entry.filename);
                if(entry.attributes.isRegularFile()){
                    continue;
                }

                Directory dir = new SftpDirectory(fileSystem, childPath, entry);
                if(filter==null || filter.accept(dir))
                    nodes.add(dir);
            }
            return nodes;
        } catch (IOException ex) {
            return nodes;
        }

        
    }

    public List<File> getFiles(NodeFilter filter) {
        List<File> nodes = new ArrayList<File>();
        try {
            Vector<SFTPv3DirectoryEntry> entries = fileSystem.sftpc.ls(path.toString());
            for(SFTPv3DirectoryEntry entry:entries){
                Path childPath = path.clone();
                childPath.addLevel(entry.filename);
                if(entry.attributes.isDirectory()){
                    continue;
                }

                File file = new SftpFile(fileSystem, childPath, entry);
                if(filter==null || filter.accept(file))
                    nodes.add(file);
            }
            return nodes;
        } catch (IOException ex) {
            return nodes;
        }

    }

    public void delete(boolean recursive) throws IOException {
        fileSystem.sftpc.rmdir(path.toString());
    }

    public boolean isBundle() {
        return getName().contains(".");
    }

}
