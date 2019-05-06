package hackerRank;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SherlockAndAnagramsTest {

    @Test
    public void testCase1() {
        SherlockAndAnagrams sherlockAndAnagrams = new SherlockAndAnagrams("ABCD");
        assertThat(sherlockAndAnagrams.countAnagrams(), is(0));
    }

    @Test
    public void testCase2() {
        SherlockAndAnagrams sherlockAndAnagrams = new SherlockAndAnagrams("KKKK");
        assertThat(sherlockAndAnagrams.countAnagrams(), is(10));
    }

    @Test
    public void testCase3() {
        SherlockAndAnagrams sherlockAndAnagrams = new SherlockAndAnagrams("ifailuhkqq");
        assertThat(sherlockAndAnagrams.countAnagrams(), is(3));
    }

    @Test
    public void testCase4() {
        SherlockAndAnagrams sherlockAndAnagrams = new SherlockAndAnagrams("abba");
        assertThat(sherlockAndAnagrams.countAnagrams(), is(4));
    }

}