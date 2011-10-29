package dk.apaq.vfs;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.io.Serializable;

/**
 *
 * @author michael
 */
public interface FileSystem extends Serializable {
    public static final String FSInfo_Name="FSInfo_Name";
    public static final String FSInfo_Description="FSInfo_Description";
    public static final String FSInfo_Version="FSInfo_Version";
    public static final String FSInfo_HasSizeInformation="FSInfo_HasSizeInformation";
    public static final String FSInfo_HasFreeSpaceInformation="FSInfo_HasFreeSpaceInformation";
    
    
    /**
     * Retrieves the name of the filesystem.
     * @return The name.
     */
    public String getName();
    
    //public FileSystemSession createSession(Principal principal);
    
    /**
     * Retrieves a list of information about the filesystem.
     * @return A map hold key/pair information about the filesystem. Default keys are available as static variables on the FileSystem interface.
     */
    public Map getInfo();
    
    
    /**
     * Retrieves the security used by the filesystem.
     * @return The Security object.
     */
    //public Security getSecurity();
    
    /**
     * Sets the security object used by the filesystem.
     * @param security The new security to use for the filesystem.
     */
    //public void setSecurity(Security security);

        /**
     * Retrieves the root directory of the filesystem.
     * @return The root directory.
     * @throws java.io.FileNotFoundException Thrown if the root directory could not be found.
     */
    public Directory getRoot() throws FileNotFoundException;

    /**
     * Retrieves the size of the filesystem in bytes.
     * @return The size of the filesystem or -1 if not supported. The filesystem should report if it supports this call via the infomap retrieveable by <code>getInfo()</code>. The key to use for retrieveal is <code>FSInfo_HasSizeInformation</code>
     */
    public long getSize();

    /**
     * Retrieves the free space available.
     * @return The free space avaliable in bytes or -1 if not supported. The filesystem should report if it supports this call via the infomap retrieveable by <code>getInfo()</code>. The key to use for retrieveal is <code>FSInfo_HasFreeSpaceInformation</code>.
     */
    public long getFreeSpace();

    
    public Node getNode(String path) throws FileNotFoundException;

    public void close() throws IOException;
}
