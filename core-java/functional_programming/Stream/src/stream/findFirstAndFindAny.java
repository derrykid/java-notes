package stream;

import util.Book;

import java.util.Optional;
import java.util.Set;

public class findFirstAndFindAny {
    public static void main(String[] args) {
        Set<Book> bookSet = Book.loadPreBuildBooks();
        Optional<Book> firstBook = bookSet.stream().findFirst();
        Optional<Book> anyBook = bookSet.stream().findAny();
    }
}
