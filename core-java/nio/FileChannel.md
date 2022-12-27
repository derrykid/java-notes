> A channel connects to a file. It's bidirectional channel, allows you to read or write to the file. It always runs in blocking mode.

## How to use `FileChannel` to read and write data

Get a `FileChannel` from:
- `InputStream`
- `OutputStream`
- `RandomAccessFile`

### Read from a file 
```java
RandomAccessFile file = new RandomAccessFile("file.txt", "rw");

FileChannel fileChannel = file.getChannel();
ByteBuffer buffer = ByteBuffer.allocate(48);

int byteRead = fileChannel.read(buffer);
while(byteRead != -1) {
	buffer.flip();
	while(buffer.hasRemaining()) {
		System.out.println((char) buffer.get());
	}
	buffer.clear();
	byteRead = fileChannel.read(buffer);
}
fileChannel.close();
```
1. Create a file object
2. get the `Filechannel` from the file
3. Create a `ByteBuffer` from factory method `ByteBuffer.allocate()`
4. read the byte 
5. in loop check if it's EOF
6. flip the butter to read mode
7. print to console
8. clear the buffer 
9. read the byte again, and return to step 5

### Writing Data to a file by `FileChannel`

Write is done by `FileChannel.write(targetBuffer);`
```java
RandomAccessFile file = new RandomAccessFile("newCreatedFile.txt", "rw");
String text = "dummy text";

FileChannel fileChannel = file.getChannel();

ByteBuffer buffer = ByteBuffer.allocate(48);
buffer.clear();
buffer.put(text.getBytes());
buffer.flip();

while(buffer.hasRemaining()) {
	fileChannel.write(buffer);
}
fileChannel.close();
```
1. Get the file object 
2. Prepare the input data, in this case: the "dummy text"
3. Get the `FileChannel`
4. Create a `ByteBuffer` from factory method `ByteBuffer.allocate()`
5. clear the buffer before calling
6. put the data into buffer `buffer.put(byte[])`
7. call `buffer.flip()` to make the buffer into write mode
8. use loop to keep writing the data until all data is written by `fileChannel.write(buffer);`
9. Close the channel `fileChannel.close()`

## Force to write by `FileChannel.force(true);`

An operating system may cache file changes for performance reasons, and data may be lost if the system crashes. To force file content and metadata to write to disk continuously we can use the force method:


`fileChannel.force(true);`

This method is guaranteed only when the file resides on a local device.
