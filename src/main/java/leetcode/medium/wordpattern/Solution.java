package leetcode.medium.wordpattern;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
 * https://leetcode.com/problems/word-pattern/
 */
class Solution {
    public boolean wordPattern(String pattern, String s) {
		String[] words = s.split(" ");
		if (pattern.length() != words.length) {
			return false;
		}
		Map<Character, String> charMap = new HashMap<>();
		Set<String> wordMap = new HashSet<>();
		for(int i = 0; i < pattern.length(); i++ ) {
			char c = pattern.charAt(i);
			if(charMap.containsKey(c)) {
				if(!charMap.get(c).equals(words[i])) {
					return false;
				}
			}
			else {
				/*Check if the word is already mapped*/
				if(wordMap.contains(words[i])) {
					return false;
				}
				wordMap.add(words[i]);
				charMap.put(c,words[i]);
				
			}
		}
		
		return true;
    }
}