package org.example.future;

import java.util.concurrent.*;

public class FutureDemo {
    private static final ExecutorService executor = Executors.newFixedThreadPool(10);
    public static void main(String[] args) {
        Future<String> future = executor.submit(() -> "Hi");

        String result = null;
        try {
            result = future.get();
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        // use timeout. if it's longer than the provided param, an exception is thrown
        try {
            result = future.get(200, TimeUnit.MILLISECONDS);
        } catch (ExecutionException | InterruptedException | TimeoutException e) {
            throw new RuntimeException(e);
        }

    }

    private static void useToValidateFuture() {
        Future<String> future = executor.submit(() -> "Hi");
        boolean isDone = future.isDone();
        boolean canceled = future.cancel(true);
        boolean isCancelled = future.isCancelled();
    }
}
