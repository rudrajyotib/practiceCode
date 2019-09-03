package leetcode.hard.validNumber;

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
    private List<TestCaseInputAndExpectation<String, Boolean>> testCaseInputAndExpectations = new ArrayList<>();

    @Before
    public void defineTestCases()
    {
        testCaseInputAndExpectations.add(new TestCaseInputAndExpectation<>("1e1", true));
        testCaseInputAndExpectations.add(new TestCaseInputAndExpectation<>("1e1   ", true));
        testCaseInputAndExpectations.add(new TestCaseInputAndExpectation<>("   1e1   ", true));
        testCaseInputAndExpectations.add(new TestCaseInputAndExpectation<>("   1e1", true));
        testCaseInputAndExpectations.add(new TestCaseInputAndExpectation<>("--1e1   ", false));
        testCaseInputAndExpectations.add(new TestCaseInputAndExpectation<>("-1.2000e1   ", true));
        testCaseInputAndExpectations.add(new TestCaseInputAndExpectation<>("-1.2000e1.2   ", false));
        testCaseInputAndExpectations.add(new TestCaseInputAndExpectation<>("-12000e1.2   ", false));
        testCaseInputAndExpectations.add(new TestCaseInputAndExpectation<>("-000e1.2   ", false));
        testCaseInputAndExpectations.add(new TestCaseInputAndExpectation<>("--000e1.2   ", false));
        testCaseInputAndExpectations.add(new TestCaseInputAndExpectation<>("-001e12e1   ", false));
        testCaseInputAndExpectations.add(new TestCaseInputAndExpectation<>("-0011.00000e12.2e1   ", false));
        testCaseInputAndExpectations.add(new TestCaseInputAndExpectation<>("0e2", true));
        testCaseInputAndExpectations.add(new TestCaseInputAndExpectation<>("0e", false));
        testCaseInputAndExpectations.add(new TestCaseInputAndExpectation<>("e2", false));
        testCaseInputAndExpectations.add(new TestCaseInputAndExpectation<>("1", true));
        testCaseInputAndExpectations.add(new TestCaseInputAndExpectation<>("00000", true));
        testCaseInputAndExpectations.add(new TestCaseInputAndExpectation<>("    ", false));
        testCaseInputAndExpectations.add(new TestCaseInputAndExpectation<>("  0.1  ", true));
        testCaseInputAndExpectations.add(new TestCaseInputAndExpectation<>("  .1  ", true));
        testCaseInputAndExpectations.add(new TestCaseInputAndExpectation<>("  00000.00001e1  ", true));
        testCaseInputAndExpectations.add(new TestCaseInputAndExpectation<>("  00000.00001e  ", false));
        testCaseInputAndExpectations.add(new TestCaseInputAndExpectation<>("  1.", true));
        testCaseInputAndExpectations.add(new TestCaseInputAndExpectation<>("  .1", true));
        testCaseInputAndExpectations.add(new TestCaseInputAndExpectation<>("  e", false));
        testCaseInputAndExpectations.add(new TestCaseInputAndExpectation<>("  .", false));
        testCaseInputAndExpectations.add(new TestCaseInputAndExpectation<>("  .0000  ", true));
        testCaseInputAndExpectations.add(new TestCaseInputAndExpectation<>(" 45.e2  ", true));
        testCaseInputAndExpectations.add(new TestCaseInputAndExpectation<>(" 45.e  ", false));
        testCaseInputAndExpectations.add(new TestCaseInputAndExpectation<>(" .e1  ", false));
        testCaseInputAndExpectations.add(new TestCaseInputAndExpectation<>(" .e1  ", false));
        testCaseInputAndExpectations.add(new TestCaseInputAndExpectation<>(" .e  ", false));
        testCaseInputAndExpectations.add(new TestCaseInputAndExpectation<>(" 1.e+7  ", true));
        testCaseInputAndExpectations.add(new TestCaseInputAndExpectation<>(" 1.e77-1  ", false));
        testCaseInputAndExpectations.add(new TestCaseInputAndExpectation<>(" 1.e-1  ", true));
        testCaseInputAndExpectations.add(new TestCaseInputAndExpectation<>(" 1.e-1.1  ", false));
        testCaseInputAndExpectations.add(new TestCaseInputAndExpectation<>(" 1.e-1 1  ", false));
    }

    @Test
    public void test()
    {
        IntStream.range(0, testCaseInputAndExpectations.size())
            .forEach(value -> {
                Solution solution = new Solution();
                assertThat(String.format("Test Case %d failed with input::[%s]", value, testCaseInputAndExpectations.get(value).getInput()),
                           solution.isNumber(testCaseInputAndExpectations.get(value).getInput()),
                           is(testCaseInputAndExpectations.get(value).getOutput()));
            });
    }

    @Test
    public void runSingleTest()
    {
        Solution solution = new Solution();
        assertThat(solution.isNumber(".e1"), is(false));
    }
}