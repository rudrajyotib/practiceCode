package leetcode.hard.firstMissingPositive;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SolutionTest {

    private Solution solution;

    @Before
    public void setUp() {
        solution = new Solution();
    }

    @Test
    public void testCase1() {
        assertThat(solution.firstMissingPositive(new int[]{3, 2, 1, 0}), is(4));
    }

    @Test
    public void testCase2() {
        assertThat(solution.firstMissingPositive(new int[]{3, 2, 1, 0, 8, 7}), is(4));
    }

    @Test
    public void testCase3() {
        assertThat(solution.firstMissingPositive(new int[]{3, 2, 1, 0, 8, 7, -5, -4}), is(4));
    }

    @Test
    public void testCase4() {
        assertThat(solution.firstMissingPositive(new int[]{}), is(1));
    }

    @Test
    public void testCase5() {
        assertThat(solution.firstMissingPositive(new int[]{1}), is(2));
    }

    @Test
    public void testCase6() {
        assertThat(solution.firstMissingPositive(new int[]{7}), is(1));
    }

    @Test
    public void testCase7() {
        assertThat(solution.firstMissingPositive(new int[]{1, 10000}), is(2));
    }

    @Test
    public void testCase8() {
        assertThat(solution.firstMissingPositive(new int[]{7, 8, 9, 11, 12}), is(1));
    }

    @Test
    public void testCase9() {
        assertThat(solution.firstMissingPositive(new int[]{1, 2, 3, 10, 2147483647, 9}), is(4));
    }

    @Test
    public void testCase10() {
        assertThat(solution.firstMissingPositive(new int[]{1, 2, 0}), is(3));
    }

    @Test
    public void testCase11() {
        assertThat(solution.firstMissingPositive(new int[]{0, 2, 2, 1, 1}), is(3));
    }

    @Test
    public void testCase12() {
        assertThat(solution.firstMissingPositive(new int[]{1, 2, 2, 1, 3, 1, 0, 4, 0}), is(5));
    }
}