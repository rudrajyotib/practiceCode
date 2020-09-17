package leetcode.medium.longestPalindromicSubstring;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SolutionTest {

    private static final List<AbstractMap.SimpleEntry<String, List<String>>> testCases
            = new ArrayList<AbstractMap.SimpleEntry<String, List<String>>>()
    {
        {
            add(new AbstractMap.SimpleEntry<>(
                    "aabad", Collections.singletonList("aba")
            ));
            add(new AbstractMap.SimpleEntry<>(
                    "X", Collections.singletonList("X")
            ));
            add(new AbstractMap.SimpleEntry<>(
                    "cbbd", Collections.singletonList("bb")
            ));
            add(new AbstractMap.SimpleEntry<>(
                    "ccc", Collections.singletonList("ccc")
            ));
        }
    };

    @Test
    public void executeTest()
    {

        Solution solution = new Solution();

        for (int i = 0; i < testCases.size(); i++) {
            assertThat(String.format("Test case %d does not match",i+1),testCases.get(i).getValue().contains(solution.longestPalindrome(testCases.get(i).getKey())),is(true));
        }
    }


}