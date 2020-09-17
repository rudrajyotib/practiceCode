package leetcode.medium.spiralmatrix;
/*
* https://leetcode.com/problems/spiral-matrix/
*/

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        
        List<Integer> ans = new ArrayList<>();
    	int nRows = matrix.length;
        if( nRows == 0 ){
            return ans;
        }
    	int nCols = matrix[0].length;
        if( nRows == 1 && nCols == 0){
            return ans;
        }
    	int left = 0;
    	int right = nCols - 1;
    	int top = 0 ;
    	int bottom = nRows - 1;
    	int dir = 0;
    	while( top <= bottom && left <= right) {
    		
    		/*Print from left to right*/
    		if(dir == 0) {
    			for(int i = left ; i <= right; i++) {
    				ans.add(matrix[top][i]);
    			}
    			top++;
    		}
    		/*Print from top to bottom*/
    		else if(dir == 1) {
    			
    			for(int i = top;i <= bottom; i++) {
    				ans.add(matrix[i][right]);
    			}
    			right --;
    		}
    		/*Print from right to left*/
    		else if( dir == 2) {
    			for(int i = right; i >= left; i--) {
    				ans.add(matrix[bottom][i]);
    			}
    			bottom--;
    		}
    		/*Print from bottom to top*/
    		else if(dir == 3) {
    			for(int i = bottom; i >= top; i--) {
    				ans.add(matrix[i][left]);
    			}
    			left++;
    		}
    		dir = (dir + 1 )%4;

    	}
    	return ans;
        
    }
}