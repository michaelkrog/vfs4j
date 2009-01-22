/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.javavfs;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author michael
 */
public class MimeType {


    private static boolean initialized=false;
    private static Properties mimetypes;

    private static void init(){
        if(initialized) return;

        mimetypes=new Properties();
        try {
            mimetypes.load(MimeType.class.getResourceAsStream("mimetypes.properties"));
        } catch (IOException ex) {
            Logger.getLogger(MimeType.class.getName()).log(Level.SEVERE, null, ex);
        }

        initialized=true;

    }

    /**
     * Retrieves the mimetype.
     * @param file The file.
     * @return The mimetype.
     */
    public static String retrieve(File file){
        init();
        return mimetypes.getProperty(file.getSuffix());
    }

    public static String retrieve(String suffix){
        init();
        return mimetypes.getProperty(suffix);
    }
}
