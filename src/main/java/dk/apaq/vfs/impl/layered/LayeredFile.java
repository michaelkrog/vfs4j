package dk.apaq.vfs.impl.layered;

import dk.apaq.vfs.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author michael
 */
public class LayeredFile extends LayeredNode implements File {

    public LayeredFile(LayeredFileSystem fileSystem, LayeredDirectory parent, File wrappedNode) {
        super(fileSystem, parent, wrappedNode);
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return ((File)wrappedNode).getInputStream();
    }

    @Override
    public OutputStream getOutputStream() throws IOException {
        return ((File)wrappedNode).getOutputStream();
    }

    @Override
    public long getLength() throws IOException {
        return ((File)wrappedNode).getLength();
    }
    
    
}
