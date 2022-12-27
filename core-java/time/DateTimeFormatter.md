# DateTimeFormatter

It's used to parse and format dates.


Let's see what and how it works at first:
Example 1: LocalDate
```java
DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE;

String formattedDate = formatter.format(LocalDate.now());

System.out.println(formattedDate);
```

Output: represents the year 2022, month 07, and the day 10
```
20220710
```

---

Example 2: ZonedDateTime
```java
DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE;

String formattedZonedDate = formatter.format(ZonedDateTime.now());
System.out.println(formattedZonedDate);
```

Output: Like the example 1, however the time zone info is +0800 (UTC+8)
```
20220710+0800
```

## About the class

This class contains a set of predefined (constant) instances which can parse and format dates from standard date formats.

There are:
```java
BASIC_ISO_DATE

ISO_LOCAL_DATE
ISO_LOCAL_TIME
ISO_LOCAL_DATE_TIME

ISO_OFFSET_DATE
ISO_OFFSET_TIME
ISO_OFFSET_DATE_TIME

ISO_ZONED_DATE_TIME

ISO_INSTANT

ISO_DATE
ISO_TIME
ISO_DATE_TIME

ISO_ORDINAL_TIME
ISO_WEEK_DATE

RFC_1123_DATE_TIME
```
