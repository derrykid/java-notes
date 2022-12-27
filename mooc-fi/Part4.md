# Introduction to object-oriented programming OOP
Object-oriented programming is concerned with isolating concepts of a problem domain into separate entities and then using those entities to solve problems. Concepts related to a problem can only be considered once they've been identified. In other words, we can form abstractions from problems that make those problems easier to approach.


# Classes and objects
In the previous parts, we've used some of the classes and objects. A **class** defines the attributes of objects, i.e., the information related to them (instance variables), and their commands and methods.

For example:
ArrayList is a class offered by Java, and we've made use of objects instantiated from it in our programs. Below, an ArrayList object named integers is created and some integers are added to it.
```
// we create an object from the ArrayList class named integers
ArrayList<Integer> integers = new ArrayList<>();
```


# Create a new class
A class named Person
```java
public class Person {
    private String name;
    private int age;
}
```
The `name` and `age` variables here are called **instance variables**, or object attributes, object fields.
The instance variables are preceded by `private` keyword, which means these are **hidden** inside the object. This is known as **encapsulation.** 

We have now defined a blueprint - a class - for the person object.
Each new person object has the variables `name` and `age`, which are able to hold object-specific values. The "state" of a person consists of the values assigned to their name and age.

## Constructor
An object is always **instantiated** by calling a method that created an object, i.e., a **constructor** by using the `new` keyword.

The constructor is always `public className`

- If the programmer doesn't define a constructor for a class. Java automatically creates a default one. It doesn't do anything apart from creating an object. The object's variables remain uninitialized (generally will be null)

**Example** 
```
public class Person {
    private String name;
    private int age;
    private int weight;
    private int height;

    // constructor
    public Person(String initialName) {
        this.age = 0;
        this.weight = 0;
        this.height = 0;
        this.name = initialName;
    }

    // "setter" Java naming convention.
    public void setHeight(int newHeight) {
        this.height = newHeight;
    }

    // "setter" again.
    public void setWeight(int newWeight) {
        this.weight = newWeight;
    }

    // "getter"
    public void getName(){
        return this.name;
    }

    public double bodyMassIndex() {
        double heigthPerHundred = this.height / 100.0;
        return this.weight / (heigthPerHundred * heigthPerHundred);
    }

    // ...
}
```

