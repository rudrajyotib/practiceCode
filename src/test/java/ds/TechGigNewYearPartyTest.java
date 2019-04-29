package ds;

import org.junit.Test;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class TechGigNewYearPartyTest {

    @Test
    public void testCase1() {
        TechGigNewYearParty techGigNewYearParty = new TechGigNewYearParty(new int[] {6,5,1,-9,-8,4,-1,11});
        Result maxSum = techGigNewYearParty.findMaxSum();
        assertThat(maxSum.consolidate(), is("11416") );
    }

    @Test
    public void testCase2() {
        TechGigNewYearParty techGigNewYearParty = new TechGigNewYearParty(new int[] {-1,7,8,-5,4});
        Result maxSum = techGigNewYearParty.findMaxSum();
        assertThat(maxSum.consolidate(), is("48") );
    }

    @Test
    public void testCase3() {
        TechGigNewYearParty techGigNewYearParty = new TechGigNewYearParty(new int[] {11,12,-2,-1});
        Result maxSum = techGigNewYearParty.findMaxSum();
        assertThat(maxSum.consolidate(), is("12") );
    }

    @Test
    public void testCase4() {
        TechGigNewYearParty techGigNewYearParty = new TechGigNewYearParty(new int[] {4,3,4,5});
        Result maxSum = techGigNewYearParty.findMaxSum();
        assertThat(maxSum.consolidate(), is("54") );
    }

    @Test
    public void testCase5() {
        TechGigNewYearParty techGigNewYearParty = new TechGigNewYearParty(new int[] {5, 10, 4, -1});
        Result maxSum = techGigNewYearParty.findMaxSum();
        assertThat(maxSum.consolidate(), is("10") );
    }

    @Test
    public void testCase6() {
        TechGigNewYearParty techGigNewYearParty = new TechGigNewYearParty(new int[] {-1, -1, 10, 1});
        Result maxSum = techGigNewYearParty.findMaxSum();
        assertThat(maxSum.consolidate(), is("10") );
    }

    @Test
    public void testCase7() {
        TechGigNewYearParty techGigNewYearParty = new TechGigNewYearParty(new int[] {6, 9, -3, 9, 9, 2, 4, 8, -7, -5, 9});
        Result maxSum = techGigNewYearParty.findMaxSum();
        assertThat(maxSum.consolidate(), is("98299") );
    }

    @Test
    public void testCase8() {
        TechGigNewYearParty techGigNewYearParty = new TechGigNewYearParty(new int[] {9,-9});
        Result maxSum = techGigNewYearParty.findMaxSum();
        assertThat(maxSum.consolidate(), is("9") );
    }

    @Test
    public void testCase9() {
        TechGigNewYearParty techGigNewYearParty = new TechGigNewYearParty(new int[] {1,2,4,0,0,0,7,9,0,0,0,0,-1,-2,-3,100});
        Result maxSum = techGigNewYearParty.findMaxSum();
        assertThat(maxSum.consolidate(), is("100941") );
    }

    @Test
    public void testCase10() {
        TechGigNewYearParty techGigNewYearParty = new TechGigNewYearParty(new int[] {4, 5, 4, 3, -2, -2, -2, 4, 5, 4, 3});
        Result maxSum = techGigNewYearParty.findMaxSum();
        assertThat(maxSum.consolidate(), is("4444") );
    }

    @Test
    public void testCase11() {
        TechGigNewYearParty techGigNewYearParty = new TechGigNewYearParty(new int[] {9,1,2,8});
        Result maxSum = techGigNewYearParty.findMaxSum();
        assertThat(maxSum.consolidate(), is("89") );
    }

    @Test
    public void testCase12() {
        TechGigNewYearParty techGigNewYearParty = new TechGigNewYearParty(new int[] {-1,-2,-3,-4});
        Result maxSum = techGigNewYearParty.findMaxSum();
        assertThat(maxSum.consolidate(), is("-1") );
    }

    @Test
    public void testCase13() {
        TechGigNewYearParty techGigNewYearParty = new TechGigNewYearParty(new int[] {2,4,6,4});
        Result maxSum = techGigNewYearParty.findMaxSum();
        assertThat(maxSum.consolidate(), is("62") );
    }

    @Test
    public void testCase14() {
        TechGigNewYearParty techGigNewYearParty = new TechGigNewYearParty(new int[] {4,6,4,2});
        Result maxSum = techGigNewYearParty.findMaxSum();
        assertThat(maxSum.consolidate(), is("44") );
    }

    @Test
    public void testCase15() {
        TechGigNewYearParty techGigNewYearParty = new TechGigNewYearParty(new int[] {5,6,3,2});
        Result maxSum = techGigNewYearParty.findMaxSum();
        assertThat(maxSum.consolidate(), is("35") );
    }

    @Test
    public void testCase16() {
        TechGigNewYearParty techGigNewYearParty = new TechGigNewYearParty(new int[] {5,5,4,4});
        Result maxSum = techGigNewYearParty.findMaxSum();
        assertThat(maxSum.consolidate(), is("45") );
    }

    @Test
    public void testCase17() {
        TechGigNewYearParty techGigNewYearParty = new TechGigNewYearParty(new int[] {6,0,0,2});
        Result maxSum = techGigNewYearParty.findMaxSum();
        assertThat(maxSum.consolidate(), is("26") );
    }

    @Test
    public void testCase18() {
        TechGigNewYearParty techGigNewYearParty = new TechGigNewYearParty(new int[] {5,4,2,4,1});
        Result maxSum = techGigNewYearParty.findMaxSum();
        assertThat(maxSum.consolidate(), is("45") );
    }

    @Test
    public void testCase19() {
        TechGigNewYearParty techGigNewYearParty = new TechGigNewYearParty(new int[] {1,4,2,4,5});
        Result maxSum = techGigNewYearParty.findMaxSum();
        assertThat(maxSum.consolidate(), is("54") );
    }

    @Test
    public void testCase20() {
        TechGigNewYearParty techGigNewYearParty = new TechGigNewYearParty(new int[] {-1,-1,0,-1});
        Result maxSum = techGigNewYearParty.findMaxSum();
        assertThat(maxSum.consolidate(), is("") );
    }

    @Test
    public void testWithTime1()
    {
        for (int j=0;j<200;j++) {
            int[] testCase = new int[1000];
            Random random = new Random();
            for (int i = 0; i < testCase.length; i++) {
                testCase[i] = random.nextInt();
            }
            TechGigNewYearParty techGigNewYearParty = new TechGigNewYearParty(testCase);
            ZonedDateTime start = ZonedDateTime.now();
            techGigNewYearParty.findMaxSum().consolidate();
            ZonedDateTime end= ZonedDateTime.now();
            System.out.println(Duration.between(start, end).toMillis());
        }
    }

    @Test
    public void testCase21()
    {
        List<String> expectedTestCases = new ArrayList<String>()
        {{
            add("4 5 4 3 1 7 9 3 1,19144");
            add("8 9 -2 3 5 6 -6 -2 1 0,1639");
            add("48 65 184 24 17 108 56 423 148 44,4442310818448");
            add("4 5 4 3 -2 -2 -2 4 5 4 3,4444");
            add("12 15 25 -15 35,352512");
            add("1 2 50 3 2 50,50501");
            add("5 4 6 8,85");
            add("9 1 2 8 5 4 6 8,8489");
            add("4 7 4 1 1 5,544");
            add("3 7 5 1 1 5,553");
            add("1 2 2 1 2 2,221");
        }};


        expectedTestCases.forEach(s -> {
            String input = s.split(",")[0];
            String output = s.split(",")[1];


            TechGigNewYearParty techGigNewYearParty=new TechGigNewYearParty(Arrays.stream(input.split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray());
            assertThat(techGigNewYearParty.findMaxSum().consolidate(), is(output));

        });

    }

}