package leetcode.hard.wildCardMatch;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SolutionTest {

    List<String[]> testCases = new ArrayList<String[]>()
    {
        {
            add(new String[]{"","","True"});
            add(new String[]{"","a*?","False"});
            add(new String[]{"a","a","True"});
            add(new String[]{"a","","False"});
            add(new String[]{"a","*","True"});
            add(new String[]{"abc","a**","True"});
            add(new String[]{"abc","a**??","True"});
            add(new String[]{"abc","a??","True"});
            add(new String[]{"abc","a???","False"});
            add(new String[]{"aaaaaaaaa","a*","True"});
            add(new String[]{"aaaaaaaaa","a*??????****","True"});
            add(new String[]{"aaaaaaaaab","a*??????****","True"});
            add(new String[]{"aaaaaaaaab","a*??????****c","False"});
            add(new String[]{"abbabb","a*a*b","True"});
            add(new String[]{"abbabb","a*a?b","True"});
        }
    };

    @Test
    public void evaluate()
    {
        for (int i=0;i<testCases.size();i++)
        {
            System.out.println("Executing test case::"+(i+1));
            Solution solution = new Solution();
            assertThat(String.format("Test case %d failed", i+1),
                    solution.isMatch(testCases.get(i)[0], testCases.get(i)[1]),
                    is(Boolean.parseBoolean(testCases.get(i)[2])));
        }
    }

    @Test
    public void evaluateSingleTestCase()
    {
        int i=4;
        Solution solution = new Solution();
        assertThat(String.format("Test case %d failed", i+1),
                solution.isMatch(testCases.get(i)[0], testCases.get(i)[1]),
                is(Boolean.parseBoolean(testCases.get(i)[2])));
    }

}


