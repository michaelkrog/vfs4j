/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dk.apaq.vfs;

/**
 *
 * @author mzk
 */
public interface NodeFilter {
    /**
     * Called to check if the filter accepts the node.
     * @param node The node to perform the check on.
     * @return True/False wether the filter accepts it or not.
     */
    public boolean accept(Node node);
}
