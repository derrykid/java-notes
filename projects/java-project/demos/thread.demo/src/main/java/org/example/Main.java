package org.example;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {

        Runnable runnable = ()-> {
            System.out.println("running!");
        };

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);

        long start = System.currentTimeMillis();

        executorService.scheduleWithFixedDelay(runnable, 1000, 2000, TimeUnit.MILLISECONDS);
//        var get1 = executorService.scheduleAtFixedRate(runnable, 1000, 2000, TimeUnit.MILLISECONDS);

        executorService.execute(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(3000);
                System.out.println("Hi");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        System.out.println(start - System.currentTimeMillis());

    }
}