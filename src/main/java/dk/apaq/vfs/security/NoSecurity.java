/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dk.apaq.vfs.security;

import java.security.Principal;
import java.util.logging.Level;
import java.util.logging.Logger;
import dk.apaq.vfs.Node;

/**
 *
 * @author michael
 */
public class NoSecurity implements Security{

    Principal guest = new Principal() {

        public String getName() {
            return "Guest";
        }
    };
    
    public boolean canRead(Principal principal, Node node) {
        Logger.getLogger(NoSecurity.class.getName()).log(Level.INFO,"Node: "+node.getPath());
        return true;
    }

    public boolean canWrite(Principal principal, Node node) {
        return true;
    }

    public void checkRead(Principal principal, Node node) {
        if(!canRead(principal,node))
            throw new SecurityException("Unable to read.");
    }

    public void checkWrite(Principal principal, Node node) {}

    public Principal getPrincipal(String username, String password) {
        throw new SecurityException("Only Guest principal supported.");
    }

    public Principal getGuestPrincipal() {
        return guest;
    }

}
