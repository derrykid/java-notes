# Try-with-resources

It will automatically close the resources like InputStream, JDBC Connection, when you leave the block. Any resource opened within the try-with-resources block is closed, regardless whether any exception is thrown.

## construct

Notice that `try (...some code...)` is the construct.

```java
private static void printFile() throws IOException {
    try (FileInputStream input = new FileInputStream("file.txt")) {
        int data = input.read();
        while(data != -1) {
            System.out.print((char) data);
            data = input.read();
        }
    }
}
```

The variable is declared inside the parentheses after the `try`. It's instantiated and assigned. The variable will be closed automatically. It's made possible because **FileInputStream implements `AutoCloseable` interface.** 

## Enhancement since Java 9

After Java 9, you can do this in construct:
```java

FileInputStream input = new FileInputStream("file.txt");

try(input) {
    // do sth
}
```

Notice that you don't have to instantiate the variable in the parentheses as long as the referencing resources is effectively final.

The resource is still closed properly once exit the block.

## Use multiple resources in the block

Both of these resources will be closed automatically when execution leaves the try block.
```java
    try ( FileInputStream input = new FileInputStream("file.txt");
          BufferedInputStream bufferedInput = new BufferedInputStream(input)) {

        int data = bufferedInput.read();
        while(data != -1){
        System.out.print((char) data);
        data = bufferedInput.read();
        }
    }
```

## Closing order

The resources will be closed in reverse order.
For example, the above code will close `BufferedInputStream` then `FileInputStream`.

## Custom AutoCloseable Implementations

You can create your own `AutoCloseable` implementations and use them in the try-with-resources.

AutoCloseable interface:
```java
public interface AutoCloseable {
    public void close() throws Exception;
}
```

Own implementation:
```java
public class MyAutoClosable implements AutoCloseable {

    public void doIt() {
        System.out.println("MyAutoClosable doing it!");
    }

    @Override
    public void close() throws Exception {
        System.out.println("MyAutoClosable closed!");
    }
}
```

The `doIt()` isn't part of the interface but simply for demonstration.

Program:
```java
try(MyAutoClosable myAutoClosable = new MyAutoClosable()){
    myAutoClosable.doIt();
}
```

Output:
```
MyAutoClosable doing it!
MyAutoClosable closed!
```

## catch and finally block

You can, of course, add catch and finally block.

```java
try(AutoClosableResource resourceOne = new AutoClosableResource("One", true)) {
    resourceOne.doOp(true);
} catch(Exception e) {
    Throwable[] suppressed = e.getSuppressed();
    throw e;
} finally {
    throw new Exception("From finally block!");
}
```
