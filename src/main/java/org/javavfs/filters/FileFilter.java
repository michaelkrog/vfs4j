/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.javavfs.filters;

import org.javavfs.Node;
import org.javavfs.NodeFilter;

/**
 *
 * @author mzk
 */
public class FileFilter implements NodeFilter{

    public boolean accept(Node node) {
        return !node.isDirectory();
    }

}
