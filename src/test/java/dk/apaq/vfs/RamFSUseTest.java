package dk.apaq.vfs;

import dk.apaq.vfs.impl.nativefs.NativeFileSystem;
import dk.apaq.vfs.impl.ram.RamFilesystem;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author mzk
 */
public class RamFSUseTest extends AbstractUseTest{

    public RamFSUseTest() {
        super(new RamFilesystem());
    }
    
    
    
    
}