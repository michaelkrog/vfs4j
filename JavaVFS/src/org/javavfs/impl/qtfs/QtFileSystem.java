/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.javavfs.impl.qtfs;

import java.security.Principal;
import java.util.Map;
import org.javavfs.FileSystem;
import org.javavfs.FileSystemSession;
import org.javavfs.security.Security;

/**
 *
 * @author michael
 */
public class QtFileSystem implements FileSystem {

    public String getName() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public FileSystemSession createSession(Principal principal) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Map getInfo() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Security getSecurity() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setSecurity(Security security) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
