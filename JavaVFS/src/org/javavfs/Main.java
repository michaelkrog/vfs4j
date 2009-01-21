/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.javavfs;

import com.trolltech.qt.core.QDataStream;
import com.trolltech.qt.core.QDir;
import com.trolltech.qt.core.QFile;
import com.trolltech.qt.core.QFileInfo;
import java.util.List;

/**
 *
 * @author mediacenter
 */
public class Main {

    public static void main(String[] args){
        QFile file = new QFile(System.getProperty("user.dir")+"/testdata/test");

        file.open(QFile.OpenModeFlag.ReadWrite);
        QDataStream stream = new QDataStream(file);
        stream.writeString("Test");
        stream.device().close();
    }
}
