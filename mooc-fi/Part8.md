# HashMap - key-value pairs

A HashMap is, in addition to ArrayList, one of the most widely used of Java's pre-built data structures. The hash map is used whenever data is stored as key-value pairs, where values can be added, retrieved, and deleted using keys.

```java
import java.util.HashMap;

HashMap<String, String> postalCodes = new HashMap<>();
postalCodes.put("00303", "A city");
postalCodes.put("29103", "B city");
postalCodes.put("31232", "C city");

boolean output;
output = postalCodes.containsKey("00303"); // true

System.out.println(postalCodes.get("00303")); // A city
```

Often used command
```java
postalCodes.put("Key","value");
string output = postalCodes.get("Key"); // value
```

The internal state of the hash map created above looks like this. Each key refers to some value.

| Key   | Value  |
|-------|--------|
| 00303 | A city |
| 29103 | B city |
| 31232 | C city |

If the hash map doesn't contained the key used for search, it returns `null`.

# HashMap keys correspond to a single value at most
If a new key-value pair is added to the hash map, but the key has already been associated with other value, the old value will be replaced.

```java
HashMap<String, String> numbers = new HashMap<>();
numbers.put("Ace", "One");
numbers.put("Deuce", "Second");
numbers.put("Ace", "First");

System.out.println(numbers.get("Ace")); // print First
```

# A reference type variable as a hash map value

We can use object as the HashMap's key and/or value.
```java
HashMap<String, Book> directory = new HashMap<>();
```

Books can be retrieved from the directory by book name.

```java
Book senseAndSensibility = new Book("Sense and Sensibility", 1811, "...");
Book prideAndPrejudice = new Book("Pride and Prejudice", 1813, "....");

HashMap<String, Book> directory = new HashMap<>();
directory.put(senseAndSensibility.getName(), senseAndSensibility);
directory.put(prideAndPrejudice.getName(), prideAndPrejudice);
```

# When should hash maps be used?
In simple, hash map generates a "hash value" from the key, i.e. a piece of code, which is used to stored the value of a specific location. When a key is used to retrieve information from a hash map, this particular code identifies the location where the value associated with the key is.

In general, if there are millions of data/information that stored in, and the user wants to search for an information. By using hash map, the speed will be faster than ArrayList.

# HashMap as an instance variable
```java
public class Library {
    private HashMap<String, Book> directory;

    public Library() {
        this.directory = new HashMap<>();
    }

    public void addBook(Book book) {
        String name = book.getName()
        if (name == null) {
            name = "";
        }

        name = name.toLowerCase();
        name = name.trim();

        if (this.directory.containsKey(name)) {
            System.out.println("Book is already in the library!");
        } else {
            directory.put(name, book);
        }
    }
}
```

The `containsKey` method of the hash map is being used above to check for the existence of a key.
It will return a boolean value.

# Going through a hashmap's keys by `keySet()` method

We can go through the values of a hash map by using a for-each loop on the set returned by the keySet() method of the hash map.

```java
    for(String bookTitle : this.directory.keySet()) {
        if(!bookTitle.contains(titlePart)) {
            continue;
        }
```
It will go through every key in the HashMap, which will sacrifice the speed advantage.

# Going through a hashmap's values by `values()` method

Like using `keySet()` method, we can use `values()` to iterate the values of the hashmap.
```java
    for(Book book : this.directory.values()) {
        if(!book.getName().contains(titlePart)) {
            continue;
        }
```

# Primitive variables in HashMap and `getOrDefault` method

**HashMap and ArrayList** expects only reference variables are added to it.  Java converts primitive variables to their corresponding reference-types when using any Java's built in data structures (such as ArrayList and HashMap). Although the value 1 can be represented as a value of the primitive int variable, its type should be defined as Integer when using ArrayLists and HashMaps.

If you want to use a primitive variable as a key or value, there exists a reference-type version for each one.

| Primitive | Reference-type Equivalent |
|-----------|---------------------------|
| int       | Integer                   |
| double    | Double                    |
| char      | Character                 |

There is some risk in type conversions. If we attempt to convert a `null` reference to an integer, we witness an error.

When performing automatic conversion, we should ensure that the value to be converted is not null.
```java
public int timesSighted(String sighted) {
    return this.allSightings.getOrDefault(sighted, 0);
}
```

