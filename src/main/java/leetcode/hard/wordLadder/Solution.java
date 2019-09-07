package leetcode.hard.wordLadder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Solution
{
    private int target;
    private List<SingleLinkedList<Integer>> allPathsToDestination = new ArrayList<>();
    private int minimumDistanceSolution = -1;
    private int inputLength;
    private boolean[] startingPoint;
    private int[][] matrix;

    List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList)
    {
        return solveAdjacencyMatrixBfs(beginWord, endWord, wordList);
    }

    private List<List<String>> solveAdjacencyMatrixBfs(String beginWord, String endWord, List<String> wordList)
    {
        inputLength = wordList.size();
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
                    transitionAsFarAsPossibleBfs(i);
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

    private void transitionAsFarAsPossibleBfs(int beginIndex)
    {
        SingleLinkedList<Integer> traversalPathQueue = new SingleLinkedList<>();
        @SuppressWarnings("unchecked") PathTrace<Integer>[] pathToReach = new PathTrace[inputLength];
        traversalPathQueue.add(beginIndex);
        PathTrace<Integer> routeToFirstVertex = new PathTrace<>(beginIndex);
        routeToFirstVertex.initialiseSelf();
        pathToReach[beginIndex] = routeToFirstVertex;
        int[][] localMatrix = clone2dSquareArray(matrix);
        while (traversalPathQueue.size > 0)
        {
            Integer presentVertex = traversalPathQueue.poll();
            if (presentVertex == target)
            {
                traversalPathQueue.clear();
                continue;
            }
            if ((pathToReach[presentVertex].traceSize >= minimumDistanceSolution) && (minimumDistanceSolution > -1))
            {
                continue;
            }
            if (pathToReach[target] != null && pathToReach[target].traceSize <= pathToReach[presentVertex].traceSize)
            {
                continue;
            }
            for (int i = 0; i < inputLength; i++)
            {
                int nextVertex = localMatrix[presentVertex][i];
                if (nextVertex == -1)
                {
                    continue;
                }
                localMatrix[presentVertex][i] = -1;
                localMatrix[i][presentVertex] = -1;
                if (pathToReach[i] == null)
                {
                    PathTrace<Integer> pathToReachNextVertex = new PathTrace<>(i);
                    pathToReachNextVertex.addTrace(pathToReach[presentVertex]);
                    pathToReach[i] = pathToReachNextVertex;
                    traversalPathQueue.add(i);
                }
                else
                {
                    if (pathToReach[i].isTraceAcceptable(pathToReach[presentVertex]))
                    {
                        pathToReach[i].addTrace(pathToReach[presentVertex]);
                        traversalPathQueue.add(i);
                    }
                }
            }
        }
        if (pathToReach[target] != null)
        {
            if ((minimumDistanceSolution == -1) || (pathToReach[target].traceSize == minimumDistanceSolution))
            {
                allPathsToDestination.addAll(pathToReach[target].listOfTraces);
                minimumDistanceSolution = pathToReach[target].traceSize;
                return;
            }
            if (pathToReach[target].traceSize < minimumDistanceSolution)
            {
                minimumDistanceSolution = pathToReach[target].traceSize;
                allPathsToDestination.clear();
                allPathsToDestination.addAll(pathToReach[target].listOfTraces);
                minimumDistanceSolution = pathToReach[target].traceSize;
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

    private int[][] clone2dSquareArray(int[][] baseArray)
    {
        int[][] result = new int[baseArray.length][baseArray.length];
        for (int i = 0; i < baseArray.length; i++)
        {
            System.arraycopy(baseArray[i], 0, result[i], 0, baseArray.length);
        }
        return result;
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

        void clear()
        {
            size = 0;
            head = tail = null;
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

        T poll()
        {
            if (size == 0)
            {
                return null;
            }
            if (size == 1)
            {
                T value = head.value;
                head = tail = null;
                size = 0;
                return value;
            }
            T value = head.value;
            head = head.next;
            --size;
            return value;
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

    private static class PathTrace<T>
    {
        private List<SingleLinkedList<T>> listOfTraces;
        private T identity;
        private int traceSize = -1;

        PathTrace(T identity)
        {
            this.identity = identity;
            listOfTraces = new LinkedList<>();
        }

        boolean isTraceAcceptable(PathTrace<T> trace)
        {
            if (traceSize == -1)
            {
                return true;
            }
            return trace.traceSize < traceSize;
        }

        void initialiseSelf()
        {
            SingleLinkedList<T> selfPath = new SingleLinkedList<>();
            selfPath.add(identity);
            listOfTraces.add(selfPath);
            traceSize = 1;
        }

        void addTrace(PathTrace<T> trace)
        {
            if ((trace.traceSize + 1 < traceSize) || (traceSize == -1))
            {
                listOfTraces.clear();
                copyTrace(trace);
                return;
            }
            if (trace.traceSize + 1 == traceSize)
            {
                copyTrace(trace);
            }
        }

        private void copyTrace(PathTrace<T> trace)
        {
            for (SingleLinkedList<T> singleTrace : trace.listOfTraces)
            {
                SingleLinkedList<T> traceCopy = new SingleLinkedList<>(singleTrace);
                traceCopy.add(identity);
                listOfTraces.add(traceCopy);
            }
            traceSize = trace.traceSize + 1;
        }
    }
}