Create a new object and calling a method
```java
// Student.java class
public class Student {
    
    private int credits;

    // constructor
    public Student() {
        this.credits = 0;
    }

    public void play() {
        this.credits = this.credits - 8;
    }
}

----------------------------

// main.java

public static void main(String[] args) {

    // create a new object
    Student matt = new Student();

    // calling a method in Student.java
    matt.play();
}
```
The constructor contains the expression `this.credits = 0` This expression sets the instance variable of the newly created object(i.e., "this" object's age) to 0. The second expression.

A class diagram to depict a class.

The **minus** sign before the variable name indicates that the variable is encapsulated.
The **plus sign** indicates that the method is `public`.
| Person                       |
|------------------------------|
| -name: String                |
| -age: int                    |
|------------------------------|
| +Person(initialName: String) |

# Changing an instance variable's value in a method
The method is written under the constructor.
```
public void growOlder() {
    this.age = this.age + 1;
}
```

# Return a value from a method
A method can return a value. The methods we've created in our objects haven't so far returned anything. This has been marked by typing the keyword **void** in the method definition.
```
public class Door {
    public void knock() {
        // code
    }
}
```

If we want the method to return a value, we need to replace `void` keyword with the type of the variable to be returned. The following example always return `int` an integer-type variable.
```
public class Teacher {
    public int grade() {
        return 10;
    ]
}
```

For the return value to be used, it has to be assign to a variable
```
int input = teacher.grade();
```

We can return the types we have encountered so far
```
public int returnInt() {
    //
}

public String retrunString() {
    //
}
.
.
.

```

## Java naming convention
It's the convention in Java to name a method that returns an instance variable exactly this way, i.e., `getVariableName`. These calls "getter".

## A string representation of an object and the `toString` method
The method returning the string representation is always `toString` in Java. Let's define this method for the person in the following example:
```
public class person {
    //
    public String toString() {
        return this.name + ", age " + this.age;
    }
}
```
It doesn't itself print anything but instead returns a string representation, which the calling method can execute for printing as needed.

The method is used in a somewhat surprising way:
```
public static void main(String[] args) {
    Person pekka = new Person("Pekka");

    System.out.println(pekka); // same as System.out.println(antti.toString());
}
```

# Method parameters
It works like the method we introduced before. You can pass parameters into the method.
```
public static void main(String[] args) {
    Person matti = new Person("Matti");
    Person juhana = new Person("Juhana");

    matti.setHeight(180);
    matti.setWeight(86);

    juhana.setHeight(175);
    juhana.setWeight(64);

    System.out.println(matti.getName() + ", body mass index is " + matti.bodyMassIndex());
    System.out.println(juhana.getName() + ", body mass index is " + juhana.bodyMassIndex());
}
```

# Calling an internal method
When an object calls an internal method, the name of the method and this prefix suffice. An alternative way is to call the object's own method in the form bodyMassIndex(), whereby no emphasis is placed on the fact that the object's own bodyMassIndex method is being called:
```
public String toString() {
    return this.name + ", age " + this.age + " years, my body mass index is " + bodyMassIndex();
}
```

**I would still specify the prefix. For readability.**

# Add obects to ArrayList
The types parameter used in creating  a list defines the type of the variables that are added to the list.
For example, `ArrayList<String>` or `ArrayList<Integer>`.
In the example below we first add strings to a list, after which the strings in the list are printed.
```
ArrayList<String> names = new ArrayList<>();

String name = "Betty";
names.add(name);

// get the contents
String output = names.get(0);

// or print the contents
for (String name: names) {
    System.out.println(name);
}
```

# Adding object to a list
Handling objects in a list is not really different in any way from the previous experience we have with lists. The essential difference is only to define the type for the stored elements when you create the list.

In the example below we first create a list meant for storing Person type object, after which we add persons to it. Finally the person objects are printed one by one.
```
// suppose we've created a Person class

ArrayList<Person> persons = new ArrayList<>();

// a person object can be created first
Person john = new Person("john");
// add to the list
persons.add(john);

// objects can also be created in the same expression
persons.add(new Person("matt");

for (Person person: persons) {
    System.out.println(person);
}
```

## Reading input in a specific format e.g. csv
If the name and age were separated by a comma, the program could work in the following manner.
```
Scanner scanner = new Scanner(System.in);
ArrayList<Person> persons = new ArrayList<>();

// Read person info from the user input
System.out.println("Enter details seperated by a comma");
while (true) {
    System.out.print("Enter the details, empty will stop: ");
    String details = scanner.nextLine();
    if (details.isEmpty()) {
        break;
    }

    // create an array to store information
    String[] parts = details.split(",");
    String name = parts[0];
    int age = Integer.valueOf(parts[1]);
    persons.add(new Person(name, age));
}

// Print the number of the entered persons, and the persons themselves
System.out.println();
System.out.println("Total number of persons: " + persons.size());
System.out.println("Persons: ");

for (Person person: persons) {
    System.out.println(person);
}

```

# Files and reading data
Reading from the keyboard
```
Scanner scanner = new Scanner(System.in);
String input = scanner.nextLine();

// if it's a value
// int input = Integer.valueOf(scanner.nextLine());
```

## Reading from a file - `Paths.get("filename.extension")`

When the `Scanner`-object that reads the file has been created, the file can be read using a while-loop.

```
// first
import java.util.Scanner;
import java.nio.file.Paths;

// in the program:

// we create a scanner for reading the file
try (Scanner scanner = new Scanner(Paths.get("file.txt"))) {

    // we read the file until all lines have been read
    while (scanner.hasNextLine()) {
        // we read one line
        String row = scanner.nextLine();
        // we print the line that we read
        System.out.println(row);
    }
} catch (Exception e) {
    System.out.println("Error: " + e.getMessage());
}
```

**At times, there are empty lines in a file. We can use the `continue` and `isEmpty`** 
```
// we create a scanner for reading the file
try (Scanner scanner = new Scanner(Paths.get("henkilot.csv"))) {

    // we read all the lines of the file
    while (scanner.hasNextLine()) {
        String line = scanner.nextLine();

        // if the line is blank we do nothing
        if (line.isEmpty()) {
            continue;
        }

        // do something with the data

    }
} catch (Exception e) {
    System.out.println("Error: " + e.getMessage());
}
```

# Reading objects from a file
Creating objects from data that is read from a file is straightforward. Let's assume that we have a class called `Person`, as well as the data from before.

Reading obejcts can be like:
```
ArrayList<Person> people = new ArrayList<>();

try (Scanner scanner = new Scanner(Paths.get("records.txt"))) {

    while (scanner.hasNextLine()) {
        String line = scanner.nextLine();

        String[] parts = line.split(",");
        String name = parts[0];
        int age = Integer.valueOf(parts[1]);

        people.add(new Person(name, age));
    }
}

System.out.println("Total amount of people read: " + people.size());
```

So far we have learnt class, object, method, variables, print, loop, array, and arraylist.
