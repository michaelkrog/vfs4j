/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.javavfs;

/**
 *
 * @author mzk
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.List;
import org.javavfs.nativefs.NativeFileSystem;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class AbstractFileTest {

    public AbstractFileTest(FileSystem filesystem) {
        this.filesystem=filesystem;
    }

    FileSystem filesystem;
    
    @After
    public void tearDown() throws IOException {
        clean();
    }

    
        private void clean() throws IOException{
        List<Node> nodes = filesystem.getRoot().getChildren();
        
        for(Node node:nodes){
            if(node.isDirectory()){
                ((Directory)node).delete(true);
            } else {
                node.delete();
            }
        }
    } 
        
    /**
     * Test of getInputStream method, of class File.
     */
    @Test
    public void testGetInputStream() throws Exception {
        System.out.println("getInputStream");
        Directory root = filesystem.getRoot();
        File tmp = root.getFile("tmp", true);
        
        int expResult = 123;
        OutputStream os = tmp.getOutputStream();
        os.write(expResult);
        os.close();
        
        InputStream is = tmp.getInputStream();
        int result  = is.read();
        is.close();
        
        assertEquals(expResult, result);
        
        tmp.delete();
        
    }

    /**
     * Test of getLength method, of class File.
     */
    @Test
    public void testGetLength() throws Exception {
        System.out.println("getLength");
        Directory root = filesystem.getRoot();
        File tmp = root.getFile("tmp", true);
        
        long expResult = 3;
        OutputStream os = tmp.getOutputStream();
        os.write(1);
        os.write(2);
        os.write(3);
        os.close();
        
        long result = tmp.getLength();
        
        assertEquals(expResult, result);
        
        tmp.delete();
        
    }

}
