package leetcode.hard.editdistance;

/*
https://leetcode.com/problems/edit-distance/
*/

class Solution {
    public int minDistance(String word1, String word2) {
        int M = word1.length() + 1;
    	int N = word2.length() + 1;
    	int[][] T = new int[M][N];
    	
    	/*Initialize the 1st row */
    	for(int i = 0; i < N; i++) {
    		T[0][i] = i;
    		
    	}
    	/*Initialize the first column*/
    	for (int i = 0; i < M; i++ ) {
    		T[i][0] = i;
    	}
    	
    	for(int i = 1; i < M; i++) {
    		for(int j = 1; j < N; j++) {
    			
    			if(word1.charAt(i - 1) == word2.charAt(j - 1)) {
    				T[i][j] = T[i-1][j-1];
    			}
    			else {
    				T[i][j] = minimum(T[i-1][j], T[i-1][j-1], T[i][j-1]) + 1; 
    			}
    			
    		}
    		
    	}
    	
    	return T[M -1 ][N -1];
        
    }
    
    public static int minimum( int a, int b, int c) {
    	
    	return Integer.min(a, Integer.min(b,c));
    }
}