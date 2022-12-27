package stream;

import util.Book;

import java.util.*;

public class minAndMax {
    public static void main(String[] args) {
        Set<Book> books = Book.loadPreBuildBooks();

        Optional<Book> mostExpensiveBook =
                books.stream().max(Comparator.comparing(Book::getPrice));
        Optional<Book> alphabeticallyFirstBook =
                books.stream().min(Comparator.comparing(Book::getAuthor));


    }
}
