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

    public Path() {
    }

    public Path(Path parent, String child) {
        this.levels.addAll(parent.levels);
        this.levels.add(child);
    }

    public Path(String path) {
        if(path.startsWith("/"))
            path=path.substring(1);
        
        if(path.endsWith("/"))
            path.substring(0,path.length()-1);
        
        String[] elements = path.split("/");
        
        for(String element:elements)
            levels.add(element);
    }
    
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

    public Path clone(){
        Path clone = new Path();
        clone.levels.addAll(this.levels);
        return clone;
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
        if(levels.isEmpty())
            return "/";
        
        StringBuilder buf = new StringBuilder();
        
        for(String level:levels){
            buf.append("/");
            buf.append(level);
        }
        return buf.toString();
    }

    @Override
    public boolean equals(Object compareTo) {
        Path otherPath = (Path)compareTo;

        if(otherPath.levels.size()!=levels.size())
            return false;

        for(int i=0;i<levels.size();i++){
            if(!levels.get(i).equals(otherPath.levels.get(i)))
                return false;
        }
        return true;
    }


}
