# `InputStream`

> The Java `InputStream` class is the base class (superclass) of all input streams in the Java IO API. Each subclass of `InputStream` typically has a very specific use, but can be used as an `InputStream`.

![](https://www.tutorialspoint.com/java/images/file_io.jpg)


## methods

### read data by `read()`

To read all bytes in a Java `InputStream` you must keep reading until the value -1 is returned.

```java
int data = inputstream.read();
while(data != -1) {
	// do sth
	data = inputstream.read(); // read next byte
}
```

#### Read into byte array by `read(targetByte[])`

The `InputStream` class also contains two read() methods which can read data from the `InputStream` source into a byte array. These methods are:

- `int read(byte[])`
- `int read(byte[], int offset, int length)`

```java
InputStream inputstream = new FileInputStream("file.txt");

byte[] data = new byte[1024];

int bytesRead = inputstream.read(data);
while(bytesRead != -1) {
  doSomethingWithData(data, bytesRead);

  bytesRead = inputstream.read(data);
}
inputstream.close();
```
#### `readAllBytes()`

The Java `InputStream` class contains a method called `readAllBytes()` (since Java 9). This method reads all the bytes available in the `InputStream` and returns a single byte array with the bytes in. This method is useful if you need to read all bytes from a file via a `FileInputStream` into a byte array.

```java
byte[] fileBytes = null;
try(InputStream input = new FileInputStream("myfile.txt")) {
   fileBytes = input.readAllBytes();
}
```

### close the `InputStream` by close()

use `close()` or try-with-resources to auto close it.

## Convert `InputStream` to Reader

The Java `InputStream` is a byte based stream of data. 
Java IO API also has a character based set of input streams called `Reader`.

You can convert a java `InputStream` to a Java Reader using the `InputStreamReader`

```java
InputStream inputStream       = new FileInputStream("c:\\data\\input.txt");
Reader      inputStreamReader = new InputStreamReader(inputStream);
```
