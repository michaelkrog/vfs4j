package dk.apaq.vfs.impl.layered;

import dk.apaq.vfs.Directory;
import dk.apaq.vfs.FileSystem;
import dk.apaq.vfs.Node;
import dk.apaq.vfs.Path;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author michael
 */
public class LayeredFileSystem implements FileSystem {

    private final FileSystem layerForFilesystem;
    private final List<LayerEntry> entries = new ArrayList<LayerEntry>();
    
    private class LayerEntry {
        private final Path path;
        private final Directory directory;

        public LayerEntry(Path path, Directory directory) {
            this.path = path;
            this.directory = directory;
        }

        public Directory getDirectory() {
            return directory;
        }

        public Path getPath() {
            return path;
        }
    }

    public LayeredFileSystem(FileSystem layerForFilesystem) {
        this.layerForFilesystem = layerForFilesystem;
    }
    
    public void addLayer(Path injectPath, Directory directory) {
        entries.add(new LayerEntry(injectPath, directory));
    }
    
    protected List<Directory> getLayers(Path path) {
        List<Directory> dirs = new ArrayList<Directory>();
        for(LayerEntry entry : entries) {
            if(path.toString().equals(entry.path.toString())) {
                dirs.add(entry.directory);
            }
        }
        return dirs;
    }
    
    @Override
    public String getName() {
        return "Layered filesystem between for "+layerForFilesystem.getName();
    }

    @Override
    public Map getInfo() {
        return layerForFilesystem.getInfo();
    }

    @Override
    public Directory getRoot() throws FileNotFoundException {
        return new LayeredDirectory(this, null, layerForFilesystem.getRoot());
    }

    @Override
    public long getSize() {
        return layerForFilesystem.getSize();
    }

    @Override
    public long getFreeSpace() {
        return layerForFilesystem.getFreeSpace();
    }

    @Override
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

    @Override
    public void close() throws IOException {
        layerForFilesystem.close();
    }
    
}
