# About

This notes down the general file or directory manipulation including:

- how to get the working directory
- how to check if a directory exist
- create a directory

## get the working directory

```java
    private static void getPath() {
        File file = new File("");
        var path = file.getAbsolutePath();

        // or use this
        // var path = file.getCanonicalPath();

        System.out.println(path);
    }

```

## check if a directory exists

```java
Path path = Path.of("myFir");

if (Files.exists(path)) {
    System.out.println("yes");
} else {
    System.out.println("no");
}
```

## create a directory

- `mkdir()` - this will fail if the parent dir doesn't exist. Like if you `mkdir oneLevel/secondLevel/3rdLevel`, you have to create the oneLevel and secondLevel first.
- `mkdirs()` - it's like `mkdir -p`, which creates the parent dir

```java
new File("dirName").mkdirs();
```

### Example - create a directory if it doesn't exist

```java
File f = new File("downloadMusic/");
Path musicDir = f.toPath();

if (Files.notExists(musicDir)) {
    f.mkdir();
    System.out.println("created!");
}
```

## Convert Path to File, and vise versa

Path to file
```java
Path path = Paths.get("/home/mkyong/test/file.txt");

// Convert Path to File
File file = path.toFile();

System.out.println(file);
```

File to path
```java
File file = new File("/home/mkyong/test/file.txt");

// Java 1.7, convert File to Path
Path path = file.toPath();

System.out.println(path);
```
