package dk.apaq.vfs;

import java.net.MalformedURLException;
import jcifs.smb.SmbException;

/**
 *
 * @author mzk
 */
public class CIFSFileTest extends AbstractFileTest{

    public CIFSFileTest() throws MalformedURLException, SmbException {
        setFilesystem(null);
        //super(new CifsFileSystemSession("smb://mzk:Kodeord08@mzk-laptop/datatest/"));
    }



}