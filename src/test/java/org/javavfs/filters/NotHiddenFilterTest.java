/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.javavfs.filters;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.util.Date;
import org.javavfs.Directory;
import org.javavfs.FileSystem;
import org.javavfs.FileSystemSession;
import org.javavfs.Node;
import org.javavfs.Path;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author michael
 */
public class NotHiddenFilterTest {

    private class HideableNode implements Node{

        private boolean hidden=false;

        public void moveTo(Directory newParent) throws IOException {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public void moveTo(Directory newParent, String newName) throws IOException {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public void setName(String name) throws IOException {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public String getName() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public String getBaseName() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public String getSuffix() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public Directory getParent() throws FileNotFoundException {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public FileSystem getFileSystem() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public boolean isDirectory() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public boolean isFile() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public boolean isHidden() {
            return hidden;
        }

        public void setHidden(boolean hidden){
            this.hidden=hidden;
        }

        public Date getLastModified() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public void setLastModified(Date date) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public int compareTo(Node node) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public void delete() throws IOException {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public URI toUri() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public boolean equals(Node node) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public Path getPath() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public boolean canRead() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public boolean canWrite() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public int compareTo(Object o) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

    }

    public NotHiddenFilterTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of accept method, of class NotHiddenFilter.
     */
    @Test
    public void testAccept() {
        System.out.println("accept");
        HideableNode node = new HideableNode();
        node.setHidden(false);
        NotHiddenFilter instance = new NotHiddenFilter();
        
        assertTrue(instance.accept(node));

        node.setHidden(true);
        assertTrue(!instance.accept(node));
    }

}