package leetcode.hard.slidingWindowMedian;

/*
https://leetcode.com/problems/sliding-window-median/submissions/
 */

import java.util.Arrays;

public class Solution
{
    double[] medianSlidingWindow(int[] nums, int k)
    {
        SlidingWindow slidingWindow = new SlidingWindow(nums, k);
        return slidingWindow.slide();
    }

    private static class SlidingWindow
    {
        private final int[] arrayWindow;
        private final int windowSize;
        private final double[] midStream;
        private final int[] originalArray;
        int slideCount = 0;

        private SlidingWindow(int[] numbers, int windowSize)
        {
            this.arrayWindow = new int[windowSize];
            this.windowSize = windowSize;
            this.originalArray = new int[numbers.length];
            System.arraycopy(numbers, 0, originalArray, 0, numbers.length);
            System.arraycopy(numbers, 0, arrayWindow, 0, windowSize);
            midStream = new double[numbers.length - windowSize + 1];
            Arrays.sort(this.arrayWindow);
            if (windowSize % 2 == 0)
            {
                midStream[slideCount] = (((double) arrayWindow[windowSize / 2] + (double) arrayWindow[(windowSize / 2) - 1])) / 2d;
            }
            else
            {
                midStream[slideCount] = (double) arrayWindow[windowSize / 2];
            }
        }

        double[] slide()
        {
            while ((slideCount + windowSize) < originalArray.length)
            {
                int leftMostNumber = originalArray[slideCount];
                int entrant = originalArray[slideCount + windowSize];

                if (leftMostNumber != entrant)
                {
                    int index = hopSearch(leftMostNumber, arrayWindow);
                    if (leftMostNumber < entrant)
                    {
                        boolean placeFound = false;
                        while (!(placeFound))
                        {
                            if (index == arrayWindow.length - 1)
                            {
                                placeFound = true;
                            }
                            else
                            {
                                if (arrayWindow[index + 1] <= entrant)
                                {
                                    arrayWindow[index] = arrayWindow[index + 1];
                                    ++index;
                                }
                                else
                                {
                                    placeFound = true;
                                }
                            }
                        }
                    }
                    if (leftMostNumber > entrant)
                    {

                        boolean placeFound = false;
                        while (!placeFound)
                        {
                            if (index == 0)
                            {
                                placeFound = true;
                            }
                            else
                            {
                                if (arrayWindow[index - 1] >= entrant)
                                {
                                    arrayWindow[index] = arrayWindow[index - 1];
                                    --index;
                                }
                                else
                                {
                                    placeFound = true;
                                }
                            }
                        }
                    }
                    arrayWindow[index] = entrant;
                }
                if (windowSize % 2 == 0)
                {
                    midStream[slideCount + 1] = (((double) arrayWindow[windowSize / 2] + (double) arrayWindow[(windowSize / 2) - 1])) / 2d;
                }
                else
                {
                    midStream[slideCount + 1] = (double) arrayWindow[windowSize / 2];
                }
                ++slideCount;
            }
            return midStream;
        }

        int hopSearch(int number, int[] array)
        {
            int hopLength = 0;
            int startIndex;
            int endIndex = 0;
            while (endIndex <= (array.length - 1))
            {
                startIndex = endIndex;
                if ((endIndex + hopLength) > (array.length))
                {
                    endIndex = array.length - 1;
                }
                else
                {
                    endIndex = endIndex + hopLength;
                }
                for (int i = startIndex; i <= endIndex; i++)
                {
                    if (array[i] == number)
                    {
                        return i;
                    }
                }
                ++hopLength;
            }

            return -1;
        }

        @SuppressWarnings("unused")
        int binarySearch(int number, int[] array)
        {
            if (array.length == 1)
            {
                return 0;
            }
            if (array.length == 2)
            {
                if (array[0] == number)
                {
                    return 0;
                }
                return 1;
            }
            int first = 0;
            int last = array.length - 1;
            int middle = (first + last) / 2;

            while (first <= last)
            {
                if (array[middle] < number)
                { first = middle + 1; }
                else if (array[middle] == number)
                {
                    break;
                }
                else
                { last = middle - 1; }

                middle = (first + last) / 2;
            }
            return middle;
        }
    }
}
