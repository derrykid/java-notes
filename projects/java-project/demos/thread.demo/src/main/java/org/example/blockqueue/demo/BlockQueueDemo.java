package org.example.blockqueue.demo;

import javax.swing.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BlockQueueDemo {

    public static void main(String[] args) throws InterruptedException {

        int bound = 100;
        int producers = 4;
        int consumers = Runtime.getRuntime().availableProcessors(); // 4
        int poisonPill = -1;
        int poisonPillPerProducer = consumers / producers; // 1
        int mod = consumers % producers; // 0

        // if want to have bound, pass value param
        BlockingQueue<Integer> blockingQueue = new LinkedBlockingQueue<>(bound);

        for (int i = 1; i < producers; i++) {
            new Thread(new ThreadObjectProducer(blockingQueue, poisonPill, poisonPillPerProducer))
                    .start();
        }

        for (int i = 0; i < consumers; i++) {
            new Thread(new ThreadObjectConsumer(blockingQueue, poisonPill)).start();
        }

        new Thread(new ThreadObjectProducer(blockingQueue, poisonPill, poisonPillPerProducer + mod)).start();

    }

}
