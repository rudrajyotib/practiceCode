package ds;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class TechGigNewYarPartyTest {

    @Test
    public void testCase1() {
        TechGigNewYarParty techGigNewYarParty = new TechGigNewYarParty(new int[] {6,5,1,-9,-8,4,-1,11});
        Result maxSum = techGigNewYarParty.findMaxSum();
        assertThat(maxSum.consolidate(), is(String.format("%d\n%s",22, "[0, 2, 5, 7]")) );
    }

    @Test
    public void testCase2() {
        TechGigNewYarParty techGigNewYarParty = new TechGigNewYarParty(new int[] {-1,7,8,-5,4});
        Result maxSum = techGigNewYarParty.findMaxSum();
        assertThat(maxSum.consolidate(), is(String.format("%d\n%s",12, "[2, 4]")) );
    }

    @Test
    public void testCase3() {
        TechGigNewYarParty techGigNewYarParty = new TechGigNewYarParty(new int[] {11,12,-2,-1});
        Result maxSum = techGigNewYarParty.findMaxSum();
        assertThat(maxSum.consolidate(), is(String.format("%d\n%s",12, "[1]")) );
    }

    @Test
    public void testCase4() {
        TechGigNewYarParty techGigNewYarParty = new TechGigNewYarParty(new int[] {4,5,4,3});
        Result maxSum = techGigNewYarParty.findMaxSum();
        assertThat(maxSum.consolidate(), is(String.format("%d\n%s",8, "[0, 2]")) );
    }

    @Test
    public void testCase5() {
        TechGigNewYarParty techGigNewYarParty = new TechGigNewYarParty(new int[] {5, 10, 4, -1});
        Result maxSum = techGigNewYarParty.findMaxSum();
        assertThat(maxSum.consolidate(), is(String.format("%d\n%s",10, "[1]")) );
    }

    @Test
    public void testCase6() {
        TechGigNewYarParty techGigNewYarParty = new TechGigNewYarParty(new int[] {-1, -1, 10, 1});
        Result maxSum = techGigNewYarParty.findMaxSum();
        assertThat(maxSum.consolidate(), is(String.format("%d\n%s",10, "[2]")) );
    }

    @Test
    public void testCase7() {
        TechGigNewYarParty techGigNewYarParty = new TechGigNewYarParty(new int[] {6, 9, -3, 9, 9, 2, 4, 8, -7, -5, 9});
        Result maxSum = techGigNewYarParty.findMaxSum();
        assertThat(maxSum.consolidate(), is(String.format("%d\n%s",37, "[1, 3, 5, 7, 10]")) );
    }

    @Test
    public void testCase8() {
        TechGigNewYarParty techGigNewYarParty = new TechGigNewYarParty(new int[] {9,-9});
        Result maxSum = techGigNewYarParty.findMaxSum();
        assertThat(maxSum.consolidate(), is(String.format("%d\n%s",9, "[0]")) );
    }
}