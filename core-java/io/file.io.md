![overview](https://docstore.mik.ua/orelly/java-ent/jnut/figs/JN3_1101.gif) 

## Basic File.io use:
- `FileWriter` & `PrintWriter` - You have to have a `FileWirter` to tell the program what File it is, then use `PrintWriter` to perform the functionality.

```java
FileWriter fw = new FileWriter("/home/derry/playground/test.txt");
PrintWriter pw = new PrintWriter(fw);

pw.print("First");
pw.println(2);
pw.print("third");
pw.print(4);

fw.close();
```

Read the data back by `File`
```java
File file = new File("/home/derry/playground/test.txt");
Scanner scan = new Scanner(file);

while (scan.hasNext()) {
    System.out.println(scan.next());
}

scan.close();
```

