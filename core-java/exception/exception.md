[toc]

## Call Stack

Call stack mean the **sequance of method calls.** 

When we call method A, then method B, and then C, the call stack looks like this:
```java
A
B
C
```

It method C returns, and calls method D:
```java
A
B
D
```

Exception are propagated up the call stack, from the method that initially throws it, until a method in the call stack catches it.

## Create a custom exception

- an uncheck exception - create a class `extends RuntimeException`
- It returns the custom class name with the error message we define.

```java
public class ExceptionDemo extends RuntimeException{
    // constructor
    public ExceptionDemo(String errorMessage){
        super(errMessage);
    }
}
```

`Main.java`
**note that: we throw `ExceptionDemo`** 
```java
public class Main {
    public static void main(String[] args) {
        try{
            System.out.println(divideByZero(10, 0));
        } catch (ExceptionDemo ex){
            // option 1
            System.out.println("Error occurs: " + ex);
            // option 2
            ex.printStackTrace();
        }
    }

    static int divideByZero(int i, int j) throws ExceptionDemo {
        if (j == 0){
            throw new ExceptionDemo("Cannot divide by zero!");
        }
        return i / j;
    }

}
```

Output: `ex.printStackTrace()`
```java
main.java.ExceptionDemo: Cannot divide by zero!
	at main.java.Main.divideByZero(Main.java:22)
	at main.java.Main.main(Main.java:11)
```


## Throw Exception

If a method wants to throw an exception, it has to declare the exception thrown in the method signature.

```java
public void divide(int n1, int n2) throws CustomException {
    if ( n2 == 0 ) {
        throw new CustomException("Cannot divide by 0");
    }
    return n1 / n2;
}
```

**When an exception is thrown, the program terminates right after the "throw statement".**

If you want to make the program continue to work, you have to use catch block.


## Catch Exceptions

Catch the exception is done by try-catch block:
```java
public void catchDemo() {
    try {
        int a = divide(2, 1);
        System.out.println(a);
    } catch (CustomException e) {
        // do sth
        System.out.println(e.getMessage());
    }
    System.out.println("Done");
}
```

If no exception is thrown, the code in catch block is simply ignored.
If an exception is thrown, for example the `CustomException`, the code in catch block will run.

When the catch block finishes, the program **continues.** In the above example, it continues and prints out "Done" to console.

## Propagate Exceptions

If you cannot do anything about the exception where the method throwing it is called, you can just let it propagate the exception up the call stack to the method that called this method.

```java
public void catchDemo() throws CustomException {
    int a = divide(2, 1);
    System.out.println(a);
}
```

In this way, when exception is thrown from the method, the program is interrupted. The exception is propagated to the method that calls `catchDemo()`. 

Program execution doesn't resume until a catch-block in the call stack catches the exception.

If none catches the exception, at the end JVM will do, and it terminates the program.

## Exception Hierarchies

The advantage of exception hierarchies is that if you decide to catch (using try-catch) a certain exception in the hierarchy, then you will automatically also catch all subclasses of that exception too.

For example, you catch `IOException` which is the super class of `FileNotFoundException`, you will also catch `FileNotFoundException` and other exceptions.

### Multiple catch blocks with hierarchy

```java
try {
    // some methods call
} catch(FileNotFoundException e) {
    System.out.println("FileNotFoundException thrown")
} catch(IOException e) {
    System.out.println("IOException thrown")
}
```

The first catch-block throws a match exception that handles the situation. In this example, the `FileNotFoundException` is thrown, and the program will skip the `IOException` and continues.

### Design exception hierarchy for you API

You can create a base Exception, and make your other exceptions extends it. For example, when your program connects to a database, the exception might happen when the client input the wrong password or the url to the database is incorrect.

At such case, you can simply throw an exception of `ConnectionException`, or create 2 exceptions that handles these situation respectively.

```java
public class UrlException extends BaseException {
}

public class WrongPasswordException extends BaseException {
}
```

This way your program can explicitly tell the client what goes wrong, and also how to handle these exceptions.

You can subdivide the exceptions more by adding more levels to the hierarchy.

## Checked and unchecked exception

> Checked and unchecked exceptions are functionally equivalent.

> Regardless of your choice between checked and unchecked exceptions it is a matter of personal or organisational style. None is functionally better than the other.

**Checked exception** 

- must be explicitly caught or propagated
- extends `Exception` class

**Unchecked exception** 

- doesn't have to be caught or propagated explicitly
- extends `RuntimeException` class

### Example of difference between checked and unchecked exception


The checked exception example, the `BadUrlException` extends `Exception` class
```java
public void storeDataFromUrl(String url){
    try {
        String data = readDataFromUrl(url);
    } catch (BadUrlException e) {
        e.printStackTrace();
    }
}

public String readDataFromUrl(String url) throws BadUrlException{
    if(isUrlBad(url)){
        throw new BadUrlException("Bad URL: " + url);
    }

    String data = null;
    //read lots of data over HTTP and return
    //it as a String instance.

    return data;
}
```

Upon calling `storeDataFromUrl()`, you will have to use try-catch or propagate the exception by adding the `throw BadUrlException` to method signature.

**Now, let's change it to use unchecked exception.** 

```java
public void storeDataFromUrl(String url){
    String data = readDataFromUrl(url);
}

public String readDataFromUrl(String url) {
    if(isUrlBad(url)){
        throw new BadUrlException("Bad URL: " + url);
    }

    String data = null;
    //read lots of data over HTTP and
    //return it as a String instance.

    return data;
}
```

Notice that the method no longer declares it throws `BadUrlException`. The `storeDataFromUrl()` doesn't have to catch it either. 

**The `storeDataFromUrl()` can still choose to use try-catch to catch the exception, but now it's not forced to.**

### Checked or unchecked exceptions?

There is debate about which to choose. In short, it's the matter of preferences.
