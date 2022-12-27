## General introduction on forkjoinpool

[fork join pool article](https://theboreddev.com/the-unfairly-unknown-forkjoinpool/) 

## Instantiate `ForkJoinPool`

```java
// by factory
ForkJoinPool commonPool = ForkJoinPool.commonPool();

// by constructor
ForkJoinPool commonPool = new ForkJoinPool(2);
```

## `ForkJoinTask<V>` abstract class for tasks execution

subclasses:
- `RecursiveAction` - void
- `RecursiveTask<V>` - return a value
