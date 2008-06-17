/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.javavfs;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.util.Date;

/**
 *
 * @author mzk
 */
public interface Node extends Comparable {

    /**
     * Moves this node to a new directory.
     * @param newParent The directory the node should be moved to.
     * @throws java.io.IOException Thrown if an error occurs while the node is being moved.
     */
    public void moveTo(Directory newParent) throws IOException;
    
    /**
     * Moves this node to a new directory and renames it.
     * @param newParent The directory the node should be moved to.
     * @param newName The new name of the node.
     * @throws java.io.IOException Thrown if an error occurs while the node is being moved or renamed.
     */
    public void moveTo(Directory newParent, String newName) throws IOException;
    
    /**
     * Renames the node.
     * @param name The new name of the node.
     * @throws java.io.IOException Thrown if an error occurs while renaming the node.
     */
    public void setName(String name) throws IOException;
    
    /**
     * Retrieves the name of the node.
     * @return
     */
    public String getName();
    
    /**
     * Retrieves the parent directory of this node.
     * @return The parent directory.
     * @throws java.io.FileNotFoundException Thrown if parent directory could not be found - would normally only occur if called on the root directory.
     */
    public Directory getParent() throws FileNotFoundException;
    
    /**
     * Retrieves the filesystem which this node belongs to.
     * @return The filesystem.
     */
    public FileSystem getFileSystem();
    
    /**
     * Checks wether this node is a directory.
     * @return True/False wether this node is a directory.
     */
    public boolean isDirectory();
    
    /**
     * Checks wether this node is hidden.
     * @return True/False if this node is hidden.
     */
    public boolean isHidden();
    
    /**
     * Retrieves the date for last modification.
     * @return The date for alst modification.
     */
    public Date getLastModified();
    
    /**
     * Sets the date for last modification.
     * @param date The date.
     */
    public void setLastModified(Date date);
    
    /**
     * Compare this node to another node by comparing their names.
     * @param node The node to compare with.
     * @return The returnvalue of the comparison of the to nodes names.
     */
    public int compareTo(Node node);
    
    /**
     * Deletes the node.
     * @throws java.io.IOException Thrown if an error occurs during deletion.
     */
    public void delete() throws IOException;
    
    public URI toUri();
    
    public boolean equals(Node node);
    
    public String getPath();
    
    /**
     * Retrieves information about wether access to read is allowed. It should also check via the native filesystem if possible.
     * @return True/False
     */
    public boolean canRead();
    
    /**
     * Retrieves information about wether access to read is allowed. It should also check via the native filesystem if possible.
     * @return True/False
     */
    public boolean canWrite();
}
