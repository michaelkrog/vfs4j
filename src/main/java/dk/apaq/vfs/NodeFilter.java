package dk.apaq.vfs;

import java.io.Serializable;

/**
 *
 * @author mzk
 */
public interface NodeFilter extends Serializable {
    /**
     * Called to check if the filter accepts the node.
     * @param node The node to perform the check on.
     * @return True/False wether the filter accepts it or not.
     */
    public boolean accept(Node node);
}
