[toc]
# Class inheritance

Classes are used to clarify the concepts of the problem domain in OOP. Every class we create adds functionality to the programming language. **Solutions rise from the interactions between objects which are created from classes.** 

Every Java class extends the class `Object`, which means that every class we create has at its disposal all the methods defined in that class. If we want to change how the methods are defined, we should override by defining a new one. E.g. `equals` , `hashCode`. 

```
java.lang.Object
-> java.util.AbstractCollection<E>
--> java.util.AbstractList<E>
---> java.util.ArrayList<E>
```
Each class can directly extend only one class. However, a class indirectly inherits all the properties of the classes it extends. So `ArrayList` class derives from the class `AbstractList` and `Object`. So `ArrayList` has all the variables and methods of classes `AbstractList`, `AbstracCollection`, and `Object`.

Use the keyword `extends` to inherits the properties of a class. **superclass, subclass** relation are then created.

Let's create a class `Part`. This class defines the information about a car.
```java
public class Part {

    private String identifier;
    private String manufacturer;
    private String description;

    public Part(String identifier, String manufacturer, String description) {
        this.identifier = identifier;
        this.manufacturer = manufacturer;
        this.description = description;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getDescription() {
        return description;
    }

    public String getManufacturer() {
        return manufacturer;
    }
}
```

A car has an engine. We will create an `Engine` class as well.
```java
public class Engine {

    private String engineType;
    private String identifier;
    private String manufacturer;
    private String description;

    public Engine(String engineType, String identifier, String manufacturer, String description) {
        this.engineType = engineType;
        this.identifier = identifier;
        this.manufacturer = manufacturer;
        this.description = description;
    }

    public String getEngineType() {
        return engineType;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getDescription() {
        return description;
    }

    public String getManufacturer() {
        return manufacturer;
    }
}
```

We take a look at these 2 classes. We soon will find out that there are a lot of codes are overlapped. In this case, we can say that the `Engine` is actually a part of `Part` class. It extends what `Part` class doesn't provide.

We can recreate `Engine` class once again, this time we use inheritance in our implementation. 

```java
public class Engine extends Part {

    private String engineType;

    public Engine(String engineType, String identifier, String manufacturer, String description) {
        super(identifier, manufacturer, description);
        this.engineType = engineType;
    }

    public String getEngineType() {
        return engineType;
    }
}
```

The definition `public class Engine extends Part` indicates that the class `Engine` inherits the functionality of the class `Part`. We also define an object variable `engineType` in the class `Engine`.

*Pay attention to constructor*. On its first line, we use the keyword `super` to call the `Part` constructor (superclass). Through this process, the object variables defined in the superclass are initiated with their initial values. After calling `super`, we also set the value for `engineType`.

The `super` call bears some resemblance to the `this` call in the constructor. `this` is used to call a constructor of this class, while `super` calls the superclass. **If we want to use `super` to call the constructor, it must be the first line.** It's similar to `this`.

Since the class `Engine` extends the class `Part`. We can use all the methods in `Part` class. 

```
Engine engine = new Engine("combustion", "hz", "volkswagen", "VW GOLF 1L 86-91");
System.out.println(engine.getEngineType());
System.out.println(engine.getManufacturer());
```

Output
```
combustion
volkswagen
```

---
####  Access modifiers `private, protected, public`

If a method or variable has the access modifier `private`, it is visible only to the internal methods of that class. Subclasses will not see it, and a subclass has no direct means to access it. So, from the Engine class there is no way to directly access the variables identifier, manufacturer, and description, which are defined in the superclass Part. The programmer cannot access the variables of the superclass that have been defined with the access modifier private.

A subclass sees everything that is defined with the `public` modifier in the superclass. If we want to define some variables or methods that are visible to the subclasses but invisible to everything else, we can use the access modifier `protected` to achieve this.

# Calling the constructor of the superclass

When the constructor of the subclass `Engine` is called, the variables defined in the superclass are initialized. What happens with it is identical to a normal constructor call. 

We demonstrate in the example below how to call `this` and `super`. The class `Superclass` includes an object variable and two constructors. The `Subclass` class includes a constructor, but it has no object variable. 

```
public class Superclass {

    private String objectVariable;

    public Superclass() {
        this("Example");
    }

    public Superclass(String value) {
        this.objectVariable = value;
    }

    public String toString() {
        return this.objectVariable;
    }
}
```

```
public class Subclass extends Superclass {

    public Subclass() {
        super("Subclass");
    }
}
```

