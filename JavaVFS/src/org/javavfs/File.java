/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.javavfs;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author mzk
 */
public interface File extends Node {

    public InputStream getInputStream() throws IOException;
    public OutputStream getOutputStream() throws IOException;
    
    public long getLength() throws IOException;
}
