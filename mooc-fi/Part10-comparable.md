# Comparable interface

`Comparable` **interface** defines the `compareTo` method. It's used to compare objects.
If a class implements the `Comparable` interface, objects created from that class can be sorted using Java's sorting algorithms.

The `compareTo` method is required by the interface. 
An example of comparing weights:
if `this` weight more than compared object, returns positive number;

We have a diagram here
```
    |
    v
a b c d e

// compareTo() method
// anything before the arrow (left to c) is negative; anything after the arrow (right to c) is positive.
// if equals, return 0;
// the position that the arrow points at is the parameter compareTo(object c);

String str_Sample = "a";
int output = str_Sample.compareTo("c"); // the output is negative
```

```java
public class Member implements Comparable<Member> {

// ~~~~~
// ~~~~~

    @Override
    public int compareTo(Member member) {
        if (this.height == member.getHeight()) {
            return 0;
        } else if (this.height > member.getHeight()) {
            return 1;
        } else {
            return -1;
        }
```

The `compareTo` method required by the interface returns an integer that informs us the order of the comparison. As return a negative/positive number is enough. We can implement the method as follows:
```java
@Override
public int compareTo(Member member) {
    return this.length - member.getHeight();
}
```

**In fact, any class that implements the `Comparable` interface can be sorted using the `sorted` method.** 

If we want to organize the origin list, the `Collections` class method `sort` can be used. **This, of course, assumes that the objects on the list implement the `Comparable` interface**. 

```java
List<Member> member = new ArrayList<>();
member.add(new Member("mikael", 182));
member.add(new Member("matti", 187));
member.add(new Member("ada", 184));

// sort the list but we didn't change the original list
member.stream().sorted().forEach(each -> System.out.println(each));

// sort the list, and we change the original list
Collections.sort(member);
member.stream().forEach(each -> System.out.println(each));
```

- `Comparable` interface use `sorted()`
- `Collections` class use `sort()`

## Compare `String` by `compareTo()` method
We can use the `compareTo` method provided by the `String` class. It returns `int` as well.

```java
ArrayList<Person> persons = new ArrayList<>();

// add some to the list

persons.stream().sorted((p1, p2) -> {
    return p1.getName().compareTo(p2.getName());
}).forEach(p -> System.out.println(p.getName()));
```
Output
```java
Ada Lovelace
Grace Hopper
Irma Wyman
Mary Coombs
```


## Implements multiple interface

Simply separate each interface with comma
```java
public class Person implements Identifiable, Comparable<Person> {
    // code
}

```

# Sort with Lambda expression

Both the `Collections.sort(object)` and `stream().sorted()` can accept a lambda expression as a parameter.

```java
ArrayList<Person> persons = new ArrayList<>();

// add some elements to the arraylist;

// use stream way
persons.stream().sorted((p1, p2) -> {
    return p1.getBirthYear() - p2.getBirthYear();
}).forEach(p -> System.out.println(p.getName()));

// use Collections way
Collections.sort(persons, (p1, p2) -> p1.getBirthYear() - p2.getBirthYear());
persons.stream().forEach(p -> System.out.println(p.getName()));
```

# Sorting by multiple criteria

We can make use of `Comparator` class here. **It's not `Comparable` interface** 

The `Comparator` class provides 2 essential methods for sorting: `comparing` and `thenComparing`.

- `comparing` method is passed to be first
- `thenComparing` is the next value to be compared. It can be used multiple times.

The method parameter is given as `Class::method`
```java
List<Film> films = new ArrayList<>();
films.add(new Film("A", 2000));
films.add(new Film("B", 1999));
films.add(new Film("C", 2001));
films.add(new Film("D", 2000));

for (Film e: films) {
    System.out.println(e);
}

Comparator<Film> comparator = Comparator
              .comparing(Film::getReleaseYear)
              .thenComparing(Film::getName);

Collections.sort(films, comparator);

for (Film e: films) {
    System.out.println(e);
}
```

Notice that the `Collections.sort()` accepts 2 parameters here. It's different from the `Collections.sort(member)` example above. **The member example implements the `Comparable` interface.** This one isn't.

Comparable
- `Collections.sort(films)` this way, we need to implements `Comparable<Film>` in `Film` class. Override the `compareTo` method in the `Film` class.

Comparator
- `Collections.sort(films, comparator)` this, we have to `import java.util.Collections` and `import java.util.Comparator`. We also have to add `Comparator<Film> ... comparing().thenComparing()...` to the main program.

---
In last exercise of Part10, sort the card hands, we use the `Comparator` method `compare(c1, c2)` which accepts 2 parameters.
```java
import java.util.Comparator;

public class SortBySuit implements Comparator<Card> {

	public int compare(Card c1, Card c2) {
		return c1.getSuit().ordinal() - c2.getSuit().ordinal();
	}
}
```
It can be called by
```java
	Collections.sort(cards, new BySuitInValueOrder());
```
