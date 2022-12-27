package stream;

import util.Book;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TakeWhileAndDropWhile {
    public static void main(String[] args) {
        // takeWhile - take everything before it is false
        IntStream intStream = IntStream.rangeClosed(1, 10);
        List<Integer> listLessThanFive = intStream.takeWhile(i -> i < 5).boxed().toList();
        System.out.println(listLessThanFive);

        // dropWhile - take everything after it is false
        IntStream intStream2 = IntStream.rangeClosed(1, 10);
        List<Integer> listGreaterThanFive = intStream2.dropWhile(i -> i < 5).boxed().toList();
        System.out.println(listGreaterThanFive);
    }
}
