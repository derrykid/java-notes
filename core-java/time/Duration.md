# Duration

An immutable instance. 

It consists of 2 values:
- nanosecond
- second

Get a duration:
```java
Instant first = Instant.now();
Instant second = Instant.now();

Duration duration = Duration.between(first, second);
```

Access the nanosecond or seconds by:
- getNano()
- getSeconds()

Convert the full time interval `Duration` to other time units:
- toNanos()
- toMillis()
- toMinutes()
- toHours()
- toDays()

**`toNanos()` is different from `getNanos()`.** 

The `getNanos()` returns only the nano second part, it disregard the seconds. On the other hand, `toNanos()` gives you the full time interval.

## Duration Calculation

- plusNanos()
- plusMillis()
- plusSeconds()
- plusMinutes()
- plusHours()
- plusDays()
- minusNanos()
- minusMillis()
- minusSeconds()
- minusMinutes()
- minusHours()
- minusDays()

```java
Duration start = // get object
Duration add = start.plusDays(3);
```
