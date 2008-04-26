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

    public void moveTo(Directory newParent) throws IOException;
    public void moveTo(Directory newParent, String newName) throws IOException;
    public void setName(String name) throws IOException;
    public String getName();
    public Directory getParent() throws FileNotFoundException;
    public FileSystem getFileSystem();
    public boolean isDirectory();
    public boolean isHidden();
    public Date getLastModified();
    public void setLastModified(Date date);
    
    public int compareTo(Node node);
    
    public void delete() throws IOException;
    
    public URI toUri();
    
    public boolean equals(Node node);
    
}
