package dk.apaq.vfs.impl.sftp;

import ch.ethz.ssh2.SFTPv3DirectoryEntry;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import dk.apaq.vfs.File;
import dk.apaq.vfs.Path;

/**
 *
 * @author krog
 */
public class SftpFile extends SftpNode implements File{

    public SftpFile(SftpFileSystem filesystem, Path path, SFTPv3DirectoryEntry entry) throws IOException {
        super(filesystem,path, entry);
    }


    public InputStream getInputStream() throws IOException {
       return new SftpInputStream(fileSystem.sftpc, entry, fileSystem.sftpc.openFileRO(path.toString()));
    }

    public OutputStream getOutputStream() throws IOException {
            return null;
        
    }

    public long getLength() throws IOException {
        return entry.attributes.size;
    }

}
