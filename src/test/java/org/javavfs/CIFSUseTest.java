package org.javavfs;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jcifs.smb.SmbException;
import org.javavfs.Directory;
import org.javavfs.File;
import org.javavfs.FileSystemSession;
import org.javavfs.impl.cifs.CifsFileSystemSession;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mzk
 */
public class CIFSUseTest extends AbstractUseTest{

    public CIFSUseTest() throws MalformedURLException, IOException {
        super(null);
        //super(CIFSUtil.getFileSystem());
    }


    
    @BeforeClass
    public static void setUpClass() throws Exception {

    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }
    
    

}