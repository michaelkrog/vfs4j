package dk.apaq.vfs.impl.cifs;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;
import dk.apaq.vfs.Directory;
import dk.apaq.vfs.File;
import dk.apaq.vfs.Node;
import dk.apaq.vfs.NodeFilter;

/**
 *
 * @author mzk
 */
public class CifsDirectory extends CifsNode implements Directory{

    public CifsDirectory(CifsFileSystem filesystem, SmbFile file) {
        super(filesystem, file);
    }

    public CifsDirectory(CifsFileSystem filesystem, String url) throws MalformedURLException {
        super(filesystem, url);
    }

    public Directory createDirectory(String name) throws IOException {
        //Slash is needed behind the name for directories
        SmbFile newDir = new SmbFile(innerFile,name+"/");
        newDir.mkdir();
        return new CifsDirectory(filesystem, newDir);
    }

    public File createFile(String name) throws IOException {
        SmbFile newFile = new SmbFile(innerFile,name);
        newFile.createNewFile();
        return new CifsFile(filesystem, newFile);
    }

    public boolean hasChild(String name) {
        try {
            SmbFile newFile = new SmbFile(innerFile, name);
            return newFile.exists();
        } catch (SmbException ex) {
            Logger.getLogger(CifsDirectory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(CifsDirectory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(CifsDirectory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean hasFile(String name) {
        try {
            SmbFile newFile = new SmbFile(innerFile, name);
            return newFile.exists() && newFile.isFile();
        } catch (SmbException ex) {
            Logger.getLogger(CifsDirectory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(CifsDirectory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(CifsDirectory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean hasDirectory(String name) {
        try {
            SmbFile newFile = new SmbFile(innerFile, name);
            return newFile.exists() && newFile.isDirectory();
        } catch (SmbException ex) {
            Logger.getLogger(CifsDirectory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(CifsDirectory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(CifsDirectory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public Node getChild(String name) throws FileNotFoundException {
        try {
            SmbFile[] files = innerFile.listFiles();
            for(SmbFile file:files)
                if(file.getName().equals(name)){
                    SmbFile newFile = new SmbFile(innerFile, name);
                    if (newFile.isFile()) {
                        return new CifsFile(filesystem, newFile);
                    } else {
                        return new CifsDirectory(filesystem, newFile);
                    }
                }
            throw new FileNotFoundException("File not found in the directory.");
        } catch (SmbException ex) {
            throw new FileNotFoundException(ex.getMessage());
        } catch (MalformedURLException ex) {
            throw new FileNotFoundException(ex.getMessage());
        } catch (UnknownHostException ex) {
            throw new FileNotFoundException(ex.getMessage());
        }
        
    }

    public File getFile(String name) throws FileNotFoundException {
        Node node = getChild(name);
        if(node.isDirectory())
            throw new FileNotFoundException("The node is not a file, but a directory.");
        return (File)node;
    }

    public Directory getDirectory(String name) throws FileNotFoundException {
         try{
            SmbFile newfile = new SmbFile(innerFile,name+"/");
            if(!newfile.exists())
                throw new FileNotFoundException("File not found.");
            return new CifsDirectory(filesystem, newfile);
         } catch (SmbException ex) {
            throw new FileNotFoundException(ex.getMessage());
        } catch (UnknownHostException ex) {
            throw new FileNotFoundException(ex.getMessage());
         } catch(MalformedURLException ex){
            throw new FileNotFoundException(ex.getMessage());
         }
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
            return getDirectory(name);
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
        try {
            SmbFile[] files;

            if (filter == null) {
                files = innerFile.listFiles();
            } else {
                files = innerFile.listFiles(new SmbFilterWrapper(filesystem, filter));
            }
            ArrayList<Node> nodes = new ArrayList<Node>();

            for (SmbFile file : files) {
                Node node;
                if (file.isDirectory()) {
                    node = new CifsDirectory(filesystem, file);
                } else {
                    node = new CifsFile(filesystem, file);
                }
	        nodes.add(node);
                
            }
            return nodes;
        } catch (SmbException ex) {
            Logger.getLogger(CifsDirectory.class.getName()).log(Level.SEVERE, null, ex);
            return new ArrayList<Node>();
        }
    }

    public List<Directory> getDirectories(NodeFilter filter) {
        try {
            SmbFile[] files;

            if (filter == null) {
                files = innerFile.listFiles();
            } else {
                files = innerFile.listFiles(new SmbFilterWrapper(filesystem, filter));
            }
            ArrayList<Directory> nodes = new ArrayList<Directory>();

            for (SmbFile file : files) {
                Node node;
                if (file.isDirectory()) {
                    node = new CifsDirectory(filesystem, file);
                    nodes.add((Directory)node);
                
                }	

                
            }
            return nodes;
        } catch (SmbException ex) {
            Logger.getLogger(CifsDirectory.class.getName()).log(Level.SEVERE, null, ex);
            return new ArrayList<Directory>();
        }
    }

    public List<File> getFiles(NodeFilter filter) {
        try {
            SmbFile[] files;

            if (filter == null) {
                files = innerFile.listFiles();
            } else {
                files = innerFile.listFiles(new SmbFilterWrapper(filesystem, filter));
            }
            ArrayList<File> nodes = new ArrayList<File>();

            for (SmbFile file : files) {
                Node node;
                if (!file.isDirectory()) {
                    node = new CifsFile(filesystem, file);
                    nodes.add((File)node);
                
                }

                
            }
            return nodes;
        } catch (SmbException ex) {
            Logger.getLogger(CifsDirectory.class.getName()).log(Level.SEVERE, null, ex);
            return new ArrayList<File>();
        }
    }

    public void delete(boolean recursive) throws IOException {
        if(isRoot())
            throw new IOException("Cannot delete root.");
        
        if(!recursive)
            innerFile.delete();
        else
            deleteRecursive(innerFile);
    }

    public boolean isBundle() {
        return innerFile.getName().contains(".");
    }
    


    

}
