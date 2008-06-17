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
    public boolean canRead(Node node);
    public boolean canWrite(Node node);
    
    
}
