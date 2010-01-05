/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.javavfs.impl.nativefs;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.javavfs.File;



/**
 *
 * @author mzk
 */
public class NativeFile extends NativeNode implements File{

    public NativeFile(NativeFileSystem fs, java.io.File file) throws FileNotFoundException {
        super(fs,file);
        
        if(file.isDirectory())
            throw new FileNotFoundException("The fileobject must point at an existing file.");
    }

    public InputStream getInputStream() throws IOException {
        try {
            return new FileInputStream(file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(NativeFile.class.getName()).log(Level.SEVERE, null, ex);
            throw new IOException(ex.getMessage());
        }
    }

    public OutputStream getOutputStream() throws IOException {
        try {
            return new FileOutputStream(file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(NativeFile.class.getName()).log(Level.SEVERE, null, ex);
            throw new IOException(ex.getMessage());
        }
    }

    public long getLength() {
        return file.length();
        
    }

    

}
