package dk.apaq.vfs.impl.ram;

import dk.apaq.vfs.Directory;
import dk.apaq.vfs.FileSystem;
import dk.apaq.vfs.Node;
import dk.apaq.vfs.Path;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class RamFilesystem implements FileSystem {

    public RamFilesystem() {
     //Add info to infomap
        infomap.put(FileSystem.FSInfo_Name, "RamFS");
        infomap.put(FileSystem.FSInfo_Description, "A filesystem based upon objects in ram.");
        infomap.put(FileSystem.FSInfo_Version, "0.1.0");

        //Java version < 1.6
        infomap.put(FileSystem.FSInfo_HasFreeSpaceInformation, "false");
        infomap.put(FileSystem.FSInfo_HasSizeInformation, "false");

    }

    RamDirectory root = new RamDirectory(null, "root");
    HashMap infomap = new HashMap();

    public Map getInfo() {
        return infomap;
    }


    public String getName() {
        return "RamFS";
    }


    public Directory getRoot() throws FileNotFoundException {
        return root;
    }

    public long getSize() {
        return -1;
    }

    public long getFreeSpace() {
        return -1;
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
