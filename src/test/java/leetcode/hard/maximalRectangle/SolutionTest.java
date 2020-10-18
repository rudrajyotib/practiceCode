package leetcode.hard.maximalRectangle;

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
                    {'1','1','1','1','1','1','1','1','0','0','0'}}
    );
    private int[] results = {6,0,0,1,2,24};

    @Test
    public void evaluate()
    {
        for(int i=0;i<testCasesInputs.size();i++)
        {
            Solution solution = new Solution();
            assertThat(String.format("Test case %d failed", i+1),
                    solution.maximalRectangle(testCasesInputs.get(i)),
                    is(results[i]));
        }
    }

}