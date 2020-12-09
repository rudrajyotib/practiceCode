package playground.largestTree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Solution
{

    public static Integer largestTree(final Map<Integer, Integer> immediateParent)
    {
        HashMap<Integer, Node> nodeMap = new HashMap<>(immediateParent.size());

        for (Map.Entry<Integer, Integer> integerIntegerEntry : immediateParent.entrySet())
        {
            if(nodeMap.containsKey(integerIntegerEntry.getValue()))
            {
                Node node = new Node(integerIntegerEntry.getKey(),
                        nodeMap.get(integerIntegerEntry.getValue()));
                nodeMap.get(integerIntegerEntry.getValue()).addChild(node);
                nodeMap.put(integerIntegerEntry.getKey(), node);
            }else
            {
                Node parentNode = new Node(integerIntegerEntry.getValue(), null);
                Node childNode = new Node(integerIntegerEntry.getKey(), parentNode);
                parentNode.addChild(childNode);
                nodeMap.put(parentNode.nodeId, parentNode);
                nodeMap.put(childNode.nodeId, childNode);
            }
        }

        return nodeMap.values()
                .stream()
                .filter(node -> node.parent == null)
                .max((o1, o2) -> {
                    if (o1.getSize() == o2.getSize()) {
                        return o2.nodeId - o1.nodeId;
                    }
                    return o1.getSize() - o2.getSize();
                })
                .map(node -> node.nodeId)
                .orElse(0);
    }

    public static class Node
    {
        private final int nodeId;
        private Node parent;
        private final LinkedList<Node> children;
        private int size = 0;

        public Node(int nodeId, Node parent)
        {
            this.nodeId = nodeId;
            this.parent = parent;
            this.children = new LinkedList<>();
        }

        public void addChild(Node node)
        {
            this.children.add(node);
        }

        public int getSize()
        {
            if (size==0)
            {
                updateSize();
            }
            return size;
        }

        private void updateSize()
        {
            int size = 1;
            while (children.size()!=0)
            {
                Node firstChild = children.pollFirst();
                ++size;
                children.addAll(firstChild.children);
            }
            this.size = size;
        }

    }

}
