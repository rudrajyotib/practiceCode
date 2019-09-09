package leetcode.hard.medianFromDataStream;

public class MedianFinder
{
    private DataStreamLinkedList dataStreamLinkedList;

    MedianFinder()
    {

        dataStreamLinkedList = new DataStreamLinkedList();
    }

    @SuppressWarnings("unused")
    public void print()
    {
        LinkNode node = dataStreamLinkedList.head;
        StringBuilder stringBuilder = new StringBuilder();
        while (node != null)
        {
            stringBuilder.append(node.value).append(" > ");
            node = node.next;
        }
        System.out.println(stringBuilder.toString() + "::MID::" + dataStreamLinkedList.mid.value + "::NEXT::" + (dataStreamLinkedList.mid.next == null ? "" : dataStreamLinkedList.mid.next.value));
    }

    void addNum(int num)
    {
        dataStreamLinkedList.addNumber(num);
        //print();
    }

    double findMedian()
    {
        if (dataStreamLinkedList.count % 2 == 0)
        {
            return (double) (dataStreamLinkedList.mid.value + dataStreamLinkedList.mid.next.value) / 2;
        }
        return (double) dataStreamLinkedList.mid.value;
    }

    private static class DataStreamLinkedList
    {
        LinkNode head = null;
        LinkNode tail = null;
        int count = 0;
        LinkNode mid = null;

        void addNumber(int number)
        {
            if (count == 0)
            {
                head = mid = tail = new LinkNode(number);
                ++count;
                return;
            }

            if (count == 1)
            {
                LinkNode node = new LinkNode(number);
                if (number >= head.value)
                {
                    head.next = node;
                    tail = node;
                    tail.prev = head;
                }
                else
                {
                    head = mid = node;
                    head.next = tail;
                    tail.prev = head;
                }
                ++count;
                return;
            }
            LinkNode newNode = new LinkNode(number);
            if (number < mid.value)
            {
                LinkNode tempNode = mid;
                while (tempNode != null)
                {
                    if (number >= tempNode.value)
                    {
                        break;
                    }
                    tempNode = tempNode.prev;
                }
                if (tempNode == null)
                {
                    head.prev = newNode;
                    newNode.next = head;
                    head = newNode;
                }
                else
                {
                    tempNode.next.prev = newNode;
                    newNode.next = tempNode.next;
                    tempNode.next = newNode;
                    newNode.prev = tempNode;
                }
                if (count % 2 == 1)
                {
                    mid = mid.prev;
                }
            }
            else if (number > mid.value)
            {
                LinkNode tempNode = mid;
                while (tempNode != null)
                {
                    if (number <= tempNode.value)
                    {
                        break;
                    }
                    tempNode = tempNode.next;
                }
                if (tempNode == null)
                {
                    tail.next = newNode;
                    newNode.prev = tail;
                    tail = newNode;
                }
                else
                {
                    tempNode.prev.next = newNode;
                    newNode.prev = tempNode.prev;
                    tempNode.prev = newNode;
                    newNode.next = tempNode;
                }
                if (count % 2 == 0)
                {
                    mid = mid.next;
                }
            }
            else
            {
                mid.next.prev = newNode;
                newNode.next = mid.next;
                mid.next = newNode;
                newNode.prev = mid;
                if (count % 2 == 0)
                {
                    mid = mid.next;
                }
            }
            ++count;
        }
    }

    private static class LinkNode
    {
        private LinkNode next;
        private LinkNode prev;
        private int value;

        LinkNode(int value)
        {
            this.value = value;
        }

        int getValue()
        {
            return value;
        }
    }
}
