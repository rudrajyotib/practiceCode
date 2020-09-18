package leetcode.hard.substringWithConcatenationOfAllWords;

//https://leetcode.com/problems/substring-with-concatenation-of-all-words/


import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Solution {

    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new LinkedList<>();
        Map<String, Integer> wordCount = new HashMap<>();

        int individualWordLength=words[0].length();
        int totalWordsLength = individualWordLength*words.length;
        int maxLimitToStart = s.length() - totalWordsLength;
        for (String word : words)
        {
            wordCount.put(word, 1+(wordCount.getOrDefault(word,0)));
        }
        for (int i=0;i<=maxLimitToStart;i++)
        {
            if (wordCount.containsKey(s.substring(i,i+individualWordLength))
                    && wordCount.containsKey(s.substring(i+totalWordsLength-individualWordLength,i+totalWordsLength)))
            {
                if(evaluatePresenceOfWords(s, wordCount, individualWordLength, words.length, i,i+totalWordsLength))
                {
                    result.add(i);
                }
            }
        }

        return result;
    }


    public boolean evaluatePresenceOfWords(String primaryString,
                                           Map<String,
                                                   Integer> wordCount,
                                           int eachWordLength,
                                           int totalWords,
                                           int startPoint,
                                           int endPoint)
    {
        String subString = primaryString.substring(startPoint, endPoint);
        Map<String, Integer> currentMap = new HashMap<>(wordCount.size());
        for (int i=0;i<totalWords;i++)
        {
            String word = subString.substring(i*eachWordLength, (i+1)*eachWordLength);
            currentMap.put(word, 1+(currentMap.getOrDefault(word, 0)));
        }

        return currentMap.equals(wordCount);
    }
}
