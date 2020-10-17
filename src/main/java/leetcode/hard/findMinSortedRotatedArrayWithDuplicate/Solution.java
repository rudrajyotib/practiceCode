package leetcode.hard.findMinSortedRotatedArrayWithDuplicate;

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

        int start = 0;
        int end = lengthOfInput-1;
        while (start<end)
        {
            int mid = findMidToLeft(start, end);
            if(input[mid]==input[start] && mid!=start)
            {
                ++start;
                continue;
            }
            if(input[mid]>input[end])
            {
                start = mid+1;
                continue;
            }
            end=mid;

        }
        return input[start];
    }





    private int findMidToLeft(int start, int end)
    {
        return (start+end)/2;
    }



}