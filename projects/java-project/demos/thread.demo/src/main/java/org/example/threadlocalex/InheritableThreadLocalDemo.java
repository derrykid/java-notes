package org.example.threadlocalex;

public class InheritableThreadLocalDemo {
    public static void main(String[] args) {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();

        Thread thread1 = new Thread(() -> {
            System.out.println("===Thread 1 ===");
            threadLocal.set("thread local String");
            inheritableThreadLocal.set("inheritance string for child");

            System.out.println(threadLocal.get());
            System.out.println(inheritableThreadLocal.get());

            Thread childThread = new Thread(() -> {
                System.out.println("===Child thread===");
                System.out.println(threadLocal.get());
                System.out.println(inheritableThreadLocal.get());
            });
            childThread.start();

        });

        thread1.start();


        // thread 2
        Thread thread2 = new Thread(() -> {

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println("===Thread 2 ===");
            System.out.println(threadLocal.get());
            System.out.println(inheritableThreadLocal.get());
        });
        thread2.start();

    }
}
