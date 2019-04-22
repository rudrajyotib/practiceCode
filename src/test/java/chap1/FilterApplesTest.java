package chap1;

import dataobjects.Apple;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class FilterApplesTest {

    private List<Apple> allApples;
    private String[] appleColors={"RED","GREEN"};
    private FilterApples filterApples;

    @Before
    public void generateApples(){
        allApples=new ArrayList<>();
        for (int i=1;i<=10;i++){
            allApples.add(new Apple(appleColors[i%2], i*17));
        }
        filterApples=new FilterApples();
    }

    @Test
    public void shouldFilterGreenApples(){
        assertThat(filterApples.filterApplesWithPredicates(allApples, new Predicate<Apple>() {
            @Override
            public boolean test(Apple apple) {
                return "GREEN".equals(apple.getColor());
            }
        }), new BaseMatcher<List<Apple>>() {
            @Override
            public boolean matches(Object item) {
                List<Apple> result=(List<Apple>)item;
                for (Apple apple : result) {
                    if (!"GREEN".equals(apple.getColor()))
                        return false;
                }
                return true;
            }

            @Override
            public void describeTo(Description description) {

            }
        });
    }

    @Test
    public void shouldFilterRedApplesHeavierThan160g(){
        assertThat(filterApples.filterApplesWithPredicates(allApples, apple -> "RED".equals(apple.getColor()) && apple.getMass()>160), new BaseMatcher<List<Apple>>() {
            @Override
            public boolean matches(Object item) {
                List<Apple> result=(List<Apple>)item;
                return result.size()==1;
            }

            @Override
            public void describeTo(Description description) {

            }
        });
    }

    @Test
    public void shouldPrintOnlyRedApples(){
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));
        filterApples.printApplesWithCriteria(allApples, object -> {
            if ("RED".equals(object.getColor())){
                System.out.println(String.format("APPLE COLOR::%s",object.getColor()));
            }
        });
        String consoleOutput=byteArrayOutputStream.toString();
        assertTrue(consoleOutput.contains("RED"));
        assertFalse(consoleOutput.contains("GREEN"));
    }

}