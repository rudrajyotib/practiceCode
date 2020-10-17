package leetcode.hard.largestRectangleInHistogram;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SolutionTest {

    private final List<List<int[]>> testCases = new ArrayList<List<int[]>>()
    {
        {
            add(Arrays.asList(new int[]{1}, new int[]{1}));
            add(Arrays.asList(new int[]{2,1,5,6,2,3}, new int[]{10}));
            add(Arrays.asList(new int[]{1,3,3,7,6,6,6,2,2,3}, new int[]{24}));
            add(Arrays.asList(new int[]{1,3,3,7,0,6,6,6,2,2,3}, new int[]{18}));
            add(Arrays.asList(new int[]{1,1,1,1,1,1,1,1}, new int[]{8}));
        }
    };

    @Test
    public void evaluate()
    {
        for (int i=0;i<testCases.size(); i++)

        {
            Solution solution = new Solution();
            assertThat(String.format("test case %d failed",i+1),
                    solution.largestRectangleArea(testCases.get(i).get(0)),
                    is(testCases.get(i).get(1)[0]));
        }
    }

    @Test
    public void evaluateSingleTestCase()
    {
        int testIndex = 2;
        Solution solution = new Solution();
        assertThat(String.format("test case %d failed",testIndex+1),
                solution.largestRectangleArea(testCases.get(testIndex).get(0)),
                is(testCases.get(testIndex).get(1)[0]));
    }

}