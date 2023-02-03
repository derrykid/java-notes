package org.example.threadlocalex;

public class ThreadLocalDemo {

    public static void main(String[] args) throws InterruptedException {
        MyRunnableForThreadLocal sharedRunnableInstance = new MyRunnableForThreadLocal();

        Thread thread1 = new Thread(sharedRunnableInstance);
        Thread thread2 = new Thread(sharedRunnableInstance);

        thread1.start();
        thread2.start();

        thread1.join(); //wait for thread 1 to terminate
        thread2.join(); //wait for thread 2 to terminate
    }

}
