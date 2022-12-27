[tutorial jenkov](https://jenkov.com/tutorials/java-util-concurrent/countdownlatch.html) 

> It allows a set of instructions complete.

A CountDownLatch is initialized with a given count.

```java
CountDownLatch latch = new CountDownLatch(3);
```

This count is decremented by `countDown()`

```java
latch.countDown();
```

The calling thread waiting for this count to reach zero by calling `await()`. It'll block the calling thread until the count reaches zero.

## Example
```java
CountDownLatch latch = new CountDownLatch(3);

Waiter      waiter      = new Waiter(latch);
Decrementer decrementer = new Decrementer(latch);

new Thread(waiter)     .start();
new Thread(decrementer).start();
```

`Waiter.class` & `Decrementer.class`
```java
public class Waiter implements Runnable{

    CountDownLatch latch = null;

    public Waiter(CountDownLatch latch) {
        this.latch = latch;
    }

    public void run() {
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Waiter Released");
    }
}

--- 

public class Decrementer implements Runnable {

    CountDownLatch latch = null;

    public Decrementer(CountDownLatch latch) {
        this.latch = latch;
    }

    public void run() {

        try {
            Thread.sleep(1000);
            this.latch.countDown();

            Thread.sleep(1000);
            this.latch.countDown();

            Thread.sleep(1000);
            this.latch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
```

## An example I encounter

I use `yt-dlp` to download youtube songs. I created `ProcessBuilder` and wrap the cli tool. A song is wrapped in one thread.

I use multi-threading approach to download songs.

```java
CountDownLatch countDownLatch = new CountDownLatch(processBuilderList.size());

ExecutorService service = Executors.newCachedThreadPool();

List<Runnable> tasks = createRunnable(processList, countDownLatch);

for (var task : tasks) {
    service.execute(task);
}

countDownLatch.await();

service.shutdown();

---

private static List<Runnable> createRunnable(List<ProcessBuilder> processBuilderList,
                                             CountDownLatch countDownLatch) {

    List<Runnable> list = new ArrayList<>();

    for (ProcessBuilder e : processBuilderList) {
        Runnable task = () -> {
            try {
                e.start();
                // process

                countDownLatch.countDown();

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        };
        list.add(task);
    }
    return list;
}
```

After a runnable is completed, the countdownlatch is decremented by `countDown()`. When it reaches 0, it means every thread is completed its job. The program terminates.
