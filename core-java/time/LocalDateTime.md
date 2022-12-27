# LocalDateTime

Immutable object

Think it as a combination of `LocalDate` & `LocalTime`

```java
LocalDateTime localDateTime = LocalDateTime.now();

// or create custome one
LocalDateTime localDateTime2 =
    LocalDateTime.of(2015, 11, 26, 13, 55, 36, 123);
```

## Access the value

- getYear()
- getMonth()
- getDayOfMonth()
- getDayOfWeek()
- getDayOfYear()
- getHour()
- getMinute()
- getSecond()
- getNano()

Some of these methods return an int and some of them return an enum. Via the methods that return an enum you can get an int representation of the enum by calling the getValue() of the enum .

## DateTime calculations

- plusYears()
- plusMonths()
- plusDays()
- plusHours()
- plusMinutes()
- plusSeconds()
- plusNanos()
- minusYears()
- minusMonths()
- minusDays()
- minusHours()
- minusMinutes()
- minusSeconds()
- minusNanos()

```java
LocalDateTime localDateTime  = LocalDateTime.now();

LocalDateTime localDateTime1 = localDateTime.plusYears(3);
```
