/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.javavfs.security;

import java.security.Principal;
import org.javavfs.Node;

/**
 *
 * @author michaelkrog
 */
public abstract class AbstractSecurity implements Security{

    public static ThreadLocal<Principal> principal = new ThreadLocal<Principal>(){

        @Override
        protected Principal initialValue() {
            return null;
        }
        
    };
    
    public Principal getPrincipal() {
        return principal.get();
    }

    public void setPrincipal(Principal principal) {
        this.principal.set(principal);
    }

}
