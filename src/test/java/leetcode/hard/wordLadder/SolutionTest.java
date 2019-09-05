package leetcode.hard.wordLadder;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class SolutionTest
{
    @Test
    public void shouldFindOutSinglePathSolution()
    {
        Solution solution = new Solution();
        List<List<String>> result = solution.findLadders("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log", "cog"));
        assertThat(result, is(notNullValue()));
        assertThat(result.size(), is(2));
        List<List<String>> expectedResult = new ArrayList<List<String>>()
        {
            {
                add(Arrays.asList("hit", "hot", "dot", "dog", "cog"));
                add(Arrays.asList("hit", "hot", "lot", "log", "cog"));
            }
        };
        for (List<String> strings : result)
        {
            expectedResult.removeIf(expectedStrings -> {
                for (int i = 0; i < expectedStrings.size(); i++)
                {
                    if (!strings.get(i).equals(expectedStrings.get(i)))
                    {
                        return false;
                    }
                }
                return expectedStrings.size() == strings.size();
            });
        }

        assertThat(expectedResult.size(), is(0));
    }

    @Test
    public void shouldNotFindSolutionIfBeginWordDoesNotMatchInDictionary()
    {
        Solution solution = new Solution();
        List<List<String>> result = solution.findLadders("ait", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log", "cog"));
        assertThat(result, is(notNullValue()));
        assertThat(result.size(), is(0));
    }

    @Test
    public void shouldNotFindSolutionIfEndWordDoesNotMatchInDictionary()
    {
        Solution solution = new Solution();
        List<List<String>> result = solution.findLadders("hit", "cag", Arrays.asList("hot", "dot", "dog", "lot", "log", "cog"));
        assertThat(result, is(notNullValue()));
        assertThat(result.size(), is(0));
    }

    @Test
    public void shouldNotFindSolutionIfBeginWordLengthDoesNotMatchInDictionary()
    {
        Solution solution = new Solution();
        List<List<String>> result = solution.findLadders("hiit", "cag", Arrays.asList("hot", "dot", "dog", "lot", "log", "cog"));
        assertThat(result, is(notNullValue()));
        assertThat(result.size(), is(0));
    }

    @Test
    public void shouldNotFindSolutionIfEndWordLengthDoesNotMatchInDictionary()
    {
        Solution solution = new Solution();
        List<List<String>> result = solution.findLadders("hit", "caag", Arrays.asList("hot", "dot", "dog", "lot", "log", "cog"));
        assertThat(result, is(notNullValue()));
        assertThat(result.size(), is(0));
    }

    @Test
    public void shouldNotFindSolutionIfTheDictionaryIsNotConnectedTillEnd()
    {
        Solution solution = new Solution();
        List<List<String>> result = solution.findLadders("hit", "cog", Arrays.asList("hot", "dot", "dag", "and", "log", "cog"));
        assertThat(result, is(notNullValue()));
        assertThat(result.size(), is(0));
    }
}