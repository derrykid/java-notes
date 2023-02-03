package org.example.threadpoolex;

import java.util.concurrent.BlockingQueue;

public class PoolThreadRunnable implements Runnable {

    private Thread thread = null;
    private BlockingQueue queue = null;
    private boolean isStopped = false;

    public PoolThreadRunnable(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        this.thread = Thread.currentThread();
        while (!isStopped) {
            Runnable runnable;
            try {
                runnable = (Runnable) queue.take();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            runnable.run();
        }
    }

    public synchronized void doStop() {
        isStopped = true;
        this.thread.interrupt();
    }

    public synchronized boolean isStopped() {
        return this.isStopped;
    }


}
