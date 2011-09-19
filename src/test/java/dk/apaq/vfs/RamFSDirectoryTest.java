package dk.apaq.vfs;

import dk.apaq.vfs.impl.nativefs.NativeFileSystem;
import dk.apaq.vfs.impl.ram.RamFilesystem;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author michael
 */
public class RamFSDirectoryTest extends AbstractDirectoryTest {

    public RamFSDirectoryTest() {
        super(new RamFilesystem());
    }

   

}