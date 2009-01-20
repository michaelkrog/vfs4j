/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.javavfs.impl.cifs;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import jcifs.smb.SmbFile;
import org.javavfs.File;

/**
 *
 * @author mzk
 */
public class CifsFile extends CifsNode implements File{

    public CifsFile(CifsFileSystemSession filesystem, SmbFile file) {
        super(filesystem, file);
    }

    public CifsFile(CifsFileSystemSession filesystem, String url) throws MalformedURLException {
        super(filesystem, url);
    }
    
    public InputStream getInputStream() throws IOException {
        return innerFile.getInputStream();
    }

    public OutputStream getOutputStream() throws IOException {
        return innerFile.getOutputStream();
    }

    public long getLength() throws IOException {
        return innerFile.length();
    }

}
