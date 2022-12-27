# `InputStreamReader`

> The Java `java.io.InputStreamReader`, wraps a Java `InputStream`, thereby turning the byte based `InputStream` into a character based Reader.

In other words, the Java `InputStreamReader` interprets the bytes of an `InputStream` as text instead of numerical data. The Java `InputStreamReader` class is thus a subclass of the Java Reader class.

## Example

### With type casting, works with large file
```java
InputStream inputStream       = new FileInputStream("c:\\data\\input.txt");
Reader      inputStreamReader = new InputStreamReader(inputStream);

int data = inputStreamReader.read();
while(data != -1){
    char theChar = (char) data;
    data = inputStreamReader.read();
}

inputStreamReader.close();
```

### With char array declaration to work with small file

This works for smaller file as the array length is fixed:
```java
// Creates an array of character
char[] array = new char[50];

try(InputStreamReader input = new InputStreamReader(new FileInputStream("demo.txt"))) { 
  // Reads characters from the file
  input.read(array);

  System.out.println(array);
} catch (Exception e) {
  e.getStackTrace();
}
```

## set charset encoding

```java
InputStream inputStream = new FileInputStream("data.txt");

InputStreamReader inputStreamReader =
    new InputStreamReader(inputStream, Charset.forName("UTF-8"));
```

## Get `InputStreamReader` char encoding 

```java
String encoding = inputStreamReader.genEncoding();
```

## read()

```java
int data = inputStreamReader.read();
```

Cast the `int` to `char`:
```java
char aChar = (char) data;
```

## close()

`close()` it or use try-with-resource
