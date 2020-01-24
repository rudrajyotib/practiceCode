package leetcode.hard.SerialiseDeserialiseBinaryTree;

import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class CodecTest
{
    @Test
    public void shouldSerialise()
    {
        TreeNode rootNode = new TreeNode(1);
        rootNode.left = new TreeNode(2);
        rootNode.right = new TreeNode(3);
        rootNode.left.left = new TreeNode(4);
        rootNode.right.right = new TreeNode(5);

        Codec codec = new Codec();
        assertThat(codec.serialize(rootNode), is("[Root, 1, Next, 2, 3, Next, 4, NULL, NULL, 5, Next, NULL, NULL, NULL, NULL]"));

        String serialised = codec.serialize(rootNode);

        TreeNode deserializedRootNode = codec.deserialize(serialised);
        assertThat(codec.serialize(deserializedRootNode), is(serialised));
    }

    @Test
    public void shouldSerialiseSingleElement()
    {
        TreeNode rootNode = new TreeNode(1);
        Codec codec = new Codec();
        String serialised = codec.serialize(rootNode);
        assertThat(serialised, is("[Root, 1, Next, NULL, NULL]"));
        TreeNode deserializedRootNode = codec.deserialize(serialised);
        assertThat(serialised, is(codec.serialize(deserializedRootNode)));
    }

    @Test
    public void shouldSerialiseEmptyTree()
    {
        Codec codec = new Codec();
        String serialised = codec.serialize(null);
        assertThat(serialised, is("EMPTY"));
        TreeNode deserializedRootNode = codec.deserialize(serialised);
        assertThat(deserializedRootNode, is(nullValue()));
    }

    @Test
    public void shouldSerialiseRightJustifiedTree()
    {
        TreeNode rootNode = new TreeNode(1);
        rootNode.right = new TreeNode(2);
        rootNode.right.right = new TreeNode(3);
        rootNode.right.right.right = new TreeNode(4);
        Codec codec = new Codec();
        String serialised = codec.serialize(rootNode);
        assertThat(serialised, is("[Root, 1, Next, NULL, 2, Next, NULL, 3, Next, NULL, 4, Next, NULL, NULL]"));
        TreeNode deserializedRootNode = codec.deserialize(serialised);
        assertThat(serialised, is(codec.serialize(deserializedRootNode)));
    }
}