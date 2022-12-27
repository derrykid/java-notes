[Thorough article](https://www.geeksforgeeks.org/java-util-intsummarystatistics-class-with-examples/)

We can use `IntSummaryStatistics` to manipulate a `List` in a `Stream`.
```java
List<Integer> list = Arrays.asList(1, 3, 5, 7, 8, 21);                                                   
IntSummaryStatistics stats = list.stream().collect(Collectors.summarizingInt(i -> i));                   
System.out.println(stats.getCount());
```

* getCount();
* getSum();
* getAverage();
* getMin();
* getMax();

Example of passing a class method
```java
IntSummaryStatistics summaryStatistics = dishes.stream().collect(Collectors.summarizingInt(Dish::getCalories));
double average = summaryStatistics.getAverage();
long count = summaryStatistics.getCount();
int max = summaryStatistics.getMax();
int min = summaryStatistics.getMin();
long sum = summaryStatistics.getSum();
```
