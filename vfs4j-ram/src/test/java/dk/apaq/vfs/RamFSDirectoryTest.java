package dk.apaq.vfs;

import dk.apaq.vfs.impl.ram.RamFilesystem;

/**
 *
 * @author michael
 */
public class RamFSDirectoryTest extends AbstractDirectoryTest {

    public RamFSDirectoryTest() {
        super(new RamFilesystem());
    }

   

}