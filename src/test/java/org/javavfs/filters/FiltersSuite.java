/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.javavfs.filters;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author michael
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({org.javavfs.filters.AndFilterTest.class,org.javavfs.filters.DirectoryFilterTest.class,org.javavfs.filters.FileFilterTest.class,org.javavfs.filters.OrFilterTest.class,org.javavfs.filters.HiddenFilterTest.class,org.javavfs.filters.NotHiddenFilterTest.class})
public class FiltersSuite {

}