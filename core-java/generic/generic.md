*Abbreviation:* 
* K - Key
* V - Value
* T - Type
* E - Element
* N - Number
* R - result
* A - Accumulator
* S, U, V - 2nd, 3rd, 4th types

With generic method, parameters and return types can be more flexible. When we pass the objects to a method that implements an interface, it accepts all the classes that implements that interface. Same applies to **Generic methods.** 

* Generic methods have a type parameter (the diamond operator enclosing the type) before the return type of the method declaration.
* Type parameters can be bounded
* Generic methods can have different type parameters separated by commas in the method signature
* Method body for a generic method is like a normal method
* `?` it's the wild card in Java

Example of a generic method to convert an array to a list:
```java
public <T> List<T> fromArrayToList(T[] array) {
    return Arrays.stream(array).collect(Collectors.toList());
} 
```

Breaking it down:
```
       (A)   (B)                   (C)
        |     |                     |
public <T> List<T> fromArrayToList(T[] array) {}
```
- A - only the "T" type can use this method. E.g. \<Animal>, only Animal types can use this.
- B - return type
- C - parameter type

We can specify the **upper bound and lower bound** for the (A).

Upper bound example
```java
public <T extends Account> List<T> getAcc() {}
```
**T** is the subclass of the `Account` class, for example: saving account or current account.

Lower bound example
```java
public <T super SavingAccount> List<T> getAcc(){}
```
**T** is the super class of `SavingAccount`. It can be `Account` object.



This generic type can also be `Comparator<T>`
```java
public static <T extends Comparator<T>> T getMax(T o1, T o2) {}
```

## Create a generic class and implements generic iterator

```java
public class MyCollection<T> implements Iterable<T> {

    private final ArrayList<T> list;

    public MyCollection() {
        this.list = new ArrayList<>();
    }

    public void add(T t) {
        this.list.add(t);
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }
}
```

main method:
```java
MyCollection<String> collection = new MyCollection<>();
collection.add("Hi");
collection.add("Hello");
collection.add("Oi");

Iterator<String> iterator = collection.iterator();

while (iterator.hasNext()) {
    String string = iterator.next();
    System.out.println(string);
}
```

## Generic Wildcards

The generic wildcards target two primary needs:
- Reading from a generic collection
- Inserting into a generic collection

```java
List<?>             listUnknown = new ArrayList<A>();
List<? extends A>   listUnknown = new ArrayList<A>();
List<? super A>     listUnknown = new ArrayList<A>();
```

### The `?` wildcard

```java
public void processElements(List<?> elements){
   for(Object o : elements){
      System.out.println(o);
   }
}
```

The `processElements()` method can be called with any generic `List` as parameter. E.g. `List<A>, List<B>, List<String>`

```java
List<A> listA = new ArrayList<A>();

processElements(listA);
```

### The `<? extends A>` wildcard boundary
List<? extends A> means a List of objects that are instances of the class A, or subclasses of A (e.g. B and C).

When you know that the instances in the collection are of instances of A or subclasses of A, it is safe to read the instances of the collection and cast them to A instances.

```java
public void processElements(List<? extends A> elements) {
    for(A a : elements) {
        System.out.println(a.getValue());   // suppose class A has a method getValue()
    }
}
```

### The `<? super A>` wildcard boundary
List<? super A> means that the list is typed to either the A class, or a superclass of A.

When you know that the list is typed to either A, or a superclass of A, it is safe to insert instances of A or subclasses of A (e.g. B or C) into the list.

```java
public static void insertElements(List<? super A> list){
    list.add(new A());
    list.add(new B());
    list.add(new C());
}
```

All of the elements inserted here are either A instances, or instances of A's superclass. Since both B and C extend A, if A had a superclass, B and C would also be instances of that superclass.
