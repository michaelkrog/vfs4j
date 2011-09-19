package dk.apaq.vfs.filters;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author michael
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({AndFilterTest.class,DirectoryFilterTest.class,FileFilterTest.class,OrFilterTest.class,HiddenFilterTest.class,NotHiddenFilterTest.class})
public class FiltersSuite {

}