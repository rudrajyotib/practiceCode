package leetcode.medium.longestPalindromicSubstring;

//https://leetcode.com/problems/longest-palindromic-substring/

public class Solution {

    public String longestPalindrome(String s) {

        if (s.length()<=1)
        {
            return s;
        }
        int length=s.length();
        char[] charArray = s.toCharArray();
        PalindromeContainer longestPalindromeContainer = new PalindromeContainer(0,1);
        for (int i=1;i<length;i++)
        {
            if (charArray[i-1]==charArray[i])
            {
                PalindromeContainer palindromeContainer = expandPalindrome(charArray, i - 1, i);
                if(palindromeContainer.getLength()>longestPalindromeContainer.getLength())
                {
                    longestPalindromeContainer = palindromeContainer;
                }
            }
        }
        for (int i=1;i<length-1;i++)
        {
            if (charArray[i-1]==charArray[i+1])
            {
                PalindromeContainer palindromeContainer = expandPalindrome(charArray, i - 1, i+1);
                if(palindromeContainer.getLength()>longestPalindromeContainer.getLength())
                {
                    longestPalindromeContainer = palindromeContainer;
                }
            }
        }
        return s.substring(longestPalindromeContainer.startIndex, longestPalindromeContainer.endIndex);
    }

    private PalindromeContainer expandPalindrome(char[] charArray, int startIndex, int endIndex)
    {
        if (charArray[startIndex]!=charArray[endIndex])
        {
            return new PalindromeContainer(endIndex-1,endIndex);
        }
        while (startIndex>0 && endIndex<charArray.length-1)
        {
            if (charArray[startIndex-1]==charArray[endIndex+1])
            {
                --startIndex;
                ++endIndex;
            }else
            {
                return new PalindromeContainer(startIndex, endIndex+1);
            }
        }
        return new PalindromeContainer(startIndex, endIndex+1);
    }

    public static class PalindromeContainer
    {
        private final int startIndex;
        private final int endIndex;

        public PalindromeContainer(int startIndex, int endIndex) {
            this.startIndex = startIndex;
            this.endIndex = endIndex;
        }

        public int getLength()
        {
            return (endIndex-startIndex);
        }
    }


}
