package org.example.threadlocalex;

public class MyRunnableForThreadLocal implements Runnable {

    private ThreadLocal<Integer> threadLocal = new ThreadLocal<>();


    @Override
    public void run() {
        threadLocal.set((int) (Math.random() * 100D));

        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        System.out.println(threadLocal.get());

    }
}
