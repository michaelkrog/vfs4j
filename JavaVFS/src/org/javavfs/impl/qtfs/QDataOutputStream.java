/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.javavfs.impl.qtfs;

import com.trolltech.qt.core.QDataStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author michael
 */
public class QDataOutputStream extends OutputStream {

    public QDataOutputStream(QDataStream stream) {
        innerstream=stream;
    }

    private QDataStream innerstream;
    
    @Override
    public void write(int data) throws IOException {
        innerstream.writeInt(data);
    }

}
