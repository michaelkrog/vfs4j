/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.javavfs.impl.sftp;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UserInfo;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import org.javavfs.Directory;
import org.javavfs.FileSystem;
import org.javavfs.Node;

/**
 *
 * @author krog
 */
public class SftpFileSystem implements FileSystem {

    public SftpFileSystem(String host, int port, String user, String password) throws JSchException {
        //Add info to infomap
        infomap.put(FileSystem.FSInfo_Name, "SftpFS");
        infomap.put(FileSystem.FSInfo_Description, "A filesystem based upon Sftp.");
        infomap.put(FileSystem.FSInfo_Version, "0.1.0");

        //Java version < 1.6
        infomap.put(FileSystem.FSInfo_HasFreeSpaceInformation, "false");
        infomap.put(FileSystem.FSInfo_HasSizeInformation, "false");

        jSch = new JSch();
        jSchSession = jSch.getSession(user, host, port);
        jSchSession.setUserInfo(new SftpUserInfo(password));
    }


    public String getName() {
        return "SftpFileSystem";
    }

    private HashMap infomap = new HashMap();
    private JSch jSch = null;
    private Session jSchSession = null;

    public Map getInfo() {
        return infomap;
    }

    public Directory getRoot() throws FileNotFoundException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public long getSize() {
        return 0;
    }

    public long getFreeSpace() {
        return 0;
    }

    public Node getNode(String path) throws FileNotFoundException {
        throw new UnsupportedOperationException("Not supported yet.");
    }


}
