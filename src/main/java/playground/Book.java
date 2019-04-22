package playground;

public class Book {

    private final String id;
    private final String bookName;

    public Book(String id, String bookName) {
        this.id = id;
        this.bookName = bookName;
    }

    public String getId() {
        return id;
    }

    public String getBookName() {
        return bookName;
    }
}
