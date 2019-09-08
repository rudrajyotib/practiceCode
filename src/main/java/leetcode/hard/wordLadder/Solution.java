package leetcode.hard.wordLadder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Solution
{
    private int target;
    private List<SingleLinkedList<Integer>> allPathsToDestination = new ArrayList<>();
    private int minimumDistanceSolution = -1;
    private int inputLength;
    private SingleLinkedList<Integer> startingPointList = new SingleLinkedList<>();

    List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList)
    {
        inputLength = wordList.size();
        if (!isValidProblem(beginWord, endWord, wordList, startingPointList))
        {
            return new ArrayList<>();
        }
        return solveAdjacencyListBfs(beginWord, wordList);
    }

    private List<List<String>> solveAdjacencyListBfs(String beginWord, List<String> wordList)
    {
        SingleLinkedList<Integer>[] adjacencyList = createAdjacencyList(wordList);
        startingPointList
            .iterator()
            .forEachRemaining(integer -> transitionAsFarAsPossibleAdjacencyListBfs(integer, adjacencyList));

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

    private SingleLinkedList<Integer>[] createAdjacencyList(List<String> wordList)
    {
        @SuppressWarnings("unchecked") SingleLinkedList<Integer>[] adjacencyList = new SingleLinkedList[inputLength];
        for (int i = 0; i < inputLength; i++)
        {
            for (int j = i + 1; j < inputLength; j++)
            {
                if ((makesPair(wordList.get(i), wordList.get(j))))
                {
                    if (adjacencyList[i] != null)
                    {
                        adjacencyList[i].add(j);
                    }
                    else
                    {
                        SingleLinkedList<Integer> adjacency = new SingleLinkedList<>();
                        adjacency.add(j);
                        adjacencyList[i] = adjacency;
                    }
                    if (adjacencyList[j] != null)
                    {
                        adjacencyList[j].add(i);
                    }
                    else
                    {
                        SingleLinkedList<Integer> adjacency = new SingleLinkedList<>();
                        adjacency.add(i);
                        adjacencyList[j] = adjacency;
                    }
                }
            }
        }
        return adjacencyList;
    }

    private void transitionAsFarAsPossibleAdjacencyListBfs(int beginIndex, SingleLinkedList<Integer>[] adjacencyList)
    {
        SingleLinkedList<Integer> traversalPathQueue = new SingleLinkedList<>();
        @SuppressWarnings("unchecked") PathTrace<Integer>[] pathToReach = new PathTrace[inputLength];
        traversalPathQueue.add(beginIndex);
        PathTrace<Integer> routeToFirstVertex = new PathTrace<>(beginIndex);
        routeToFirstVertex.initialiseSelf();
        pathToReach[beginIndex] = routeToFirstVertex;
        boolean[][] visited = new boolean[inputLength][inputLength];
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
                traversalPathQueue.clear();
            }
            if (pathToReach[target] != null && pathToReach[target].traceSize <= pathToReach[presentVertex].traceSize)
            {
                continue;
            }
            Iterator<Integer> iterator = adjacencyList[presentVertex].iterator();
            while (iterator.hasNext())
            {
                int nextVertex = iterator.next();
                if (visited[presentVertex][nextVertex])
                {
                    continue;
                }
                visited[presentVertex][nextVertex] = true;
                visited[nextVertex][presentVertex] = true;
                if (pathToReach[nextVertex] == null)
                {
                    PathTrace<Integer> pathToReachNextVertex = new PathTrace<>(nextVertex);
                    pathToReachNextVertex.addTrace(pathToReach[presentVertex]);
                    pathToReach[nextVertex] = pathToReachNextVertex;
                    traversalPathQueue.add(nextVertex);
                }
                else
                {
                    if (pathToReach[nextVertex].isTraceAcceptable(pathToReach[presentVertex]))
                    {
                        pathToReach[nextVertex].addTrace(pathToReach[presentVertex]);
                        traversalPathQueue.add(nextVertex);
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

    private boolean isValidProblem(String beginWord, String endWord, List<String> wordList, SingleLinkedList<Integer> startingPointList)
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
                if (startingPointList != null)
                {
                    startingPointList.add(i);
                }
            }
            if (wordList.get(i).equals(endWord))
            {
                endWordIsInDictionary = true;
                target = i;
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

    static class SingleLinkedList<T>
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

        Iterator<T> iterator()
        {
            return new Iterator<T>()
            {
                LinkNode<T> node = head;

                @Override
                public boolean hasNext()
                {
                    return node != null;
                }

                @Override
                public T next()
                {
                    if (node != null)
                    {
                        T val = node.value;
                        node = node.next;
                        return val;
                    }
                    return null;
                }
            };
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
