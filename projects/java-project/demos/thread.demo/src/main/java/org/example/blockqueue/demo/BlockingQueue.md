The capacity of blockingQueue, by default, will be set to Integer.MAX_VALUE.

> The most important thing when designing a producer-consumer program using unbounded BlockingQueue is that consumers should be able to consume messages as quickly as producers are adding messages to the queue. Otherwise, the memory could fill up and we would get an OutOfMemory exception.

Bound and unbound:
```java
BlockingQueue<String> queue = new LinkedBlockingDeque<>(10);
BlockingQueue<String> queue = new LinkedBlockingDeque<>();
```

Adding methods:
- add(): true, if succeed, or throws `IllegalStateException`
- put(): inserts, wait when queue is full
- offer(): boolean
- offer(E e, long timeout, TimeUnit unit): boolean, wait within the time slot

Retrieving:
- take(): wait for a head element of a queue and removes it. If the queue is empty, it blocks and waits for it.
- poll(long timeout, TimeUnit unit): retrieves and removes the head of the queue, wait if necessary. If timeout, returns null

**Poison pill**, see in the example program that the poison pill is used to signal the consumer that there's no more input, so the threads will finish execution gracefully.
