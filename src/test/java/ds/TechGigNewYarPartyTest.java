package ds;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class TechGigNewYarPartyTest {

    @Test
    public void testCase1() {
        TechGigNewYarParty techGigNewYarParty = new TechGigNewYarParty(new int[] {6,5,1,-9,-8,4,-1,11});
        Result maxSum = techGigNewYarParty.findMaxSum();
        assertThat(maxSum.consolidateReverseWay(), is("11416") );
    }

    @Test
    public void testCase2() {
        TechGigNewYarParty techGigNewYarParty = new TechGigNewYarParty(new int[] {-1,7,8,-5,4});
        Result maxSum = techGigNewYarParty.findMaxSum();
        assertThat(maxSum.consolidateReverseWay(), is("48") );
    }

    @Test
    public void testCase3() {
        TechGigNewYarParty techGigNewYarParty = new TechGigNewYarParty(new int[] {11,12,-2,-1});
        Result maxSum = techGigNewYarParty.findMaxSum();
        assertThat(maxSum.consolidateReverseWay(), is("12") );
    }

    @Test
    public void testCase4() {
        TechGigNewYarParty techGigNewYarParty = new TechGigNewYarParty(new int[] {4,3,4,5});
        Result maxSum = techGigNewYarParty.findMaxSum();
        assertThat(maxSum.consolidateReverseWay(), is("53") );
    }

    @Test
    public void testCase5() {
        TechGigNewYarParty techGigNewYarParty = new TechGigNewYarParty(new int[] {5, 10, 4, -1});
        Result maxSum = techGigNewYarParty.findMaxSum();
        assertThat(maxSum.consolidateReverseWay(), is("10") );
    }

    @Test
    public void testCase6() {
        TechGigNewYarParty techGigNewYarParty = new TechGigNewYarParty(new int[] {-1, -1, 10, 1});
        Result maxSum = techGigNewYarParty.findMaxSum();
        assertThat(maxSum.consolidateReverseWay(), is("10") );
    }

    @Test
    public void testCase7() {
        TechGigNewYarParty techGigNewYarParty = new TechGigNewYarParty(new int[] {6, 9, -3, 9, 9, 2, 4, 8, -7, -5, 9});
        Result maxSum = techGigNewYarParty.findMaxSum();
        assertThat(maxSum.consolidateReverseWay(), is("98299") );
    }

    @Test
    public void testCase8() {
        TechGigNewYarParty techGigNewYarParty = new TechGigNewYarParty(new int[] {9,-9});
        Result maxSum = techGigNewYarParty.findMaxSum();
        assertThat(maxSum.consolidateReverseWay(), is("9") );
    }

    @Test
    public void testCase9() {
        TechGigNewYarParty techGigNewYarParty = new TechGigNewYarParty(new int[] {1,2,4,0,0,0,7,9,0,0,0,0,-1,-2,-3,100});
        Result maxSum = techGigNewYarParty.findMaxSum();
        assertThat(maxSum.consolidateReverseWay(), is("100941") );
    }
}