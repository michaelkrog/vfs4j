/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.javavfs.impl.sftp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javavfs.File;

/**
 *
 * @author krog
 */
public class SftpFile extends SftpNode implements File{

    public InputStream getInputStream() throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public OutputStream getOutputStream() throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public long getLength() throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
