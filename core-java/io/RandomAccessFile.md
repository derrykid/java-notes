# `RandomAccessFile`

> This allows you to read from it or write to it as you please. You can replace the content of the file. This cannot achieve by `FileInputStream` or `FileOutputStream`.

## Create a `RandomAccessFile` object

```java
RandomAccessFile file = new RandomAccessFile("file.txt", "rw");
```

| Mode | Description                                                                                                         |
|------|---------------------------------------------------------------------------------------------------------------------|
| r    | Read mode. Calling write methods will result in an IOException.                                                     |
| rw   | Read and write mode                                                                                                 |
| rwd  | Read and write mode - synchronously. All updates to file content is written to the disk synchronously.              |
| rws  | Read and write mode - synchronously. All updates to file content or meta data is written to the disk synchronously. |


## Read 

```java
try(RandomAccessFile file = new RandomAccessFile("file.txt", "rw")) {

	// may wrap this with a loop to read entire file
	var returnType = file.readChar();
}
```

`RandomAccessFile` provides many methods to read:
- `readInt()`
- `readByte()`
- `readBoolean()`
- `readLong()`
- `readDouble()`
- `readFloat()`
- `readChar()`
- `readShort()`
- `readFully(byte[])`

Or read the file full line by
- `readLine()`

## Write 

Write to file has similar methods, though you have to pass the input, as reading from the file. 
```java
byte[] bytes = // bytes
try(RandomAccessFile file = new RandomAccessFile("file.txt", "rw")) {
	file.write(bytes);
}
```

It's better to use `FileWriter` to do the writing. It's because there might be encoding issue with these methods.
[StackOverFlow](https://stackoverflow.com/questions/65383199/randomaccessfile-writechar-method-writing-nulto-file-and-writeutf-writing-enq)
