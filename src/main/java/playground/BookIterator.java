package playground;

import java.util.*;

public class BookIterator {

    public static void main(String[] args) {

        Map<String, Book> modifiableBooks = generateBooks(5);

        Map<String, Book> unmodifiableBooksMap = Collections.unmodifiableMap(modifiableBooks);


        for (Book book : unmodifiableBooksMap.values())
        {
            if (book.getId().contains("2"))
            {
                modifiableBooks.put("6", new Book("B6","B6"));
            }
        }

    }


    private static Map<String, Book> generateBooks(int numberOfBooks)
    {
        Map<String, Book> books = new TreeMap<String, Book>();
        for (int i=0;i<numberOfBooks;i++)
        {
            Book singleBook = new Book(String.format("%s-%d","bookId", i),String.format("%s-%d","book name", i));
            books.put(singleBook.getId(), singleBook);
        }
        return books;
    }


}
