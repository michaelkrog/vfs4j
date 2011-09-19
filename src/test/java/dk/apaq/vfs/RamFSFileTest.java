package dk.apaq.vfs;

import dk.apaq.vfs.impl.nativefs.NativeFileSystem;
import dk.apaq.vfs.impl.ram.RamFilesystem;
import org.junit.AfterClass;

/**
 *
 * @author michael
 */
public class RamFSFileTest extends AbstractFileTest {

    public RamFSFileTest() {
        setFilesystem(new RamFilesystem());
    }

    
}