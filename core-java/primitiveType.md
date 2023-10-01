| Keyword | Type                        | Example |
|---------|-----------------------------|---------|
| boolean | trur of false               | true    |
| byte    | 8-bit integral value        | 123     |
| short   | 16-bit integral value       | 123     |
| int     | 32-bit integral value       | 123     |
| long    | 64-bit integral value       | 123L    |
| float   | 32-bit floating-point value | 123.45f |
| double  | 64-bit floating-point value | 123.456 |
| char    | 16-bit unicode value        | 'a'     |

Java treat literals as int:
```java
// won't compile, compiler treat it as int, but the literal is larger than int.
long rep = 12312331123123213; 

// unless this fix, append a L to it, compiler knows it's long
long rep = 12312331123123213L; 
```

## Apply casting

> Remember, casting primitives is required any time you are going from a larger numerical data type to a smaller numerical data type, or converting from a floating-point number to an integral value.

```java
int trainer = (int)1.0;
short ticketTaker = (short)1921222; // Stored as 20678
int usher = (int)9f;
long manager = 192301398193810323L;
```
