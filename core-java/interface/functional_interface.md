[toc]

## Functional Interface

[FunctionalInterface](https://www.youtube.com/watch?v=HsOVdmmBS9E) 

`@FunctionalInterface` is the annotation for Functional Interface.

**A FunctionalInterface must have a single abstract method.** But it might have as many default methods as well.

### Function<T, R> interface

It's most used interface. There's one abstract method. 
> Function interface takes `generic T` and return type `Result R`

#### Function interface `R apply(T t)`
```java
@FunctionalInterface
public interface Function<T, R> {
    R apply(T t);
}
```

Example 1: Create a function interface with lambda. `<Integer(T), Double(R)>`
```java
Function<Integer, Double> function = t -> t / 3.0;
function.apply(12); // give us (Double) 4.0
```

Example 2: Stream API, `map(Function<? super T, ? extends R> mapper)`
```java
List<Integer> ids = customerList.stream()
                                .map(customer -> customer.getId()) // or use (Customer::getId)
                                .collect(Collectors.toList())
```
We passed lambda express `customer -> customer.getId()` to `map()`. We imply `<Customer (T), Integer(R)>` for this lambda.


#### `andThen(Function)`, `compose(Function)`
- `andThen(Function)` - comes after the function
- `compose(Function)` - comes before the function
> These two default methods can combine with another `Function`

Example `andThen(param)` :
```java
Function<Double, Double> function = t -> t / 3.0;
Function<Double, Double> secondFunction = t -> t - 2.0;
function.andThen(secondFunction).apply(12); // Double: 2.0
```

Example `compose(param)`:
```java
Function<Double, Double> function = t -> t / 3.0;
Function<Double, Double> comesBeforeFunction = t -> t - 2.0;
function.compose(comesBeforeFunction).apply(12); // Double: 3.33
```

#### primitive types Function Interface
The following has `R apply(${primitiveType} value)`. It returns a parameterised type.
- `IntFunction` - `R apply(int value)`
- `LongFunction` - `R apply(long value)`
- `DoubleFunction` - `R apply(double value)`

The following maps the type to primitive types
- `ToIntFunction` - `int applyAsInt(T value)`
- `ToLongFunction` - `long applyAsLong(T value)`
- `ToDoubleFunction` - `double applyAsDouble(T value)`

For `T` are primitives and `R` are primitives
- `IntToDouble` - `double applyAsDouble(int value)`
- `IntToLong` - `long applyAsLong(int value)`
- `DoubleToInt`
- `DoubleToLong`
- `LongToInt`
- `LongToDouble`

### BiFunction<T, U, R> interface

> Represents a function that accepts two arguments and produces a result. This is the two-arity specialization of Function.
> This is a functional interface whose functional method is apply(Object, Object).

```java
R apply(T, U)
```

Example
```java
BiFunction<Integer, Integer, Integer> biFunction = (t, u) -> t * u;
biFunction.apply(6, 6); // output: 36
```

### Predicate - FunctionalInterface that always returns boolean

Most common use case is the Stream API, `filter(predicate)` method

The Predicate interface:
```java
@FunctionalInterface
public Interface Predicate<T> {
    boolean test(T t);
}
```

Example 1: Create with lambda and use it explicitly
```java
Predicate<String> predicate = t -> t.startsWith("Geek");
predicate.test("Geekific"); // true
```

Example 2: Stream API, `filter(predicate)`
```java
List<Customer> filteredCustomers = customerList.stream()
                                            .filter(customer -> customer.getId() < 20)
                                            .collect(Collectors.toList());
```

default methods:
- and(Predicate)
- or(Predicate)
- negate(Predicate)

*Custom* distinct method for Stream use
```java
<T, U> Predicate<T> distinct(Function<T, U> extractor){
    Set<U> mask = new HashSet<>();
    return t -> mask.add(extractor.apply(t));
}
```

Stream example:
```java
Stream.of("Cat", "Deer", "Dog", "Antelope", "Bear", "Wolverine")
        .filter(distinct(String::length))
        .forEach(System.out::println);
```

Output: where the length is distinct
```
Cat
Deer
Antelope
Wolverine
```

#### more predicate interfaces

For primitive types
- `IntPredicate`
- `LongPredicate`
- `DoublePredicate`

Taking 2 params
- `Bipredicate<T, U>`

### Supplier

A @FunctionalInterface that takes nothing but return a `T`
```java
@FunctionalInterface
public interface Supplier<T> {
    T get();
}
```

Example 1:
```java
Supplier<Double> supplier = Math::random;
supplier.get(); // returns a random number
```

Example 2: use the `supplier` in example 1
```java
List<Double> tenRandom = Stream.generate(supplier)
                                .limit(10)
                                .collect(Collectors.toList());
```

primitive type suppliers:
- `IntSupplier` - `int getAsInt();`
- `LongSupplier` - `long getAsLong();`
- `DoubleSupplier` - `double getAsDouble();`
- `BooleanSupplier` - `boolean getAsBoolean();`

### Consumer

Most common use case is Stream API, forEach(consumer).

A `@FunctionalInterface` that takes a parameter and returns nothing
```java
@FunctionalInterface
public interface Consumer<T> {
    void accept(T t);
}
```

```java
Stream.generate(()-> "Geekific").limit(10).forEach(System.out::println);
```

#### primitive types Consumer
- `IntConsumer` - `void accept(int value);`
- `LongConsumer` - `void accept(long value);`
- `DoubleConsumer` - `void accept(double value);`

#### BiConsumer<T, U>

```java
@FunctionalInterface
public interface BiConsumer<T, U> {
    void accept(T t, U u);
}
```

also object, primitive type consumer
- `ObjIntConsumer` - `void accept(T t, int value)`
- `ObjLongConsumer` - `void accept(T t, long value)`
- `ObjDoubleConsumer` - `void accept(T t, double value)`

### Operator

> Represents an operation on operand(s) that produce(s) a result of the same type as its operand. This is a specialization of Function for the case where the operand and result are of the same type.

It inherits `Function` interface.

Inherited methods:
- `apply`
- `andThen`
- `compose`

Operator interfaces:
1. Unary ones
- `UnaryOperator`
- `IntUnaryOperator`
- `DoubleUnaryOperator`
- `LongUnaryOperator`

2. Binary ones
- `BinaryOperator`
- `IntBinaryOperator`
- `LongBinaryOperator`
- `DoubleBinaryOperator`

#### `UnaryOperator<T>`

It extends `Function` interface. It doesn't return a new type but process the source.

```java
List<String> list = Arrays.asList("HELLO", "WORLD");
list.replaceAll(String::toLowerCase);   // the UnaryOperator
```

#### `BinaryOperator<T>`

It extends `BiFunction` interface. 

Example: Stream#reduce method
```java
Integer sum = Stream.of(20, 10, -41, 80, 30)
                    .reduce(0, (a, b) -> a + b);
```

### Runnable

`Runnable` and `Callable` interfaces that are used in concurrency APIs. 

```java
Thread thread = new Thread(() -> System.out.println("Hello From another thread"));
thread.start();
```

