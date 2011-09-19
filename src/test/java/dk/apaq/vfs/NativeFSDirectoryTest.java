package dk.apaq.vfs;

import dk.apaq.vfs.impl.nativefs.NativeFileSystem;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

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