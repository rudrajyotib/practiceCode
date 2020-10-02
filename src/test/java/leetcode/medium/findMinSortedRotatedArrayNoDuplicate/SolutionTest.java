package leetcode.medium.findMinSortedRotatedArrayNoDuplicate;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SolutionTest {

    private final List<int[]> inputs = new ArrayList<int[]>()
    {
        {
            add(new int[]{1,2,3,4});
            add(new int[]{1});
            add(new int[]{2,1});
            add(new int[]{1,2});
            add(new int[]{6,7,8,1,2});
            add(new int[]{6,7,8,9,1});
        }
    };

    private final int[] results = new int[]{1,1,1,1,1,1};

    @Test
    public void evaluate()
    {
        Solution solution = new Solution();
        for (int i=0;i<inputs.size();i++)
        {
            assertThat(String.format("Test case %d failed", i+1),
                    solution.findMin(inputs.get(i)), is(results[i]));
        }
    }

    @Test
    public void runSingTestCase()
    {
        Solution solution = new Solution();
        int testIndex = 4;
        assertThat(solution.findMin(inputs.get(testIndex)), is(results[testIndex]));
    }

}