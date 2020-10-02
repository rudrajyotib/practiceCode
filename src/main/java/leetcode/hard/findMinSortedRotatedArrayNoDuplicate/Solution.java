package leetcode.hard.findMinSortedRotatedArrayNoDuplicate;

//https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/

public class Solution {

    public int findMin(int[] input) {
        if (input.length==1)
        {
            return input[0];
        }
        int lengthOfInput = input.length;
        if(input[0]<input[lengthOfInput-1])
        {
            return input[0];
        }
        boolean pointOfInflectionFound = false;
        int startIndex = 0;
        int endIndex = lengthOfInput-1;
        int mid = findMid(startIndex, endIndex);
        while (!pointOfInflectionFound)
        {
            if (input[mid]>input[0])
            {
                startIndex = mid;
                endIndex = lengthOfInput-1;
                mid = findMid(startIndex, endIndex);
            }else if (input[mid]<input[0])
            {
                if (input[mid]<input[mid-1])
                {
                    pointOfInflectionFound=true;
                }else
                {
                    endIndex = mid;
                    mid = findMid(startIndex, endIndex);
                }
            }
        }
        return input[mid];

    }

    private int findMid(int start, int end)
    {
        if((start+end)%2==1)
        {
            return ((start+end)/2)+1;
        }
        return (start+end)/2;
    }

}
