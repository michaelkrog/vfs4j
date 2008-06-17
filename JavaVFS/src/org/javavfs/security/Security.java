/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.javavfs.security;

import java.security.Principal;
import org.javavfs.Node;

/**
 *
 * @author michael
 */
public interface Security {
    /**
     * Retrieves information about wether the node can be read or not.
     * @param node The node to retrieve the security information for.
     * @return True/False
     */
    public boolean canRead(Principal principal, Node node);
    
    /**
     * Retrieves information about wether the node can be written to/changed or not.
     * @param node The node to retrieve the security information for.
     * @return True/False
     */
    public boolean canWrite(Principal principal, Node node);
    
    /**
     * Same as <code>canRead</code> but throws an exception if access is denied.
     * @param node The node to check for.
     */
    public void checkRead(Principal principal, Node node);
    
    /**
     * Same as <code>canWrite</code> but throws an exception if access is denied.
     * @param node The node to check for.
     */
    public void checkWrite(Principal principal, Node node);
    
    public Principal getPrincipal(String username, String password);
    
    public Principal getGuestPrincipal();
}
