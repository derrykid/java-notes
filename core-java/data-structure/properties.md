properties inherit `HashTable` and `HashTable` implement `Map`, so properties perform like `Map`.
```java
Properties props = new Properties();
props.setProperty("username", "justin");
props.setProperty("password", "123456");
System.out.println(props.getProperty("username"));
```

It can read from `.properties` file by `load` method.
```java
props.load(new FileInputStream(args[0]));
```
The `InputStream` will close automatically.
