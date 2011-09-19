/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dk.apaq.vfs.impl.subfs;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import dk.apaq.vfs.File;
import dk.apaq.vfs.FileSystem;

/**
 *
 * @author michael
 */
public class SubFile extends SubNode implements File{

    public SubFile(FileSystem fs, File subbedFile) {
        super(fs, subbedFile);
        this.subbedFile=subbedFile;
    }

    File subbedFile;

    public InputStream getInputStream() throws IOException {
         return subbedFile.getInputStream();
    }

    public OutputStream getOutputStream() throws IOException {
        return subbedFile.getOutputStream();
    }

    public long getLength() throws IOException {
        return subbedFile.getLength();
    }

}
