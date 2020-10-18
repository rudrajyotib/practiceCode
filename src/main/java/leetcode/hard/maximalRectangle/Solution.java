package leetcode.hard.maximalRectangle;

//https://leetcode.com/problems/maximal-rectangle/

import java.util.LinkedList;

public class Solution {
    public int maximalRectangle(char[][] input) {

        if (input.length==0)
        {
            return 0;
        }

        if(input[0].length==0)
        {
            return 0;
        }

        int maxArea = 0;

        int rowLength = input.length;
        int columnLength = input[0].length;

        int[][] matrix = new int[rowLength][columnLength];

        for (int i=0;i<columnLength;i++)
        {
            if('1'==input[0][i])
            {
                matrix[0][i] = 1;
            }else
            {
                matrix[0][i]= 0;
            }
            maxArea = largestRectangleArea(matrix[0]);
        }
        
        for (int rowIndex = 1;rowIndex<rowLength; rowIndex++)
        {
            for (int columnIndex = 0;columnIndex<columnLength; columnIndex++)
            {
                if (input[rowIndex][columnIndex]=='1')
                {
                    matrix[rowIndex][columnIndex] = matrix[rowIndex-1][columnIndex]+1;
                }
                else
                {
                    matrix[rowIndex][columnIndex] = 0;
                }
            }
            maxArea = Math.max(maxArea, largestRectangleArea(matrix[rowIndex]));
        }


        return maxArea;
    }

    public int largestRectangleArea(int[] heights) {
        if(heights.length==1)
        {
            return heights[0];
        }
        if(heights.length==0)
        {
            return 0;
        }

        LinkedList<MinimumGuaranteedHeight> minimumGuaranteedHeights
                = new LinkedList<>();

        minimumGuaranteedHeights.add(new MinimumGuaranteedHeight(0, heights[0]));

        int maxArea = heights[0];
        MinimumGuaranteedHeight lastElement;

        for (int i=1;i<heights.length;i++)
        {
            if(heights[i]>heights[i-1])
            {
                minimumGuaranteedHeights.add(new MinimumGuaranteedHeight(i, heights[i]));
                continue;
            }
            if(heights[i]==heights[i-1])
            {
                continue;
            }
            if(heights[i]<heights[i-1])
            {
                boolean keepPopping=true;
                int lastPoppedIndex = i;
                while (keepPopping && minimumGuaranteedHeights.size()>0)
                {
                    if(minimumGuaranteedHeights.peekLast().height>heights[i])
                    {
                        lastElement = minimumGuaranteedHeights.removeLast();
                        int areaCoveredByLastBlock = lastElement.height * (i-lastElement.index);
                        lastPoppedIndex = lastElement.index;
                        if(areaCoveredByLastBlock > maxArea)
                        {
                            maxArea = areaCoveredByLastBlock;
                        }
                        continue;
                    }
                    if(minimumGuaranteedHeights.peekLast().height < heights[i])
                    {
                        keepPopping=false;
                        lastPoppedIndex = Math.min(lastPoppedIndex, i);
                        continue;
                    }
                    if(minimumGuaranteedHeights.peekLast().height==heights[i])
                    {
                        lastElement = minimumGuaranteedHeights.removeLast();
                        lastPoppedIndex = lastElement.index;
                        keepPopping=false;
                    }
                }
                minimumGuaranteedHeights.add(new MinimumGuaranteedHeight(lastPoppedIndex, heights[i]));
            }
        }
        int max = minimumGuaranteedHeights
                .stream()
                .mapToInt(value -> value.height * (heights.length - value.index))
                .max()
                .orElse(0);


        return Math.max(max, maxArea);
    }

    public static class MinimumGuaranteedHeight
    {
        private final int index;
        private final int height;

        public MinimumGuaranteedHeight(int index, int height) {
            this.index = index;
            this.height = height;
        }

    }
}
