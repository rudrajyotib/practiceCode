package leetcode.hard.trappingRainWater;
/*
https://leetcode.com/problems/trapping-rain-water/
 */

class Solution
{
    int trap(int[] height)
    {

        Basin basin = new Basin();
        for (int i : height)
        {
            basin.addWall(i);
        }

        return basin.confirmedContainer;
    }

    class Block
    {
        private final int width;
        private final int height;

        Block(int height)
        {
            this.width = 1;
            this.height = height;
        }

        Block(int width, int height)
        {
            this.width = width;
            this.height = height;
        }

        int calculateBasinSizeForHeight(int wallHeight)
        {
            if (wallHeight < height)
            {
                return 0;
            }
            return width * (wallHeight - height);
        }
    }

    @SuppressWarnings("SuspiciousNameCombination")
    class Basin
    {
        private static final char Up = 'U';
        private static final char Down = 'D';
        DoubleLinkedList<Block> allBlocks = new DoubleLinkedList<>();
        private int tallestBlockOnLeftOfBasin = -1;
        private int confirmedContainer = 0;
        private char direction;

        void addWall(int height)
        {
            if (allBlocks.size == 0)
            {
                if (height != 0)
                {
                    allBlocks.push(new Block(height));
                    tallestBlockOnLeftOfBasin = height;
                    direction = Up;
                }
                return;
            }
            if (height < allBlocks.peek().height)
            {
                allBlocks.push(new Block(height));
                direction = Down;
                return;
            }
            if (height == allBlocks.peek().height)
            {
                if (direction == Down)
                {
                    allBlocks.push(new Block(height));
                }
                return;
            }
            if (height > allBlocks.peek().height && direction == Up)
            {
                allBlocks.clear();
                allBlocks.push(new Block(height));
                direction = Up;
                tallestBlockOnLeftOfBasin = height;
                return;
            }
            if (height >= tallestBlockOnLeftOfBasin)
            {
                int currentBasinSize = 0;
                while (allBlocks.size > 1)
                {
                    Block lastBlockOnList = allBlocks.pop();
                    currentBasinSize += lastBlockOnList.calculateBasinSizeForHeight(tallestBlockOnLeftOfBasin);
                }
                confirmedContainer += currentBasinSize;
                allBlocks.clear();
                allBlocks.push(new Block(height));
                tallestBlockOnLeftOfBasin = height;
                direction = Up;
                return;
            }
            boolean levelFound = false;
            int currentBasinSize = 0;
            int currentWidth = 1;
            while (!levelFound)
            {
                if (allBlocks.peek().height <= height)
                {
                    Block lastBlockOnList = allBlocks.pop();
                    currentBasinSize += lastBlockOnList.calculateBasinSizeForHeight(height);
                    currentWidth += lastBlockOnList.width;
                }
                else
                {
                    levelFound = true;
                    allBlocks.push(new Block(currentWidth, height));
                    direction = Down;
                }
            }
            confirmedContainer += currentBasinSize;
        }
    }

    @SuppressWarnings("unchecked")
    class DoubleLinkedList<T>
    {
        private Node<T> head;
        private Node<T> tail;
        private int size = 0;

        void clear()
        {
            head = tail = null;
            size = 0;
        }

        void push(T value)
        {

            if (size == 0)
            {
                head = tail = new Node(value);
            }

            if (size == 1)
            {
                tail = new Node(value);
                tail.previous = head;
                head.next = tail;
            }

            if (size > 1)
            {
                Node node = new Node(value);
                tail.next = node;
                node.previous = tail;
                tail = node;
            }

            ++size;
        }

        T peek()
        {
            return size == 0 ? null : tail.value;
        }

        T pop()
        {
            T value = tail.value;
            if (size == 1)
            {
                head = tail = null;
                size = 0;
                return value;
            }

            if (size == 2)
            {
                head.next = null;
                tail = head;
                size = 1;
                return value;
            }

            tail = tail.previous;
            tail.next = null;
            --size;

            return value;
        }
    }

    class Node<T>
    {
        private T value;
        @SuppressWarnings("unused")
        private Node next;
        private Node previous;

        Node(T value)
        {
            this.value = value;
        }
    }
}