Main program
```
Superclass sup = new Superclass();
Subclass sub = new Subclass();

System.out.println(sup);
System.out.println(sub);
```

Output
```
Example
Subclass
```

**Summary: Class inheritance - it's like parents and child. The superclass is like parent, while the subclass is like child.** 

- `Superclass` - father
- `Subclass` - child

Father class has defined its object variables, constructors, and methods. The child class use keyword `extends` to inherits its property. It doesn't have to have object variables in the class, nor the methods. 

**I don't know if the object variables have saved in superclass or not. It's created as subclass object but it has to call super.getName() or any method to call its object varialbes** I have to find those out later on.


# The actual type of an object dictates which method is executed

An object's type decides what the methods provided by the object are.
For instance, we implemented the class `Student` earlier. If a reference to a `Student` type object is stored in a `Person` type variable, only the methods defined in the `Person` class (and its superclass and interfaces) are available:
```
Person ollie = new Student("Ollie", "6381 Hollywood Blvd. Los Angeles 90028");
ollie.credits();        // DOESN'T WORK!
ollie.study();              // DOESN'T WORK!
System.out.println(ollie);   // ollie.toString() WORKS
```

So an object has at its disposal the methods that relate to its type, and also to its superclasses and interfaces. The Student object above offers the methods defined in the the classes Person and Object.

The method to be executed is chosen based on the actual type of the object, which means the class whose constructor is called when the object is created. If the method has no definition in that class, the version of the method is chosen from the class that is closest to the actual type in the inheritance hierarchy.

# When is inheritance worth using?

Inheritance is a tool for building and specializing hierarchies of concepts; a subclass is always a special case of the superclass. If the class to be created is a special case of an existing class, this new class could be created by extending the existing class. For example, in the previously discussed car part scenario an engine is a part, but an engine has extra functionality that not all parts have.

**Subclass must be a special case of the superclass**. The misuse shows itself in how the code breaks the single responsibility principle.

# Abstract classes

**Why to use?** Planning a hierarchy of inheritance, when we have a concept. We can create a blueprint of the class by having `instance variable` and `functionality`. And those classes that inherit it all share it.

**What -** You can't create instances of them - can only create instances of subclasses of abstract class. 

**How** - use keyword `abstract`. This class can include normal methods and return something. 

**When** - When you have a concept of it, but don't know how it should go. You can create a method with parameters and the behaviours it should do.

```java
public abstract class Operation {

    private String name;

    public Operation(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public abstract void execute(Scanner scanner);
}
```

The abstract class `Operation` works as a basis for implementing different actions. For instance, you can implement the plus operation by extending the Operation class in the following manner.

```
public class PlusOperation extends Operation {

    public PlusOperation() {
        super("PlusOperation");
    }

    @Override
    public void execute(Scanner scanner) {
        System.out.print("First number: ");
        int first = Integer.valueOf(scanner.nextLine());
        System.out.print("Second number: ");
        int second = Integer.valueOf(scanner.nextLine());

        System.out.println("The sum of the numbers is " + (first + second));
    }
}
```
**Since all the classes that extends from `Operation` have also the type `Operation`**, we can create a user interface by using `Operation` type variable.
*Think about Objects hierarchy. We can use the methods that Object provides directly or indirectly. We just create our own here* .

```
public class UserInterface {

    private Scanner scanner;
    private ArrayList<Operation> operations;

    public UserInterface(Scanner scanner) {
        this.scanner = scanner;
        this.operations = new ArrayList<>();
    }

    public void addOperation(Operation operation) {
        this.operations.add(operation);
    }

    // skip 
}
```

# Interface

- Whatever methods you define in Interface, it **must** be implemented.
- Interface defines only the names, parameters, and return values of the methods. 
- The interface doesn't perform anything in the method.

How to create an Interface:
`public interface ...`

The visibility attribute, i.e. `public`, isn't marked explicitly because they're always `public`. 

```
public interface Readable {
    String read();
}
```

The classes that implement the interface decide how the methods are implemented. A class implements the interface by adding `implements` after the class name.

```
public class TextMessage implements Readable {
    private String sender;
    private String content;

    public TextMessage(String sender, String content) {
        this.sender = sender;
        this.content = content;
    }

    public String getSender() {
        return this.sender;
    }

    // this must be added. This must be public.
    public String read() {
        return this.content;
    }
}
```

# Interface as variable type

