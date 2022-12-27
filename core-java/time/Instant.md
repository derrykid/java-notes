[details of time API](https://stackoverflow.com/questions/32437550/whats-the-difference-between-instant-and-localdatetime/32443004#32443004) 

# Create an Instant

In package (java.time.Instant)

The instant is defined as an offset since the origin (called an epoch), since 1st Jan, 1970.

`Instant now = Instant.now()`

## Access Instant

- getEpochSecond()
- getNano()

## Calculation methods

- plusSeconds()
- plusMillis()
- plusNanos()
- minusSeconds()
- minusMillis()
- minusNanos()

```java
Instant now = Instant.now();
Instant later = now.plusSeconds(3);
```

## Instant vs LocalDateTime

Instant and LocalDateTime are two entirely different animals: One represents a moment, the other does not.

- `Instant` represents a moment, a specific point in the timeline.
- `LocalDateTime` represents a date and a time-of-day. But lacking a time zone or offset-from-UTC, **this class cannot represent a moment.** Its value is inherently ambiguous.
