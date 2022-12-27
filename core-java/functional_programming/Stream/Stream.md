[toc]
# stream

## Methods

### Intermediate operator, or non-terminal operations

#### distinct()

intermediate operator, return a stream of distinct elements
```java
List<String> list = // ...
List<String> distinctList = list.stream().distinct.toList();
```

#### limit()

intermediate operator, only get the first n object `limit(int n)`
```java
list.stream.limit(2).forEach(System.out::println);
```

#### peek()

Intermediate operator, `peek(Consumer<?> consumer)`

### Terminal operations

#### anyMatch(Predicate): boolean

One element match, return true

#### allMatch(Predicate): boolean

All match, return true, otherwise false

#### collect()

#### findAny(): Optional

#### findFirst(): Optional

#### forEach(Consumer)

#### toArray(): array

### Concatenate streams

```java
var concatStream = Stream.concat(stream1, stream2);
```

## Other Streams

- IntStream
- IntSummaryStatistics
- `Files.lines()` -  gives us the stream as well

## IntStream to go through every element in a list

```java
IntStream.range(0, integerList.size() - 1)
  .mapToObj(i -> new IntPair(integerList.get(i), integerList.get(i + 1))
  .filter(pair -> biPredicate.test(pair.first, pair.second))
  .forEach(System.out::println);
```

`record class`:
```java
record IntPair(int first, int second) {}
```

## Custom code to chunk stream element

[Message](https://discord.com/channels/272761734820003841/272768007191658497/974611666329276437) 

A stream processes an element at a time, you have to write your own method to chunk the elements
```java
    <T> stream<List<T>> chunk(stream<T> source, int maxChunkSize) {
        Iterator<T> i = source.iterator();

        return stream.generate(() -> {
            if (!i.hasNext()) {
                return null;
            }

            List<T> chunk = new ArrayList<>(maxChunkSize);

            int c = 0;
            do {
               chunk.add(i.next());
            } while (++c < maxChunkSize && i.hasNext());

            return chunk;
        })
        .takeWhile(Objects::nonNull)
        .onClose(source::close);
    }
```

Use case:
```java
chunk(stream.of("Cat", "Deer", "Dog", "Antelope", "Bear", "Wolverine"), 2).
        forEach(System.out::println);
```

Output:
```java
[Cat, Deer]
[Dog, Antelope]
[Bear, Wolverine]
```
