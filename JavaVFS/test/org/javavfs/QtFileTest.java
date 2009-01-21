/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.javavfs;

import java.net.MalformedURLException;
import jcifs.smb.SmbException;
import org.javavfs.impl.qtfs.QtFileSystem;

/**
 *
 * @author mzk
 */
public class QtFileTest extends AbstractFileTest{

    public QtFileTest() throws MalformedURLException, SmbException {
        super(new QtFileSystem(System.getProperty("user.dir")+"/testdata"));
        //super(new CifsFileSystemSession("smb://mzk:Kodeord08@mzk-laptop/datatest/"));
    }



}