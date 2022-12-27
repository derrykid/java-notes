In multithreaded applications, queues need to handle multiple concurrent producers-consumers scenarios. **The correct choice of a concurrent queue could be crucial in achieving good performance in our algorithms.** 

## Blocking vs non-blocking queue

BlockingQueue offers a simple **thread-safe mechanism.**  In this queue, threads need to wait for the queue's availability. The producers will wait for available capacity before adding elements, while consumers will wait until the queue is empty. In those cases, the non-blocking queue will either throw an exception or return a special value, like null or false.

To achieve this blocking mechanism, the BlockingQueue interface exposes two functions on top of the normal Queue functions: put and take. Those functions are the equivalent of add and remove in a standard Queue.

Methods in short: 
use:
- put() for adding
- take() for retrieving

### Concurrent queue implementations

#### ArrayBlockingQueue 

Fixed size. Good for if memory is an issue.

#### LinkedBlockingQueue

unbound, or bound. LinkedList needs to allocate and de-allocate nodes every time an item is added or removed. For this reason, ArrayBlockingQueue can be better alternative sometimes. The performance is said to be unpredictable.

#### PriorityBlockingQueue

Use when we need to consume items in a specific order.

#### DelayQueue

#### LinkedTransferQueue

#### SynchronousQueue

**This will always have, at most, one single item.** We use this as a simple way to **exchange some data between 2 threads.** 

#### ConcurrentLinkedQueue
