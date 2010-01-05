/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.javavfs;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author michael
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({org.javavfs.NativeFSFileTest.class,org.javavfs.NativeFSDirectoryTest.class,org.javavfs.NativeFSUseTest.class,
                    org.javavfs.CIFSFileTest.class,org.javavfs.CIFSDirectoryTest.class,org.javavfs.CIFSUseTest.class})
public class JavavfsSuite {

    public JavavfsSuite() {
        
    }

}