The variable types are either: primitive-type or reference-type. An object's type can be more than its class. For example, the type of `Ebook` class that implements `Readable` interface is both `Ebook` and `Readable` object.

Because an interface can be used as a type, it's possible to create a list that contains objects of the interface's type.

```
ArrayList<Readable> readingList = new ArrayList<>();

readingList.add(new TextMessage("ope", "never been programming before..."));
readingList.add(new TextMessage("ope", "gonna love it i think!"));

ArrayList<String> pages = new ArrayList<>();
pages.add("A method can call itself.");

readingList.add(new Ebook("Introduction to Recursion.", pages));

for (Readable readable: readingList) {
    System.out.println(readable.read());
}
```

Note that although the `Ebook` class inherits `Readable` interface, but not all classes that implements `Readable` are of type `Ebook`. They might have different instance variables.

```
Readable readable = new TextMessage("ope", "TextMessage is Readable!"); // works
TextMessage message = readable; // doesn't work

TextMessage castMessage = (TextMessage) readable; // works if, and only if, readable is of text message type
```

# Interfaces as Method parameters

The true benefits of interfaces are reaped when they are used as the type of parameter provided to a method.

It works like `abstract` class we learn above. We can pass the objects that implements the interface as parameter.

```
public class Printer {
    public void print(Readable readable) {
        System.out.println(readable.read());
    }
}
```

# Interface as a return type of a method
```
import java.util.Random;

public class Factory {

    public Factory() {
        // Note that there is no need to write an empy constructor without
        // parameters if the class doesn't have other constructors.
        // In these cases Java automatically creates a default constructor for
        // the class which is an empty constructor without parameters.
    }

    public Packable produceNew() {
        // The Random-object used here can be used to draw random numbers.
        Random ticket = new Random();
        // Draws a number from the range [0, 4). The number will be 0, 1, 2, or 3.
        int number = ticket.nextInt(4);

        if (number == 0) {
            return new CD("Pink Floyd", "Dark Side of the Moon", 1973);
        } else if (number == 1) {
            return new CD("Wigwam", "Nuclear Nightclub", 1975);
        } else if (number == 2) {
            return new Book("Robert Martin", "Clean Code", 1);
        } else {
            return new Book("Kent Beck", "Test Driven Development", 0.7);
        }
    }
}
```
And the `Packer` class that calls
```java
// Box is an object. box.add(). adds items to it.

public class Packer {
    private Factory factory;

    public Packer() {
        this.factory = new Factory();
    }

    public Box giveABoxOfThings() {
         Box box = new Box(100);

         int i = 0;
         while (i < 10) {
             Packable newThing = factory.produceNew();
             box.add(newThing);

             i = i + 1;
         }

         return box;
    }
}
```

Because the `Packer` class doesn't know anything inside `Packable` class, if we want to add new stuff we can do it without changing anything in `Packer`. 

###  Reducing the dependencies between classes
> Using interfaces in programming enables reducing dependencies between classes. In the previous example the Packer does not depend on the classes that implement the Packable interface. Instead, it just depends on the interface. This makes possible to add new classes that implement the interface without changing the Packer class. What is more, adding new Packable classes doesn't affect the classes that use the Packer class.

# Built-in Interface
There are a lot of built-in interfaces. 4 commonly used: `List`, `Map`, `Set`, and `Collection`.

* List - we learnt the ArrayList

* Map - we learnt the HashMap

* Set - The set interface describes functionality related to sets. Sets always contain either 0 or 1 amounts of any given object. The set interface is implemented by `HashSet`.

* Collection - We learnt the ArrayList

# Object polymorphism

We've encountered situations where reference-type variables have other types besides their own one. For example, all objects are of type Object, i.e., any given object can be represented as a Object-type variable in addition to its own type.

A String-type variable is assigned to an Object-type variable. However, assignment in the other direction, i.e., setting an Object-type variable to a String type, will not work. This is because Object-type variables are not of type String.
```
String text = "text";
Object textString = text;

//

Object textString = "Another string";
String text = textString; // Won't work!

// However, this will work

Object textString = "Third string";
String text = (String) textString;
```

Like we learnt above, `Ebook` is `Readable` object, but `TextMessage` might not be `Ebook`.

Knowledge of the fact that objects can be of many different types — of type Object, for instance — makes programming simpler. If we only need methods defined in the Object class, such as toString, equals and hashCode in a method, we can simply use Object as the type of the method parameter. In that case, you can pass the method for any object as a parameter.