# `equals` method and `hashCode` method for HashMap use

for a class to be used as a HashMap's key, we need to define for it:

- the `equals` method, so that all equal or approximately equal objects cause the comparison to return true and all false for all the rest
- the `hashCode` method, so that as few objects as possible end up with the same hash value

---------------

Remember that in order to check 2 objects, we have to write our own `equals` method in the class. Otherwise the `equals` method won't work as we expect it.
```java
Book bookObject = new Book("Book object", 2000, "...");
Book anotherBookObject = bookObject;

if (bookObject.equals(anotherBookObject)) {
    System.out.println("The books are the same");
} else {
    System.out.println("The books aren't the same");
}

// we now create an object with the same content that's nonetheless its own object
anotherBookObject = new Book("Book object", 2000, "...");

if (bookObject.equals(anotherBookObject)) {
    System.out.println("The books are the same");
} else {
    System.out.println("The books aren't the same");
}
```
Output
```java
The books are the same
The books aren't the same
```

In order to fix this, we implement the following code we learn before
```java
    @Override
    public boolean equals(Object comparedObject) {
        // if the variables are located in the same place, they're the same
        if (this == comparedObject) {
            return true;
        }

        // if comparedObject is not of type Book, the objects aren't the same
        if (!(comparedObject instanceof Book)) {
            return false;
        }

        // let's convert the object to a Book-object
        Book comparedBook = (Book) comparedObject;

        // if the instance variables of the objects are the same, so are the objects
        if (this.name.equals(comparedBook.name) &&
            this.published == comparedBook.published &&
            this.content.equals(comparedBook.content)) {
            return true;
        }

        // otherwise, the objects aren't the same
        return false;
    }
```

Now the output
```java
The books are the same
```

---------------

ArrayList also uses the `equals` method for its internal use. For example, the `contains` method.
```java
ArrayList<Book> books = new ArrayList<>();
Book bookObject = new Book("Book Object", 2000, "...");
books.add(bookObject);

if (books.contains(bookObject)) {
    System.out.println("Book Object was found.");
}

bookObject = new Book("Book Object", 2000, "...");

if (!books.contains(bookObject)) {
    System.out.println("Book Object was not found.");
}
```
Output
```java
Book Object was found.
Book Object was not found.
```

# Approximate comparison with HashMap
Hash codes are used in HashMaps, for instance. HashMap's internal behavior is based on the fact that key-value pairs are stored in an array of lists based on the key's hash value. Each array index points to a list. The hash value identifies the array index, whereby the list located at the array index is traversed. The value associated with the key will be returned if, and only if, the exact same value is found in the list (equality comparison is done using the equals method). This way, the search only needs to consider a fraction of the keys stored in the hash map.

So far, we've only used String and Integer-type objects as HashMap keys, which have conveniently had ready-made hashCode methods implemented. Let's create an example where this is not the case: we'll continue with the books and keep track of the books that are on loan. We'll implement the book keeping with a HashMap. The key is the book and the value attached to the book is a string that tells the borrower's name:

```java
HashMap<Book, String> borrowers = new HashMap<>();

Book bookObject = new Book("Book Object", 2000, "...");
borrowers.put(bookObject, "Pekka");
borrowers.put(new Book("Test Driven Development", 1999, "..."), "Arto");

System.out.println(borrowers.get(bookObject));
System.out.println(borrowers.get(new Book("Book Object", 2000, "...")));
System.out.println(borrowers.get(new Book("Test Driven Development", 1999, "...")));
```
Output
```java
Pekka
null
null
```

We find the borrower when searching for the same object that was given as a key to the hash map's put method. However, when searching by the exact same book but with a different object, a borrower isn't found, and we get the null reference instead. The reason lies in the default implementation of the hashCode method in the Object class. The default implementation creates a hashCode value based on the object's reference, which means that books having the same content that are nonetheless different objects get different results from the hashCode method. As such, the object is not being searched for in the right place.

