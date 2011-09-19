package dk.apaq.vfs.security;

import java.io.File;

/**
 * This file is not functional now. It depended on JSON but
 * I havent found a good lib for that.
 * @author michael
 */
public class SimpleSecurity /*implements Security*/{

    public SimpleSecurity(File securityDir) {
        this.securityDir = securityDir;
    }

    File securityDir;
    /*
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
    }*/

}
