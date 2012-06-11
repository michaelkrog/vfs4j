package dk.apaq.vfs;

import dk.apaq.vfs.impl.ram.RamFilesystem;
import dk.apaq.vfs.impl.subfs.SubFs;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author michael
 */
public class SubFsFileTest extends AbstractFileTest {

    
    private static final FileSystem filesystem = new RamFilesystem();
    private static Directory folder = null;
    
    static {
        try {
            folder = filesystem.getRoot().getDirectory("folder", true);
        } catch (IOException ex) {
            Logger.getLogger(SubFsDirectoryTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  public SubFsFileTest() {
        setFilesystem(new SubFs(filesystem, folder));
    }
  
    
}