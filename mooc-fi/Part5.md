A famous programmer once remarked "Any fool can write code that a computer can understand .Good Programmers write code that humans can understand".

# Concept of Object-oriented programming OOP
**Object-oriented programming is primarily about isolating concepts into their own entities or, in other words, creating abstractions.** 

**Abstraction**, i.e. the action of removing something from something else; the process of being removed from something else;
Or: a general idea not based on any particular thing.

Let's make an example of a clock.
```java
int hours = 0;
int minutes = 0;
int seconds = 0;

while (true) {
    // 1. Printing the time
    if (hours < 10) {
        System.out.print("0");
    }
    System.out.print(hours);

    System.out.print(":");

    if (minutes < 10) {
        System.out.print("0");
    }
    System.out.print(minutes);

    System.out.print(":");

    if (seconds < 10) {
        System.out.print("0");
    }
    System.out.print(seconds);
    System.out.println();

    // 2. The second hand's progress
    seconds = seconds + 1;

    // 3. The other hand's progress when necessary
    if (seconds > 59) {
        minutes = minutes + 1;
        seconds = 0;

        if (minutes > 59) {
            hours = hours + 1;
            minutes = 0;

            if (hours > 23) {
                hours = 0;
            }
        }
    }
}
```

However, if we want to make the code more understandable, we can improve by creating a `ClockHand` class that describes a clock hand, in which contains information about its value.
```java
public class ClockHand {
    private int value;
    private int limit;

    public ClockHand(int limit) {
        this.limit = limit;
        this.value = 0;
    }

    public void advance() {
        this.value = this.value + 1;

        if (this.value >= this.limit) {
            this.value = 0;
        }
    }

    public int value() {
        return this.value;
    }

    public String toString() {
        if (this.value < 10) {
            return "0" + this.value;
        }

        return "" + this.value;
    }
}
```

Once we've created the `ClockHand` class, it becomes clearer. Next, we use that for our code of `int` time example above:
```
ClockHand hours = new ClockHand(24);
ClockHand minutes = new ClockHand(60);
ClockHand seconds = new ClockHand(60);

while (true) {
    // 1. Printing the time
    System.out.println(hours + ":" + minutes + ":" + seconds);

    // 2. Advancing the second hand
    seconds.advance();

    // 3. Advancing the other hands when required
    if (seconds.value() == 0) {
        minutes.advance();

        if (minutes.value() == 0) {
            hours.advance();
        }
    }
}
```

Separating a concept into its own class is a good idea for many reasons. Firstly, certain details (such as the rotation of a hand) can be hidden inside the class (i.e., **abstracted**). Instead of typing an if-statement and an assignment operation, it's enough for the one using the clock hand to call a clearly-named method `advance()`.

We realized that the clock contains three hands, i.e., it consists of three concepts. The clock is a concept in and of itself. As such, we can create a class for it too. Next, we create a class called "Clock" that hides the hands inside of it.
```
public class Clock {
    private ClockHand hours;
    private ClockHand minutes;
    private ClockHand seconds;

    public Clock() {
        this.hours = new ClockHand(24);
        this.minutes = new ClockHand(60);
        this.seconds = new ClockHand(60);
    }

    public void advance() {
        this.seconds.advance();

        if (this.seconds.value() == 0) {
            this.minutes.advance();

            if (this.minutes.value() == 0) {
                this.hours.advance();
            }
        }
    }

    public String toString() {
        return hours + ":" + minutes + ":" + seconds;
    }
}
```

The way the program functions has become increasingly clearer. When you compare our program below to the original one that was made up of integers, you'll find that the program's readability is superior.
```
Clock clock = new Clock();
While (true) {
    System.out.print(clock);
    clock.advance();
}
```
The clock we implemented above is an object whose functionality is based on "simpler" objects, i.e., its hands.

This is precisely the **great idea behind object-oriented programming: a program is built from small and distinct objects that work together** 

# Object
An Object refers to an independent entity that contains both data (instance variables) and behavior (methods). Objects may come in lots of different forms: some may describe problem-domain concepts, others are used to coordinate the interaction that happens between objects. Objects interact with one another through method calls — these method calls are used to both request information from objects and give instructions to them.

# Class
A class defines the types of objects that can be created from it. It contains instance variables describing the object's data, a constructor or constructors used to create it, and methods that define its behavior.

The `this` keyword refers to the current object in a method or constructor.

