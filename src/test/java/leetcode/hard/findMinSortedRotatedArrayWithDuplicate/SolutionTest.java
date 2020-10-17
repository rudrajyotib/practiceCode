package leetcode.hard.findMinSortedRotatedArrayWithDuplicate;

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
            add(new int[]{6,7,8,1,2,3,4,5});
            add(new int[]{6,7,8,9,10,11,12,13,1,2,3,4,5});
            add(new int[]{6,7,8,8,8,8,9,1,4,6});
            add(new int[]{1,1,1,1,1,1,1,1,1});
            add(new int[]{2,2,2,2,2,2,2,2,1});
            add(new int[]{3,1,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3});
            add(new int[]{5,5,5,5,1,1,1,1,2,2,2,2,3,3,3,3,4,4,4,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5});
            add(new int[]{1,3,1,1});
        }
    };

    private final int[] results = new int[]{1,1,1,1,1,1,1,1,1,1,1,1};

    @Test
    public void evaluate()
    {
        leetcode.hard.findMinSortedRotatedArrayWithDuplicate.Solution solution =
                new leetcode.hard.findMinSortedRotatedArrayWithDuplicate.Solution();
        for (int i=0;i<inputs.size();i++)
        {
            System.out.println(String.format("Executing test case %d", i+1));
            assertThat(String.format("Test case %d failed", i+1),
                    solution.findMin(inputs.get(i)), is(results[i]));
        }
    }

    @Test
    public void runSingTestCase()
    {
        leetcode.hard.findMinSortedRotatedArrayWithDuplicate.Solution solution =
                new leetcode.hard.findMinSortedRotatedArrayWithDuplicate.Solution();
        int testIndex =11;
        assertThat(solution.findMin(inputs.get(testIndex)), is(results[testIndex]));
    }

}