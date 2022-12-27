package stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class CollectionToStream {
    public static void main(String[] args) {
        // use static method provided by Array
        Integer[] integers = {10, 32, -12, 29, 85};
        Stream<Integer> integerStream = Arrays.stream(integers);

        List<Integer> list = Arrays.asList(integers);
        Stream<Integer> listStream = list.stream();
    }
}
