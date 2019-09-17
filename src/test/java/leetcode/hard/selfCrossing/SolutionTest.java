package leetcode.hard.selfCrossing;

import leetcode.hard.dataObjects.TestCaseInputAndExpectation;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class SolutionTest
{
    private List<TestCaseInputAndExpectation<int[], Boolean>> testCaseInputAndExpectations
        = new ArrayList<TestCaseInputAndExpectation<int[], Boolean>>()
    {
        {
            add(new TestCaseInputAndExpectation<>(new int[]{2, 1, 1, 2}, true));
            add(new TestCaseInputAndExpectation<>(new int[]{1, 2, 3, 4}, false));
            add(new TestCaseInputAndExpectation<>(new int[]{1, 1, 1, 1}, true));
            add(new TestCaseInputAndExpectation<>(new int[]{1, 1, 2, 1, 1}, true));
            add(new TestCaseInputAndExpectation<>(new int[]{1, 1, 2, 2, 1, 1}, true));
            add(new TestCaseInputAndExpectation<>(new int[]{3, 3, 4, 2, 2}, false));
            add(new TestCaseInputAndExpectation<>(new int[]{1, 1, 2, 2, 3, 1, 1}, true));
            add(new TestCaseInputAndExpectation<>(new int[]{2, 2, 3, 3, 2, 2}, true));
        }
    };

    @Test
    public void testCase()
    {
        IntStream.range(0, testCaseInputAndExpectations.size())
            .forEach(value -> {
                Solution solution = new Solution();
                assertThat(String.format("Test case %d failed", value + 1), solution.isSelfCrossing(testCaseInputAndExpectations.get(value).getInput()), is(testCaseInputAndExpectations.get(value).getOutput()));
            });
    }

    @Test
    public void runIndividualTestCase()
    {
        Solution solution = new Solution();
        int value = 7;
        assertThat(String.format("Test case %d failed", value), solution.isSelfCrossing(testCaseInputAndExpectations.get(value).getInput()), is(testCaseInputAndExpectations.get(value).getOutput()));
    }
}