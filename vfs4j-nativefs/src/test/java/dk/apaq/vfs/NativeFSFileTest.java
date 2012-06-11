package dk.apaq.vfs;

import dk.apaq.vfs.impl.nativefs.NativeFileSystem;
import org.junit.AfterClass;

/**
 *
 * @author michael
 */
public class NativeFSFileTest extends AbstractFileTest {

    public NativeFSFileTest() {
        setFilesystem(new NativeFileSystem(getFile().toURI()));
    }
    
    private static java.io.File getFile() {
        java.io.File file = new java.io.File(System.getProperty("user.dir"),"testdata");
        file.mkdirs();
        return file;
    }
    
    @AfterClass
    public static void tearDownClass() throws Exception {
        getFile().delete();
    }

    
}