/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.javavfs.impl.qtfs;

import com.trolltech.qt.core.QDataStream;
import com.trolltech.qt.core.QFile;
import com.trolltech.qt.core.QFileInfo;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javavfs.File;

/**
 *
 * @author michael
 */
public class QtFile extends QtNode implements File {

    public QtFile(QtFileSystemSession session, QFileInfo file) {
        super(session, file);
    }

    
    public InputStream getInputStream() throws IOException {
        QFile tmp = new QFile(super.file.absoluteFilePath());
        return new QDataInputStream(new QDataStream(tmp));
    }

    public OutputStream getOutputStream() throws IOException {
        QFile tmp = new QFile(super.file.absoluteFilePath());
        return new QDataOutputStream(new QDataStream(tmp));
    }

    public long getLength() throws IOException {
        return file.size();
    }

}
