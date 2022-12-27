```java
ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
```

Print out 10 objects in the queue:
```java
public static void main(String[] args) {
    Queue<String> queue = new ArrayDeque<>();
    IntStream.rangeClosed(1, 10).mapToObj(String::valueOf).forEach(queue::add);

    service.schedule(() -> getElement(queue), 200, TimeUnit.MILLISECONDS);
}

static <T> void getElement(Queue<T> queue) {
    if (!queue.isEmpty()) {
        var threadName = Thread.currentThread().getName();
        System.out.println("Thread:" + threadName + ": " + queue.poll());
        service.schedule(() -> getElement(queue), 200, TimeUnit.MILLISECONDS);
    } else {
        System.out.println("Finished");
        service.shutdown();
    }
}
```
