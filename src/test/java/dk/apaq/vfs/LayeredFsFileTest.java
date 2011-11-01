package dk.apaq.vfs;

import dk.apaq.vfs.impl.layered.LayeredFileSystem;
import dk.apaq.vfs.impl.nativefs.NativeFileSystem;
import dk.apaq.vfs.impl.ram.RamFilesystem;
import org.junit.AfterClass;

/**
 *
 * @author michael
 */
public class LayeredFsFileTest extends AbstractFileTest {

    public LayeredFsFileTest() {
        setFilesystem(new LayeredFileSystem(new RamFilesystem()));
    }

    
}