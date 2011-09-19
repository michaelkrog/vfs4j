/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dk.apaq.vfs;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author mzk
 */
public interface File extends Node {

    /**
     * Retrieves the inputstream of the file to read from.
     * @return The inputstream.
     * @throws java.io.IOException Throw if an error occures while retrieving the inputstream.
     */
    public InputStream getInputStream() throws IOException;
    
    /**
     * Retrieves the outputstream of the file to write to.
     * @return The outputstream.
     * @throws java.io.IOException Throw if an error occures while retrieving the outputstream.
     */
    public OutputStream getOutputStream() throws IOException;
    
    /**
     * Retrieves the size/length of the file in bytes.
     * @return The size/length of the file in bytes.
     * @throws java.io.IOException Throw if an error occures while retrieving the size/length.
     */
    public long getLength() throws IOException;

}
