package leetcode.hard.regexmatch;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class RegexMatchSolutionTest {

    private Solution solution;

    @Before
    public void setUp() {
        solution = new Solution();
    }


    @Test
    public void testCase1() {
        assertThat(solution.isMatch("aaaabbbbbbcccccc", "a*b*c*"), is(true));
    }

    @Test
    public void testCase2() {
        assertThat(solution.isMatch("aaabbbccc", "a*b*c."), is(false));
    }

    @Test
    public void testCase3() {
        assertThat(solution.isMatch("abcdefg", "......."), is(true));
    }

    @Test
    public void testCase4() {
        assertThat(solution.isMatch("abcdef", "...*.."), is(true));
    }

    @Test
    public void testCase5() {
        assertThat(solution.isMatch("aaabbbccc", "x*e*d*a*b*c*"), is(true));
    }

    @Test
    public void testCase6() {
        assertThat(solution.isMatch("aaabbbcc", "a*b*c."), is(true));
    }

    @Test
    public void testCase7() {
        assertThat(solution.isMatch("aaabbbccc", "a*b*c*d*e*."), is(true));
    }

}