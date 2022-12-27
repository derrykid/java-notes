# `BufferedWriter`

> The Java `BufferedWriter` class, `java.io.BufferedWriter`, provides buffering to Writer instances. Buffering can speed up IO quite a bit. Rather than writing one character at a time to the network or disk, the `BufferedWriter` writes a larger block at a time.

## BufferedWriter example

```java
BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("file.txt"));
```

Change the buffered write size:
```java
int bufferSize = 8 * 1024;
    
BufferedWriter bufferedWriter = 
    new BufferedWriter(
        new FileWriter("c:\\data\\output-file.txt"),
            bufferSize);
```

This example sets the internal buffer of the BufferedWriter to 8 KB. It is best to use buffer sizes that are multiples of 1024 bytes. That works best with most built-in buffering in hard disks etc.



## methods

### Write a string
```java
Writer writer = new BufferedWriter(new FileWriter("output.txt"), 1024);

String text = "hello!";

writer.write(text);
```

### Write a char array

```java
try (Writer writer = new BufferedWriter(new FileWriter("output.txt"), 1024)) {

	char[] chars = new char[]{'A', 'B', 'C', 'D', 'E'};
	writer.write(chars);
}
```


### write a new line by `newLine()`
The `newLine()` method which can write a new-line character to the underlying Writer.

### Assure writing by `flush()`

Flushing all data written to the BufferedWriter to the underlying data destination. By calling flush() you can assure that any buffered data will be flushed (written) to disk (or network, or whatever else the destination of your BufferedWriter has).

```java
bufferedWriter.flush();
```

### Close the `BufferedWriter`

Remember to close the `Bufferedwriter` by:
```java
bufferedWriter.close();
```

Or use try-with-resources
```java
try(BufferedWriter bw = new BufferedWriter(output)) {
        // codes
}
```
