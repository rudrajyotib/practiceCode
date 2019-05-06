package hackerRank;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class JumpingOnTheCloudTest {

    @Test
    public void testCase1() {
        JumpingOnTheCloud jumpingOnTheCloud = new JumpingOnTheCloud(new int[]{0, 0, 0, 0, 1, 0});
        assertThat(jumpingOnTheCloud.howManyJumpsNeeded(), is(3));
    }

    @Test
    public void testCase2() {
        JumpingOnTheCloud jumpingOnTheCloud = new JumpingOnTheCloud(new int[]{0, 0, 0, 1, 0, 0});
        assertThat(jumpingOnTheCloud.howManyJumpsNeeded(), is(3));
    }

}