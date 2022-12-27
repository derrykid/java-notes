# Java pipes

Pipes in Java IO provides the ability for **two threads running in the same JVM to communicate.** Therefore pipes can also be sources or destinations of data.

## Difference to Unix / Linux pipes

> The pipe concept in Java is different from the pipe concept in Unix / Linux, where two processes running in different address spaces can communicate via a pipe. In Java, the communicating parties must be running in the same process, and should be different threads.

## Creating pipes

Creating a pipe using Java IO is done via the PipedOutputStream and PipedInputStream classes. A PipedInputStream should be connected to a PipedOutputStream. The data written to the PipedOutputStream by one thread can be read from the connected PipedInputStream by another thread.

```java
public class PipeExample {

    public static void main(String[] args) throws IOException {

        final PipedOutputStream output = new PipedOutputStream();
        final PipedInputStream  input  = new PipedInputStream(output);


        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    output.write("Hello world, pipe!".getBytes());
                } catch (IOException e) {
                }
            }
        });


        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    int data = input.read();
                    while(data != -1){
                        System.out.print((char) data);
                        data = input.read();
                    }
                } catch (IOException e) {
                }
            }
        });

        thread1.start();
        thread2.start();

    }
}

```

## Is it necessary to use pipes?

There are many other ways than pipes that threads can communicate within the same JVM. In fact, threads more often exchange complete objects rather than raw byte data. But - if you need to exchange raw byte data between threads, Java IO's pipes are a possibility.

## `PipedInputStream`

Since PipedInputStream is a subclass of InputStream, PipedInputStream has the same basic methods and use patterns as an InputStream.

```java
InputStream input = new PipedInputStream(pipedOutputStream);

int data = input.read();
while(data != -1) {
  //do something with data...
  doSomethingWithData(data);

  data = input.read();
}
input.close();
```

## PipedOutputStream

Since PipedOutputStream is a subclass of OutputStream, PipedOutputStream has the same basic methods and use patterns as an OutputStream.

```java
OutputStream output = new PipedOutputStream(pipedInputStream);

while(moreData) {
  int data = getMoreData();
  output.write(data);
}
output.close();
```
