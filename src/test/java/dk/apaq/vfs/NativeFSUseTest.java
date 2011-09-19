package dk.apaq.vfs;

import dk.apaq.vfs.impl.nativefs.NativeFileSystem;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author mzk
 */
public class NativeFSUseTest extends AbstractUseTest{

    public NativeFSUseTest() {
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

    @After
    public void tearDown() {
    }
    
    
}