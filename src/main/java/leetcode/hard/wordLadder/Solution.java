package leetcode.hard.wordLadder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Solution
{
    private int target;
    private List<SingleLinkedList<Integer>> allPathsToDestination = new ArrayList<>();
    private int minimumDistanceSolution = -1;
    private int inputLength;
    private int[] minimumDistanceToReachVertex;
    private boolean[] startingPoint;
    private int[][] matrix;

    List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList)
    {
        inputLength = wordList.size();
        minimumDistanceToReachVertex = new int[wordList.size()];
        startingPoint = new boolean[wordList.size()];
        if (!isValidProblem(beginWord, endWord, wordList))
        {
            return new ArrayList<>();
        }
        matrix = new int[inputLength][inputLength];

        for (int i = 0; i < inputLength; i++)
        {
            for (int j = i + 1; j < inputLength; j++)
            {
                if (!(makesPair(wordList.get(i), wordList.get(j))))
                {
                    matrix[i][j] = -1;
                    matrix[j][i] = -1;
                }
            }
            matrix[i][i] = -1;
        }

        for (int i = 0; i < inputLength; i++)
        {
            if (startingPoint[i])
            {
                if (i == target)
                {
                    SingleLinkedList<Integer> singlePointResult = new SingleLinkedList<>();
                    singlePointResult.add(i);
                    allPathsToDestination.add(singlePointResult);
                    if (minimumDistanceSolution != 1)
                    {
                        allPathsToDestination.clear();
                    }
                    allPathsToDestination.add(singlePointResult);
                    minimumDistanceSolution = 1;
                    continue;
                }

                {
                    SingleLinkedList<Integer> possibleSolution = new SingleLinkedList<>();
                    possibleSolution.add(i);
                    minimumDistanceToReachVertex[i] = 1;
                    transitionAsFarAsPossible(i, possibleSolution);
                }
            }
        }

        return allPathsToDestination
            .stream()
            .map(integerSingleLinkedList -> {
                List<String> path = new ArrayList<>(integerSingleLinkedList.size + 1);
                LinkNode<Integer> tempNode = integerSingleLinkedList.head;
                path.add(beginWord);
                while (tempNode != null)
                {
                    path.add(wordList.get(tempNode.value));
                    tempNode = tempNode.next;
                }
                return path;
            })
            .collect(Collectors.toList());
    }

    private void transitionAsFarAsPossible(int beginIndex, SingleLinkedList<Integer> pathToSolution)
    {
        if (beginIndex == target)
        {
            if (allPathsToDestination.size() == 0 || (minimumDistanceSolution == pathToSolution.size))
            {
                allPathsToDestination.add(pathToSolution);
                minimumDistanceSolution = pathToSolution.size;
                return;
            }
            else if ((minimumDistanceSolution > pathToSolution.size))
            {
                allPathsToDestination.clear();
                allPathsToDestination.add(pathToSolution);
                minimumDistanceSolution = pathToSolution.size;
                return;
            }
            minimumDistanceSolution = pathToSolution.size;
            return;
        }
        if ((allPathsToDestination.size() > 0 && (minimumDistanceSolution <= pathToSolution.size)))
        {
            return;
        }
        for (int i = 0; i < inputLength; i++)
        {
            if (matrix[beginIndex][i] == 0)
            {
                if ((minimumDistanceToReachVertex[i] == 0) || (minimumDistanceToReachVertex[i] > pathToSolution.size))
                {
                    SingleLinkedList<Integer> updatedPath = new SingleLinkedList<>(pathToSolution);
                    updatedPath.add(i);
                    minimumDistanceToReachVertex[i] = updatedPath.size;
                    transitionAsFarAsPossible(i, updatedPath);
                }
            }
        }
    }

    private boolean isValidProblem(String beginWord, String endWord, List<String> wordList)
    {
        if ((beginWord.length() != wordList.get(0).length()) || (beginWord.length() != endWord.length()))
        {
            return false;
        }
        boolean pairAvailable = false;
        boolean endWordIsInDictionary = false;
        int inputSize = wordList.size();
        for (int i = 0; i < inputSize; i++)
        {
            if (makesPair(wordList.get(i), beginWord))
            {
                pairAvailable = true;
                startingPoint[i] = true;
            }
            if (wordList.get(i).equals(endWord))
            {
                endWordIsInDictionary = true;
                target = i;
            }
        }
        for (String word : wordList)
        {
            if (makesPair(word, beginWord))
            {
                pairAvailable = true;
            }
            if (word.equals(endWord))
            {
                endWordIsInDictionary = true;
            }
        }
        return pairAvailable && endWordIsInDictionary;
    }

    private boolean makesPair(String word1, String word2)
    {
        int mismatchedLetterCount = 0;
        for (int i = 0; i < word1.length(); i++)
        {
            if (word1.charAt(i) != word2.charAt(i))
            {
                ++mismatchedLetterCount;
            }
        }
        return (1 == mismatchedLetterCount);
    }

    private static class SingleLinkedList<T>
    {
        private int size = 0;
        private Solution.LinkNode<T> head;
        private Solution.LinkNode<T> tail;

        SingleLinkedList()
        {
        }

        @SuppressWarnings("CopyConstructorMissesField")
        SingleLinkedList(Solution.SingleLinkedList<T> source)
        {
            if (source != null && source.head != null)
            {
                head = tail = new Solution.LinkNode<>(source.head.value);
                Solution.LinkNode<T> tempNode = source.head;
                ++size;
                while (tempNode.hasNext())
                {
                    //noinspection unchecked
                    tail.next = new Solution.LinkNode(tempNode.next.value);
                    tail = tail.next;
                    tempNode = tempNode.next;
                    ++size;
                }
            }
        }

        void add(T value)
        {
            if (size == 0)
            {
                //noinspection unchecked
                head = tail = new Solution.LinkNode(value);
            }
            else
            {
                @SuppressWarnings("unchecked") Solution.LinkNode linkNode = new Solution.LinkNode(value);
                //noinspection unchecked
                tail.next = linkNode;
                //noinspection unchecked
                tail = linkNode;
            }
            ++size;
        }
    }

    private static class LinkNode<T>
    {
        private Solution.LinkNode<T> next;
        private T value;

        LinkNode(T value)
        {
            this.value = value;
        }

        boolean hasNext()
        {
            return next != null;
        }
    }
}
