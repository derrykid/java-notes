# `FileOutputStream`

> `java.io.FileOutputStream` makes it possible to write a file as a stream of bytes. 

## Methods

### Overwrite or appending in constructor

In `FileOutputStream()` constructor, pass the boolean as 2nd parameter so as to set the object overwrite or appending. 

```java
OutputStream output = new FileOutputStream("out.txt", true); //append

OutputStream output = new FileOutputStream("out.txt", false); //overwrite
```

### write a byte by `write(int byte)`
```java
OutputStream outputStream = new FileOutputStream("file.txt");

byte aByte = 32;

outputStream.write(aByte);
```

### write an array of byte by `write(byte[])`

```java
OutputStream outputStream = new FileOutputStream("file.txt");

byte bytes =  new byte[]{1,2,3,4,5};

outputStream.write(bytes);
```

### Close the `FileOutputStream`

Either by `close()` or use `try-with-resources` to auto close it.

## Add buffering to `FileOutputStream` by `BufferedOutputStream`

Add buffering usually results in faster speed. 
All bytes written to the `FileOutputStream` will first get buffered inside an internal byte array in the `BufferedOutputStream`. When the buffer is full, the buffer is flushed to disk all at once.

To add buffering, simply wrap it in `BufferedOutputStream` constructor 
```java
int bufferSize = 8 * 1024;
FileOutputStream output = new BufferedOutputStream( new FileOutputStream("file.txt"), bufferSize );
```

## Convert `FileOutputStream` to `Writer`

The Java `FileOutputStream` is a byte based stream. You can convert a `FileOutputStream` to a character based Writer using the Java `OutputStreamWriter` class.

```java
FileOutputStream outputStream = new FileOutputStream("file.txt");
Writer writer = new OutputStreamWriter(outputStream);

writer.write("Hello World");
```