For the HashMap to work in the way we want it to, that is, to return the borrower when given an object with the correct content (not necessarily the same object as the original key), the class that the key belongs to must overwrite the hashCode method in addition to the equals method. The method must be overwritten so that it gives the same numerical result for all objects with the same content. Also, some objects with different contents may get the same result from the hashCode method. However, with the HashMap's performance in mind, it is essential that objects with different contents get the same hash value as rarely as possible.

We've used `String` objects as HashMap keys, so we can deduce that the `String` class has a well-functioning `hashCode` implementation of its own. Now, we'll delegate, transfer the computational responsibility to the `String` object.

```java
public int hashCode() {
    return this.name.hashCode();
}
```

The above solution is quite good. But if the `name` is null, there's an error. So:
```java
public int hashCode() {
    if (this.name == null) {
        return this.published;
    }

    return this.name.hashCode();
}
```

Now, all of the books that share a name are bundled into one group. Let's improve it further so that the year of publication is also taken into account in the hash value calculation that's based on the book title.

```java
public int hashCode() {
    if (this.name == null) {
        return this.published;
    }

    return this.published + this.name.hashCode();
}
```

It's now possible to use the book as the hash map's key. This way the problem we faced earlier gets solved and the book borrowers are found:
```java
HashMap<Book, String> borrowers = new HashMap<>();

Book bookObject = new Book("Book Object", 2000, "...");
borrowers.put(bookObject, "Pekka");
borrowers.put(new Book("Test Driven Development",1999, "..."), "Arto");

System.out.println(borrowers.get(bookObject));
System.out.println(borrowers.get(new Book("Book Object", 2000, "...")));
System.out.println(borrowers.get(new Book("Test Driven Development", 1999)));
```

Output
```java
Pekka
Pekka
Arto
```

# Using ArrayList as variable in HashMap
A hash map has at most one value per each key. In the following example, we store the phone numbers of people into the hash map.

```java
HashMap<String, ArrayList<String>> phoneNumbers = new HashMap<>();
```

Each key of the hash map now has a list attached to it. Although the new command creates a hash map, the hash map initially does not contain a single list. They need to be created separately as needed.

```java
HashMap<String, ArrayList<String>> phoneNumbers = new HashMap<>();

// let's initially attatch an empty list to the name Pekka
phoneNumbers.put("Pekka", new ArrayList<>());

// and add a phone number to the list at Pekka
phoneNumbers.get("Pekka").add("040-12348765");
// and let's add another phone number
phoneNumbers.get("Pekka").add("09-111333");

System.out.println("Pekka's numbers: " + phoneNumbers.get("Pekka"));
```
Output
```java
Pekka's number: [040-12348765, 09-111333]
```

We define the type of the phone number as HashMap<String, ArrayList<String>>. This refers to a hash map that uses a string as a key and a list containing strings as its value. As such, the values added to the hash map are concrete lists.

```java
phoneNumbers.put("Pekka", new ArrayList<>());
```

We can implement, for instance, an exercise point tracking program in a similar way. The example below outlines the TaskTracker class, which involves user-specific tracking of points from tasks. The user is represented as a string and the points as integers.

```java
public class TaskTracker {
    private HashMap<String, ArrayList<Integer>> completedExercises;

    public TaskTracker() {
        this.completedExercises = new HashMap<>();
    }

    public void add(String user, int exercise) {
        // an empty list has to be added for a new user if one has not already been added
        this.completedExercises.putIfAbsent(user, new ArrayList<>());

        // let's first retrieve the list containing the exercises completed by the user and add to it
        ArrayList<Integer> completed = this.completedExercises.get(user);
        completed.add(exercise);

        // the previous would also work without the helper variable as follows
        // this.completedExercises.get(user).add(exercise);
    }

    public void print() {
        for (String name: completedExercises.keySet()) {
            System.out.println(name + ": " + completedExercises.get(name));
        }
    }
}
```
Example of use:
```java
TaskTracker tracker = new TaskTracker();
tracker.add("Ada", 3);
tracker.add("Ada", 4);
tracker.add("Ada", 3);
tracker.add("Ada", 3);

tracker.add("Pekka", 4);
tracker.add("Pekka", 4);

tracker.add("Matti", 1);
tracker.add("Matti", 2);

tracker.print();
```
Output
```java
Matti: [1, 2]
Pekka: [4, 4]
Ada: [3, 4, 3, 3]
```
