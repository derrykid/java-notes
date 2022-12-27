# ZonedDateTime

A date and time with time zone information.

It's an immutable object.

```java
ZonedDateTime zonedDateTime = ZonedDateTime.now();
```

Create a custom one with ZoneId
```java
ZoneId zoneId = ZoneId.of("UTC+1");

ZonedDateTime zonedDateTime2 =
    ZonedDateTime.of(2015, 11, 30, 23, 45, 59, 1234, zoneId);
```

Print out an instance:
```
2022-07-10T21:08:31.183822302+08:00[Asia/Taipei]
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

Some of these methods return an enum, and others return an int. From the enums you can return an int representation of their value using their getValue() methods. For instance:

## Date and time calculations

- plusYears()
- plusMonths()
- plusDays()
- plusHours()
- plusMinutes
- plusSeconds
- plusNanos()
- minusYears()
- minusMonths()
- minusDays()
- minusHours()
- minusMinutes
- minusSeconds
- minusNanos()

Be aware that calculations that span across the **daylight savings changes** (start or end) may not give the result you expect! An alternative is to use a Period instance, like this:

```java
ZonedDateTime newZoneDateTime =
    previousDateTime.plus(Period.ofDays(3));
```

This should result in a more correct calculation.

## Time Zones

You can create or get `ZoneId` by:
```java
ZoneId id = ZoneId.of("UTC+1");

// or
ZoneId id = ZonedDateTime.now().getZone();
```

In the example above the ID is "UTC+1" which is an offset from UTC (Greenwich) time.

You can also use another type of time zone id which consists of the name of the location where the time zone is active. Here is an example:
```java
ZoneId zoneId2 = ZoneId.of("Europe/Copenhagen");

ZoneId zoneId3 = ZoneId.of("Europe/Paris");
```

Specify a proper time zone name in the format of Continent/Region, such as America/Montreal, Africa/Casablanca, or Pacific/Auckland. Never use the 2-4 letter abbreviation such as EST or IST as they are not true time zones, not standardized, and not even unique(!).
