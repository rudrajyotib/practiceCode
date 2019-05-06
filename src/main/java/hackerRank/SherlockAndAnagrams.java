package hackerRank;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("WeakerAccess")
public class SherlockAndAnagrams {
    private final String input;
    private Map<String, Counter> anagramsMap = new HashMap<>();
    private int countOfAnagrams = 0;

    public SherlockAndAnagrams(String input) {
        this.input = input;
    }

    public int countAnagrams() {
        for (int startIndex = 0; startIndex < input.length(); startIndex++) {
            for (int endIndex = startIndex + 1; endIndex <= input.length(); endIndex++) {
                String subString = (input.substring(startIndex, endIndex));
                char[] subStringCharacters = subString.toCharArray();
                Arrays.sort(subStringCharacters);
                String sortedSubString = new String(subStringCharacters);
                if (anagramsMap.containsKey(sortedSubString)) {
                    Counter counter = anagramsMap.get(sortedSubString);
                    countOfAnagrams += counter.getCount();
                    counter.incrementCount();
                } else {
                    anagramsMap.put(sortedSubString, new Counter());
                }

            }
        }
        return countOfAnagrams;
    }
}

@SuppressWarnings("WeakerAccess")
class Counter {
    private int count = 1;

    public int getCount() {
        return count;
    }

    public void incrementCount() {
        ++count;
    }

}
