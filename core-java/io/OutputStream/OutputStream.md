# `OutputStream` 
> `java.io.OutputStream`, is the base class of all output streams in the Java IO API.

![](https://www.tutorialspoint.com/java/images/file_io.jpg)

A Java `OutputStream` is typically connected to some data destination. e.g. network, file, memory, etc.

## methods

### write data by `write(byte)` or `write(byte[])`

Write a single char by type casting
```java
try(OutputStream out = new FileOutputStream("output.txt")) {  
    char x = 'a';  
    out.write((byte) x);  
}
```

Or convert the char array in a loop

```java
try(OutputStream out = new FileOutputStream("output.txt")) {  
	char[] chars = {'A', 'B'};
	for (//) {
	    out.write((byte) chars[i]);  
	}
}
```

### close the `InputStream` by close()

use `close()` or *try-with-resources* to auto close it.

## Convert `OutputStream` to `Writer`

The Java `OutputStream` is a byte based stream. You can convert a `OutputStream` to a character based Writer using the Java `OutputStreamWriter`, `BufferedWriter`, or `FileWriter` class.

```java
OutputStream outputStream       = new FileOutputStream("file.txt");
Writer       outputStreamWriter = new OutputStreamWriter(outputStream);

outputStreamWriter.write("Hello World");
```