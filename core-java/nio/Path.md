## `java.io.File` vs `java.nio.file.Path`

> In many cases, these two class objects are similar. Sometimes you can safely replace `File` object with `Path` object

## Create a `Path` object

```java
Path path = Path.of("file.txt");
```

**This creates the Path object, but it does not mean the object exists in file system.**

### Prefer `Path.of()` to `Paths.get()`

TL;DR: use `Path.of()`

There is no difference between `Path.of()` and `Paths.get()`.  `Path.of()` was later introduced because in early version, there can not have static method in interface.

## methods

- `relativize()`: to get the relative `Path` 
- `normalize()`: eliminate `.` or `..` to normalize the path