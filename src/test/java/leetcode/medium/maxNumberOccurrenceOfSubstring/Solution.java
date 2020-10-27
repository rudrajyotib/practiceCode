package leetcode.medium.maxNumberOccurrenceOfSubstring;

//https://leetcode.com/problems/maximum-number-of-occurrences-of-a-substring/

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Solution {

    public int maxFreq(String s, int maxLetters, int minSize, @SuppressWarnings("unused") int maxSize)
    {

        if (s.length()==0)
        {
            return 0;
        }

        if (minSize > s.length())
        {
            return 0;
        }

        int maxIndexInStringToCheck = s.length() - minSize;
        Map<String, SubstringFrequency> mapOfSubstringFrequency = new HashMap<>(maxIndexInStringToCheck);
        int maxFrequency = 0;
        for (int i=0;i<=maxIndexInStringToCheck;i++)
        {
            String subString = s.substring(i, i+minSize);
            if (isValid(subString, maxLetters))
            {
                if (mapOfSubstringFrequency.containsKey(subString))
                {
                    int frequency = mapOfSubstringFrequency.get(subString).incrementAndReturn();
                    if (frequency > maxFrequency)
                    {
                        maxFrequency = frequency;
                    }
                }else
                {
                    mapOfSubstringFrequency.put(subString, new SubstringFrequency());
                    if(maxFrequency ==0 )
                    {
                        ++maxFrequency;
                    }
                }
            }
        }

        return maxFrequency;
    }

    private boolean isValid(String subString, int maxLetters)
    {
        HashSet<Character> distinctCharacters = new HashSet<>();
        for (int i=0;i<subString.length();i++)
        {
            distinctCharacters.add(subString.charAt(i));
        }
        return distinctCharacters.size() <= maxLetters;
    }


    private static class SubstringFrequency
    {
        private int frequency = 1;

        public SubstringFrequency() {
        }

        public int incrementAndReturn()
        {
            return ++frequency;
        }
    }

}
