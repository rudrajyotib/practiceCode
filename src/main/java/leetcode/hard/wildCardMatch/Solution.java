package leetcode.hard.wildCardMatch;

//https://leetcode.com/problems/wildcard-matching/

public class Solution {

    public boolean isMatch(String input, String pattern) {

        StringBuilder shortenedPatternBuilder = new StringBuilder();

        if (pattern.isEmpty())
        {
            return input.isEmpty();
        }

        shortenedPatternBuilder.append(pattern.charAt(0));

        for (int i=1;i<pattern.length();i++)
        {
            if(pattern.charAt(i)=='*')
            {
                if(pattern.charAt(i-1) != '*')
                {
                    shortenedPatternBuilder.append(pattern.charAt(i));
                }
                continue;
            }
            shortenedPatternBuilder.append(pattern.charAt(i));
        }

        String shortenedPattern = shortenedPatternBuilder.toString();

        boolean[][] dpResult = new boolean[input.length()+1][shortenedPattern.length()+1];

        dpResult[0][0] = true;
        if(shortenedPattern.charAt(0)=='*')
        {
            dpResult[0][1]=true;
        }

        for (int i=1;i<dpResult.length;i++)
        {
            for(int j=1;j<dpResult[0].length;j++)
            {
                if(shortenedPattern.charAt(j-1)=='*')
                {
                    dpResult[i][j] = dpResult[i-1][j] || dpResult[i][j-1];
                }else if(  (shortenedPattern.charAt(j-1)==input.charAt(i-1))  || (shortenedPattern.charAt(j-1)=='?') )
                {
                    dpResult[i][j] = dpResult[i-1][j-1];
                }
            }
        }

        return dpResult[dpResult.length-1][dpResult[0].length-1];
    }

}