# Constructor overloading
We can have two or more constructors in a class. It's called constructor overloading.
```
public Person(String name) {
        this.name = name;
        this.age = 0;
        this.weight = 0;
        this.height = 0;
    }

public Person(String name, int age) {
    this.name = name;
    this.age = age;
    this.weight = 0;
    this.height = 0;
}
```

## Calling your constructor
It is also possible for a constructor to call another constructor. But the call must be the first command in the constructor
```
public Person(String name) {
    this(name, 0);
    //here the code of the second constructor is run, and the age is set to 0
}

public Person(String name, int age) {
    this.name = name;
    this.age = age;
    this.weight = 0;
    this.height = 0;
}
```

## Method overloading
Methods can be overloaded in the same way as constructors, i.e., multiple versions of a given method can be created.
```
public void growOlder() {
    this.age = this.age + 1;
}

public void growOlder(int years) {
    this.age = this.age + years;
}
```
We may also modify the program so that the parameterless method is implemented using the method with parameter
```
public void growOlder() {
    this.growOlder(1);
}

public void growOlder(int years) {
    this.age = this.age + years;
}
```

# Primitive and reference variables
[Primitive and reference type explanation - Youtube](https://www.youtube.com/watch?v=OmcFVHpb0v0)

## Primitive variables
Java has eight different primitive variables.
- boolean
- byte
- char
- short
- int
- long
- float
- double

Each one has its own memory location to which the value that is assigned is copied.
The values of variables are also copied whenever they're used in method calls.
So the value won't change.

## Reference variables
All of the variables provided by Java (other than the eight primitive variables mentioned above) are reference type.

A reference variable stores the **address** pointing to the value.
For example, `Person luke = new Person(Luke);` we created a new object in Person class, name luke. Furthermore, we set age of `luke`
```
luke.setAge(30);
```
By doing this, `luke` object age is set to 30. In memory, it might looks something like this:

| Address | value   | Note     |
|---------|---------|----------|
| @3B     | @c62381 | luke.age |
| @c62381 | 30      |

The variable stores the address to that value, instead of the '30' that we assigned.

Like we mentioned above, all variables except **8 primitive types** are referenced. Including *HashMap, ArrayList, etc*.

So in methods, we pass 'reference' as parameter. In the method, reference is copied and modified. Therefore, **the value will change too!** 

[Origin tutorial](https://java-programming.mooc.fi/part-5/3-primitive-and-reference-variables)

# Assigning a reference type ` = ` copies the reference
```java
Person joan = new Person("John");
Person ball = joan;
```

The statement `Person ball = joan;` creates a new `Person` variable `ball`, and copies the value of variable `joan`. `ball` refers to the same object as `joan`.

| Address | value | Note      |
|---------|-------|-----------|
| @a3c41  | joan  |           |
| @a3c41  | ball  | copy joan |

A simple method call can show the result:
```java
Person joan = new Person("Joan Ball");
System.out.println(joan);

Person ball = joan;
ball.growOlder();
ball.growOlder();

System.out.println(joan);
```

Output:
```java
Joan Ball, age 0 years
Joan Ball, age 2 years
```
The Person object ball is aged by two years, and Joan Ball ages as a consequence!

# `null` value of a reference variable
We can set reference variable to `null`, i.e. "to nothing". The `null` reference can be set as the value of any reference type variable.
```java
Person john = new Person("John ball");
john = null; // Person john now points to nothing
```

The `Person john` object was created. However, `Person john`, by second line of code, points to `null`. Therefore, the first line of code, that object, was referred to by nobody. 
It becomes "garbage" now. In Java, programmer need not worry about the program's memory use. The automatic garbage collector of the Java cleans u the objects that have become garbage. If the collection doesn't happen, the garbage would reserve a memory until the end of the program.

# Object as object variable
```java
public class Person {
    private String name;
    private SimpleDate birthday;
    private int weight = 0;
    private int length = 0;

// ...
```
**String** `name` is an object itself; the variable birthday is a SimpleDate object.
Both variables contain a reference to an object.

Assume we've created 2 objects `Person` in `main`, the two `Person` objects are connected by strands with `main`. A `Person` has a name and a birthday. Since both are objects, these attributes exist at the other ends of the strands.

# Comparing the equality of objects `equals`

Working with strings, we know strings must be compared using ` equals` method.
```java
String first = "Hello";
String second = "Hello";
if (first.equals(second)) {
    System.out.println("True"); // print True
}
```

If comparing 2 reference variables, we cannot use `==` to compare. It's because the memory location saves "the reference". With reference variables, such comparisons would examine the equality of the memory references.

The method equals is similar to the method toString in the respect that it is available for use even if it has not been defined in the class.

**If we want to be able to compare tow objects of our own design with the `equals` method, that method must be defined in the class**. The method `equals` is defined as a method that returns a boolean type value.

We design the method `equals` in a way that it receives an `Object`-type object as its parameter, steps as follow:

1. compares if the addresses are equal. If so, true;
2. examine the types of the objects are the same. If not, false
3. `Object` type converted to the type of the object that's being examined by type cast.
4. The values of the object variables then can be compared.

```java
    public boolean equals(Object compared) {
        // if the variables are located in the same position, they are equal
        if (this == compared) {
            return true;
        }

        // if the type of the compared object is not SimpleDate, the objects are not equal
        if (!(compared instanceof SimpleDate)) {
            return false;
        }

        // convert the Object type compared object
        // into a SimpleDate type object called comparedSimpleDate
        SimpleDate comparedSimpleDate = (SimpleDate) compared;

        // if the values of the object variables are the same, the objects are equal
        if (this.day == comparedSimpleDate.day &&
            this.month == comparedSimpleDate.month &&
            this.year == comparedSimpleDate.year) {
            return true;
        }

        // otherwise the objects are not equal
        return false;
    }
```
Another example:
```java
    public boolean equals(Object compared) {
        // if the variables are located in the same position, they are equal
        if (this == compared) {
            return true;
        }

        // if the compared object is not of type Person, the objects are not equal
        if (!(compared instanceof Person)) {
            return false;
        }

        // convert the object into a Person object
        Person comparedPerson = (Person) compared;

        // if the values of the object variables are equal, the objects are equal
        if (this.name.equals(comparedPerson.name) &&
            this.age == comparedPerson.age &&
            this.weight == comparedPerson.weight &&
            this.height == comparedPerson.height) {
            return true;
        }

        // otherwise the objects are not equal
        return false;
    }
```

## What is object?
Every class we create inherits the class Object, even though it is not specially visible in the code. Inheriting the Object can be seen elsewhere too. For example, the `toString` method exists even if you haven't implemented it yourself, just as the `equals` method does.

# Object equality and ArrayList
```java
ArrayList<Bird> birdList = new ArrayList<>();
Bird red = new Bird("Red");

boolean result;
result = birds.contains(red); // false
birds.add(red);
result = birds.contains(red); // true

// However!
red = new Bird("Red");
result = birds.contains(red); // false
```

After the re-assignment, even though the same content, it's still false.
**It's because it compares the reference value, so even though the content is the same, the reference is different.**
We have to implement the `equals` method for the class, so it will work in our desired way.

```java
    public boolean equals(Object compared) {
        // if the variables are located in the same position, they are equal
        if (this == compared) {
            return true;
        }

        // if the compared object is not of type Bird, the objects are not equal
        if (!(compared instanceof Bird)) {
            return false;
        }

        // convert the object to a Bird object
        Bird comparedBird = (Bird) compared;

        // if the values of the object variables are equal, the objects are, too
        return this.name.equals(comparedBird.name);
```

# Object as a method's return value
```java
public Counter {
    private int value;

    // example of using multiple constructors:
    // you can call another constructor from a constructor by calling this
    // notice that the this call must be on the first line of the constructor
    public Counter() {
        this(0);
    }

    public Counter(int initialValue) {
        this.value = initialValue;
    }

    public void increase() {
        this.value = this.value + 1;
    }

    public String toString() {
        return "value: " + value;
    }

    public Counter clone() {
        // create a new counter object that receives the value of the cloned counter as its initial value
        Counter clone = new Counter(this.value);

        // return the clone to the caller
        return clone;
    }
}
```
An example of using:
```java
Counter counter = new Counter();
counter.increase();
counter.increase();

System.out.println(counter);         // prints 2

Counter clone = counter.clone();

System.out.println(counter);         // prints 2
System.out.println(clone);          // prints 2

counter.increase();
counter.increase();
counter.increase();
counter.increase();

System.out.println(counter);         // prints 6
System.out.println(clone);          // prints 2

clone.increase();

System.out.println(counter);         // prints 6
System.out.println(clone);          // prints 3
```

In the class `Counter` method `clone()`, it starts with same value. It's copied content but different object. So they're different object. Therefore, increasing one will not result in increasing another.
