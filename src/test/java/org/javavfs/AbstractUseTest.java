/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.javavfs;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.javavfs.Directory;
import org.javavfs.File;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mzk
 */
public abstract class AbstractUseTest {

    public AbstractUseTest(FileSystem filesystem) {
        this.filesystem=filesystem;
    }
    
    FileSystem filesystem;
    
    @Test
    public void testFileSystemInfo() {
        assertNotNull(filesystem.getInfo().get(FileSystem.FSInfo_Name));
        assertNotNull(filesystem.getInfo().get(FileSystem.FSInfo_Description));
        assertNotNull(filesystem.getInfo().get(FileSystem.FSInfo_Version));
        
    }

    @Test
    public void testFileSystemAndRoot() {
        try {
            assertNotNull(filesystem.getName());
            assertNotNull(filesystem.getRoot());

            Directory root = filesystem.getRoot();

            assertEquals(null, root.getParent());
        } catch (FileNotFoundException ex) {
            fail(ex.getMessage());
        }
    }
    
    @Test
    public void testCreateAndDeleteDirectoryAndDirectory(){
        try {
            Directory root = filesystem.getRoot();
            Directory testdata = root.getDirectory("testdata",true);
            assertNotNull(testdata);
            assertTrue(testdata.isDirectory());

            assertTrue(root.hasChild("testdata"));
            assertTrue(root.hasDirectory("testdata"));
            assertTrue(!root.hasFile("testdata"));

            File file = testdata.createFile("testfile");
            OutputStream os = file.getOutputStream();
            os.write(new byte[]{12, 14});
            os.close();
            
            assertEquals(2L, file.getLength());
            assertTrue(!file.isDirectory());
            assertEquals("testfile", file.getName());

            file.delete();

            testdata.delete(true);
            assertTrue(!root.hasChild("testdata"));
            assertTrue(!root.hasDirectory("testdata"));
            assertTrue(!root.hasFile("testdata"));
        } catch (IOException ex) {
            fail(ex.getMessage());
        }
    }
    
    @Test
    public void testCreateManyFiles(){
        try {
            byte[] data = {56,78,45,90,78};
            Directory root = filesystem.getRoot();
            int noOfFiles=10;
            Directory testdata = root.getDirectory("testdata",true);
            
            //Create files
            for(int i=0;i<noOfFiles;i++){
                File file = testdata.createFile(i+".dat");
                OutputStream os = file.getOutputStream();
                os.write(data);
                os.close();
            }
            
            assertEquals(noOfFiles,testdata.getChildren().size());
            
            //delete files
            for(int i=0;i<noOfFiles;i++){
                File file = testdata.getFile(i+".dat");
                file.delete();
            }
            
            assertEquals(0,testdata.getChildren().size());
            
            testdata.delete();
            //assertEquals(0,root.getChildren().size());
            
        } catch (IOException ex) {
            Logger.getLogger(CIFSUseTest.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    
    @Test
    public void testDeleteRecursively(){
        try{
            Directory root = filesystem.getRoot();
            Directory dir1 = root.createDirectory("dir1");
            dir1.createFile("file1");
            dir1.createFile("file2");
            dir1.createFile("file3");
            dir1.createDirectory("dir2");
            
            try{
                root.delete(true);
                
                //It should have thrown an exception now
                fail("Did not throw exception when deleting root.");
            } catch(IOException ex){
                //NOOP
            }
            
            try{
                dir1.delete();
                
                //It should have thrown an exception now
                fail("Did not throw exception when deleting nonempty directory nonrecursively.");
            } catch(IOException ex){
                //NOOP
            }
            
            dir1.delete(true);
        } catch(IOException ex){
            Logger.getLogger(CIFSUseTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
