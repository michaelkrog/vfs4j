/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.javavfs.filters;

import org.javavfs.Node;
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
        Node node = null;
        NotHiddenFilter instance = new NotHiddenFilter();
        boolean expResult = false;
        boolean result = instance.accept(node);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}