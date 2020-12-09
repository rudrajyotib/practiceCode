package playground.largestTree;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class LargestTreeTest {

    @Test
    public void largestTree() {
        HashMap<Integer,Integer> map = new HashMap<>();
        map.put(1,2);
        map.put(3,4);
        Assert.assertEquals(Integer.valueOf(2),Solution.largestTree(map));

    }
    @Test
    public void largestTree1(){
        HashMap<Integer,Integer> map = new HashMap<>();
        map.put(1,2);
        map.put(6,1);
        map.put(9,6);
        map.put(10,6);
        map.put(8,4);
        map.put(5,4);
        map.put(3,4);
        Assert.assertEquals(Integer.valueOf(5),Solution.largestTree(map));
    }
}