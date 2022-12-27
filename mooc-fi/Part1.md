# punctuations
- `{}` curly braces
- `()` parentheses

# Print "Hello World"
```java
public class Hello{
    public static void main(String[] args){
        System.out.println("Hello World");
    }
}
```

# Program Bolerplate - file name and program name

`Hello.java` must be the file name for the above program

# Parameter

```
# Hello in double quotes is the parameter that passed here
System.out.println("Hello")
```

# Comments in java
```
// this is one line  comment

/*  this is
    multiple lines
    and more lines
    of comments
*/
```

# Read input by scanner
Input is always read as string
Import scanner util to run
```java
import java.util.Scanner

public class Program{
    public static void main(String[] args) {
        
        // Create a tool for reading input
        Scanner scanner = new Scanner(System.in);

        // Print Hint message
        System.out.println("Write a message");

        String message = scanner.nextLine();

        System.out.println(message);
    }
}
```

# declaration
Once declared, the data type cannot be changed
```java
// declare a string
String mystring = "Hello world!"; // 'mystring' is a variable. It's a container.

// declare a integar
int value = 10;

// boolean
boolean integerAssignmentWillWork = false;
integerAssignmentWillWork = 43; // Doesn't work

// exception - int can be assigned to a double type
double floatingPoint = 0.42;
floatingPoint = 1;

int value = 10;
floatingPoint = value; // this works
```

# name variable
```
// camelCase style
int camelCaseVariable = 1;

// not allowed!
int 7variable = 4; 

// reserved functions or commands aren't allowed
int System.out.print = 4;

// cannot declare twice
int camelCaseVariable = 1;
int camelCaseVariable = 1;

// no exclamation mark or space in use
int !name // not allowed
int the code id // not allowed
```

# Concatenation
```java
// User "+" to concatenate

public class Program{
    public static void main(String[] args) (

        String message = "Hello world!";

        System.out.println(message);

        // or use to join the varible
        System.out.print(message + " is the message!");
    )
}
```

# Reading integers, double, boolean with `$Type.valueOf`
This command, for example, converts a string to an integer.
```
String valueAsString = "42";
int value = Integer.valueOf(valueAsString);

System.out.println(value);

// simple output
42
```
same syntax works for `Double.valueOf` & `Boolean.valueOf`
```
// note that when convert a string to boolean, the string must be true (insensitive case) to print true

System.out.println("Write a boolean ");
boolean value = Boolean.valueOf(scanner.nextLine());

// input "I won't" will print false
// input "True" will print true
```

# Calculation
The basic mathematical operations are as customary in elementary school mathematics
```java
int first = 2;
int second = 5;
System.out.print(first + 3 * second); // print 17
```

# Expression and statement
An *expression* is a combination of values that is turned into another value through a calculation or evaluation. 

```java
int calculation = 1 + 3 * 4 + 2; // the mathematical calc is performed first
```

The evaluation of an expression is always performed before its value is assigned to a variable. As such, the calculation in the example is performed before the result is assigned to the variable.

# Type conversion in expression
```
System.out.println("here is an integer --> " + 2); // the integer 2 will be convert to string 

//type casting

int first = 3;

double result = (double) first / 2;
System.out.print(result); // print 1.5
```

# Condition
```
int number = 11;

if (number > 10) {
    System.out.println("The number is greater than 10.");
    
}

if (number > 20) {
    System.out.println("Your number is greater than 20!");
} else {
    System.out.println("Your number is 20 or less");
}
```

# Comparison operators

- `>` greater than
- `>=` greater than or equal to
- `<` less than
- `<=` less than or equal to
- `==` equal to
- `!=` not equal to

# Logical operators
- and `&&`
- or `||`
- not `!` this not operator flips the result in parentheses
