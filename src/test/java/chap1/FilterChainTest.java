package chap1;

import chap1.FilterApples;
import dataobjects.Apple;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class FilterChainTest {

    @Test
    public void shouldBringApplesRedAndHeavierThan150g(){
        List<Apple> apples=new ArrayList(){{
            add(new Apple("Red", 200));
            add(new Apple("Red", 50));
            add(new Apple("Red", 30));
            add(new Apple("Red", 230));
            add(new Apple("Red", 230));
            add(new Apple("Green", 200));
            add(new Apple("Green", 200));
            add(new Apple("Green", 200));
            add(new Apple("Green", 200));
            add(new Apple("Green", 200));
        }};

        FilterApples filterApples=new FilterApples();

        Assert.assertEquals(3, filterApples.filterApplesWithPredicates(apples, ((Predicate<Apple>) apple -> apple.getColor().equalsIgnoreCase("Red")).and(apple -> apple.getMass()>50)).size());
    }

}
