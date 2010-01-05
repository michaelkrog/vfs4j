/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.javavfs.impl.nativefs;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.javavfs.Directory;
import org.javavfs.File;
import org.javavfs.Node;
import org.javavfs.NodeFilter;

/**
 *
 * @author mzk
 */
public class NativeDirectory extends NativeNode implements Directory{

    public NativeDirectory(NativeFileSystem fs, java.io.File file) throws FileNotFoundException {
        super(fs,file);
        if(!file.isDirectory())
            throw new FileNotFoundException("The fileobject must point at an existing directory.");
        
        
    }

    public Directory createDirectory(String name) throws IOException {
            
        java.io.File newDir = new java.io.File(file,name);
        if(newDir.isFile())
            throw new IOException("A file with that name already exists. [uri="+newDir.toURI()+"]");
        
        if(newDir.isDirectory())
            throw new IOException("A directory with that name already exists. [uri="+newDir.toURI()+"]");
        
        boolean created = newDir.mkdir();
        if(!created)
            throw new IOException("Could not create folder.");
        
        return new NativeDirectory(fs, newDir);
    }

    public org.javavfs.File createFile(String name) throws IOException {
            
        java.io.File newFile = new java.io.File(file, name);

        boolean created = newFile.createNewFile();
        if(created)
            return new NativeFile(fs, newFile);
        else
            throw new IOException("File already exists. Cannot be created.");
    }

    public List<Node> getChildren() {
        return getChildren(null);
    }

    public List<Directory> getDirectories() {
        return getDirectories(null);
    }

    public List<org.javavfs.File> getFiles() {
        return getFiles(null);
    }

    public List<Node> getChildren(NodeFilter filter) 
    {
            
        java.io.File[] files = file.listFiles();
        ArrayList<Node> nodes = new ArrayList<Node>();
        
        for(java.io.File file:files){
            try {
                Node node;
                if (file.isDirectory()) {
                    node = new NativeDirectory(fs, file);
                } else {
                    node = new NativeFile(fs, file);
                }
                if (filter == null || filter.accept(node)) {
                    nodes.add(node);
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(NativeDirectory.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return nodes;
    }

    public List<Directory> getDirectories(NodeFilter filter) {
            
        java.io.File[] files = file.listFiles();
        ArrayList<Directory> nodes = new ArrayList<Directory>();
        
        for(java.io.File file:files){
            Directory node;
            if(file.isDirectory()){
                try {
                    node = new NativeDirectory(fs, file);

                    if (filter == null || filter.accept(node)) {
                        nodes.add(node);
                    }
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(NativeDirectory.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return nodes;
    }

    public List<File> getFiles(NodeFilter filter) {
            
        java.io.File[] files = file.listFiles();
        ArrayList<File> nodes = new ArrayList<File>();
        
        for(java.io.File file:files){
            File node;
            if(!file.isDirectory()){
                try {
                    node = new NativeFile(fs, file);

                    if (filter == null || filter.accept(node)) {
                        nodes.add(node);
                    }
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(NativeDirectory.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return nodes;
    }

    public boolean hasChild(String name) {
            
        java.io.File file = new java.io.File(this.file,name);
        return file.exists();
    }

    public boolean hasFile(String name) {
            
        java.io.File file = new java.io.File(this.file,name);
        return file.isFile();
    }

    public boolean hasDirectory(String name) {
            
        java.io.File file = new java.io.File(this.file,name);
        return file.isDirectory();
    }

    public Node getChild(String name) throws FileNotFoundException {
            
        Node node;
        java.io.File file = new java.io.File(super.file,name);
        if(!file.exists())
            throw new FileNotFoundException("The file does not exist. [uri="+file.toURI()+"]");
        
        if(file.isDirectory())
            node = new NativeDirectory(fs, file);
        else
            node = new NativeFile(fs, file);
            
        return node;
    }

    public File getFile(String name) throws FileNotFoundException {
        Node node = getChild(name);
        if(node.isDirectory())
            throw new FileNotFoundException("The uri is not a file, but a directory. [uri="+node.toUri()+"]");
        return (File)node;
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

    public Directory getDirectory(String name) throws FileNotFoundException {
        Node node = getChild(name);
        if(!node.isDirectory())
            throw new FileNotFoundException("The uri is not a directory, but a file. [uri="+node.toUri()+"]");
        return (Directory)node;
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

    public boolean isBundle() {
        return file.getName().contains(".");
    }
}
