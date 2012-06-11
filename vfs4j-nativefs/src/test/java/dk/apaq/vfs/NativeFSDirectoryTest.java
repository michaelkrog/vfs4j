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
        super(new NativeFileSystem(getFile().toURI()));
    }

    private static java.io.File getFile() {
        java.io.File file = new java.io.File(System.getProperty("user.dir"),"testdata");
        file.mkdirs();
        return file;
    }
    
    @BeforeClass
    public static void setUpClass() throws Exception {

    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        getFile().delete();
    }

    
    @Before
    public void setUp() {
        
    }

   

}