package leetcode.hard.textJustification;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.*;

public class SolutionTest
{
    @Test
    public void shouldJustifyAndAddSpaceEvenlyWhenPossible()
    {
        matchExpectations(new String[]{"I", "abc", "def", "g", "hij", "klm"}, 9, new ArrayList<String>()
        {
            {
                add("I abc def");
                add("g hij klm");
            }
        });
    }

    @Test
    public void shouldJustifyAndAddSpaceWhenLastLineDoesNotFillEntireWidth()
    {
        matchExpectations(new String[]{"I", "abc", "def", "g", "hij", "klm", "123456789"}, 9, new ArrayList<String>()
        {
            {
                add("I abc def");
                add("g hij klm");
                add("123456789");
            }
        });
    }

    @Test
    public void exampleTestCase1()
    {
        matchExpectations(new String[]{"This", "is", "an", "example", "of", "text", "justification."}, 16, new ArrayList<String>()
        {
            {
                add("This    is    an");
                add("example  of text");
                add("justification.  ");
            }
        });
    }

    @Test
    public void exampleTestCase2()
    {
        matchExpectations(new String[]{"What", "must", "be", "acknowledgment", "shall", "be"}, 16, new ArrayList<String>()
        {
            {
                add("What   must   be");
                add("acknowledgment  ");
                add("shall be        ");
            }
        });
    }

    @Test
    public void exampleTestCase3()
    {
        matchExpectations(new String[]{
            "Science", "is", "what", "we", "understand", "well", "enough", "to", "explain",
            "to", "a", "computer.", "Art", "is", "everything", "else", "we", "do"
        }, 20, new ArrayList<String>()
        {
            {
                add("Science  is  what we");
                add("understand      well");
                add("enough to explain to");
                add("a  computer.  Art is");
                add("everything  else  we");
                add("do                  ");
            }
        });
    }

    private void matchExpectations(String[] words, int maxWidth, List<String> expectation)
    {
        Solution solution = new Solution();
        List<String> result = solution.fullJustify(words, maxWidth);
        assertThat(result, is(notNullValue()));
        assertThat(result.size(), is(expectation.size()));
        IntStream
            .range(0, expectation.size())
            .forEach(value -> assertThat(String.format("Result at index %d does not match", value), result.get(value), is(expectation.get(value))));
    }
}