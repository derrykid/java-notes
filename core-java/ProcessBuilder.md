# ProcessBuilder

To use operating system command line tools.


To create a ProcessBuilder with constructor:
```java
ProcessBuilder pb = new ProcessBuilder("pwd");
```
Run the command simply by `start()`:
```
Process process = pb.start();
```

Alternative way to add commands instead of using constructor:
```java
ProcessBuilder pb = new ProcessBuilder();
pb.command("pwd");
Process process = pb.start();
```

## env value of OS

You can get the OS env value by `System.getenv()` or `processBuilder.environment()`
```java
var map = processBuilder.environment();

map.put("GREETING", "Hola Mundo");

processBuilder.command("/bin/bash", "-c", "echo $GREETING");
Process process = processBuilder.start();
```

## redirect IO

- `redirectInput(File file)`
- `redirectOnput(File file)`
- `redirectError(File file)`

### redirect output

#### to console by `Reader` class
```java
ProcessBuilder processBuilder = new ProcessBuilder("java", "--version");
var process = processBuilder.start();

InputStream inputStream = process.getInputStream();

try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

    var line = reader.readLine();
    while (line != null) {
        System.out.println(line);
        line = reader.readLine();
    }
}
```

#### to file

```java
ProcessBuilder processBuilder = new ProcessBuilder("java", "--version");

File file = new File("log");
processBuilder.redirectOutput(file);

var process = processBuilder.start();
```

### inherit IO

## change the working directory

By `directory(File file)`

```java
ProcessBuilder processBuilder = new ProcessBuilder("/bin/sh", "-c", "ls");

processBuilder.directory(new File("src"));
```

## pipeline

Since Java 9, concept of pipelines is introduced to ProcessBuilder API:
```java
public static List<Process> startPipeline(List<ProcessBuilder> builders)
```

For example, a terminal command:
```bash
find . -name *.java -type f | wc -l
```

Implementation in Java:
```java
List<ProcessBuilder> builders = List.of(
        new ProcessBuilder("find", "src", "-name", "*.java", "-type", "f"),
        new ProcessBuilder("wc", "-l")
);

List<Process> processList = ProcessBuilder.startPipeline(builders);

Process lastOne = processList.get(processList.size() - 1);
```

Then you can print out the output by `Reader`
