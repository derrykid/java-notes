# LocalTime

Immutable object

Represent a specific time, e.g. 8 a.m. there, without time zone info.

```java
LocalTime localTime = LocalTime.now();

// or create a specific object
LocalTime localTime2 = LocalTime.of(21, 30, 59, 11001);
```

## Access the value

- getHour()
- getMinute()
- getSecond()
- getNano()

## Calculation

- plusHours()
- plusMinutes()
- plusSeconds()
- plusNanos()
- minusHours()
- minusMinutes()
- minusSeconds()
- minusNanos()

```java
LocalTime localTime = LocalTime.now();

LocalTime later = localTime.plusHours(3);
```
