/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dk.apaq.vfs.impl.cifs;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import jcifs.smb.SmbFile;
import dk.apaq.vfs.File;

/**
 *
 * @author mzk
 */
public class CifsFile extends CifsNode implements File{

    public CifsFile(CifsFileSystem filesystem, SmbFile file) {
        super(filesystem, file);
    }

    public CifsFile(CifsFileSystem filesystem, String url) throws MalformedURLException {
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
