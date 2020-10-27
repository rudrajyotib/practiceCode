package leetcode.medium.maxNumberOccurrenceOfSubstring;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SolutionTest {

    public List<String[]> testCases = new ArrayList<String[]>()
    {
        {
            add(new String[]{"aababcaab","2","3","4","2"});
            add(new String[]{"aaaaaaaaa","2","3","4","7"});
            add(new String[]{"aaabbbbbbaaa","1","4","5","3"});
            add(new String[]{"aa","1","4","5","0"});
            add(new String[]{"","1","4","5","0"});
        }
    };

    @Test
    public void evaluate()
    {
        for (int i=0;i<testCases.size();i++)
        {
            Solution solution = new Solution();
            assertThat(String.format("Test case %d failed", i+1),
                    solution.maxFreq(testCases.get(i)[0],
                            Integer.parseInt(testCases.get(i)[1]),
                            Integer.parseInt(testCases.get(i)[2]),
                            Integer.parseInt(testCases.get(i)[3]) ),
                    is(Integer.parseInt(testCases.get(i)[4])));
        }
    }

}