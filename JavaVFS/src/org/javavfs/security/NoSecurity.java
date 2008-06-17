/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.javavfs.security;

import org.javavfs.Node;

/**
 *
 * @author michael
 */
public class NoSecurity extends AbstractSecurity{

    public boolean canRead(Node node) {
        return true;
    }

    public boolean canWrite(Node node) {
        return true;
    }

    public void checkRead(Node node) {}

    public void checkWrite(Node node) {}

}
