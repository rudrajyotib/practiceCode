package leetcode.medium.maximalSquare;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SolutionTest {

    private List<char[][]> testCasesInputs = Arrays.asList(
            new char[][]{
                    {'1','0','1','0','0'},
                    {'1','0','1','1','1'},
                    {'1','1','1','1','1'},
                    {'1','0','0','1','0'}},
            new char[][]{},
            new char[][]{{'0'}},
            new char[][]{{'1'}},
            new char[][]{{'0','1'},{'0','0'}},
            new char[][]{{'0','1'},{'0','1'}},
            new char[][]{
                    {'1','0','1','1','0','1','1','1','1','1','1'},
                    {'1','0','1','0','0','0','1','1','0','0','1'},
                    {'1','1','0','1','0','0','1','1','0','1','1'},
                    {'0','1','1','0','0','0','1','1','0','1','1'},
                    {'1','1','1','1','1','1','1','1','1','1','1'},
                    {'0','0','0','1','1','1','1','1','1','1','0'},
                    {'1','0','1','0','1','1','1','1','1','1','0'},
                    {'1','1','0','1','1','1','1','1','0','0','0'},
                    {'0','1','1','0','1','1','1','1','0','0','0'},
                    {'1','1','1','1','1','1','1','1','0','0','0'}},
            new char[][]
                    {
                            {'1','1'},
                            {'1','1'}
                    }
    );
    private int[] results = {4,0,0,1,1,1,16,4};

    @Test
    public void evaluate()
    {
        for(int i=0;i<testCasesInputs.size();i++)
        {
            System.out.println("Executing test case::"+(i+1));
            Solution solution = new Solution();
            assertThat(String.format("Test case %d failed", i+1),
                    solution.maximalSquare(testCasesInputs.get(i)),
                    is(results[i]));
        }
    }

    @Test
    public void evaluateSingleTestCase()
    {
        int testIndex = 6;
        Solution solution = new Solution();
        assertThat(String.format("Test case %d failed", testIndex+1),
                solution.maximalSquare(testCasesInputs.get(testIndex)),
                is(results[testIndex]));
    }


}