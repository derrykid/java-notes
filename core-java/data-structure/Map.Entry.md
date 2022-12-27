Suppose we have a `HashMap` phoneBook:

```java
Map<String, String> phoneBook = new HashMap<>();

phoneBook.put("John", "912736172");
phoneBook.put("Jack", "912283162");
phoneBook.put("Andrew", "912381921");
```

We can get key by `phoneBook.keySet()` and print out the values
```java
Set<String> keys = phoneBook.keySet();

for (String per: keys) {
    System.out.println(per + " : "+ phoneBook.get(i));
}
```

Or we can use `Map.Entry<String, String>` as an entry for `Set<>`
```java
Set<Map.Entry<String, String>> entries = phoneBook.entrySet();

for (Map.Entry<String, String> e : entries) {
    System.out.println(e.getKey() + " : " + e.getValue());
}
```

`Map.Entry<K, V>` It's a `Map` interface, and `Entry` interface inside `Map` interface. In other words, an interface inside an interface.
**We can get an entry by using `entrySet()` method.** 
