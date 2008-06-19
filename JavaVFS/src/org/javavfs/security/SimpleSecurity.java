/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.javavfs.security;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import org.javavfs.Node;
import org.javavfs.Path;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author michael
 */
public class SimpleSecurity implements Security{

    public SimpleSecurity(File securityDir) {
        this.securityDir = securityDir;
    }

    File securityDir;
    
    private JSONObject getPrincipalData(String username) throws JSONException, UnsupportedEncodingException, IOException{
        File userfile = new File(securityDir, username);
        if(!userfile.exists()){
            throw new SecurityException("User '"+username+"' does not exist.");
        }
        
        JSONObject json = new JSONObject(new FileInputStream(userfile));
        return json;
    }
    
    private JSONObject getAccessInfo(JSONObject securitydata,Path path){
        //TODO
        return null;
    }
            
    
    public boolean canRead(Principal principal, Node node) {
        return true;
    }

    public boolean canWrite(Principal principal, Node node) {
        return true;
    }

    public void checkRead(Principal principal, Node node) {
        if(!canRead(principal, node))
            throw new SecurityException("Not allowed to read from '"+node.getPath().toString()+"'");
    }

    public void checkWrite(Principal principal, Node node) {
        if(!canWrite(principal, node))
            throw new SecurityException("Not allowed to write to '"+node.getPath().toString()+"'");
    }

    public Principal getPrincipal(String username, String password) {
        return new Principal() {

            public String getName() {
                return "mzk";
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
