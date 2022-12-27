>  `java.io.FileInputStream` makes it possible to read the contents of a file as a stream of bytes. 

## Methods

### Construct by `FileInputStream(File file)` or `FileInputStream(String path)`
```java
// pass file object
FileInputStream fileInputStream = new FileInputStream(new File("in.txt"));

// pass path string
FileInputStream fileInputStream = new FileInputStream(path);
```

### Read file a byte by `read()`

```java
try(InputStream in = new FileInputStream("file.txt")) {

	int data = in.read();
	while(data != -1) {
		System.out.print((char) data);
		data = in.read();
	}

}
```

### Read an array by `read(byte[])`

```java
FileInputStream fileInputStream = new FileInputStream("input.txt");

byte[] data      = new byte[1024];
int    bytesRead = fileInputStream.read(data, 0, data.length);

while(bytesRead != -1) {
  doSomethingWithData(data, bytesRead);

  bytesRead = fileInputStream.read(data, 0, data.length);
}
```

### Close the `FileInputStream`

Do it by either `close()` or with *try-with-resources*

## Add buffering to `FileInputStream` by `BufferedInputStream`

Simply wrap by `BufferedInputStream` constructor:
```java
InputStream input = new BufferedInputStream( new FileInputStream("c:\\data\\input-file.txt"), buffSize);
```

## Convert `FileInputStream` to `Reader`

> `FileInputStream` is a byte based stream of data. As you may know, the Java IO API also has a character based set of input streams called `Readers`. You can convert a Java `FileInputStream` to a Java Reader using the Java `InputStreamReader`.
```java
InputStream inputStream       = new FileInputStream("input.txt");
Reader      inputStreamReader = new InputStreamReader(inputStream);
```