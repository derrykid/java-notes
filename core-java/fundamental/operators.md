[toc]
# Operators

1. Unary operators - `expr++`
2. Binary operators - `a * b`
3. Ternary operators - `expr ? a : b`

## Operator precedence


| Operator                      | Symbols                                | Example                |
|-------------------------------|----------------------------------------|------------------------|
| Pre-unary op / Post-unary op  | expr++, ++expr                         | i++, ++i               |
| Unary op                      | +,-,!,~                                | !true, -a              |
| Multiply / Division / Modulus | *, / , %                               | 9 % 2                  |
| Add, subtract                 | +, -                                   | 3 + 4                  |
| Shift op                      | <<, >>                                 |                        |
| Relational op                 | >,<,>=,<=, instanceof                  | john instanceof Driver |
| Equal, not equal              | ==, !=                                 | A != B                 |
| Logical op                    | &, \|, ^                               | a & b                  |
| Short-circuit logical op      | &&, \|\|                               | a && b                 |
| ternary op                    | `expr ? true : false                   | a == 3 ? "Yes" : "No"  |
| Assignment op                 | `=`, `+=, -=, *=, /=, %=, &=, ^=, \|=` | a += 3                 |

## Unary Operator

- Logical complements - `!true`
- negation operator - `a = 3;`, `-a` (it's negative 3)
- Increment, decrement operator - `a++, a--, ++a, --a`
    - Pre-operator - `++a, --a`
    - Post-operator - `a++, a--`
> remember the term: "pre/post operator". If it's pre, then it does the math first. If it's post, then it returns the value first

```java
int a = 3;
int v = ++a;  // v is 4. a is incremented first and returned

int b = 3;
int x = b++;  // x is 3. b is return first and incremented
```

## Binary Operator

> Parentheses overrides the precedence

| Operator | Description |
|----------|-------------|
| +        | add         |
| \-       | subtract    |
| *        | multiply    |
| /        | divide      |
| %        | reminder    |


# Automatically casting with arithmetic operations

- Every operation will automatially prompt to `int`, solution:
    - declare it as `int` or any primitive type with more bits `long, float, double`
    - explicitly cast it
```java
short a = 3;
short b = 4;
short sum = 3 + 4; // error, cuz it prompts to int

// solution
int sum = 3 + 4;
short sum = (short) (3 + 4);
```
