# StringBuilder

String creation - although unnoticeable at a small scale - is not a quick operation. Space is allocated in memory for each string where the string is then placed. If the string is only needed as part of creating a larger string, performance should be improved.

Java's `StringBuilder` class provides a way to concatenate strings without the need to create them. In other words, using `StringBuilder` is more efficient than creating strings with the `+` operator.

```java
StringBuilder numbers = new StringBuilder();
for (int i = 1; i < 5; i++) {
    numbers.append(i);
}
System.out.println(numbers.toString());
```

The content is added to the object sing the overloaded `append` method.

# Regular expression regex
- Alternation (vertical bar) `|` e.g. `00|111` mean `00` or `111`
- Parentheses `()` a part of the expression e.g. `0000(0|1)` means `00000` or `00001`
- Square brackets `[]` e.g. [145] means (1|4|5) and [a-z] means a to z
Quantifiers
- `*` repeats 0 ... many times e.g. `trol(lo)*`
- `+` repeats 1 ... many times e.g. `tro(lo)+`
- `?` repeats 0 or 1 time e.g. `colo(u)?r`
- `{a}` repeats a times e.g. `(10){3}` is `101010`
- `{a,b}` repeats a ... b times e.g. `1{2,4}` 
- `{a,}` repeats aleast a times

# Enumerated type - enum

If we know the possible values of a variable in advance, we can use a class type `enum`. We create enum class like any other classes.
For example:
```java
public enum Suit {
    DIMAND, SPADE, CLUB, HEART
}
```

In its simplest form, enum lists the constant values it declares, separated by a comma. Enum types, i.e., constants, are conventionally written with capital letters.

```java
public class Card {

    private int value;
    private Suit suit;

    public Card(int value, Suit suit) {
        this.value = value;
        this.suit = suit;
    }

    @Override
    public String toString() {
        return suit + " " + value;
    }

    public Suit getSuit() {
        return suit;
    }

    public int getValue() {
        return value;
    }
}
```
The card is used in the following way:
```java
// assign enum value
Card first = new Card(10, Suit.HEART);

System.out.println(first);

if (first.getSuit() == Suit.SPADE) {
    System.out.println("is a spade");
} else {
    System.out.println("is not a spade");
}
```

Comparing Enums value
> Each enum field gets a unique number code, and they can be compared using the equals sign. These values inherit the `Object` class and its equals method. It compares its numeric identifier in enum types too.
We can use `ordianl()` find the number code.

```java
System.out.println(Suit.DIAMOND.ordinal());
System.out.println(Suit.HEART.ordinal());
```

# Object variable in enum
Enumerated types may contain object reference variables.
The values of the reference variables should be set in an internal constructor of the class defining the enumerated type, i.e., within a constructor having a private access modifier. Enum type classes cannot have a public constructor.

```java
public enum Color {
    // constructor parameters are defined as
    // the constants are enumerated
    RED("#FF0000"),
    GREEN("#00FF00"),
    BLUE("#0000FF");

    private String code;        // object reference variable

    private Color(String code) { // constructor
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }
}
```
The enum `Color` can be used like:
```java
System.out.println(Color.GREEN.getCode()); // #00FF00
```

# Iterator
ArrayList and other "object containers" that implement the `Collection` interface implement the `Iterable` interface. We can use the help of `iterator` to go through an object collection.

One way:
```java
public class Card {

    private List<Card> cards;

// codes

public void print() {
    this.cards.stream().forEach(card -> {
        System.out.println(card);
    });
}
```
Another:
```java
public void print() {
    Iterator<Card> iterator = cards.iterator();

    while (iterator.hasNext()) {
        System.out.println(iterator.next());
    }
}
```
Or:
```java
public void print(){
    Iterator<Card> iterator = cards.iterator();

    while (iterator.hasNext()) {
        Card nextInLine = iterator.next();
        System.out.println(nextInLine);
    }
}
```

Imagine there's a "finger" that always points to a particular object inside the list.  Initially it points to the first item, then to the next, and so on... until all the objects have been gone through with the help of the "finger".

The iterator offers a few methods. The `hasNext()` method is used to ask if there are any objects still to be iterated over. If there are, the next object in line can be requested from the iterator using the `next()` method. This method returns the next object in line to be processed and moves the iterator, or "finger", to point to the following object in the collection.

We can use the help of `Iterator` to remove the item from the list, while we cannot do so by `stream().forEach()`.
Calling the `remove` method of the `Iterator` neatly removes the item from the previous `next()` call.
```java
public class Hand {
    // ...

    public void removeWorse(int value) {
        Iterator<Card> iterator = cards.iterator();

        while(iterator.hasNext()) {
            if (iterator.next().getValue() < value) {
                iterator.remove();
            }
        }
    }
}
```
