package hackerRank;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class FrequencyQueriesTest {

    @Test
    public void testCase1() {
        FrequencyQueries frequencyQueries = new FrequencyQueries();
        frequencyQueries.executeCommand(1, 1);
        frequencyQueries.executeCommand(1, 1);
        frequencyQueries.executeCommand(1, 1);
        frequencyQueries.executeCommand(3, 1);
        frequencyQueries.executeCommand(3, 3);

        List<Integer> collateResult = frequencyQueries.collateResult();

        assertThat(collateResult.get(0), is(0));
        assertThat(collateResult.get(1), is(1));
    }

    @Test
    public void testCase2() {
        FrequencyQueries frequencyQueries = new FrequencyQueries();
        frequencyQueries.executeCommand(1, 3);
        frequencyQueries.executeCommand(2, 3);
        frequencyQueries.executeCommand(3, 2);
        frequencyQueries.executeCommand(1, 4);
        frequencyQueries.executeCommand(1, 5);
        frequencyQueries.executeCommand(1, 5);
        frequencyQueries.executeCommand(1, 4);
        frequencyQueries.executeCommand(3, 2);
        frequencyQueries.executeCommand(2, 4);
        frequencyQueries.executeCommand(3, 2);

        List<Integer> collateResult = frequencyQueries.collateResult();

        assertThat(collateResult.get(0), is(0));
        assertThat(collateResult.get(1), is(1));
        assertThat(collateResult.get(2), is(1));
    }

    @Test
    public void testCase3() {
        FrequencyQueries frequencyQueries = new FrequencyQueries();
        frequencyQueries.executeCommand(1, 5);
        frequencyQueries.executeCommand(1, 6);
        frequencyQueries.executeCommand(3, 2);
        frequencyQueries.executeCommand(1, 10);
        frequencyQueries.executeCommand(1, 10);
        frequencyQueries.executeCommand(1, 6);
        frequencyQueries.executeCommand(2, 5);
        frequencyQueries.executeCommand(3, 2);

        List<Integer> collateResult = frequencyQueries.collateResult();

        assertThat(collateResult.get(0), is(0));
        assertThat(collateResult.get(1), is(1));
    }

    @Test
    public void testCase4() throws IOException {
        /*(this.getClass().getClassLoader().getResource("hackerRank/frquencyQueriesTestCase4Input.txt"));
        File file=new File()*/
        BufferedReader bufferedReaderInput = new BufferedReader(new InputStreamReader((this.getClass().getClassLoader().getResourceAsStream("hackerRank/frquencyQueriesTestCase4Input.txt"))));
        String line = bufferedReaderInput.readLine();
        FrequencyQueries frequencyQueries = new FrequencyQueries();
        int inputIndex = 0;
        while (line != null) {
            String[] inputs = line.split(" ");
            frequencyQueries.executeCommand(Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1]));
            line = bufferedReaderInput.readLine();
        }
        List<Integer> result = frequencyQueries.collateResult();
        BufferedReader bufferedReaderOutput = new BufferedReader(new InputStreamReader((this.getClass().getClassLoader().getResourceAsStream("hackerRank/frquencyQueriesTestCase4Output.txt"))));
        int outputIndex = 0;
        line = bufferedReaderOutput.readLine();
        while (line != null) {
            assertThat(String.format("Expectation does not meet at line %d", outputIndex), result.get(outputIndex), is(Integer.parseInt(line)));
            line = bufferedReaderOutput.readLine();
            ++outputIndex;
        }
    }

    @Test
    public void testCase5() throws IOException {
        /*(this.getClass().getClassLoader().getResource("hackerRank/frquencyQueriesTestCase4Input.txt"));
        File file=new File()*/
        BufferedReader bufferedReaderInput = new BufferedReader(new InputStreamReader((this.getClass().getClassLoader().getResourceAsStream("hackerRank/frquencyQueriesTestCase5Input.txt"))));
        String line = bufferedReaderInput.readLine();
        FrequencyQueries frequencyQueries = new FrequencyQueries();
        int inputIndex = 0;
        while (line != null) {
            String[] inputs = line.split(" ");
            frequencyQueries.executeCommand(Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1]));
            line = bufferedReaderInput.readLine();
        }
        List<Integer> result = frequencyQueries.collateResult();
        BufferedReader bufferedReaderOutput = new BufferedReader(new InputStreamReader((this.getClass().getClassLoader().getResourceAsStream("hackerRank/frquencyQueriesTestCase5Output.txt"))));
        int outputIndex = 0;
        line = bufferedReaderOutput.readLine();
        while (line != null) {
            assertThat(String.format("Expectation does not meet at line %d", outputIndex + 1), result.get(outputIndex), is(Integer.parseInt(line)));
            line = bufferedReaderOutput.readLine();
            ++outputIndex;
        }
    }
}