package leetcode.hard.dataObjects;

public class TestCaseInputAndExpectation<T, R>
{
    private T input;
    private R output;

    public TestCaseInputAndExpectation(T input, R output)
    {
        this.input = input;
        this.output = output;
    }

    public T getInput()
    {
        return input;
    }

    public R getOutput()
    {
        return output;
    }
}
