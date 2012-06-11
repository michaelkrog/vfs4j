package dk.apaq.vfs;

import dk.apaq.vfs.impl.ram.RamFilesystem;

/**
 *
 * @author michael
 */
public class RamFSFileTest extends AbstractFileTest {

    public RamFSFileTest() {
        setFilesystem(new RamFilesystem());
    }

    
}