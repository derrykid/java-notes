package stream;

import java.util.Arrays;
import java.util.List;

public class ReduceDemo {
    public static void main(String[] args) {
        List<String> letters = Arrays.asList("a", "b", "c");

        // "abc"
        String result = letters.stream().reduce("", (partialString, element) ->
                partialString + element);
        System.out.println(result);

    }
}
