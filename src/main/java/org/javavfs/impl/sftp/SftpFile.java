/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.javavfs.impl.sftp;

import ch.ethz.ssh2.SFTPv3DirectoryEntry;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.javavfs.File;
import org.javavfs.Path;

/**
 *
 * @author krog
 */
public class SftpFile extends SftpNode implements File{

    public SftpFile(SftpFileSystem filesystem, Path path, SFTPv3DirectoryEntry entry) throws IOException {
        super(filesystem,path, entry);
    }


    public InputStream getInputStream() throws IOException {
       return null;
    }

    public OutputStream getOutputStream() throws IOException {
            return null;
        
    }

    public long getLength() throws IOException {
        return 0;
    }

}
