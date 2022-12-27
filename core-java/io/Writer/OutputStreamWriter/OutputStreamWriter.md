## Quick note 
- close after use 
- Can wrap with `BufferedWriter`

# `OutputStreamWriter` 

> `java.io.OutputStreamWriter`, is intended to wrap an Java `OutputStream` and thereby turning the byte based output stream into a character based Writer. The Java `OutputStreamWriter` can also wrap any subclass of `OutputStream`.


In other words, the Java `OnputStreamReader` interprets the bytes of an `OnputStream` as text instead of numerical data. The Java `OnputStreamReader` class is thus a subclass of the Java Writer class.

## Example 
### Write a string
```java
OutputStream       outputStream       = new FileOutputStream("c:\\data\\output.txt");
OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);

outputStreamWriter.write("Hello World");

outputStreamWriter.close();
```

### Write a char 
```java
OutputStream       outputStream       = new FileOutputStream("c:\\data\\output.txt");
OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");

outputStreamWriter.write('A');
```

### Write char array for smaller file 
```java
char[] chars = new char[]{'A','B','C','D','E'};
outputStreamWriter.write(chars);
```

### Close the `OutputStreamWriter`
Either by *try-with-resources* or *close()*
```java
writer.close();

// or
try(OutputStreamWriter outputStreamWriter = new OutputStreamWriter(output)) {
	// code
    }
```

## Encoding constructor 

Specify the encoding format in `OutputStreamWriter` constructor 2nd parameter.
```java
OutputStream       outputStream       = new FileOutputStream("file.txt");
OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
```