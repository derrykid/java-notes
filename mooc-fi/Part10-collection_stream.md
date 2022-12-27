# Handling collections as streams

In this part, we will get to know more about collections, e.g. lists, streams.
Stream is a way of going through a collection of data such that the programmer determines the operation to be performed on each value. **No record is kept of the index or the variable being processed at any given time** .

With streams, we can **defines a sequence of events that is executed for each value.** 

Stream doesn't change the values. It processes them. If you want to retain the transformation, you have to compile them into another data collection.

For example, we create a program that we're familiar with: Ask the user for inputs, and we calculate the average of the numbers:

```java
Scanner scanner = new Scanner(System.in);
List<String> inputs = new ArrayList<>();

while (true) {
    String row = scanner.nextLine();
    if (row.equals("end")) {
        break;
    }

    inputs.add(row);
}

// working out the average
double average = inputs.stream()
    .mapToInt(s -> Integer.valueOf(s))
    .average()
    .getAsDouble();

System.out.println("Average number: " + average);
```

**A stream can be formed from any object that implements the `Collection` interface.** e.g. ArrayList, HashMap, HashSet... with the `stream` method.

The String values are converted ("mapped") to integer form by using `mapToInt(s -> Integer.valueOf(s))` method. Then we calculate average by method `average` and get the result by `getAsDouble()` method the `terminating operation` as the final step.

# Lambda Expressions

Stream values are handled by methods. For example, the `filter` method used for filtering elements. It requires an expression that return boolean value. `filter(number -> number > 3)`. Moreover, the `mapToInt` method maps every element to an integer.

`value -> value > 5` is an lambda expression.

The lambda expression is an anonymous methods that doesn't have an owner class/interface. It can be coded in different ways:

```java
// origin
stream().filter(numbers -> numbers > 6).*functions()*

// same as
stream().filter((Integer numbers) -> {

    if (numbers > 6) {
        return true;
    }
    return false;

}).*functions()*


// or we simply call a 'Screeners' class method to perform
stream().filter(numbers -> Screeners.greaterThanSix(numbers)).*functions()*

// or even shorter
stream().filter(Screeners::greaterThanSix).*functions()*
```

**Functions that handle stream elements do not change the values of variables**. This has to do with how static methods behave. During a method call, there is no access to any variable outside of the method.

# Stream methods

1. intermediate operations e.g. `filter` `mapToInt`
2. terminal operations e.g. `average`

Intermediate operations return a value that can be further processed. We have have infinite intermediate operations.

Terminal operations return a value, which is formed from stream elements.

The figure below illustrates how a stream works.

1. A list of values
2. A stream of list values is created. Each value is processed individually.
3. Filter out the irrelevant value
4. map each value to be double up
5. use `collect` method to create a new list

