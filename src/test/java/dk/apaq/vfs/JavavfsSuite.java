package dk.apaq.vfs;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author michael
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({NativeFSFileTest.class,NativeFSDirectoryTest.class,NativeFSUseTest.class,
                    CIFSFileTest.class,CIFSDirectoryTest.class,CIFSUseTest.class})
public class JavavfsSuite {

    public JavavfsSuite() {
        
    }

}