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
public class NoSecurity implements Security{

    public boolean canRead(Node node) {
        return true;
    }

    public boolean canWrite(Node node) {
        return true;
    }

}
