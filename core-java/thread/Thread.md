We can create multiple thread in a program.

**Methods to remember** 
1. `ThreadClassName thread1 = new ThreadClassName();`
2. `MyRunnable runnable1 = new MyRunable;` 

    `Thread thread2 = new Thread(runnable1)`

3. `thread1.start();`
4. `thread1.join();` - then `thread2` can join the program.
5. `Thread.sleep(ms);` - in the class, we can use this to `sleep` the program

The benefits are: 

When one thread terminates, other threads will not be affected.

The **main method** is a thread running. We can create another thread in main and when the **main** thread has error and stops, the other thread will still run.

People tend to use `Runnable` way since it can `extends` other superclass


-------

Main program
```java
public static void main (String[] args) {

    // create thread by first way
    MyThread thread1 = new MyThread();

    // create a thread by the second way
    MyRunnable runnable1 = new MyRunnable;
    Thread thread2 = new Thread(runnable1);

}
```


Way to create thread in Java

1. Create a class that `implements Runnable`
```java
public class MyRunnable implements Runnable{

	@Override
	public void run() {
		
		for(int i =0;i<10;i++) {
			System.out.println("Thread #2 : "+i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Thread #2 is finished :)");
	}
}
```

2. Create a class extends `Thread` interface
```java
public class MyThread extends Thread {

    @Override
    public void run() {
        		
		for(int i =10;i>0;i--) {
			System.out.println("Thread #1 : "+i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Thread #1 is finished :)");
    }
}
```


## Read / Write before guarantee


**If read the volatile variable, put it in the first.** Imagine that CPU wants to read something, it goes to the RAM, and know it also want other value. So CPU **by the way** take those value along with it.
```java
private volatile int days
private int month;
private int year;


public int get() {

    int total = this.days;  // read the volatile variable
    total += months * 30;
    total += year * 365;

    return total;
}

```

Write, put the volatile variable at the last.
**Put the write volatile variable at last.** Imagine CPU wants to write something back to RAM, it will be nicer to take other things with it, make it more effective. If we put the volatile at first, it doesn't know what will happen next, when it comes back from RAM, it finds out the other variables are not volatile, so it will just keep it in cache
```java
public void set(int year, int month, int days) {

    this.year = year;
    this.month = month;
    this.days = days;
}
```
