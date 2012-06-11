package dk.apaq.vfs;

import dk.apaq.vfs.impl.layered.LayeredFileSystem;
import dk.apaq.vfs.impl.ram.RamFilesystem;

/**
 *
 * @author michael
 */
public class LayeredFsFileTest extends AbstractFileTest {

    public LayeredFsFileTest() {
        setFilesystem(new LayeredFileSystem(new RamFilesystem()));
    }

    
}