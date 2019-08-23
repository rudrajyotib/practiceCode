package leetcode.hard.longestParanthesesSequence;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class LongestParenthesesSequenceSolutionTest {

    @Test
    public void testCase1() {
        Solution solution = new Solution();
        assertThat(solution.longestValidParentheses(")))))))"), is(0));
    }

    @Test
    public void testCase2() {
        Solution solution = new Solution();
        assertThat(solution.longestValidParentheses("((((()))))"), is(10));
    }

    @Test
    public void testCase3() {
        Solution solution = new Solution();
        assertThat(solution.longestValidParentheses(")(()))((())())"), is(8));
    }

    @Test
    public void testCase4() {
        Solution solution = new Solution();
        assertThat(solution.longestValidParentheses(")()()()()()()((())))))))(((((("), is(18));
    }

    @Test
    public void testCase5() {
        Solution solution = new Solution();
        assertThat(solution.longestValidParentheses("(()()"), is(4));
    }

    @Test
    public void testCase6() {
        Solution solution = new Solution();
        assertThat(solution.longestValidParentheses("()(()"), is(2));
    }

}