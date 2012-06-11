package dk.apaq.vfs;

import dk.apaq.vfs.impl.layered.LayeredFileSystem;
import dk.apaq.vfs.impl.ram.RamFilesystem;

/**
 *
 * @author mzk
 */
public class LayeredFsUseTest extends AbstractUseTest{

    public LayeredFsUseTest() {
        super(new LayeredFileSystem(new RamFilesystem()));
    }
    
    
    
    
}