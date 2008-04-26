/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.javavfs;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import org.javavfs.nativefs.NativeFileSystem;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author michael
 */
public class NativeFSDirectoryTest extends AbstractDirectoryTest {

    public NativeFSDirectoryTest() {
        super(new NativeFileSystem(new java.io.File(System.getProperty("user.dir"),"testdata").toURI()));
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

   

}