package leetcode.hard.distinctSubsequence;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SolutionTest {

    private final List<String[]> testCases = new ArrayList<String[]>()
    {
        {
            add(new String[]{"1","1","1"});
            add(new String[]{"","","1"});
            add(new String[]{"aa","","1"});
            add(new String[]{"aabbcc","abc","8"});
            add(new String[]{"aaaaaa","a","6"});
            add(new String[]{"aaaaaa","aa","15"});
            add(new String[]{"aaabbbccc","abc","27"});
            add(new String[]{"aaabbbcccaaabbbccc","abc","108"});
        }
    };

    @Test
    public void evaluate()
    {
        for(int i=0;i<testCases.size();i++)
        {
            Solution solution = new Solution();
            assertThat(String.format("Test case %d failed", i+1),
                    solution.numDistinct(testCases.get(i)[0], testCases.get(i)[1]),
                    is(Integer.parseInt(testCases.get(i)[2])));
        }
    }


}