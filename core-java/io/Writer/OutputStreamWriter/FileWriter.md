# `FileWriter`

This works much alike `Writer`. Calling the methods that has defined in `Writer` interface suffices the need for text writing.

- This can be wrapped by `BufferedWriter`
- Close the `FileWriter` with `close()` or *try-with-resources.* 

## Methods

### Overwrite or appending in constructor

In `FileWriter()` constructor, pass the boolean as 2nd parameter so as to set the object overwrite or appending. 

```java
Writer fileWriter = new FileWriter("file.txt", true);  //appends to file

Writer fileWriter = new FileWriter("file.txt", false); //overwrites file
```

### Writing a string

```java
Writer fileWriter = new FileWriter("data\\filewriter.txt");

fileWriter.write("data 1");
fileWriter.write("data 2");
fileWriter.write("data 3");

fileWriter.close();
```

### Write an array of char 

```java
FileWriter fileWriter = new FileWriter("file.txt");

char[] chars = new char[]{'A','B','C','D','E'};
fileWriter.write(chars);
```

### Write a char

```java
FileWriter fileWriter = new FileWriter("file.txt");

fileWriter.write('A');
```

## Encoding

Set a different encoding is impossible in `FileWriter`. Use `OutputStreamWriter` instead.