package dk.apaq.vfs.impl.nativefs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import dk.apaq.vfs.Directory;
import dk.apaq.vfs.FileSystem;
import dk.apaq.vfs.Node;
import dk.apaq.vfs.Path;
import dk.apaq.vfs.security.NoSecurity;
import dk.apaq.vfs.security.Security;

/**
 *
 * @author michael
 */
public class NativeFileSystem implements FileSystem {
    public NativeFileSystem(URI uri) throws IllegalArgumentException {
        this(new File(uri));

    }
    
    public NativeFileSystem(String path) throws IllegalArgumentException {
        this(new File(path));
    }
    
    public NativeFileSystem(File file) throws IllegalArgumentException {
        //Add info to infomap
        infomap.put(FileSystem.FSInfo_Name, "NativeFS");
        infomap.put(FileSystem.FSInfo_Description, "A filesystem based upon native files via the java.io package.");
        infomap.put(FileSystem.FSInfo_Version, "0.1.0");
        
        //Java version < 1.6
        infomap.put(FileSystem.FSInfo_HasFreeSpaceInformation, "false");
        infomap.put(FileSystem.FSInfo_HasSizeInformation, "false");
        
        
        root = file;
        if(!root.exists() || !root.isDirectory())
            throw new IllegalArgumentException("uri must point at a valid directory. [uri="+root.toString()+"]");
    }

    File root;
    HashMap infomap = new HashMap();
    Security security = new NoSecurity();

    /*public File getRoot() {
        return root;
    }*/
    
    
    public Map getInfo() {
        return infomap;
    }

    /*public Security getSecurity() {
        return security;
    }

    public void setSecurity(Security security) {
        this.security=security;
    }*/

    public String getName() {
        return "NativeFS("+root.toURI()+")";
    }



    /*public FileSystemSession createSession(Principal principal) {
        return new NativeFileSystemSession(this, principal);
    }*/

    public Directory getRoot() throws FileNotFoundException {
        return new NativeDirectory(this, root);
    }

    public long getSize() {
        //Java version < 1.6
        return -1;

        //Java version 1.6+
        //return root.innerFile.getTotalSpace();
    }

    public long getFreeSpace() {
        //Java version < 1.6
        return -1;

        //Java version 1.6+
        //return root.innerFile.getFreeSpace();
    }

    public Node getNode(String path) throws FileNotFoundException {
        Path pathObject = new Path(path);
        Node currentNode = getRoot();

        for(int i=0;i<pathObject.getLevels();i++){
            if(currentNode.isDirectory()){
                currentNode = ((Directory)currentNode).getChild(pathObject.getLevel(i));
            } else
                throw new FileNotFoundException("The path '"+path+"' does not exist.");
        }
        return currentNode;
    }

    public void close() throws IOException {
    }


}
