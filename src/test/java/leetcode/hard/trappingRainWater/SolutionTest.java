package leetcode.hard.trappingRainWater;

import leetcode.hard.dataObjects.TestCaseInputAndExpectation;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class SolutionTest
{
    private List<TestCaseInputAndExpectation<int[], Integer>> testCases
        = new ArrayList<>();

    @Before
    public void defineTestCases()
    {
        testCases.add(new TestCaseInputAndExpectation<>(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}, 6));
        testCases.add(new TestCaseInputAndExpectation<>(new int[]{0, 2, 0, 2, 0, 3, 0, 4, 0, 0, 5, 0, 4, 0, 3, 0, 0, 6}, 38));
        testCases.add(new TestCaseInputAndExpectation<>(new int[]{0, 2, 0, 1, 0, 1, 0, 6, 0, 5, 0, 4, 0, 3, 0, 2, 0, 1, 0, 0, 0, 0, 1, 0, 2, 0, 3}, 50));
        testCases.add(new TestCaseInputAndExpectation<>(new int[]{0, 1, 2, 3, 3, 3, 4, 0, 4, 0, 3, 0, 6, 0, 2, 3, 0, 1, 0, 4, 0, 6}, 51));
        testCases.add(new TestCaseInputAndExpectation<>(new int[]{0, 1, 2, 3, 4, 0, 3, 0, 2, 0, 3, 0, 1, 3, 0, 1, 2, 0, 1, 1, 1, 1}, 19));
        testCases.add(new TestCaseInputAndExpectation<>(new int[]{0}, 0));
        testCases.add(new TestCaseInputAndExpectation<>(new int[]{0, 1, 2, 3, 4, 5, 6, 5, 4, 3, 2, 1, 0}, 0));
    }

    @Test
    public void testCase()
    {
        IntStream.range(0, testCases.size())
            .forEach(value -> {
                Solution solution = new Solution();
                assertThat(String.format("Test case %d failed", value), solution.trap(testCases.get(value).getInput()), is(testCases.get(value).getOutput()));
            });
    }
}