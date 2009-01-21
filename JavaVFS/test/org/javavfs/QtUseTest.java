package org.javavfs;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.MalformedURLException;
import org.javavfs.impl.qtfs.QtFileSystem;

/**
 *
 * @author mzk
 */
public class QtUseTest extends AbstractUseTest{

    public QtUseTest() throws MalformedURLException, IOException {
        super(new QtFileSystem(System.getProperty("user.dir")+"/testdata"));
        //super(CIFSUtil.getFileSystem());
    }

    
    

}