package org.example.executor;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ExecutorDemo {
    public static void main(String[] args) {
        try {
            useCountDownLatch();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void executorDemo() {
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> System.out.println("Hello World"));
    }


    // below are ThreadPoolExecutor examples

    /**
     * {@link https://www.baeldung.com/thread-pool-java-and-guava#2-threadpoolexecutor}
     */
    private static void newFixedThreadPool() {
        // can submit runnable or callable
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);

        executor.submit(() -> {
            Thread.sleep(1000);
            return null;
        });
    }

    // this may group threads without bounds to accommodate any number of tasks
    // good for short-living tasks
    private static void newCachedThreadPool() {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        executor.submit(() -> {
            Thread.sleep(1000);
            return null;
        });
    }

    // ideal for creating an event loop
    private static void newSingleThreadPool() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        AtomicInteger counter = new AtomicInteger();
        executor.submit(() -> {
            counter.set(1);
        });
    }

    private static void scheDuledThreadPoolExecutor() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(5);
        executor.schedule(() -> {
            System.out.println("Hello World");
        }, 500, TimeUnit.MILLISECONDS);
    }

    private static void useCountDownLatch() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(5);
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(5);
        ScheduledFuture<?> future = executor.scheduleAtFixedRate(() -> {
            System.out.println("Hello World");
            lock.countDown();
        }, 500, 100, TimeUnit.MILLISECONDS);

        lock.await(1000, TimeUnit.MILLISECONDS);
        future.cancel(true);
    }
}
