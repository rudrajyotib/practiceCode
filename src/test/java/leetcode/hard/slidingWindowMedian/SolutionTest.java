package leetcode.hard.slidingWindowMedian;

import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class SolutionTest
{
    @Test
    public void testCase1()
    {
        Solution solution = new Solution();
        assertThat(new double[]{3d, 4d, 5d, 6d, 7d}, is(solution.medianSlidingWindow(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, 5)));
    }

    @Test
    public void testCase2()
    {
        Solution solution = new Solution();
        assertThat((solution.medianSlidingWindow(new int[]{10, 12, 3, 15, 5, 16, 7, 18, 9}, 5)), is(new double[]{10d, 12d, 7d, 15d, 9d}));
    }

    @Test
    public void testCase3()
    {
        Solution solution = new Solution();
        assertThat(new double[]{11d, 7.5d, 9d, 10d, 10.5d, 11.5d, 12.5d, 13.5d}, is(solution.medianSlidingWindow(new int[]{10, 12, 3, 15, 5, 16, 7, 18, 9}, 2)));
    }

    @Test
    public void testCase4()
    {
        Solution solution = new Solution();
        assertThat(new double[]{11d, 8.5d, 10d, 11d, 11.5d, 12.5d}, is(solution.medianSlidingWindow(new int[]{10, 12, 3, 15, 5, 16, 7, 18, 9}, 4)));
    }

    @Test
    public void testCase5()
    {
        Solution solution = new Solution();
        assertThat((solution.medianSlidingWindow(new int[]{10, 12, 3, 15}, 1)), is(new double[]{10d, 12d, 3d, 15d}));
    }

    @Test
    public void testCase6()
    {
        Solution solution = new Solution();
        assertThat((solution.medianSlidingWindow(new int[]{10, 10, 10, 10, 9, 10, 10, 10, 10}, 3)), is(new double[]{10d, 10d, 10d, 10d, 10d, 10d, 10d}));
    }

    @Test
    public void testCase7()
    {
        Solution solution = new Solution();
        assertThat((solution.medianSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3)), is(new double[]{1.0d, -1.0d, -1.0d, 3.0d, 5.0d, 6.0d}));
    }

    @Test
    public void testCase8()
    {
        Solution solution = new Solution();
        assertThat((solution.medianSlidingWindow(new int[]{5, 2, 2, 7, 3, 7, 9, 0, 2, 3}, 9)), is(new double[]{3d, 3d}));
    }

    @Test
    public void testCase9()
    {
        Solution solution = new Solution();
        assertThat((solution.medianSlidingWindow(new int[]{2147483647, 2147483647}, 2)), is(new double[]{2147483647d}));
    }
}