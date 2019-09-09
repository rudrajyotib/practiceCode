package leetcode.hard.medianFromDataStream;

import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class MedianFinderTest
{
    @Test
    public void shouldTestSingleNumber()
    {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(1);
        assertThat(medianFinder.findMedian(), is(1d));
    }

    @Test
    public void shouldTestTwoDifferentNumbers()
    {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(1);
        medianFinder.addNum(2);
        assertThat(medianFinder.findMedian(), is(1.5d));
    }

    @Test
    public void shouldTestTwoSameNumbers()
    {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(2);
        medianFinder.addNum(2);
        assertThat(medianFinder.findMedian(), is(2d));
    }

    @Test
    public void shouldTestThreeNumbersPushedInOrder()
    {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(2);
        medianFinder.addNum(3);
        medianFinder.addNum(4);
        assertThat(medianFinder.findMedian(), is(3d));
    }

    @Test
    public void shouldTestThreeNumbersPushedOutOfOrder()
    {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(4);
        medianFinder.addNum(3);
        medianFinder.addNum(2);
        assertThat(medianFinder.findMedian(), is(3d));
    }

    @Test
    public void shouldTestAnyNumbersPushedOutOfOrder2()
    {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(4);
        medianFinder.addNum(3);
        medianFinder.addNum(2);
        assertThat(medianFinder.findMedian(), is(3d));
        medianFinder.addNum(10);
        medianFinder.addNum(9);
        assertThat(medianFinder.findMedian(), is(4d));
        medianFinder.addNum(9);
        assertThat(medianFinder.findMedian(), is(6.5d));
    }

    @Test
    public void shouldTestAnyNumbersPushedOutOfOrder1()
    {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(6);
        assertThat(medianFinder.findMedian(), is(6d));
        medianFinder.addNum(10);
        assertThat(medianFinder.findMedian(), is(8d));
        medianFinder.addNum(2);
        assertThat(medianFinder.findMedian(), is(6d));
        medianFinder.addNum(6);
        assertThat(medianFinder.findMedian(), is(6d));
        medianFinder.addNum(5);
        assertThat(medianFinder.findMedian(), is(6d));
        medianFinder.addNum(0);
        assertThat(medianFinder.findMedian(), is(5.5d));
        medianFinder.addNum(6);
        assertThat(medianFinder.findMedian(), is(6d));
        medianFinder.addNum(3);
        assertThat(medianFinder.findMedian(), is(5.5d));
        medianFinder.addNum(1);
        assertThat(medianFinder.findMedian(), is(5d));
        medianFinder.addNum(0);
        assertThat(medianFinder.findMedian(), is(4d));
        medianFinder.addNum(0);
        assertThat(medianFinder.findMedian(), is(3d));
    }
}