package dk.apaq.vfs;

import dk.apaq.vfs.Directory;
import dk.apaq.vfs.FileSystem;
import dk.apaq.vfs.Path;
import dk.apaq.vfs.impl.layered.LayeredFileSystem;
import dk.apaq.vfs.impl.ram.RamFilesystem;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.junit.Test;

/**
 *
 * @author michael
 */
public class LayeredFsDirectoryTest extends AbstractDirectoryTest {

    private final LayeredFileSystem filesystem;
    
    public LayeredFsDirectoryTest() {
        this.filesystem = new LayeredFileSystem(new RamFilesystem());
        setFileSystem(filesystem);
        
    }

    @Test
    public void testLayer() throws IOException {
        FileSystem injectedFs = new RamFilesystem();
        Directory themesToInject = injectedFs.getRoot().getDirectory("System", true).getDirectory("Themes", true);
        themesToInject.createDirectory("Simple");
        
        Path path = new Path("/Themes");
        filesystem.addLayer(path, themesToInject);
        
        Directory themes = filesystem.getRoot().getDirectory("Themes", true);
        themes.getDirectory("Simple");
        
        themes.getChildren().get(0).getName().equals("Simple");
    }
   

}