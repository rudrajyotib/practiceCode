package hackerRank;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class HillsAndValleysTest {

    @Test
    public void testCase1() {
        HillsAndValleys hillsAndValleys = new HillsAndValleys("DDUUUUUDUDDDDDDUDUDUUU");
        assertThat(hillsAndValleys.countValleys(), is(2));
    }

    @Test
    public void testCase2() {
        HillsAndValleys hillsAndValleys = new HillsAndValleys("UDDDUDUU");
        assertThat(hillsAndValleys.countValleys(), is(1));
    }
}