# Java abstract class

> An abstract class is like a structure of data. However, unlike interface, it can define methods and attribute. You can view it as an enforced blueprint for an object. You cannot initiate an object from it but from its sub-classes.

An abstract class is:
- It cannot be instantiated
- It can contain *constructor and object fields*.
- Contains `abstract` method (0 to many), and concrete methods
- Its subclasses **must override** its `abstract` method

An abstract class example:
```java
public abstract Animal {

    private int age;
    private String name;

    public Animal(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public abstract void makeSound();

    public String getName() {
        return this.name;
    }

}
```

## To create an object that is enforced by abstract class 

**You can only create it by its subclass.** 

From the last section, we've defined an `Animal` abstract class. This section will create a subclass that can be used to create an object from.

```java
public class Cat extends Animal {

    public Cat(int age, String name) {
        super(age, name);
    }

    @Override
    public void makeSound() {
        System.out.println("Meow");
    }
}
```

Then in the main:
```java
public class Main {

    public static void main(String[] args) {

        Animal silver = new Cat(12, "Silver");
        silver.makeSound();   // Meow

    }
}
```

## Why we use `abstract class`? What about `interface`?

[stackoverflow](https://softwareengineering.stackexchange.com/a/152785) 

> Abstract classes improve the situation by preventing a developer from instantiating the base class, because a developer has marked it as having missing functionality. It also provides compile-time safety so that you can ensure that any classes that extend your abstract class provide the bare minimum functionality to work.

Interfaces are a totally separate topic. An interface lets you describe what operations can be performed on an object. You would typically use interfaces when writing methods, components, etc.

For example:
```
public class FileDatabase implements IProductDatabase {
    public void addProduct(String name, Double price) {
         //TODO: just write to file
    }
}
```

## Combine abstract class and interface together
Sometimes you can combine both. Here is an example:
You want to create a database connection. Now you have different databases remotely and you want your program to be able to connect to each one. You want to **make sure that each connection class has `addProduct()` method.** 

You can use `interface` to define the `addProduct()` method you want, and use `abstract` to create the backbone for the subclasses.

The base abstract class:
```java
abstract class RemoteDatabase implements IProductDatabase { 
    public abstract String[] connect();
    public abstract void writeRow(string col1, string col2);

    public void addProduct(String name, Double price) {
        connect();
        writeRow(name, price.toString());
    }
}
```

The subclasses:
```java
public class SqlDatabase extends RemoteDatabase {
    //TODO override connect and writeRow
}

public class OracleDatabase extends RemoteDatabase { 
    //TODO override connect and writeRow
}
```

