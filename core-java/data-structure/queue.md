`Queue` interface is helpful to use for *First in first out*. 

```java
Queue<String> waitingQueue = new LinkedList<>();

waitingQueue.add("Jack");
waitingQueue.add("Will");
waitingQueue.add("Ray");

waitingQueue.peek(); // this will see the first element of the queue
waitingQueue.poll(); // this will remove the first element
System.out.println(waitingQueue.poll()); // remove another element again, it also prints out "Will"
```

* `waitingQueue.size()` - get size
* `waitingQueue.toArray()` - get array
