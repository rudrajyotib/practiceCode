package leetcode.hard.textJustification;

import java.util.ArrayList;
import java.util.List;

public class Solution
{
    List<String> fullJustify(String[] words, int maxWidth)
    {
        int index = 0;
        List<String> lineOfResult = new ArrayList<>();
        int charactersConsumed = 0;
        int lineStartIndex = 0;
        int lineEndIndex = 0;
        boolean firstWordOfNewLine = true;

        while (index < words.length)
        {
            String word = words[index];
            int addSpace = firstWordOfNewLine ? 0 : 1;
            if ((charactersConsumed + addSpace + word.length()) <= maxWidth)
            {
                charactersConsumed = charactersConsumed + addSpace + word.length();
                firstWordOfNewLine = false;
                lineEndIndex = index;
            }
            else
            {
                lineOfResult.add(generateLine(maxWidth, words, lineStartIndex, lineEndIndex));
                lineStartIndex = lineEndIndex + 1;
                lineEndIndex = lineStartIndex;
                firstWordOfNewLine = true;
                charactersConsumed = 0;
                continue;
            }
            ++index;
        }
        if (charactersConsumed > 0)
        {
            lineOfResult.add(generateLine(maxWidth, words, lineStartIndex, lineEndIndex));
        }
        return lineOfResult;
    }

    private String generateLine(int maxWidth, String[] words, int startIndex, int endIndex)
    {
        char[] result = new char[maxWidth];
        //Arrays.fill(result, ' ');
        int counterPositionInResult = words[startIndex].length();
        int minimumNumberOfSpaces;
        int additionalSpacesToLeftJustify = 0;
        if (!((endIndex + 1) == words.length))
        {
            int emptySpaces = maxWidth;
            for (int i = startIndex; i <= endIndex; i++)
            {
                emptySpaces -= words[i].length();
            }
            int numberOfGaps = endIndex - startIndex;
            minimumNumberOfSpaces = (numberOfGaps > 0) ? emptySpaces / numberOfGaps : 0;
            additionalSpacesToLeftJustify = (numberOfGaps > 0) ? emptySpaces % numberOfGaps : 0;
        }
        else
        {
            minimumNumberOfSpaces = 1;
        }

        arrayCopy(result, 0, words[startIndex]);
        for (int i = startIndex + 1; i <= endIndex; i++)
        {
            int emptySpacesToAdd;
            emptySpacesToAdd = minimumNumberOfSpaces + ((additionalSpacesToLeftJustify > 0) ? 1 : 0);
            fillWithEmptySpaces(result, counterPositionInResult, emptySpacesToAdd);
            counterPositionInResult += emptySpacesToAdd;
            arrayCopy(result, counterPositionInResult, words[i]);
            --additionalSpacesToLeftJustify;
            counterPositionInResult += words[i].length();
        }
        fillRemainingWithEmptySoaces(result, counterPositionInResult);
        return String.copyValueOf(result);
    }

    private void arrayCopy(char[] targetArray, int startPosition, String wordToCopy)
    {
        int targetLength = wordToCopy.length();
        for (int i = 0; i < targetLength; i++)
        {
            targetArray[startPosition + i] = wordToCopy.charAt(i);
        }
    }

    private void fillWithEmptySpaces(char[] targetArray, int startPosition, int length)
    {
        for (int i = 0; i < length; i++)
        {
            targetArray[startPosition + i] = ' ';
        }
    }

    private void fillRemainingWithEmptySoaces(char[] targetArray, int startPosition)
    {
        while (startPosition < targetArray.length)
        {
            targetArray[startPosition] = ' ';
            ++startPosition;
        }
    }
}
