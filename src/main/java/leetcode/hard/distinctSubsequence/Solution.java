package leetcode.hard.distinctSubsequence;

//https://leetcode.com/problems/distinct-subsequences/

public class Solution {

    public int numDistinct(String input, String pattern) {

        if(pattern.length() > input.length())
        {
            return 0;
        }

        int[][] dpResult = new int[pattern.length()+1][input.length()+1];

        //first row is for empty pattern - all 1
        //first column is for empty input - all 0

        for (int i=0;i<=input.length();i++)
        {
            dpResult[0][i] = 1;
        }

        for (int i=1;i<=pattern.length();i++)
        {
            dpResult[i][0] = 0;
        }

        for (int i=0;i<pattern.length();i++)
        {
            for (int j=0;j<input.length();j++)
            {
                if (pattern.charAt(i)==input.charAt(j))
                {
                    dpResult[i+1][j+1] = dpResult[i+1][j] + dpResult[i][j];
                }else
                {
                    dpResult[i+1][j+1] = dpResult[i+1][j];
                }
            }
        }

        return dpResult[pattern.length()][input.length()];
    }

}
