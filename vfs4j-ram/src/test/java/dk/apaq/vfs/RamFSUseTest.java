package dk.apaq.vfs;

import dk.apaq.vfs.impl.ram.RamFilesystem;

/**
 *
 * @author mzk
 */
public class RamFSUseTest extends AbstractUseTest{

    public RamFSUseTest() {
        super(new RamFilesystem());
    }
    
    
    
    
}