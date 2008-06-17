/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.javavfs.cifs;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;
import org.javavfs.Directory;
import org.javavfs.FileSystem;
import org.javavfs.security.Security;

/**
 *
 * @author mzk
 */
public class CifsFileSystem implements FileSystem {

    public CifsFileSystem(String server, String share) throws MalformedURLException, SmbException {
        this("smb://"+server+"/"+share+"/");
    }
    
    public CifsFileSystem(String url) throws MalformedURLException, SmbException {
        SmbFile smbfile = new SmbFile(url);
        
        /*int type = smbfile.getType();
        type = SmbFile.TYPE_SHARE;
        type = SmbFile.TYPE_FILESYSTEM;*/
        
        if(smbfile.getType()!=SmbFile.TYPE_SHARE && (smbfile.getType()!=SmbFile.TYPE_FILESYSTEM || !smbfile.isDirectory()))
                throw new MalformedURLException("The URL syntax was OK, but it must point to a share or a directory.");
        root = new CifsDirectory(this, smbfile);
    
        //Add info to infomap
        infomap.put(FileSystem.FSInfo_Name, "CIFS");
        infomap.put(FileSystem.FSInfo_Description, "A filesystem based upon samba share using the JCIFS package.");
        infomap.put(FileSystem.FSInfo_Version, "0.1.0");
        infomap.put(FileSystem.FSInfo_HasFreeSpaceInformation, "true");
        infomap.put(FileSystem.FSInfo_HasSizeInformation, "false");
        
    }

    CifsDirectory root;
    HashMap infomap = new HashMap();
    
    public String getName() {
        return "CIFS";
    }

    public Directory getRoot() throws FileNotFoundException {
        return root;
    }

    public Map getInfo() {
        return infomap;
    }

    public static String[] getWorkgroups() throws IOException{
        try {
            SmbFile file = new SmbFile("smb://");
            return file.list();
        } catch(MalformedURLException ex){
            throw new IOException("Unable to liste workgroups. "+ex.getMessage());
        }
        
    }
    
    public static String[] getServers(String workgroup) throws IOException{
        try {
            SmbFile file = new SmbFile("smb://"+workgroup);
            return file.list();
        } catch(MalformedURLException ex){
            throw new IOException("Unable to liste servers. "+ex.getMessage());
        }
    }
    
    public static String[] getShares(String server) throws IOException{
        try {
            SmbFile file = new SmbFile("smb://"+server);
            return file.list();
        } catch(MalformedURLException ex){
            throw new IOException("Unable to liste shares. "+ex.getMessage());
        }
    }

    public long getSize() {
        return -1;
    }

    public long getFreeSpace() {
        try{
            return root.innerFile.getDiskFreeSpace();
        } catch(SmbException ex){
            return -1;
        }
    }

    public Security getSecurity() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setSecurity(Security security) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
