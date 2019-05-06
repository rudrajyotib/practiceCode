package hackerRank;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class RepeatedStringTest {

    @Test
    public void testCase1() {
        RepeatedString repeatedString = new RepeatedString("aaaaa", 17);
        assertThat(repeatedString.countAs(), is(17L));
    }

    @Test
    public void testCase2() {
        RepeatedString repeatedString = new RepeatedString("aabaa", 17);
        assertThat(repeatedString.countAs(), is(14L));
    }

}