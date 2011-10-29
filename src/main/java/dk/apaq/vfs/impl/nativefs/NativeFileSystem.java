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
        
        infomap.put(FileSystem.FSInfo_HasFreeSpaceInformation, "true");
        infomap.put(FileSystem.FSInfo_HasSizeInformation, "true");
        
        
        root = file;
        if(!root.exists() || !root.isDirectory())
            throw new IllegalArgumentException("uri must point at a valid directory. [uri="+root.toString()+"]");
    }

    File root;
    HashMap infomap = new HashMap();
    
    public Map getInfo() {
        return infomap;
    }

    public String getName() {
        return "NativeFS("+root.toURI()+")";
    }

    public Directory getRoot() throws FileNotFoundException {
        return new NativeDirectory(this, root);
    }

    public long getSize() {
        return root.getTotalSpace();
    }

    public long getFreeSpace() {
        return root.getFreeSpace();
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
