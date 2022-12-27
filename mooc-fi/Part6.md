# Objects on a list and a list as part of an object

In the following example, we have made a class for the concept of a playlist. The playlist contains songs: songs can be added, songs can be removed, and the songs that the playlist contains can be printed.

```java
// imports

public class Playlist {
    private ArrayList<String> songs;

    public Playlist() {
        this.songs = new ArrayList<>();
    }

    public void addSong(String song) {
        this.songs.add(song);
    }

    public void removeSong(String song) {
        this.songs.remove(song);
    }

    public void printSongs() {
        for (String song: this.songs) {
            System.out.println(song);
        }
    }
}
```

Creating playlists is easy with the help of the class above
```java
Playlist list = new Playlist();
list.addSong("Sorateiden kuningas");
list.addSong("Teuvo, maanteiden kuningas");
list.printSongs();
```

# Objects in an Instance Variable List

A list that is an object's instance variable can contain objects other than strings as long as the type of objects in the list is specified when defining the list.

We'll extend the class so that the amusement park keeps track of the people on the ride. In this version, the ride has, as an instance variable, a list of the people who have been allowed on the ride. The list is created in the constructor.

```java
public class AmusementParkRide {
    private String name;
    private int minimumHeigth;
    private int visitors;
    private ArrayList<Person> riding;

    public AmusementParkRide(String name, int minimumHeigth) {
        this.name = name;
        this.minimumHeigth = minimumHeigth;
        this.visitors = 0;
        this.riding = new ArrayList<>();
    }

    // ..
}
```

# Printing an Object from a List
Modify the `toString` method so
```java
public class AmusementParkRide {
    private String name;
    private int minimumHeight;
    private int visitors;
    private ArrayList<Person> riding;

    // ...

    public String toString() {
        // let's form a string from all the people on the list
        String onTheRide = "";
        for (Person person: riding) {
            onTheRide = onTheRide + person.getName() + "\n";
        }

        // we return a string describing the object
        // including the names of those on the ride
        return this.name + ", minimum height requirement: " + this.minimumHeight +
            ", visitors: " + this.visitors + "\n" +
            "riding:\n" + onTheRide;
    }
}
```

We can use `isEmpty` method to check/modify the output
```java
        if (riding.isEmpty()) {
            return printOutput + "no one is on the ride.";
        }
```

# Clearing an object's list
We can clear the arraylist by `clear()` method. It removes each and every person currently on the ride.
```java
    public void removeEveryoneOnRide() {
        this.riding.clear();
    }
```

# Retrieving a specific object from a list
For example, we want to get the tallest person in an arraylist of object.

Methods that retrieve objects from a list should be implemented in the following way.

1. check whether or not the list is empty, if so, return `null`
2. We create an object reference variable that describes the object to be returned.
3. Set the first object on the list as its value
4. Go through the values on the list by comparing each list object with the object variable.
5. If finds a better matching object, replace it.
6. Return the object

```java
public Person getTallest() {
    // return a null reference if there's no one on the ride
    if (this.riding.isEmpty()) {
        return null;
    }

    // create an object reference for the object to be returned
    // its first value is the first object on the list
    Person returnObject = this.riding.get(0);

    // go through the list
    for (Person prs: this.riding) {
        // compare each object on the list
        // to the returnObject -- we compare heights
        // since we're searching for the tallest,

        if (returnObject.getHeight() < prs.getHeight()) {
            // if we find a taller person in the comparison,
            // we assign it as the value of the returnObject
            returnObject = prs;
        }
    }

    // finally, the object reference describing the
    // return object is returned
    return returnObject;
}
```

# Separate the user interface
In the larger example above, we were following the advice given here.

- Proceed with small steps

    - Try to separate the program into several sub-problems and work on only one sub-problem at a time

    - Always test that the program code is advancing in the right direction, in other words: test that the solution to the sub-problem is correct

    - Recognize the conditions that require the program to work differently. In the example above, we needed a different functionality to test whether a word had been already entered before.

- Write as "clean" code as possible
    - Indent your code
    - Use descriptive method and variable names
    - Don't make your methods too long, not even the main method
    - Do only one thing inside one method
    - Remove all copy-paste code
    - Replace the "bad" and unclean parts of your code with clean code
- If needed, take a step back and assess the program as a whole. If it doesn't work, it might be a good idea to return into a previous state where the code still worked. As a corollary, we might say that a program that's broken is rarely fixed by adding more code to it.
