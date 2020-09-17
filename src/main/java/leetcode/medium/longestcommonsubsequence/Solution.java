package leetcode.medium.longestcommonsubsequence;

/*
 *https://leetcode.com/problems/longest-common-subsequence/
*/

class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
		int n = text2.length();
		
		/*Table to hold the calculation at each step*/
		int[][] T = new int[m+1][n+1];
		
		for( int i = 1; i <= m; i++ ){
			for(int j = 1; j <= n; j++){
			
				if(text1.charAt(i-1) == text2.charAt(j-1)){
				
					T[i][j] = 1 + T[i-1][j-1];
				}
				else {
				
					T[i][j] = Integer.max( T[i-1][j], T[i][j-1]);
				}
			}
		}
		
		return T[m][n];
    }
}