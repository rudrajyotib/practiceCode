package leetcode.hard.minimumWindowSubstring;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SolutionTest {

    private final List<String[]> testCases =
            new ArrayList<String[]>() {
                {
                    add(new String[]{"ADOBECODEBANC", "ABC", "BANC"});
                    add(new String[]{"AAAAAAAAAAA", "AAA", "AAA"});
                    add(new String[]{"AXYTEYTYTYGBCXXXXA", "ABC", "BCXXXXA"});
                    add(new String[]{"AXBUYRERDBUAUC", "ABC", "BUAUC"});
                    add(new String[]{"AYBYAYBYBYAXXXXCYAUB", "ABC", "CYAUB"});
                    add(new String[]{"AXXXXBXXXXCXBXA", "ABC", "CXBXA"});
                    add(new String[]{"AXXXXBXXXXCXBXA", "ABC", "CXBXA"});
                    add(new String[]{"AXXXBXXXBXXAAXXC", "ABC", "BXXAAXXC"});
                    add(new String[]{"AXXXBXXXBXXAAXXC", "ABBC", "BXXXBXXAAXXC"});
                    add(new String[]{"ABCHJYUYWUYUJHJHJGHGHGHSGFTEFGDFG", "ABC", "ABC"});
                    add(new String[]{"ABCHJYUYWUYUJHJHJGHGHGHSGFTEFGDFG", "UYUYHAGHXBGS", ""});
                    add(new String[]{"AHJYUYWUYUJHJHJGHGHGHSGFTEFGDFGBC", "ABC", "AHJYUYWUYUJHJHJGHGHGHSGFTEFGDFGBC"});
                }
            };

    @Test
    public void test() {
        for (int i = 0; i < testCases.size(); i++) {
            long startTime = System.currentTimeMillis();
            Solution solution = new Solution();
            String result = solution.minWindow(testCases.get(i)[0], testCases.get(i)[1]);
            long elapsedTime = System.currentTimeMillis() - startTime;
            assertThat(String.format("Test case %d result does not match", i),
                    result, is(testCases.get(i)[2]));
            assertThat(String.format("Test case %d took %dms", i, elapsedTime),
                    elapsedTime < 150, is(true));
        }
    }

}