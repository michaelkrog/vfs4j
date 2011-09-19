/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dk.apaq.vfs.filters;

import dk.apaq.vfs.Node;
import dk.apaq.vfs.NodeFilter;

/**
 *
 * @author mzk
 */
public class DirectoryFilter implements NodeFilter{

    public boolean accept(Node node) {
        return node.isDirectory();
    }

}
