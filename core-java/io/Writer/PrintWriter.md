# `PrintWriter`
> `java.io.PrintWriter` enables you to write formatted data to an underlying Writer. It writes primitive types, string as formatted text instead of the byte values.

**If you want to write text, use this is one for all solution!** You can skip `OutputStream`, `FileOutputStream`, etc and just use this.

## Methods

### create a `PrintWriter` 
Create a `PrintWriter` by calling the constructor. You can pass in:
- `Writer` instance
- `File` instance 
- `OutputStream` instance
- `String` of file path
```java
FileWriter  writer      = new FileWriter("file.txt");
PrintWriter printWriter = new PrintWriter(writer);
```

### Write to a file 

`print()` low level use `write()`. You can simply use `print()` to do most operation safely anyway.

You can make use of:
- `print()` - print without new line
- `println()` - print with new line
- `format()` - format the text like `System.out.format()`

```java
FileWriter  writer      = new FileWriter("file.txt");
PrintWriter printWriter = new PrintWriter(writer);

printWriter.printf("I am %d years old", 25);
printWriter.format("haha %s", ", this is fun");
printWriter.println("This is print with new line!");
```

### Close the `PrintWriter`

Either by `close()` or use *try-with-resources* to auto close it.