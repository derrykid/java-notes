[Origin](https://www.liaoxuefeng.com/wiki/1252599548343744/1265117109276544)       
       
       ┌───┐
       │Map│
       └───┘
         ▲
    ┌────┴─────┐
    │          │
┌───────┐ ┌─────────┐
│HashMap│ │SortedMap│
└───────┘ └─────────┘
               ▲
               │
          ┌─────────┐
          │ TreeMap │
          └─────────┘
          
`SortedMap<K, V>` is an interface. The `TreeMap` is the implementation map that we can use.

TreeMap can ensure that the key is ordered in our desired way if we **implement `Comparable`**. `String` and `Integer` has already implements `Comparable` so we can use it right away. **For objects, we have to implement `Comparable`, otherwise we create a method when we create `TreeMap.** 

String
```java
        Map<String, Integer> map = new TreeMap<>();
        map.put("orange", 1);
        map.put("apple", 2);
        map.put("pear", 3);
        for (String key : map.keySet()) {
            System.out.println(key);
        }
        // apple, orange, pear
```

Person object
```java
    public static void main(String[] args) {
        Map<Person, Integer> map = new TreeMap<>(new Comparator<Person>() {
            public int compare(Person p1, Person p2) {
                return p1.name.compareTo(p2.name);
            }
        });
        map.put(new Person("Tom"), 1);
        map.put(new Person("Bob"), 2);
        map.put(new Person("Lily"), 3);
        for (Person key : map.keySet()) {
            System.out.println(key);
        }
        // {Person: Bob}, {Person: Lily}, {Person: Tom}
        System.out.println(map.get(new Person("Bob"))); // 2
    }
}
```

We don't have to override `equals()` and `hashCode()` methods because `TreeMap` don't require those methods to sort.

`SortedMap` is an interface, programmer implements `TreeMap` mostly. We have to implement `Comparable` for objects, otherwise we pass `Comparator`.
Strictly, we have to follow the `compare()` logic to override method, i.e. return 0, -1, 1 when compare.
