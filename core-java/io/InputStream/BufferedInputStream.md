# `BufferedInputStream`

> The `java.io.BufferedInputStream`, provides transparent reading of chunks of bytes and buffering for a Java `InputStream`, including any subclasses of `InputStream`.

## Example

```java
try(BufferedInputStream bufferedInputStream = new BufferedInputStream( new FileInputStream("input.txt") ) ) {

    int data = bufferedInputStream.read();
    while(data != -1){
        data = bufferedInputStream.read();
    }
}
```

## Setting buffer size

```java
int bufferSize = 8 * 1024;
    
BufferedInputStream bufferedInputStream = new BufferedInputStream( new FileInputStream("file.txt"), bufferSize);
```

## Close

use `close()` or try-with-resources
