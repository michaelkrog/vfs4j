/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dk.apaq.vfs.impl.cifs;

import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileFilter;
import dk.apaq.vfs.FileSystemSession;
import dk.apaq.vfs.Node;
import dk.apaq.vfs.NodeFilter;

/**
 *
 * @author mzk
 */
public class SmbFilterWrapper implements SmbFileFilter {

    public SmbFilterWrapper(CifsFileSystem filesystem, NodeFilter filter) {
        this.filesystem=filesystem;
        this.filter=filter;
    }

    CifsFileSystem filesystem;
    NodeFilter filter;
    
    public boolean accept(SmbFile file) throws SmbException {
        Node node;
        if(file.isDirectory())
            node=new CifsDirectory(filesystem, file);
        else
            node=new CifsFile(filesystem, file);
        
        return filter.accept(node);
    }

}
