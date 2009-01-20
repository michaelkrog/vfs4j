/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.javavfs;

import java.net.MalformedURLException;
import jcifs.smb.SmbException;
import org.javavfs.impl.cifs.CifsFileSystemSession;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mzk
 */
public class CIFSFileTest extends AbstractFileTest{

    public CIFSFileTest() throws MalformedURLException, SmbException {
        super(null);
        //super(new CifsFileSystemSession("smb://mzk:Kodeord08@mzk-laptop/datatest/"));
    }



}