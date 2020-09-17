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
                expandPalindrome(charArray, i - 1, i, longestPalindromeContainer);
            }
        }
        for (int i=1;i<length-1;i++)
        {
            if (charArray[i-1]==charArray[i+1])
            {
                expandPalindrome(charArray, i - 1, i+1, longestPalindromeContainer);
            }
        }
        return s.substring(longestPalindromeContainer.startIndex, longestPalindromeContainer.endIndex);
    }

    private void expandPalindrome(char[] charArray, int startIndex, int endIndex, PalindromeContainer palindromeContainer)
    {
        if (charArray[startIndex]!=charArray[endIndex])
        {
            palindromeContainer.updateIfBetter(endIndex-1, endIndex);
        }
        while (startIndex>0 && endIndex<charArray.length-1)
        {
            if (charArray[startIndex-1]==charArray[endIndex+1])
            {
                --startIndex;
                ++endIndex;
            }else
            {
                palindromeContainer.updateIfBetter(startIndex, endIndex+1);
                return;
            }
        }
        palindromeContainer.updateIfBetter(startIndex, endIndex+1);
    }

    public static class PalindromeContainer
    {
        private int startIndex;
        private int endIndex;

        public PalindromeContainer(int startIndex, int endIndex) {
            this.startIndex = startIndex;
            this.endIndex = endIndex;
        }

        public int getLength()
        {
            return (endIndex-startIndex);
        }

        public void updateIfBetter(int startIndex, int endIndex)
        {
            if ((endIndex-startIndex) > this.getLength())
            {
                this.startIndex=startIndex;
                this.endIndex = endIndex;
            }
        }
    }


}
