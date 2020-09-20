package leetcode.hard.minimumWindowSubstring;

/**
 https://leetcode.com/problems/minimum-window-substring/
 */

public class Solution {

    public String minWindow(String s, String t) {
        int length = s.length();
        int backPointerIndex = 0;
        int frontPointerIndex = 0;
        int[] stringToMatch = new int[257];
        t.chars().forEach(value -> ++stringToMatch[value]);
        int[] matchedString = new int[257];
        boolean firstMatchingWindowFound = false;
        char[] sourceString = s.toCharArray();
        String resultSubString = "";
        while (frontPointerIndex < length)
        {
            if(!firstMatchingWindowFound)
            {
                if(stringToMatch[sourceString[frontPointerIndex]] > 0)
                {
                    ++matchedString[sourceString[frontPointerIndex]];
                    boolean matchFound=true;
                    for (int i=0;i<257;i++)
                    {
                        if (matchedString[i]<stringToMatch[i])
                        {
                            matchFound = false;
                            break;
                        }
                    }
                    if (matchFound)
                    {
                        boolean requiredCharFound = false;
                        while (!requiredCharFound)
                        {
                            if (stringToMatch[sourceString[backPointerIndex]]>0)
                            {
                                if (matchedString[sourceString[backPointerIndex]]==stringToMatch[sourceString[backPointerIndex]])
                                {
                                    requiredCharFound=true;
                                }else {
                                    --matchedString[sourceString[backPointerIndex]];
                                    ++backPointerIndex;
                                }
                            }else
                            {
                                ++backPointerIndex;
                            }
                        }
                        resultSubString = s.substring(backPointerIndex, frontPointerIndex+1);
                        firstMatchingWindowFound = true;
                    }
                }
            }else
            {
                if(sourceString[frontPointerIndex]==sourceString[backPointerIndex])
                {
                    ++backPointerIndex;
                    while (true)
                    {
                        if (stringToMatch[sourceString[backPointerIndex]]>0)
                        {
                            if(matchedString[sourceString[backPointerIndex]]>stringToMatch[sourceString[backPointerIndex]])
                            {
                                --matchedString[sourceString[backPointerIndex]];
                            }else
                            {
                                if ((frontPointerIndex-backPointerIndex) < resultSubString.length())
                                {
                                    resultSubString = s.substring(backPointerIndex, frontPointerIndex+1);
                                }
                                break;
                            }
                        }
                            ++backPointerIndex;
                    }
                }else
                {
                    if (stringToMatch[sourceString[frontPointerIndex]]>0)
                    {
                        ++matchedString[sourceString[frontPointerIndex]];
                    }
                }
            }
            ++frontPointerIndex;
        }
        return resultSubString;
    }
}
