# `Comparator` and `Comparable<Class>`

1. We create a `comparator`, and pass it to: sort, stream, etc.
2. **Implements `Comparable<Class>`** and override `public int compareTo(Object o)` method

**The comparator can add unlimited `thenComparing()`**, works like builder pattern



## Comparator
Create comparator
```java
Comparator<Developer> byAge = new Comparator<Developer>() {
    @Override
    public int compare(Developer o1, Developer o2) {
        return o1.getAge() - o2.getAge();
    }
};

// lambda
Comparator<Developer> byAge = (Developer o1,Developer o2) -> o1.getAge() - o2.getAge();

// use method reference: comparing (can add another condition, explain below)
Comparator<Developer> byReference = Comparator.comparing(Developer::getAge);

// use Comparator static method
Comparator<Developer> method = Comparator.comparingInt(Developer::getAge);
```

**Sort with multiple criteria** 
```java
Comparator<Developer> byMore = Comparator.compare(Developer::getAge)
                                        .thenComparing(Developer::getName);
```

Then we use it as the parameter, pass it to the function:
`Collections.sort(List<>, lambda)`

```java
List<Developer> list = new ArrayList<>();

// create the new comparator
Collections.sort(list, new Comparator<Developer>() {
    // method - copy the above method
});
```

### `List` supports `sort()` method, we can pass a comparator as well
```java
list.sort(new Comparator<Developer>() {
    // method - same to above
});

// lambda 
list.sort((Developer o1, Developer o2) -> o1.getAge() - o2.getAge());
```


## Use `compareTo()` to sort String
As usually, we have to create a comparator
```java
stringArrayList.sort((o1, o2) -> {
    return o1.compareTo(o2);
});

// lambda
stringArrayList.sort(String::CompareTo);
```
