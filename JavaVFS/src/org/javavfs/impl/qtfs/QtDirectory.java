/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.javavfs.impl.qtfs;

import com.trolltech.qt.core.QDir;
import com.trolltech.qt.core.QFile;
import com.trolltech.qt.core.QFileInfo;
import com.trolltech.qt.core.QIODevice;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.javavfs.Directory;
import org.javavfs.File;
import org.javavfs.FileSystemSession;
import org.javavfs.Node;
import org.javavfs.NodeFilter;
import org.javavfs.Path;

/**
 *
 * @author michael
 */
public class QtDirectory extends QtNode implements Directory {

    public QtDirectory(QtFileSystemSession session, QFileInfo file) {
        super(session, file);
    }

    public Directory createDirectory(String name) throws IOException {
        QDir dir = file.absoluteDir();
        boolean created = dir.mkdir(name);
        if(!created)
            throw new IOException("Unable to create directory '"+name+"' in folder '"+file.absolutePath()+"'");
            
        return new QtDirectory(session, new QFileInfo(dir, name));
    }

    public File createFile(String name) throws IOException {
        Path path = this.getPath();
        path.addLevel(name);
        
        QFile tmp = new QFile(path.toString());
        boolean created = tmp.open(QIODevice.OpenModeFlag.WriteOnly);
        tmp.close();

        if(!created)
            throw new IOException("Unable to create file '"+name+"' in folder '"+file.absolutePath()+"'");

        return new QtFile(session, new QFileInfo(file.absoluteDir(), name));
    }

    public boolean hasChild(String name) {
        QFileInfo child = new QFileInfo(file.absoluteDir(), name);
        return child.exists();
    }

    public boolean hasFile(String name) {
        QFileInfo child = new QFileInfo(file.absoluteDir(), name);
        return child.exists() && !child.isDir();
    }

    public boolean hasDirectory(String name) {
        QFileInfo child = new QFileInfo(file.absoluteDir(), name);
        return child.exists() && child.isDir();
    }

    public Node getChild(String name) throws FileNotFoundException {
        QFileInfo child = new QFileInfo(file.absoluteDir(), name);
        if(!child.exists())
            throw new FileNotFoundException("Child not found [Path="+child.absolutePath()+"].");
    
        if(child.isDir())
            return new QtDirectory(session, new QFileInfo(child.absoluteDir(), name));
        else
            return new QtFile(session, new QFileInfo(child.absoluteDir(), name));
    }

    public File getFile(String name) throws FileNotFoundException {
        try {
            return getFile(name, false);
        } catch (IOException ex) {
            throw new FileNotFoundException("Unknown error occured.");
        }
    }

    public Directory getDirectory(String name) throws FileNotFoundException {
        try {
            return getDirectory(name, false);
        } catch (IOException ex) {
            throw new FileNotFoundException("Unknown error occured.");
        }
    }

    public File getFile(String name, boolean createIfNeeded) throws FileNotFoundException, IOException {
        QFileInfo child = new QFileInfo(file.absoluteDir(), name);
        if(!child.exists()){
            if(createIfNeeded)
                return createFile(name);
            else
                throw new FileNotFoundException("File not found [Path="+child.absolutePath()+"].");
        }
        return new QtFile(session, new QFileInfo(child.absoluteDir(), name));
    }

    public Directory getDirectory(String name, boolean createIfNeeded) throws FileNotFoundException, IOException {
        QFileInfo child = new QFileInfo(file.absoluteDir(), name);
        if(!child.exists()){
            if(createIfNeeded)
                return createDirectory(name);
            else
                throw new FileNotFoundException("Directory not found [Path="+child.absolutePath()+"].");
        }
        return new QtDirectory(session, new QFileInfo(child.absoluteDir(), name));
    }

    public List<Node> getChildren() {
        List<QFileInfo> children = file.absoluteDir().entryInfoList();
        List<Node> nodes = new ArrayList<Node>();
        for(QFileInfo fileinfo : children){
            if(fileinfo.isDir())
                nodes.add(new QtDirectory(session, fileinfo));
            else
                nodes.add(new QtFile(session, fileinfo));
        }
        return nodes;
    }

    public List<Directory> getDirectories() {
        List<QFileInfo> children = file.absoluteDir().entryInfoList();
        List<Directory> nodes = new ArrayList<Directory>();
        for(QFileInfo fileinfo : children){
            if(fileinfo.isDir())
                nodes.add(new QtDirectory(session, fileinfo));
        }
        return nodes;
    }

    public List<File> getFiles() {
        List<QFileInfo> children = file.absoluteDir().entryInfoList();
        List<File> nodes = new ArrayList<File>();
        for(QFileInfo fileinfo : children){
            if(!fileinfo.isDir())
                nodes.add(new QtFile(session, fileinfo));
        }
        return nodes;
    }

    public List<Node> getChildren(NodeFilter filter) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Directory> getDirectories(NodeFilter filter) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<File> getFiles(NodeFilter filter) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean isBundle() {
        return file.fileName().contains(".");
    }


    
}
