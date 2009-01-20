/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.javavfs;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author mzk
 */
public interface Directory extends Node {
    /**
     * Creates a new directory.
     * @param name The name of the new directory.
     * @return Returns the new directory.
     * @throws java.io.IOException Throws if an error occurs while creating the directory.
     */
    public Directory createDirectory(String name) throws IOException;
    
    /**
     * Creates a new File.
     * @param name The name of the new file.
     * @return Returns the new file.
     * @throws java.io.IOException Thrown if an error occurs while creating the new file.
     */
    public File createFile(String name) throws IOException;
    
    /**
     * Checks wether this directory is the root of the filesystem.
     * @return True/False wether the directory is the root.
     */
    public boolean isRoot();
    
    /**
     * Checks wether the directory contains a child(File of Directory) with the given name.
     * @param name The name of the child.
     * @return True/False wether the directory contains a child with the given name.
     */
    public boolean hasChild(String name);
    
    /**
     * Checks wether the directory contains a file with the given name.
     * @param name The name of the file.
     * @return True/False wether the directory contains a file with the given name. 
     *         If the directory holds a directory with the given name, the method returns false.
     */
    public boolean hasFile(String name);
    
    /**
     * Checks wether the directory contains a directory with the given name.
     * @param name The name of the directory.
     * @return True/False wether the directory contains a directory with the given name. 
     *         If the directory holds a file with the given name, the method returns false.
     */
    public boolean hasDirectory(String name);
    
    /**
     * Retrieves a child with the given name.
     * @param name The name of the child to retrieve.
     * @return Returns the child as a Node. 
     * @throws java.io.FileNotFoundException Thrown if no child with the given name exists.
     */
    public Node getChild(String name) throws FileNotFoundException;
    
    /**
     * Retrieves a file with the given name.
     * @param name The name of the file to retrieve.
     * @return Returns the file. 
     * @throws java.io.FileNotFoundException Thrown if no file with the given name exists or the child with the given name is a directory.
     */
    public File getFile(String name) throws FileNotFoundException;
    
    /**
     * Retrieves a directory with the given name.
     * @param name The name of the directory to retrieve.
     * @return Returns the directory. 
     * @throws java.io.FileNotFoundException Thrown if no directory with the given name exists or the child with the given name is a file.
     */
    public Directory getDirectory(String name) throws FileNotFoundException;
    
    /**
     * Retrieves a file with the given name.
     * @param name The name of the file to retrieve.
     * @param createIfNeeded Wether the file should be created if it does not exist.
     * @return Returns the file. 
     * @throws java.io.FileNotFoundException Thrown if no file with the given name exists or the child with the given name is a directory.
     * @throws java.io.IOException Thrown if an error occurs while creating file.
     */
    public File getFile(String name, boolean createIfNeeded) throws FileNotFoundException, IOException;
    
    /**
     * Retrieves a directory with the given name.
     * @param name The name of the directory to retrieve.
     * @param createIfNeeded Wether the directory should be created if it does not exist.
     * @return Returns the directory. 
     * @throws java.io.FileNotFoundException Thrown if no directory with the given name exists or the child with the given name is a file.
     * @throws java.io.IOException Thrown if an error occurs while creating directory.
     */
    public Directory getDirectory(String name, boolean createIfNeeded) throws FileNotFoundException, IOException;
    
    /**
     * Retrieves a list of all children in the directory.
     * @return The list of all children.
     */
    public List<Node> getChildren();
    
    /**
     * Retrieves a list of all directories in the directory.
     * @return The list of directories.
     */
    public List<Directory> getDirectories();
    
    /**
     * Retrieves a list of all files in the directory.
     * @return The list of files.
     */
    public List<File> getFiles();
    
    /**
     * Retrieves a list of children in the directory.
     * @param filer The filter to use for listing children or null to list all children.
     * @return The list of children.
     */
    public List<Node> getChildren(NodeFilter filter);
    
    /**
     * Retrieves a list of directories in the directory.
     * @param filer The filter to use for listing directories or null to list all directories.
     * @return The list of directories.
     */
    public List<Directory> getDirectories(NodeFilter filter);
    
    /**
     * Retrieves a list of files in the directory.
     * @param filer The filter to use for listing files or null to list all files.
     * @return The list of files.
     */
    public List<File> getFiles(NodeFilter filter);
    
    /**
     * Deletes the current directory.
     * @param recursive Wether the deletion should be recursive.
     * @throws java.io.IOException Thrown if an error occurs during deletion.
     */
    public void delete(boolean recursive) throws IOException;

    /**
     * Wether this directory is a bundle or not. A bundle is a normal directory,
     * but its contents is not intended to be shown in a GUI. It can be treated kinda
     * like a ZIP-file(a single unit containing files and directories) except this is
     * not really packed in anyway. It is only a logical boundary.
     *
     * Bundles are identied by the directory name having a suffix. Mac OS uses
     * this a lot for Applications etc. (Safari.app)
     * @return Wether its a bundle.
     */
    public boolean isBundle();
    
}
