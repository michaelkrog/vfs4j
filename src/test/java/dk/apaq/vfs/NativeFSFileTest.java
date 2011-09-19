package dk.apaq.vfs;

import dk.apaq.vfs.impl.nativefs.NativeFileSystem;

/**
 *
 * @author michael
 */
public class NativeFSFileTest extends AbstractFileTest {

    public NativeFSFileTest() {
        setFilesystem(new NativeFileSystem(new java.io.File(System.getProperty("user.dir"),"testdata").toURI()));
    }

    
}