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
        CellData[] computedData = new CellData[matrix[0].length];
        CellData[] currentRowData = new CellData[matrix[0].length];
        int rowLength = matrix.length;
        int columnLength = matrix[0].length;

        int maxSquareLength=0;
        for (int i=0;i<columnLength;i++)
        {
            if(matrix[0][i]=='1')
            {
                computedData[i] = new CellData(1,1,1);
                if(maxSquareLength==0)
                {
                    maxSquareLength=1;
                }
            }else
            {
                computedData[i] =  new CellData(0, 0, 0);
            }
        }
        for (int i=0;i<matrix[0].length;i++)
        {
            currentRowData[i] = new CellData(0,0,0);
        }

        for (int rowIndex=1;rowIndex<rowLength;rowIndex++)
        {
            if(matrix[rowIndex][0]=='0')
            {
                currentRowData[0].height=0;
                currentRowData[0].squareLength = 0;
                currentRowData[0].width=0;
            }else
            {
                currentRowData[0].height=1;
                currentRowData[0].squareLength = 1;
                currentRowData[0].width=1;
                if(maxSquareLength==0)
                {
                    maxSquareLength=1;
                }
            }
            for (int colIndex=1;colIndex<columnLength;colIndex++)
            {
                if(matrix[rowIndex][colIndex]=='0')
                {
                    currentRowData[colIndex].width=0;
                    currentRowData[colIndex].height=0;
                    currentRowData[colIndex].squareLength=0;
                    continue;
                }
                CellData diagonalCellData = computedData[colIndex-1];
                int cellHeight = computedData[colIndex].height+1;
                int cellWidth = currentRowData[colIndex-1].width+1;
                if(diagonalCellData.squareLength==0)
                {
                    currentRowData[colIndex].width = cellWidth;
                    currentRowData[colIndex].height = cellHeight;
                    currentRowData[colIndex].squareLength = 1;
                    if(maxSquareLength==0)
                    {
                        ++maxSquareLength;
                    }
                    continue;
                }
                int contributionToSquareExtension = Math.min(cellHeight, cellWidth);
                if(contributionToSquareExtension<=diagonalCellData.squareLength)
                {
                    currentRowData[colIndex].width = cellWidth;
                    currentRowData[colIndex].height = cellHeight;
                    currentRowData[colIndex].squareLength = contributionToSquareExtension;
                }else
                {
                    currentRowData[colIndex].width = cellWidth;
                    currentRowData[colIndex].height = cellHeight;
                    currentRowData[colIndex].squareLength = diagonalCellData.squareLength+1;
                    if(maxSquareLength < (diagonalCellData.squareLength+1))
                    {
                        maxSquareLength = diagonalCellData.squareLength+1;
                    }
                }
            }
            CellData[] temp = currentRowData;
            currentRowData = computedData;
            computedData = temp;
        }
        return maxSquareLength*maxSquareLength;
    }

    public static class CellData
    {
        private  int width;
        private  int height;
        private  int squareLength;

        public CellData(int width, int height, int squareLength) {
            this.width = width;
            this.height = height;
            this.squareLength = squareLength;
        }
    }


}
