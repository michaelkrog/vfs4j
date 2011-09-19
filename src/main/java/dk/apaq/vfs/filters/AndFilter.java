/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dk.apaq.vfs.filters;

import java.util.ArrayList;
import dk.apaq.vfs.Node;
import dk.apaq.vfs.NodeFilter;

/**
 *
 * @author mzk
 */
public class AndFilter implements NodeFilter{

    public AndFilter(NodeFilter...filters) {
        for(NodeFilter filter:filters)
            this.filters.add(filter);
    }
    
    ArrayList<NodeFilter> filters = new ArrayList<NodeFilter>();

    public boolean accept(Node node) {
        for(NodeFilter filter:filters){
            if(!filter.accept(node))
                return false;
        }
        return true;
    }

}
