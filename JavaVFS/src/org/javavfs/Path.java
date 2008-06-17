/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.javavfs;

import java.util.ArrayList;

/**
 *
 * @author michael
 */
public class Path {
    
    ArrayList<String> levels = new ArrayList<String>();
    
    public void addLevel(String level){
        levels.add(level);
    }
    
    public void addLevel(int index, String level){
        levels.add(index,level);
    }
    
    public void removeLevel(int levelindex){
        levels.remove(levelindex);
    }
    
    public String getLeaf(){
        return levels.get(levels.size()-1);
    }
    
    public int getLevels(){
        return levels.size();
    }
    
    public String getLevel(int levelindex){
        return levels.get(levelindex);
    }
    
    public String toString(){
        if(levels.size()==0)
            return "/;";
        
        StringBuffer buf = new StringBuffer();
        
        for(String level:levels){
            buf.append("/");
            buf.append(level);
        }
        return buf.toString();
    }
}
