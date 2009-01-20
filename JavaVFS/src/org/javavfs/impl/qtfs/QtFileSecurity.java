/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.javavfs.impl.qtfs;

import java.security.Principal;
import org.javavfs.Node;
import org.javavfs.security.Security;

/**
 *
 * @author michael
 */
public class QtFileSecurity implements Security {

    public boolean canRead(Principal principal, Node node) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean canWrite(Principal principal, Node node) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void checkRead(Principal principal, Node node) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void checkWrite(Principal principal, Node node) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Principal getPrincipal(String username, String password) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Principal getGuestPrincipal() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
