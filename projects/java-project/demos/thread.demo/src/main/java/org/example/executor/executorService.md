# Java Concurrent package diagram

![util.concurrent package diagram](https://www.uml-diagrams.org/examples/java-7-concurrent-executors-uml-class-diagram-example.png) 

# ExecutorService implementations

`ExecutorService` is an interface: implementations available are:

- ThreadPoolExecutor
- ScheduledThreadPoolExecutor

## Creating an ExecutorService

There are some factory classes you can use to create the ExecutorService:
```java
ExecutorService service1 = ExecutorService.newSingleThreadExecutor();
ExecutorService service2 = ExecutorService.newFixedThreadPool(10);
ExecutorService service3 = ExecutorService.newScheduledThreadPool(10);
```

## ExecutorService methods

- execute(Runnable):void
- submit(Runnable):Future. Use `get()`, if returns null, means it's succeed
- submit(Callable):Futre. Use `get()`. It'll give you an object.
- invokeAny(Collection<? extends Callable> collection) - runs any one callable task in it. One succeed, others will be canceled. Return a object.
- invokeAll(Collection<? extends Callable> collection):List<Future<?>> - The list contains the result of each Callble

## Cancel Task

Calling the `calcel()` on the `Future` returned when the task is submitted. **Canceling is only possible if the task hasn't started executing yet.** 

```java
future.cancel();
```

## Shutdown ExecutorService

- shutdown() - won't accept anymore tasks, and will shutdown when all tasks done
- shutdownNow() - shutdown all tasks right away
- awaitTermination()

# ScheduledExecutorService

It can schedule tasks to run:

- after a delay
- execute repeatedly with a fixed interval between each execution

```java
ScheduledExecutorService service = Executors.newScheduledThreadPool(5);

ScheduledFuture future = service.schedule(callable, 5, TimeUnit.SECONDS)
```

The two last params specify that the callable should be executed after 5 seconds.

## ScheduledExecutorService implementations

- `ScheduledThreadPoolExecutor`

You can get the instance of it by:

```java
ScheduledExecutorService service = Executors.newScheduledThreadPool(5);

ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
```

## ScheduledExecutorService methods

- schedule(Callable task, long delay, TimeUnit unit)
- schedule(Runnable task, long delay, TimeUnit unit)
- scheduleAtFixedRate(Runnale, long initialDelay, long period, TimeUnit unit)
- scheduleWithFixedDelay(Runnale, long initialDelay, long period, TimeUnit unit)

### schedule()

Returns ScheduledFuture when pass callable
```java
ScheduledFuture future = service.schedule(callable, 5, TimeUnit.SECONDS);

var obj = future.get();
```

### scheduleAtFixedRate()

This method schedules a task to be executed periodically. The task is executed the first time after the initialDelay, and then recurringly every time the period expires.

If throws an exception at any time, it stops, otherwise it runs until the thread shutdown.

```java
ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
System.out.println(new Date());

Runnable runnable = () -> System.out.println(new Date());

service.scheduleAtFixedRate(runnable, 5, 1, TimeUnit.SECONDS);
```

This example first prints out the current time, then:
1. wait 5 seconds due to the initial delay
2. then execute runnable every second

**The tasks are executed every 'period' time. If the previous takes longer enough time than the 'period', the next will start immediately when previous is done.** 

### scheduleWithFixedDelay()

In this method, however, the period is interpreted as the delay between the end of the previous execution, until the start of the next. The delay is thus between finished executions, not between the beginning of executions.

**The delay is 'the end time of previous task' and 'the begin of the new task' is fixed.** 
