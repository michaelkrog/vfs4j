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

    private String name="QtFileSystem";
    private QtFileSecurity security = new QtFileSecurity();
    
    public String getName() {
        return name;
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
