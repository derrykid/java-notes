`Set` will not accept duplicate value

Use case:
```java
Set<String> fishSet = new HashSet<>();
fishSet.add("Salmon");
fishSet.add("Cod");
fishSet.add("Salmon");

System.out.println(fishSet); // ["Salmon", "Cod"]
```

* `retainAll()` method will return boolean and also will change elements in the set. It will merge A set with B, with elements in common. {1, 2, 3, 4, 5} and {1, 2, 3} => {1, 2, 3}
