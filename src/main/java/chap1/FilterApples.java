package chap1;

import dataobjects.Apple;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class FilterApples {

    public List<Apple> filterApplesWithPredicates(List<Apple> allApples, Predicate<Apple> appleFilter){
        List<Apple> result=new ArrayList<>();
        for (Apple apple : allApples) {
            if (appleFilter.test(apple))
                result.add(apple);
        }
        return result;
    }

    public void printApplesWithCriteria(List<Apple> allApples, ObjectPrinter<Apple> appleObjectPrinter){
        for (Apple apple : allApples) {
            appleObjectPrinter.formatAndPrint(apple);
        }
    }

}
