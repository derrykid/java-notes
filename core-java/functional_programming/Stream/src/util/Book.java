package util;


import java.util.HashSet;
import java.util.Set;

public class Book {
    private String title;
    private String author;
    private double price;

    public Book(String title, String author, double price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public double getPrice() {
        return price;
    }

    public static Set loadPreBuildBooks() {
        Set<Book> books = new HashSet<>();
        books.add(new Book("The Lord of the Rings", "Tolkien", 60.0));
        books.add(new Book("The Hobbit", "Tolkien", 40.0));
        books.add(new Book("Harry Potter", "J.K. Rowling", 20.0));
        books.add(new Book("Da Vinci Code", "Dan Brown", 30.0));
        books.add(new Book("A Song of Ice and Fire", "Martin", 50.0));
        return books;
    }
}
