package leetcode.hard.wordLadder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Solution
{
    private Map<String, Vertex> vertexMap = new HashMap<>();
    private SingleLinkedList<SingleLinkedList<String>> results = new SingleLinkedList<>();
    private int shortestTransitionLength = 0;

    List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList)
    {
        if (!isValidProblem(beginWord, endWord, wordList))
        {
            return new ArrayList<>();
        }

        for (int i = 0; i < wordList.size(); i++)
        {
            String word = wordList.get(i);
            for (int j = i; j < wordList.size(); j++)
            {
                if (makesPair(word, wordList.get(j)))
                {
                    String pairedWord = wordList.get(j);
                    if (vertexMap.containsKey(word))
                    {
                        vertexMap.get(word).addToEdge(pairedWord);
                    }
                    else
                    {
                        Vertex v1 = new Vertex(word);
                        v1.addToEdge(pairedWord);
                        vertexMap.put(word, v1);
                    }
                    if (vertexMap.containsKey(pairedWord))
                    {
                        vertexMap.get(pairedWord).addToEdge(word);
                    }
                    else
                    {
                        Vertex v2 = new Vertex(pairedWord);
                        v2.addToEdge(word);
                        vertexMap.put(pairedWord, v2);
                    }
                }
            }
        }

        vertexMap
            .forEach((s, vertex) -> {
                if (makesPair(s, beginWord))
                {
                    SingleLinkedList<String> transition = new SingleLinkedList<String>()
                    {
                        {
                            add(beginWord);
                            add(s);
                        }
                    };
                    transitionAsFarAsPossible(s, endWord, transition);
                }
            });

        if (results.size == 0)
        {
            return new ArrayList<>();
        }
        return results.toList()
            .stream()
            .map(SingleLinkedList::toList)
            .collect(Collectors.toList());
    }

    private void transitionAsFarAsPossible(String beginWord, String endWord, SingleLinkedList<String> wordsVisitedInTransition)
    {
        //who am i
        //am i destination
        if (endWord.equals(beginWord))
        {
            int transitionLength = wordsVisitedInTransition.size;
            if (results.size == 0)
            {
                results.add(wordsVisitedInTransition);
                shortestTransitionLength = transitionLength;
                return;
            }
            if (transitionLength > shortestTransitionLength)
            {
                return;
            }
            if (transitionLength < shortestTransitionLength)
            {
                shortestTransitionLength = transitionLength;
                results.clear();
                results.add(wordsVisitedInTransition);
                return;
            }
            results.add(wordsVisitedInTransition);
            return;
        }
        if (shortestTransitionLength != 0 && wordsVisitedInTransition.size >= shortestTransitionLength)
        {
            return;
        }
        //my edges
        vertexMap.get(beginWord)
            .getEdges()
            .forEachRemaining(s -> {
                if (!wordsVisitedInTransition.contains(s))
                {
                    SingleLinkedList<String> visitedTransitions = new SingleLinkedList<>(wordsVisitedInTransition);
                    visitedTransitions.add(s);
                    transitionAsFarAsPossible(s, endWord, visitedTransitions);
                }
            });
        //iterate over my edges, if not already traversed, clone visited set and add me to the set and pass on recursion.
    }

    private boolean isValidProblem(String beginWord, String endWord, List<String> wordList)
    {
        if ((beginWord.length() != wordList.get(0).length()) || (beginWord.length() != endWord.length()))
        {
            return false;
        }
        boolean pairAvailable = false;
        boolean endWordIsInDictionary = false;
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

    private static class Vertex
    {
        private final String value;
        private List<String> edges;

        Vertex(String value)
        {
            this.value = value;
            this.edges = new LinkedList<>();
        }

        void addToEdge(String connectedWord)
        {
            edges.add(connectedWord);
        }

        Iterator<String> getEdges()
        {
            return edges.iterator();
        }

        @Override
        public boolean equals(Object o)
        {
            if (this == o) { return true; }
            if (!(o instanceof Vertex)) { return false; }
            Vertex vertex = (Vertex) o;
            return Objects.equals(value, vertex.value);
        }

        @Override
        public int hashCode()
        {
            return Objects.hash(value);
        }
    }

    private static class SingleLinkedList<T>
    {
        private int size = 0;
        private LinkNode<T> head;
        private LinkNode<T> tail;

        SingleLinkedList()
        {
        }

        @SuppressWarnings("CopyConstructorMissesField")
        SingleLinkedList(SingleLinkedList<T> source)
        {
            if (source != null && source.head != null)
            {
                head = tail = new LinkNode<>(source.head.value);
                LinkNode<T> tempNode = source.head;
                ++size;
                while (tempNode.hasNext())
                {
                    //noinspection unchecked
                    tail.next = new LinkNode(tempNode.next.value);
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
                head = tail = new LinkNode(value);
            }
            else
            {
                @SuppressWarnings("unchecked") LinkNode linkNode = new LinkNode(value);
                //noinspection unchecked
                tail.next = linkNode;
                //noinspection unchecked
                tail = linkNode;
            }
            ++size;
        }

        void clear()
        {
            head = tail = null;
            size = 0;
        }

        boolean contains(T toCompare)
        {
            LinkNode<T> node = head;
            while (node != null)
            {
                if (Objects.equals(node.value, toCompare))
                {
                    return true;
                }
                node = node.next;
            }
            return false;
        }

        List<T> toList()
        {
            if (size == 0)
            {
                return new ArrayList<>();
            }
            List<T> list = new ArrayList<>();
            LinkNode<T> tempNode = head;
            list.add(head.value);
            while (tempNode.hasNext())
            {
                list.add(tempNode.next.value);
                tempNode = tempNode.next;
            }
            return list;
        }
    }

    private static class LinkNode<T>
    {
        private LinkNode<T> next;
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
