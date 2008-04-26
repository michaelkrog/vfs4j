/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.javavfs.filters;

import java.util.ArrayList;
import org.javavfs.Node;
import org.javavfs.NodeFilter;

/**
 *
 * @author mzk
 */
public class OrFilter implements NodeFilter{

    public OrFilter(NodeFilter...filters) {
        for(NodeFilter filter:filters)
            this.filters.add(filter);
    }
    
    ArrayList<NodeFilter> filters = new ArrayList<NodeFilter>();

    public boolean accept(Node node) {
        for(NodeFilter filter:filters){
            if(filter.accept(node))
                return true;
        }
        return false;
    }

}
