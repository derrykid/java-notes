package org.example.createthreads;

public class MyRunnable implements Runnable{
    @Override
    public void run() {
        System.out.println("Hello from my runnable");
    }
}
