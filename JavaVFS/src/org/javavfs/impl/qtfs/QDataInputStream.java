/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.javavfs.impl.qtfs;

import com.trolltech.qt.core.QDataStream;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author michael
 */
public class QDataInputStream extends InputStream{

    public QDataInputStream(QDataStream stream) {
        this.innerStream=stream;
    }

    private QDataStream innerStream;
    
    @Override
    public int read() throws IOException {
        return innerStream.readByte();
    }

}
