package org.example.executor.service;

import java.util.concurrent.*;

public class ScheduledExecutorServiceDemo {
    private static ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
    private static Callable<String> callable = () -> "Hi";
    private static Runnable runnable = () -> System.out.println("Hi");

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long startTime = System.currentTimeMillis();

        // put a static method here to test out
        willHaveAPause();

        long endTime = System.currentTimeMillis();
        System.out.println("Millisecond time taken: " + (endTime - startTime));
        executor.shutdown();
    }

    private static void delayOneSecondBeforeExecuting() throws ExecutionException, InterruptedException {
        Future<String> resultFuture = executor.schedule(callable, 1, TimeUnit.SECONDS);
        String result = resultFuture.get();
        System.out.println(result);
    }

    // this method will pause 100 milli before execution, and will have 450 milli inbetween each execution
    private static void delayBeforeExecuteAndHavingInterval() {
        ScheduledFuture<?> resultFuture = executor.scheduleAtFixedRate(runnable, 100, 450, TimeUnit.MILLISECONDS);
    }

    // will have an 150 milli interval in between
    private static void willHaveAPause() {
        executor.scheduleWithFixedDelay(runnable, 100, 150, TimeUnit.MILLISECONDS);
        executor.scheduleWithFixedDelay(runnable, 100, 150, TimeUnit.MILLISECONDS);
    }
}
