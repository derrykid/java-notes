# Static
> In simple words: `static` means the variable or methods that assigned to are **belonging to the class, not the object.** Therefore, there's only one copy of the *variable/method* in the memory.

When create an object where all instance variables aren't static, every object will own its memory space, and its own copy of value.


Suppose we have a ball class:
```java
class Ball{
    double radius;
    final double PI = 3.14159;
}
```

We create 2 `Ball` objects, each `Ball` object will have its own instance variables in the memory


```                            
In memory:

                            ┌───────────┐
                            │radius     │
                            │PI         │
                 ┌─────────►│           │
┌────────────────┤          └───────────┘
│                │
│  Ball          │
├────────────────┤
│radius:double   │
│PI : double     │       ┌────────────┐
│                │       │ radius     │
│                │       │ PI         │
│                ├──────►│            │
│                │       └────────────┘
└────────────────┘
```

We know that the `PI` is a unchangeable constant. So we can use `static` keyword here.
```java
                            ┌───────────┐
                            │radius     │
                            │           │
                 ┌─────────►│           │
┌────────────────┤          └───────────┘
│                │
│  Ball          │
├────────────────┤
│radius:double   │
│PI : double     │       ┌────────────┐
│(static)        │       │ radius     │
│                │       │            │
│                ├──────►│            │
│                │       └────────────┘
└────────────────┘
```

Therefore, `static PI` is in the class, not created for each object. 
Plus, we can access it without create an instance of `Ball`.
```java
System.out.println(Ball.PI);
```

### Static block
We can also define a static block in class. It'll run when we initialise an instance:
```java
public class Ball{
    // instance variable
    static {
        System.out.println("This is static block!");
    }
}
```

A lot of methods we use are provided as static method, so we can use it without initialise an object:
```java
System.out.println("HI"); // static method
Integer.parseInt(); // static method
```

### When to use static method?
---

[stack overflow - when to use static method](https://stackoverflow.com/questions/2671496/when-to-use-static-methods)

**One rule-of-thumb: ask yourself "Does it make sense to call this method, even if no object has been constructed yet?" If so, it should definitely be static.** 

#### Suitable to use `static`
---
In a class `Car`, we might have a method:
```java
public double convertMpgToKpl(double mpg)
```

This can be **static** because even if nobody has create a `Car` object, one might want to know what 43mpg converts to.
```java
public static double convertMpgToKpl(double mpg)
```

#### Not suitable for `static` keyword
---
```java
void setMileage(double mpg)
```
This, on the other hand, can't be static since it's inconceivable to call the method before any `Car` has been constructed.

1. If you're writing utility classes and they're not supposed to be changed.
2. If the method isn't using any instance variable.
3. If any operation is not dependent on instance creation.
4. If there's some code that can easily be shared by all the instance methods, extract that code into a static method.
5. If you're sure that the definition of the method will never be changed or overridden. As static methods cannot be overridden.

### Why the main() method in Java is always static?

[source](https://www.tutorialspoint.com/why-the-main-method-in-java-is-always-static)

Java **main()** method is always static,so compiler can call it **without the creation of an object** or **before the creation of an object of the class.** 

* **main()** method is the starting point from where compiler starts the program execution. So, **the compiler needs to call the main() method.** 
* Static method of a class can be called **by using the class name only.** Without the creating an object of a class. See code below.
* The main() method in Java must be declared **public, static and void.** If any is missing, an error will be thrown.
* While instantiating, it has to call the constructor of that class. There'll be ambiguity if the constructor of that class takes an argument.

```java
public class Test {
    public static void main(String[] args) {
        // call static method of Book class using class name only
        Book.getBookInfo();
    }
}
class Book {
    public static void getBookInfo() {
        System.out.println("Welcome to Library");
    }
}
```

---

## variable

Suppose we have a `Student` class, and we have to create 1000 `Student`.

```java
public class Student{
    private int id;
    private String college_name;
    private String college_id;

    // and so on
}
```

If we create 1000 students, then it will create 1000 `college_name` and `college_id` variables in RAM. It will take up too much memory. With `static` method, it will help to better up the memory usage.

```java
public class Student{
    private int id;
    private static String college_name;
    // code
}
```

### static block

The static block is initialised when the class object is created. It exec only once, and will be executed before constructor.

It works like method.

```java
public class ClassName {

    static { 
        // something here; 
    }

}
```


-----

**Benefits of using static** 

- Static variables are rarely used other than being declared as constants. Constants are variables that are declared as public/private, final, and static. Constant variables never change from their initial value.

- Static variables are stored in the static memory. It is rare to use static variables other than declared final and used as either public or private constants.

- Static variables are created when the program starts and destroyed when the program stops.

- Visibility is similar to instance variables. However, most static variables are declared public since they must be available for users of the class.

- Static variables can be accessed by calling with the class name `ClassName.VariableName`.

- When declaring class variables as public static final, then variable names (constants) are all in upper case


