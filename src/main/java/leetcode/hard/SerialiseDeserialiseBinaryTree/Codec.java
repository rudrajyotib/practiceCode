package leetcode.hard.SerialiseDeserialiseBinaryTree;

import java.util.LinkedList;
import java.util.List;

@SuppressWarnings("WeakerAccess")
public class Codec
{
    public static final String NULL_VALUE = "NULL";
    public static final String NEXT_LEVEL = "Next";
    public static final String ROOT = "Root";
    public static final String EMPTY = "EMPTY";

    // Encodes a tree to a single string.
    public String serialize(TreeNode root)
    {

        if (root == null)
        {
            return EMPTY;
        }

        LinkedList<TreeNode> childNodesAtSameLevel = new LinkedList<>();
        LinkedList<TreeNode> childNodesAtNextLevel = new LinkedList<>();
        List<String> bfsSerialiser = new LinkedList<>();
        childNodesAtSameLevel.add(root);
        bfsSerialiser.add(ROOT);
        bfsSerialiser.add(Integer.toString(root.val));
        bfsSerialiser.add(NEXT_LEVEL);

        while (childNodesAtSameLevel.size() > 0)
        {
            TreeNode treeNode = childNodesAtSameLevel.removeFirst();
            if (treeNode.left != null)
            {
                bfsSerialiser.add(Integer.toString(treeNode.left.val));
                childNodesAtNextLevel.add(treeNode.left);
            }
            else
            {
                bfsSerialiser.add(NULL_VALUE);
            }
            if (treeNode.right != null)
            {
                bfsSerialiser.add(Integer.toString(treeNode.right.val));
                childNodesAtNextLevel.add(treeNode.right);
            }
            else
            {
                bfsSerialiser.add(NULL_VALUE);
            }
            if (childNodesAtSameLevel.size() == 0)
            {
                if (childNodesAtNextLevel.size() != 0)
                {
                    childNodesAtSameLevel.addAll(childNodesAtNextLevel);
                    childNodesAtNextLevel.clear();
                    bfsSerialiser.add(NEXT_LEVEL);
                }
            }
        }

        return bfsSerialiser.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data)
    {

        if (EMPTY.equals(data))
        {
            return null;
        }

        String rawData = data.substring(1, data.length() - 1);

        String[] elements = rawData.split(", ");

        if (elements.length < 2)
        {
            return null;
        }

        if (elements.length == 2)
        {
            if (elements[1].equals(NULL_VALUE))
            {
                return null;
            }
            return new TreeNode(Integer.parseInt(elements[1]));
        }

        TreeNode rootNode = new TreeNode(Integer.parseInt(elements[1]));

        LinkedList<TreeNode> currentLevel = new LinkedList<>();
        currentLevel.add(rootNode);
        LinkedList<TreeNode> previousLevel = new LinkedList<>();

        char pos = 'L';
        for (int i = 2; i < elements.length; i++)
        {
            if (elements[i].equals(NEXT_LEVEL))
            {
                previousLevel.addAll(currentLevel);
                currentLevel.clear();
                pos = 'L';
                continue;
            }
            if (pos == 'L')
            {
                if (!elements[i].equals(NULL_VALUE))
                {
                    TreeNode treeNode = new TreeNode(Integer.parseInt(elements[i]));
                    previousLevel.getFirst().left = treeNode;
                    currentLevel.add(treeNode);
                }
                pos = 'R';
            }
            else
            {
                if (!elements[i].equals(NULL_VALUE))
                {
                    TreeNode treeNode = new TreeNode(Integer.parseInt(elements[i]));
                    previousLevel.getFirst().right = treeNode;
                    currentLevel.add(treeNode);
                }
                previousLevel.removeFirst();
                pos = 'L';
            }
        }

        return rootNode;
    }
}
