# `BufferedOutputStream`
> `java.io.BufferedOutputStream` is used to capture bytes written to the `BufferedOutputStream` in a buffer, and write the whole buffer in one batch to an underlying Java `OutputStream` for increased performance. Buffering can speed up IO quite a bit, especially when writing data to disk access or network.

## Example
```java
OutputStream bufferedOutputStream = new BufferedOutputStream( new FileOutputStream("out.txt")); 

bufferedOutputStream.write(123);
```

## Setting buffer size
```java
int bufferSize = 8 * 1024;
    
BufferedOutputStream bufferedOutputStream = new BufferedOutputStream( new FileOutputStream("file.txt"), bufferSize);
```

## Close

use `close()` or try-with-resources