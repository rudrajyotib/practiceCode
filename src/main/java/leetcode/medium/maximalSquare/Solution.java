package leetcode.medium.maximalSquare;

public class Solution {

    public int maximalSquare(char[][] matrix) {
         if(matrix.length==0)
         {
             return 0;
         }
         if(matrix[0].length==0)
         {
             return 0;
         }
        CellData[][] computedData = new CellData[matrix.length][matrix[0].length];
        int rowLength = matrix.length;
        int columnLength = matrix[0].length;
        computedData[0][0] = matrix[0][0]=='0'?new CellData(0,0,0):
                new CellData(1,1,1);
        int maxSquareLength=0;
        for (int i=0;i<columnLength;i++)
        {
            if(matrix[0][i]=='1')
            {
                computedData[0][i] = new CellData(1,1,1);
                if(maxSquareLength==0)
                {
                    maxSquareLength=1;
                }
            }else
            {
                computedData[0][i] =  new CellData(0, 0, 0);
            }
        }
        for (int i=1;i<rowLength;i++)
        {
            if(matrix[i][0]=='1')
            {
                computedData[i][0] = new CellData(1,1,1);
                if(maxSquareLength==0)
                {
                    maxSquareLength=1;
                }
            }else
            {
                computedData[i][0] = new CellData(0,0,0);
            }
        }
        for (int rowIndex=1;rowIndex<rowLength;rowIndex++)
        {
            for (int colIndex=1;colIndex<columnLength;colIndex++)
            {
                if(matrix[rowIndex][colIndex]=='0')
                {
                    computedData[rowIndex][colIndex] = new CellData(0, 0, 0);
                    continue;
                }
                CellData diagonalCellData = computedData[rowIndex-1][colIndex-1];
                int cellHeight = computedData[rowIndex-1][colIndex].height+1;
                int cellWidth = computedData[rowIndex][colIndex-1].width+1;
                if(diagonalCellData.squareLength==0)
                {
                    computedData[rowIndex][colIndex] = new CellData(cellWidth,cellHeight,1);
                    if(maxSquareLength==0)
                    {
                        ++maxSquareLength;
                    }
                    continue;
                }
                int contributionToSquareExtension = Math.min(cellHeight, cellWidth);
                if(contributionToSquareExtension<=diagonalCellData.squareLength)
                {
                    computedData[rowIndex][colIndex] = new CellData(cellWidth, cellHeight, contributionToSquareExtension);
                }else
                {
                    computedData[rowIndex][colIndex] = new CellData(cellWidth, cellHeight, diagonalCellData.squareLength+1);
                    if(maxSquareLength < (diagonalCellData.squareLength+1))
                    {
                        maxSquareLength = diagonalCellData.squareLength+1;
                    }
                }
            }
        }
        return maxSquareLength*maxSquareLength;
    }

    public static class CellData
    {
        private final int width;
        private final int height;
        private final int squareLength;

        public CellData(int width, int height, int squareLength) {
            this.width = width;
            this.height = height;
            this.squareLength = squareLength;
        }
    }


}
