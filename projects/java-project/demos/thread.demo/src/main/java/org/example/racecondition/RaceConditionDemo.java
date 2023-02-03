package org.example.racecondition;

public class RaceConditionDemo {


    public static void main(String[] args) {
        Counter counter = new Counter();

        Runnable run1 = () -> {
            for (int i = 0; i < 1_000; i++) {
                counter.inc();
            }
            System.out.println("Run1: " + counter.getCount());
        };

        Runnable run2 = () -> {
            for (int i = 0; i < 1_000; i++) {
                counter.inc();
            }
            System.out.println("Run2: " + counter.getCount());
        };

        Thread thread1 = new Thread(run1);
        Thread thread2 = new Thread(run2);

        thread1.start();
        thread2.start();

    }
}
