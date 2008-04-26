/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.javavfs.filters;

import org.javavfs.Node;
import org.javavfs.NodeFilter;
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
public class OrFilterTest {

    public OrFilterTest() {
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
    
    NodeFilter yes = new NodeFilter() {

        public boolean accept(Node node) {
            return true;
        }
    };
    
    NodeFilter no = new NodeFilter() {

        public boolean accept(Node node) {
            return false;
        }
    };
    

    /**
     * Test of accept method, of class OrFilter.
     */
    @Test
    public void testAccept() {
        System.out.println("accept");
        AndFilter instance = new AndFilter(yes,yes);
        boolean expResult = true;
        boolean result = instance.accept(null);
        assertEquals(expResult, result);
        
        instance = new AndFilter(yes,no);
        expResult = true;
        result = instance.accept(null);
        assertEquals(expResult, result);

        instance = new AndFilter(no,no);
        expResult = false;
        result = instance.accept(null);
        assertEquals(expResult, result);
    }

}