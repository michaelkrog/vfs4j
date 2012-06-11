package dk.apaq.vfs;

import dk.apaq.vfs.mime.MimeType;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author michael
 */

public class Mimetest {
    
    @Test
    public void testMime() {
        assertEquals("image/png", MimeType.retrieve("png"));
    }
}
