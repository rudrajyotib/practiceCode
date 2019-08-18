package hackerRank;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.Matchers.is;


public class CountTripletsTest {

    @Test
    public void testCase1() {
        CountTriplets countTriplets = new CountTriplets(new ArrayList<Long>() {
            {
                add(1L);
                add(2L);
                add(2L);
                add(3L);
                add(1L);
                add(2L);
                add(4L);
                add(2L);
            }
        }, 2);
        Assert.assertThat(countTriplets.countTriplets(), is(4L));
    }

    @Test
    public void testCase2() {
        CountTriplets countTriplets = new CountTriplets(new ArrayList<Long>() {
            {
                add(1L);
                add(3L);
                add(9L);
                add(9L);
                add(27L);
                add(81L);
            }
        }, 3);
        Assert.assertThat(countTriplets.countTriplets(), is(6L));
    }

    @Test
    public void testCase3() {
        CountTriplets countTriplets = new CountTriplets(new ArrayList<Long>() {
            {
                add(1L);
                add(1L);
                add(5L);
                add(5L);
                add(25L);
                add(125L);
            }
        }, 5);
        Assert.assertThat(countTriplets.countTriplets(), is(6L));
    }


    @Test
    public void testCase5() {
        CountTriplets countTriplets = new CountTriplets(new ArrayList<Long>() {
            {
                add(2L);
                add(2L);
                add(2L);
                add(2L);
                add(2L);
                add(2L);
                add(2L);
                add(2L);
                add(2L);
            }
        }, 2);
        Assert.assertThat(countTriplets.countTriplets(), is(0L));
    }

    @Test
    public void testCase6() {
        CountTriplets countTriplets = new CountTriplets(new ArrayList<Long>() {
            {
                add(1L);
                add(1L);
                add(1L);
                add(1L);
            }
        }, 1);
        Assert.assertThat(countTriplets.countTriplets(), is(4L));
    }
}