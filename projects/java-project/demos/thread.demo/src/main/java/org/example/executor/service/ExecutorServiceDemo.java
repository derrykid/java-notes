package org.example.executor.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

/**
 *  {@link https://www.baeldung.com/java-executor-service-tutorial}
 */
public class ExecutorServiceDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Set<Callable<String>> callables = new HashSet<Callable<String>>();

        callables.add(new Callable<String>() {
            public String call() throws Exception {
                return "Task 1";
            }
        });
        callables.add(new Callable<String>() {
            public String call() throws Exception {
                return "Task 2";
            }
        });
        callables.add(new Callable<String>() {
            public String call() throws Exception {
                return "Task 3";
            }
        });

        String result = executorService.invokeAny(callables);

        System.out.println("result = " + result);

        executorService.shutdown();
    }

    // directly create
    private static void directCreate() {
        ExecutorService executorService = new ThreadPoolExecutor(
                1, 1, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>());
    }


    // use factory method to create an executor
    private static void useFactory() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
    }

    // execute() method signature void: no return type
    private static void useExecute() {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        executor.execute(()-> {
            System.out.println("Hello");
        });
    }

    // submit() method returns a Future
    private static void useSubmit(){
        ExecutorService executor = Executors.newFixedThreadPool(10);
        Future<String> future = executor.submit(() -> {
            System.out.println("Hi");
            return "Yo";
        });
    }

    // invokeAny(Collection<Callable>): a successful execution of one single task(if there is)
    // invokeAll(Collection<Callable>): List<Future<?>>, all tasks executions in the form of a list of objects
    private static void useInvoke() throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        Callable<String> callableTask = () -> {
          TimeUnit.MILLISECONDS.sleep(300);
          return "Task executed";
        };
        List<Callable<String>> callableList = new ArrayList<>();
        callableList.add(callableTask);
        callableList.add(callableTask);
        callableList.add(callableTask);

        // invoke any
        String result = executor.invokeAny(callableList);

        // invoke all
        List<Future<String>> futures = executor.invokeAll(callableList);
    }

    // it stops accepting new tasks, and will stop JVM when all done
    // the proper way to shutdown will display in useProperShutDown()
    private static void useShutDown() {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        executor.execute(() -> {
            System.out.println("Hi");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        executor.shutdown();
    }

    private static void useShutDownNow() {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        executor.execute(() -> {
            System.out.println("hi");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        List<Runnable> notExecutedTasks = executor.shutdownNow();
    }

    private static void useProperShutDown() {
        // this is recommended by Oracle
        ExecutorService executor = Executors.newFixedThreadPool(10);
        executor.execute(() -> {
            System.out.println("Hi");
        });

        executor.shutdown();

        try {
            if (!executor.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
    }

}
















