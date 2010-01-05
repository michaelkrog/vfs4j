/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.javavfs;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.List;
import org.javavfs.impl.nativefs.NativeFileSystem;
import org.javavfs.impl.nativefs.NativeFileSystemSession;
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
public class NativeFSFileTest extends AbstractFileTest {

    public NativeFSFileTest() {
        setFilesystem(new NativeFileSystem(new java.io.File(System.getProperty("user.dir"),"testdata").toURI()));
    }

    
}