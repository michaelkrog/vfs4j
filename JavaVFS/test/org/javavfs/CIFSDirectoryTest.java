/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.javavfs;

import java.net.MalformedURLException;
import jcifs.smb.SmbException;
import org.javavfs.cifs.CifsFileSystemSession;
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
public class CIFSDirectoryTest extends AbstractDirectoryTest {

    public CIFSDirectoryTest() throws MalformedURLException, SmbException {
        super(null);
        //super(new CifsFileSystem("smb://mzk:Kodeord08@mzk-laptop/datatest/"));
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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}

}