![stream map](https://java-programming.mooc.fi/static/017e053fafe4c80050c350af52fbef31/c1a89/part10.1-stream.webp) 

An example program that create a new list:
```java
List<Integer> list = new ArrayList<>();
list.add(3);
list.add(7);
list.add(4);
list.add(2);
list.add(6);

ArrayList<Integer> values = list.stream().filter(value -> value >5).map(value -> value * 2).collect(Collectors.toCollection(ArrayList::new));
```

# Terminal operations

We introduce 4 terminal operations here: `count`, `forEach`, `collect`, and `reduce`.

- `count` it counts the number of the values that  on the list
- `forEach` it goes through the list values
- `collect` gathers the list values and create a new data structure
- `reduce` combine the list items

---
`count` return `long` type variable
```java
List<Integer> values = new ArrayList<>();
values.add(3);
values.add(2);
values.add(17);
values.add(6);
values.add(8);

System.out.println("Values: " + values.stream().count()); // Values: 5
```
---
`forEach` goes through each element in the stream
```java
List<Integer> values = new ArrayList<>();
values.add(3);
values.add(2);
values.add(17);
values.add(6);
values.add(8);

values.stream()
    .filter(value -> value % 2 == 0)
    .forEach(value -> System.out.println(value));
```
Output
```
2
6
8
```
---
`collect` create a new collection
```java
List<Integer> values = new ArrayList<>();
values.add(3);
values.add(2);
values.add(-17);
values.add(-6);
values.add(8);

ArrayList<Integer> positives = values.stream()
    .filter(value -> value > 0)
    .collect(Collectors.toCollection(ArrayList::new));

positiiviset.stream()
    .forEach(value -> System.out.println(value));
```
Output
```
3
2
8
```
We passed `Collectors.toCollection(ArrayList::new)` as the parameter to `collect` function. It's the `Collectors` object's method.

---
`reduce` it's used to combine stream elements to other form. You can calculate the sum or concatenate the string.
Format: `reduce(*startValue*, (*startVaraible*, value))`
```
ArrayList<Integer> values = new ArrayList<>();
values.add(7);
values.add(3);
values.add(2);
values.add(1);

int sum = values.stream()
    .reduce(0, (previousSum, value) -> previousSum + value);
System.out.println(sum); // 13
```
`reduce` String
```java
ArrayList<String> words = new ArrayList<>();
words.add("First");
words.add("Second");
words.add("Third");
words.add("Fourth");

String combined = words.stream()
    .reduce("", (previousString, word) -> previousString + word + "\n");
System.out.println(combined);
```

# Intermediate operations

Intermediate stream operations are methods that return a stream. Since the value returned is a stream, we can call intermediate operations sequentially.

Common ones: `map`, `filter`, `distinct` - identify unique value, and `sorted`.

```java
// suppose we have a list of persons
// ArrayList<Person> persons = new ArrayList<>();

persons.stream()
    .map(person -> person.getFirstName())
    .distinct()
    .sorted()
    .forEach(name -> System.out.println(name));
```

# Objects and stream

Assume we have a list of books:
```java
books.stream()
    .filter(book -> book.getName().contains("Potter"))
    .map(book -> book.getAuthor())
    .forEach(author -> System.out.println(author));
```

Streams can be used to build more complex  string:
```java
// let's assume that we have a list of books at our disposal
// ArrayList<Book> books = new ArrayList<>();

books.stream()
    .map(book -> book.getAuthor().getName() + ": " + book.getName())
    .sorted()
    .forEach(name -> System.out.println(name));
```

# Process files with stream

Streams are also very handy in handling files. We can use ready-made `Files` class. The `lines` method allows us to create an input stream.

The example below reads all the lines and adds them to the list:
```java
List<String> rows = new ArrayList<>();

try {
    Files.lines(Paths.get("file.txt").forEach(each -> rows.add(each));
} catch (Exception e) {
    System.out.println("Error: " + e.getMessage);
}
```

## Process the csv file with stream
say for example we have "president.txt"
```
Kaarlo Juho Ståhlberg; 1865
Lauri Kristian Relander; 1883
Pehr Evind Svinhufvud; 1861
Kyösti Kallio; 1873
Risto Heikki Ryti; 1889
Carl Gustaf Emil Mannerheim; 1867
Juho Kusti Paasikivi; 1870
Urho Kaleva Kekkonen; 1900
Mauno Henrik Koivisto; 1923
Martti Oiva Kalevi Ahtisaari; 1937
Tarja Kaarina Halonen; 1943
Sauli Väinämö Niinistö; 1948
```
We can process it with the following code:
```java
List<Person> presidents = new ArrayList<>();
try {
    Files.lines(Paths.get("presidents.txt"))
        // splitting the row into parts on the ";" character
        .map(row -> row.split(";"))
        // deleting the split rows that have less than two parts (we want the rows to always contain both the name and the birth year)
        .filter(parts -> parts.length >= 2)
        // creating persons from the parts
        .map(parts -> new Person(parts[0], Integer.valueOf(parts[1])))
        // and finally add the persons to the list
        .forEach(person -> presidents.add(person));
} catch (Exception e) {
    System.out.println("Error: " + e.getMessage());
}

// now the presidents are on the list as person objects
```
