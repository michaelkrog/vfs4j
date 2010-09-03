/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.javavfs.impl.sftp;

import com.jcraft.jsch.UserInfo;

/**
 *
 * @author krog
 */
public class SftpUserInfo implements UserInfo {

    public SftpUserInfo(String pass) {
        this.pass=pass;
    }

    private String pass;

    public String getPassphrase() {
        return null;
    }

    public String getPassword() {
        return pass;
    }

    public boolean promptPassword(String string) {
        return false;
    }

    public boolean promptPassphrase(String string) {
        return false;
    }

    public boolean promptYesNo(String string) {
        return true;
    }

    public void showMessage(String string) {
        
    }


}
