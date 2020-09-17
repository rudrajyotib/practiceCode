package leetcode.hard.shortestPalindrome;

//https://leetcode.com/problems/shortest-palindrome/

public class Solution {

    public String shortestPalindrome(String s) {
        if (s.isEmpty() || s.length()==1)
        {
            return s;
        }

        int length=s.length();
        char[] charArray = s.toCharArray();
        for (int i=length-1;i>0;i--)
        {
            if (charArray[i]==charArray[0])
            {
                boolean palindrome = checkPalindrome(charArray, 0, i);
                if (palindrome)
                {
                    char[] palindromicChar=new char[length+(length-1-i)];
                    int finalArrayCounter = 0;
                    while (finalArrayCounter < (length-1)-i)
                    {
                        palindromicChar[finalArrayCounter]=charArray[length-finalArrayCounter-1];
                        ++finalArrayCounter;
                    }
                    System.arraycopy(charArray, 0, palindromicChar, finalArrayCounter, length);
                    return new String(palindromicChar);
                }
            }
        }
        char[] hardReverse = new char[length*2-1];
        for (int i=0;i<length;i++)
        {
            hardReverse[i] = charArray[length-1-i];
        }
        System.arraycopy(charArray, 1, hardReverse, length + 1 - 1, length - 1);
        return new String(hardReverse);
    }

    private boolean checkPalindrome(char[] charArray, @SuppressWarnings("SameParameterValue") int startIndex, int endIndex)
    {
        while(startIndex < endIndex)
        {
            if (charArray[startIndex]!=charArray[endIndex])
            {
                return false;
            }
            ++startIndex;
            --endIndex;
        }
        return true;
    }
}
