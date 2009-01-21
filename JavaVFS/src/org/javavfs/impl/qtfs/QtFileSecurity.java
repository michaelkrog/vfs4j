/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.javavfs.impl.qtfs;

import com.trolltech.qt.core.QFileInfo;
import java.security.Principal;
import org.javavfs.Node;
import org.javavfs.security.Security;

/**
 *
 * @author michael
 */
public class QtFileSecurity implements Security {

    private QFileInfo getQFileInfo(Node node){
        if(!(node instanceof QtNode))
            throw new UnsupportedOperationException("The node is not a QtNode.");
        return ((QtNode)node).getQFileInfo();
    }

    public boolean canRead(Principal principal, Node node) {
        QFileInfo fileinfo = getQFileInfo(node);
        return fileinfo.isReadable();
    }

    public boolean canWrite(Principal principal, Node node) {
        QFileInfo fileinfo = getQFileInfo(node);
        return fileinfo.isWritable();
    }

    public void checkRead(Principal principal, Node node) {
        QFileInfo fileinfo = getQFileInfo(node);
        if(!fileinfo.isReadable())
            throw new SecurityException("Read not allowed for node: [path="+fileinfo.absolutePath()+"]");
    }

    public void checkWrite(Principal principal, Node node) {
        QFileInfo fileinfo = getQFileInfo(node);
        if(!fileinfo.isWritable())
            throw new SecurityException("Write not allowed for node: [path="+fileinfo.absolutePath()+"]");
    }

    public Principal getPrincipal(final String username, String password) {
        //Principals is not really supported by this security
        //Returns a dummy principal.
        return new Principal() {

            public String getName() {
                return username;
            }
        };

    }

    public Principal getGuestPrincipal() {
        return new Principal() {

            public String getName() {
                return "Guest";
            }
        };
    }

}
