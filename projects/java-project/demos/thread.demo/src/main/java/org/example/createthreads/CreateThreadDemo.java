package org.example.createthreads;

public class CreateThreadDemo {

    public static void main(String[] args) {
        // thread subclass
        Thread threadByExthends = new Thread("subclass") {
            public void run(){
                System.out.println("Hello!, this is extends " + getName());
            }
        };


        // runnable instance
        Runnable runnable = new MyRunnable();
        Thread threadByRunnable = new Thread(runnable, "Runnable instance");


        // runnable instance by lambda
        Thread lambdaThread = new Thread(()-> {
            System.out.println("Lambda runnable");
        }, "Lambda one");

        threadByExthends.start();
        threadByRunnable.start();
        lambdaThread.start();

        // main thread name
        String name;
        name = Thread.currentThread().getName();
        System.out.println("Main thread name: " + name);
    }


}
