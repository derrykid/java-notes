# `FileReader`

This works much alike `Reader`. Calling the methods that has defined in `Reader` interface suffices the need for text reading.

- This can be wrapped by `BufferedReader`
- Close the `FileReader` with `close()` or *try-with-resources.* 

## Example

### Read a char
```java
Reader reader = new BufferedReader(new FileReader("file.txt"));

int data = reader.read();
while (data != -1) {
    System.out.print((char) data);
    data = reader.read();
}
reader.close();
```

### Read an array of char 

```java
FileReader fileReader = new FileReader("c:\\data\\input-text.txt");

char[] destination = new char[1024];

int charsRead = fileReader.read(destination, 0, destination.length);
```