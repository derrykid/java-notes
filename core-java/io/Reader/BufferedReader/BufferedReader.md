[Bufferedreader source](https://jenkov.com/tutorials/java-io/bufferedreader.html) 

> The Java BufferedReader class is a subclass of the Java Reader class, so you can use a BufferedReader anywhere a Reader is required.

Create a `BufferedReader` is easy. Use the constructor and pass any `Reader` instance:
```java
BufferedReader bufferedReader = new BufferedReader(new FileReader("input.txt"));
```
This example creates a `BufferedReader` which wraps a `FileReader`. The `BufferedReader` will read a block of characters from the `FileReader` (typically into a char array).


## Read a line of file by `readLine()`

The `readLine()` reads one line until line break;
```java
String line = bufferedReader.readLine();
```

It will return a textual line (all text until at line break is found) read from the `BufferedReader`. If there is no more data to read from the underlying Reader, then the BufferedReader's readLine() method will return null .

Use case:
```java
BufferedReader bufferedReader = new BufferedReader( new FileReader("/path/to/file/thefile.txt"));

String line = bufferedReader.readLine();
while(line != null) {
    System.out.println(line);
    line = bufferedReader.readLine();
}
bufferedReader.close();
```

## read characters
### Read a character one time

`read()` returns the char value. If it's `-1` then there's no more data to read.

```java
Reader reader = new BufferedReader(new FileReader("file.txt"));

int charNum = reader.read();    // only read one char
while(charNum != -1) {

    char theChar = (char) charNum;

    System.out.print(theChar);

    charNum = reader.read();

}
```

### Read in `char[]` array 

```java
Reader reader = new BufferedReader(new FileReader("/path/to/file/thefile.txt"));

char[] theChars = new char[128];

int charsRead = reader.read(theChars, 0, theChars.length);
while(charsRead != -1) {
    System.out.println(new String(theChars, 0, charsRead));
    charsRead = reader.read(theChars, 0, theChars.length);
}
reader.close();
```

The `read(char[], offset, length)` method returns the number of characters read into the char array, or -1 if there are no more characters to read in the `BufferedReader`.

the `read()` method signature:
```java
 * @param      cbuf  Destination buffer
 * @param      off   Offset at which to start storing characters
 * @param      len   Maximum number of characters to read
public abstract int read(char[] cbuf, int off, int len) throws IOException;
```

### Close the `BufferedReader` 

Close by either *try-with-resources* or calling `BufferedReader.close()` 

```java
reader.close();

// or
try(Reader reader = ...) {
	// code
}
```