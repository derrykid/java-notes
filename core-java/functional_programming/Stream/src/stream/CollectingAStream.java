package stream;

import util.Book;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.function.BinaryOperator.maxBy;

public class CollectingAStream {
    public static void main(String[] args) {
        Set<Book> bookSet = Book.loadPreBuildBooks();
        Stream<Book> books = bookSet.stream();

        // comma separate title
        String commaSeparateTitles = books.map(Book::getTitle)
                .collect(Collectors.joining(", "));

        // Summary statistics
        DoubleSummaryStatistics summaryStatistics = books
                .collect(Collectors.summarizingDouble(Book::getPrice));

        // group by a condition - Map<Boolean, List<T>>
        // when meet the condition, it'll be at Key true, vice versa
        Map<Boolean, List<Integer>> evenOddMap = Stream.of(12, 32, 42, 16, 82, 27)
                .collect(Collectors.partitioningBy(i -> i % 2 == 0));


        // groupingBy - K, V - use the classifier as the Key, and it's corresponding value as list
        Map<String, List<Book>> booksByAuthor = books
                .collect(Collectors.groupingBy(Book::getAuthor));

        Map<String, List<String>> titleListByAuthor = books
                .collect(Collectors.groupingBy(
                        Book::getAuthor,
                        Collectors.mapping(
                                Book::getTitle,
                                Collectors.toList())
                ));

        Map<String, Optional<Book>> highestPriceBookByAuthor = books
                .collect(Collectors.groupingBy(
                        Book::getAuthor,
                        Collectors.reducing(maxBy(Comparator.comparingDouble(Book::getPrice)))
                ));
    }
}
