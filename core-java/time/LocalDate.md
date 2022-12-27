# LocalDate

- no time zone
- no hr, seconds, etc

A date relates to a specific day of the year.

```java
LocalDate localDate = LocalDate.now();

// or create one
LocalDate localDate2 = LocalDate.of(2015, 12, 31);
```

## Access the LocalDate info

- getYear(): int
- getMonth(): Month
- getDayOfMonth(): int
- getDayOfWeek(): DayOfWeek
- getDayOfYear(): int

## Calculation

- plusDays()
- plusWeeks()
- plusMonths()
- plusYears()
- minusDays()
- minusWeeks()
- minusMonths()
- minusYears()
