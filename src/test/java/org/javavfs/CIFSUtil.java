/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.javavfs;

import java.io.IOException;
import jcifs.smb.SmbException;
import org.javavfs.impl.cifs.CifsFileSystem;

/**
 *
 * @author michael
 */
public class CIFSUtil {
    public static CifsFileSystem getFileSystem() throws IOException{
        String[] shares = CifsFileSystem.getShares("localhost");
        if(shares.length==0)
            throw new IOException("No shares available for test.");
        
        for(int i=0;i<shares.length;i++){
            try{
                return new CifsFileSystem("localhost", shares[i]);
            } catch(SmbException ex){
                System.out.println("Unable to connect to share. -> "+ex.getMessage());
            }
        }
        throw new IOException("Found "+shares.length+" shares but could not connect to any of them.");
    }
}