package org.example.threadpoolex;

public class ThreadPoolMainDemo {

    public static void main(String[] args) throws Exception {
        ThreadPoolDemo threadPoolDemo = new ThreadPoolDemo(3, 10);

        for (int i = 0; i < 10; i++) {
            int taskNo = i;
            threadPoolDemo.execute(() -> {
                String message = Thread.currentThread().getName() + ": Task " + taskNo;
                System.out.println(message);
            });
        }

        threadPoolDemo.waitUntilAllTasksFinished();
        threadPoolDemo.stop();

    }
}
