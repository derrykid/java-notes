package org.example.blockqueue.demo;

import java.util.concurrent.BlockingQueue;
import java.util.stream.IntStream;

public class ThreadObjectConsumer implements Runnable {

    private BlockingQueue<Integer> queue;
    private final int poisonPill;

    public ThreadObjectConsumer(BlockingQueue<Integer> queue, int poisonPill) {
        this.queue = queue;
        this.poisonPill = poisonPill;
    }


    @Override
    public void run() {
        try {
            while (true) {
                Integer number = queue.take();
                if (number.equals(poisonPill)) {
                    return;
                }
                System.out.println(Thread.currentThread().getName() + " result: " + number);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
