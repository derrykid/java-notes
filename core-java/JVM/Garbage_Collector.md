# Garbage Collection
- Tracks each and every object available in heap space, and remove unused ones.


## Advantages and Disadvantages

*Advantages*:
- No manual memory handling
- No overhead of handling *dangling pointer*
- automatic memory leak management

*Disadvantages*:
- Require more CPU power because JVM has to keep track of object reference
- Programmers no control over CPU time for freeing objects
- Some GC implementations might stop program unpredictably
- Automate memory management won't be as efficient as a customize GC.

## GC steps
1. Mark - identifies which pieces of memory are in use and which aren't
2. Sweep - removes objects identified during the "mark" phase

### Mark Phase

![](images/gc-mark-phase.png)

Objects that are accessible from the threads, native handles, and other GC root sources are marked as live. Every object tree has more than one root objects. GC root is always reachable. So any object that has a garbage collection root at its root. It identifies and marks all objects that are in use, and the remaining can be considered garbage.

### Sweep phase

In this phase, the heap is traversed to find the gap between the live objects. These gaps are recorded in the free list and are available for new object allocation.

## GC implementations

- Serial Garbage collector
- Parallel Garbage collector
- Concurrent mark sweep (CMS) garbage collector 
- G1 Garbage collector
- Z Garbage collector

### Serial Garbage Collector

- Work with a single thread
- Freeze program when it runs

```java
java -XX:+UseSerialGC Application.java
```

### Parallel Garbage Collector (throughput collector)

- Defalut GC of JVM from Java 5 to Java 8.
- Use multiple threads for managing heap space
- Freeze other application threads while GC

We can specify maximum GC thread and pause time, throughput, and footprint(heap  size).

- GC threads setting: `-XX:ParallelGCThreads=<N>`
- Max pause time of milliseconds `-XX:MaxGCPauseMillis=<N>`
- Time spend in GC, and non-GC is called the *maximum throughput target*: `-XX:GCTimeRatio=<N>`
- Min heap footprint: `-Xms<N>`
- Max heap footprint: `-Xmx<N>`

Set parallel GC with minimum 300 mb and maximum 1024 mb for GC
```java
java -XX:+UseParallelGC -Xms300m -Xmx1024m Main.jav
```

## CMS Garbage collector

Deprecated since Java 9, completely remove since Java 14.

CMS GC uses multiple GC threads. It's for shorter GC pauses, and the resources are enough to share between main program and GC.

- Response slower on average but still responding to perform GC.
- This GC is concurrent, an explicit invocation of `System.gc()` while concurrent process is working, might result in `Concurrent Mode Failure / Interruption`.

```
java -XX:+UseParNewGC Main.java
```

## G1(Garbage First) Garbage Collector 

- Design for running on multi-processor machines with large memory space
- Available from JDK7
- Aim to replace CMS collector

G1 partitions the heap into a set of equal-sized heap regions. Each a contiguous range of virtual memory. When performing GC, G1 shows a concurrent global marking phase (i.e. phase 1 known as *Marking*) to determine the liveness of objects

After the mark phase is complete, G1 knows which regions are mostly empty. It collects in these areas first, which usually yields a significant amount of free space (i.e. phase 2, known as *Sweeping*). That's why this method of garbage collection is called *Garbage-First*.

```java
java -XX:+UseG1GC Main.java
```

## String deduplication parameter 

> Java 8u20 has introduced one more JVM parameter for reducing the unnecessary use of memory by creating too many instances of the same String. This optimizes the heap memory by removing duplicate String values to a global single `char[]` array.

```java
java -XX:+UseStringDeduplication Main.java
```

## Z Garbage Collector

- Scalable low-latency GC that debuted in Java 11 as an experimental option for LInux.
- JDK 14 introduce ZGC under Windows and macOS.
- ZGC obtained production status from Java 15 onwards

ZGC performs all expensive work concurrently, **without stopping the execution of application threads for more than 10 ms**, which makes it suitable for applications that require low latency. It uses **load barriers with colored pointers** to perform concurrent operations when the threads are running, and they're used to keep track of heap usage.

Reference coloring (colored pointers) is the core concept of ZGC. It means that ZGC uses some bits (metadata bits) of reference to mark the state of the object. It also **handles heaps ranging from 8MB to 16TB in size**. Furthermore, pause times don't increase with the heap, live-set, or root-set size.

```java
// For JDK lower than 15:
java -XX:+UnlockExperimentalVMOptions -XX:+UseZGC Main.java

// JDK 15 onward
java -XX:+UseZGC Application.java
```

## Shenandoah Garbage Collector (Shenandoah GC)

Similar to ZGC, Shenandoah GC aims to provide low-latency garbage collection by performing most of its work *concurrently*. It's designed to keep pause times predictable and consistent, making it suitable for applications that require high responsiveness.