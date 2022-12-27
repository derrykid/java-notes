package stream;

import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class CreateStream {
    public static void main(String[] args) {
        // Generic stream takes object
        Stream<Integer> integerStream = Stream.of(12, 32, 25);

        // primitive type stream
        IntStream intStream = IntStream.of(32, 12, 22);
        DoubleStream doubleStream = DoubleStream.of(23.2, 12.3);
        LongStream longStream = LongStream.of(123, 324);

        // use a builder to build a stream
        Stream<String> stringStream = Stream.<String>builder().add("Like")
                .add("Subscribe").build();

    }
}
