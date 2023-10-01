[toc]
# Operators

1. Unary operators - `expr++`
2. Binary operators - `a * b`
3. Ternary operators - `expr ? a : b`

## Operator precedence

The table is listed in decresing precedence:

| Operator                      | Symbols                                | Example                |
|-------------------------------|----------------------------------------|------------------------|
| Pre-unary op / Post-unary op  | expr++, ++expr                         | i++, ++i               |
| Unary op                      | +,-,!,~, (type)                        | !true, -a              |
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

### Type Casting 

Compiler will automatically cast smaller data type to larger data type
```java
int a = (short) 3; // works
```

It works in method too.
```java
public static void main(String[] args) {
    add((long) 33, 12.0f);
}

static void add(double a, float b) {
    System.out.println(a);
    System.out.println(b);
}
```

However, larger to smaller data types will throw compilation error
```java
float egg = 2.0 / 9; // assign double to float, error
```

#### Casting primitive types
> Remember, casting primitives is required any time you are going from a larger numerical data type to a smaller numerical data type, or converting from a floating-point number to an integral value.

```java
int trainer = (int)1.0;
short ticketTaker = (short)1921222; // Stored as 20678
int usher = (int)9f;
long manager = 192301398193810323L;
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

### Compound operator

It's not only a shorthand form of binary operators. 

Compound operator will implicitly cast the data type to the left hand side type;

```java
long a = 313;
short b = 12;

b = b * a;  // compiler error, assign long to short

// however, this works
b *= a;     // compound operator implicitly prompts the b to short
```

### Equality operator

| operator | prmitives                  | object                          |
|----------|----------------------------|---------------------------------|
| ==       | true if 2 same values      | true if 2 same referred objects |
| !=       | true if 2 different values | true if 2 different objects     |

Note here, the same objects mean: **they referred to the same object in the memory address. Not the same value.** 
```java
File monday = new File("schedule.txt");
File tuesday = new File("schedule.txt");
File wednesday = tuesday;
System.out.println(monday == tuesday);      // false
System.out.println(tuesday == wednesday);   // true
```

Compare null is legal
```java
boolean v = (null == null); // true
```

### Relational operator (instanceof)

| Operator       | Description                                                                  |
|----------------|------------------------------------------------------------------------------|
| >, >=, <, <=   | similar to math comparison                                                   |
| a instanceof b | true if a is the *subclass, or class, or implementation of b interface, etc* |

```java
var a = (null instanceof Number); // false
var b = (null instanceof null);   // compilation error
```

### Logical operator

| Operator | Description                                       |
|----------|---------------------------------------------------|
| &        | AND, both side true                               |
| \|       | OR, one side ture                                 |
| ^        | XOR, return true if ( one is true, another false) |

#### Short-circuit operators

| Operators | Description                                                                 |
|-----------|-----------------------------------------------------------------------------|
| &&        | Similar to `&`, but if left side is false, right side will not be evaluated |
| \|\|      | Similar to `\|`, but if left side is true, right side will not be evaluated |

The short-circuit operators are good for checking the null before operations.
```java
// could throw NPE at runtime, because both side are evaluated
if (duck != null & duck.getAge() > 5) { 
    // code
}

// won't throw NPE at runtime, because right hand side won't be evaluated
if (duck != null && duck.getAge() > 5) {
    // code
}
```

## Ternary operator

Takes 3 operands:
```java
booleanExpression ? expr_1 : expr_2 ;
```

There's no restriction on what to be returned.
```java
// example 1
int v = 3;
int x = (v > 0) ? 2 : 1;

// example 2
System.out.println( (v > 3) ? 2 : "no"); // return string "no"

// example 3
int animal = (stripes < 9) ? 3 : "Horse"; // DOES NOT COMPILE
```

It's possible that 2 ternary operators are used:
```java
int a = 2, b = 4, c = 2;
System.out.println(a > b ? b < c ? b : 2 : 1);

// better readability
System.out.println(a > b ? ( b < c ? b : 2 ) : 1);
```

# Automatically casting with arithmetic binary operations

> After all promotion has occurred and the operands have the same data type, the resulting value will have the same data type as its promoted operands

## Small data type prompts to `int`

> Smaller data types, namely, byte, short, and char, are first promoted to int any time they’re used with a Java binary arithmetic operator, even if neither of the operands is int.

- Every operation will automatially prompt to `int`, solution:
    - declare it as `int` or any primitive type with more bits `long, float, double`
    - explicitly cast it
```java
short a = 3;
short b = 4;
short sum = 3 + 4; // not compile, cuz it prompts to int

// solution
int sum = 3 + 4;
short sum = (short) (3 + 4);
```

## Others prompt to larger data type

> If two values have different data types, Java will automatically promote one of the values to the larger of the two data types


```java
// ex1
int a = 13;
long b = 20;
var z = a * b; // z is type long

// ex2
short w = 14;
float x = 13;
double y = 20;
var z = w * x + y; // z is type double
```
