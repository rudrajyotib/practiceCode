package leetcode.hard.shortestPalindrome;

import org.junit.Test;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SolutionTest {

    public List<AbstractMap.SimpleEntry<String,String>> testCases =
            new ArrayList<AbstractMap.SimpleEntry<String, String>>()
            {
                {
                    add(new AbstractMap.SimpleEntry<>("aacecaaa","aaacecaaa"));
                    add(new AbstractMap.SimpleEntry<>("abbacd","dcabbacd"));
                    add(new AbstractMap.SimpleEntry<>("abcd","dcbabcd"));
                    add(new AbstractMap.SimpleEntry<>("ababab","bababab"));
                    add(new AbstractMap.SimpleEntry<>("ababababab","bababababab"));
                    add(new AbstractMap.SimpleEntry<>("x","x"));
                    add(new AbstractMap.SimpleEntry<>("ac","cac"));
                    add(new AbstractMap.SimpleEntry<>("aa","aa"));
                    add(new AbstractMap.SimpleEntry<>("abbbbbbbbbbba","abbbbbbbbbbba"));
                    add(new AbstractMap.SimpleEntry<>("aaaaXXXXXXXXXX","XXXXXXXXXXaaaaXXXXXXXXXX"));
                }
            };

    @Test
    public void test()
    {
        Solution solution=new Solution();
        for (int i=0;i<testCases.size(); i++)
        {
            assertThat(String.format("Test case %d failed",i+1),
                    solution.shortestPalindrome(testCases.get(i).getKey()),
                    is(testCases.get(i).getValue()));
        }
    }

}