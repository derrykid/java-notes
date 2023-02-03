package org.example.blockqueue.demo;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class ThreadObjectProducer implements Runnable {

    private BlockingQueue<Integer> numberQueue;
    private final int poisonPill;
    private final int poisonPillPerProducer;
    

    public ThreadObjectProducer(BlockingQueue<Integer> queue, int poisonPill,
                                int poisonPillPerProducer) {
        this.numberQueue = queue;
        this.poisonPill = poisonPill;
        this.poisonPillPerProducer = poisonPillPerProducer;
    }

    @Override
    public void run() {
        try {
            generateNumbers();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void generateNumbers() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            numberQueue.put(ThreadLocalRandom.current().nextInt(100));
        }
        for (int i = 0; i < poisonPillPerProducer; i++) {
            numberQueue.put(poisonPill);
        }
    }
}
