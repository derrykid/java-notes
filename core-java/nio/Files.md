> `java.nio.file.Files` works with `java.nio.file.Path` class.

## Create, move, delete, copy related methods

### check file existence

`Files.exists(Path path)` check if the file exists 
```java
Path path = Path.of("inexistedFile.txt");
boolean isExisted = Files.exists(path);
```

You can provide 2nd parameter to it. It can decide if you want to follow symbolic link, etc.

### create a directory 

`Files.createDirectory(Path path)` to create a directory

```java
Path path = Paths.get("data/subdir");

try {
    Path newDir = Files.createDirectory(path);
} catch(FileAlreadyExistsException e){
    // the directory already exists.
} catch (IOException e) {
    //something else went wrong
    e.printStackTrace();
}
```

You can call `Files.exists()` before creation to check if directory existence.

### Copy a file

Copy a file by `Files.copy(sourcePath, destPath)`

```java
Path sourcePath      = Paths.get("file.txt");
Path destinationPath = Paths.get("file-copy.txt");

try {
    Files.copy(sourcePath, destinationPath);
} catch(FileAlreadyExistsException e) {
    //destination file already exists
} catch (IOException e) {
    //something else went wrong
    e.printStackTrace();
}
```

#### Overwrite the existing file in `Files.copy(source, dest, copyOption)`

This will overwrite the existing files.

```java
 Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
```

### Move or rename a file
Move/rename a file by `Files.move(sourcePath, destPath)`

This method can move and rename the file.
```java
Files.move(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
```

### Delete a file

Delete a file by `Files.delete(Path path)` 

```java
Files.delete(path);
```

## `walkFileTree()` to traverse the directory tree 

You can perform file search, file manipulation, file deletion by this.

## Read a file to `List<String>`
This provides a really quick way to read all lines from a file.
```java
List<String> fileInput = Files.readAllLines(Path path);
```

## Get `InputStream`, `OutputStream`

```java
InputStream inputStream = Files.newInputStream(Path path);
OutputStream outputStream = Files.newOutputStream(Path path);
```