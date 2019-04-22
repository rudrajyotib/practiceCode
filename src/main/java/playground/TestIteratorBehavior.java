package playground;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TestIteratorBehavior {

    public static void main(String[] args) {
        List<String> list=new ArrayList<String>()
        {
            {
                add("1");
                add("2");
                add("3");
                addAll(new ArrayList<String>(){
                    {
                        add("4");
                        add("5");
                    }
                });
                addAll(new ArrayList<String>(){
                    {
                        add("6");
                        add("7");
                    }
                });
            }
        };
        Iterator<String> iter1=list.iterator();
        Iterator<String> iter2=list.iterator();

        System.out.println(list.size());

        while(iter1.hasNext())
        {
            System.out.println(iter1.next());
        }

        while(iter2.hasNext())
        {
            System.out.println(iter2.next());
        }

        System.out.println(iter1==iter2);
    }

}
