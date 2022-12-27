package stream;

import util.Book;
import util.Bookie;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class toMapDemo {

    public static void main(String[] args) {
        List<Bookie> bookList = new ArrayList<>();
        bookList.add(new Bookie("The Fellowship of the Ring", 1954, "0395489318"));
        bookList.add(new Bookie("The Two Towers", 1954, "0345339711"));
        bookList.add(new Bookie("The Return of the King", 1955, "0618129111"));

        var keyAndValuesMap = keyIsIsbnAndValueIsTheName(bookList);
        var listToMapWithDuplicateKey = listToMapWithDuplicateKey(bookList);

        var treeMap = createATreeMap(bookList);
        System.out.println(treeMap);

    }

    private static Map<String, String> keyIsIsbnAndValueIsTheName(List<Bookie> booklist) {
        return booklist.stream()
                .collect(Collectors.toMap(Bookie::getIsbn, Bookie::getName));
    }

    // the merge function is used to resolve the conflicts when 2 keys have the same value
    // the function.identity return itself
    private static Map<Integer, Bookie> listToMapWithDuplicateKey(List<Bookie> list) {
        return list.stream().collect(Collectors.toMap(
                Bookie::getReleaseYear,
                Function.identity(),
                (existing, replacement) -> existing
        ));
    }

    // use the Collectors.toMap 4th map supplier to create a treemap
    private static Map<Integer, Bookie> createATreeMap(List<Bookie> list) {
        return list.stream().collect(Collectors.toMap(
                Bookie::getReleaseYear,
                Function.identity(),
                (existing, replacement) -> existing,
                TreeMap::new
        ));
    }


}















