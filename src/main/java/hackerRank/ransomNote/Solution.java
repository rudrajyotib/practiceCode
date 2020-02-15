package hackerRank.ransomNote;

import com.sun.corba.se.spi.orbutil.threadpool.Work;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.*;
import java.util.stream.Collectors;

public class Solution {

    static class WordCounter
    {
        private int count=1;

        public WordCounter()
        {

        }

        public void increment()
        {
            ++count;
        }

        public boolean decrement()
        {
            if(count==0)
            {
                return false;
            }

            --count;
            return true;
        }

        public int getCount()
        {
            return count;
        }
    }

    static void checkMagazine(String[] magazine, String[] note) {
        if(magazine.length < note.length)
        {
            System.out.println("No");
            return;
        }

        Map<String, WordCounter> magWordCount=new HashMap<String, WordCounter>();

        for (String word : magazine) {
            if(magWordCount.containsKey(word))
            {
                magWordCount.get(word).increment();
            }
            else
            {
                magWordCount.put(word, new WordCounter());
            }
        }

        for (String word : note) {
            if (magWordCount.containsKey(word))
            {
                boolean decrement = magWordCount.get(word).decrement();
                if(!decrement)
                {
                    System.out.println("No");
                    return;
                }
            }else
            {
                System.out.println("No");
                return;
            }
        }

        System.out.println("Yes");
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String[] mn = scanner.nextLine().split(" ");

        int m = Integer.parseInt(mn[0]);

        int n = Integer.parseInt(mn[1]);

        String[] magazine = new String[m];

        String[] magazineItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < m; i++) {
            String magazineItem = magazineItems[i];
            magazine[i] = magazineItem;
        }

        String[] note = new String[n];

        String[] noteItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            String noteItem = noteItems[i];
            note[i] = noteItem;
        }

        checkMagazine(magazine, note);

        scanner.close();
    }


}
