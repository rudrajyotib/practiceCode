package leetcode.hard.validNumber;

public class Solution
{
    boolean isNumber(String s)
    {

        int indexOfDecimalPoint = -1;
        int indexOfExponential = -1;
        int lastDigitEncountered = -1;
        int indexOfPostExponentialSign = -1;

        int trimmedLineIndexToStartWith = -1;
        int trimmedLineIndexToFinishAt = s.length();

        for (int i = 0; i < s.length(); i++)
        {
            if (s.charAt(i) != ' ')
            {
                trimmedLineIndexToStartWith = i;
                break;
            }
        }

        if (trimmedLineIndexToStartWith == -1)
        {
            return false;
        }

        for (int i = s.length() - 1; i >= 0; i--)
        {
            if (s.charAt(i) != ' ')
            {
                trimmedLineIndexToFinishAt = i;
                break;
            }
        }

        if (s.charAt(trimmedLineIndexToStartWith) == '+' || s.charAt(trimmedLineIndexToStartWith) == '-')
        {
            trimmedLineIndexToStartWith++;
        }

        if ((trimmedLineIndexToFinishAt == trimmedLineIndexToStartWith))
        {
            int asciiCode = (int) s.charAt(trimmedLineIndexToFinishAt);
            if (!((asciiCode >= 48 && asciiCode <= 57)))
            {
                return false;
            }
        }

        if (s.charAt(trimmedLineIndexToStartWith) == 'e')
        {
            return false;
        }

        for (int i = trimmedLineIndexToStartWith; i <= trimmedLineIndexToFinishAt; i++)
        {
            int asciiCode = (int) s.charAt(i);
            if (asciiCode == 43 || asciiCode == 45)
            {
                if (indexOfPostExponentialSign > -1)
                {
                    return false;
                }
                if (!((i - 1) == indexOfExponential))
                {
                    return false;
                }
                indexOfPostExponentialSign = i;
                continue;
            }

            if (asciiCode == 46)
            {
                if (indexOfExponential > -1 || indexOfDecimalPoint > -1)
                {
                    return false;
                }
                indexOfDecimalPoint = i;
                continue;
            }
            if (asciiCode == 101)
            {
                if (indexOfExponential > -1)
                {
                    return false;
                }
                if (!((lastDigitEncountered != -1) && (i > lastDigitEncountered)))
                {
                    return false;
                }
                indexOfExponential = i;
                continue;
            }
            if (!(asciiCode >= 48 && asciiCode <= 57))
            {
                return false;
            }
            lastDigitEncountered = i;
        }

        return lastDigitEncountered > indexOfExponential;
    }
}
