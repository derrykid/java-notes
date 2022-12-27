# Aims for Part 2
- Loops
- `break` and `continue`
- while loop
- For loop
- what is method
- Method parameters

```
while (expression) {
    // code to be executed
}
```

# Break and continue
Use `break` to exit the loop while use `continue` to return to beginning of the loop

Example of `break`
```java
// count the positive number
int count = 0;
while (true) {
    if (number > 0) {
        count = count + 1;
    } else {
        break;
    }
}
System.out.println("The positive number is " + count);
```


Example of `continue`
```java
// count the ones
int count = 0;

while (true) {
    if (number = 1) {
        count = count + 1;
        continue;
    }
    System.out.println(count);
}
```

# For loop
`while` loop version
```
int i = 0;
while (i < 10) {
    System.out.println(i);
    i++;
}

```

`for` loop version (shorter)
integer `i` disappear after for loop
```
for (int i = 0; i < 10; i++) {
    System.out.println(i);
}
```

# Condition of a loop is evaluated when the execution of a loop starts
**The following code will never stop** 
```
for (int i = 0; i != 100; i++) {
    System.out.println(i);
    i = 100;
    System.out.println(i);
    i = 0;
}
```

# On the structure of Programs using loop
The computation executed after the loop has ended has been implemented inside of the loop. This approach is not recommended as it can easily lead to very complex program structure.

```
while (true) {
    int input = Integer.valueOf(reader.nextLine());

    if (input == 0) {
        System.out.println("Sum of valid numbers: " + sum);
        System.out.println("Valid numbers: " + validNumbers);
        System.out.println("Invalid numbers: " + invalidNumbers);
        break;
    }

    if (input < 0) {
        invalidNumbers++;
        continue;
    }

    sum += input;
    validNumbers++;
}
```

Better this way
```
while (true) {
    int input = Integer.valueOf(reader.nextLine());

    if (input == 0) {
        break;
    }

    if (input < 0) {
        invalidNumbers++;
        continue;
    }

    sum += input;
    validNumbers++;
}

System.out.println("Sum of valid numbers: " + sum);
System.out.println("Valid numbers: " + validNumbers);
System.out.println("Invalid numbers: " + invalidNumbers);
```

When you are writing a program, whether it's an exercise or a personal project, figure out the types of parts the program needs to function and proceed by implementing them one part at a time. Make sure to test the program right after implementing each part.

# Method
Technically speaking, a method is a named set of statements. It's a piece of a program that can be called from elsewhere in the code by the name given to the method.

## custom method
Strictly speaking, program starts at main method.
```
public static void main(String[] args) {
    // code
}
```
You can create a custom method underneath the main method and within the out most curly braces.
```
public static void main(String[] args) {
    // ask the user for the number of times that the phrase will be printed
    // use the while command to call the method a suitable number of times
}

public static void printText() {
    // write some code in here
}
```
Call the custom method is simple: write the name followed by a set of parentheses and the semicolon.
```
public static void main(String[] args) {
    printText();
}

public static void printText() {
    // Write some code in here
}
```

## Method parameters
**Parameters** are values given to a method that can be used in its execution.
The parameters are defined on the uppermost lie of the method within the parentheses following its name.
The values of the parameters that the method can use are copied from the values given to the method when it is executed.

For example:
```
public static void greet(int numOfTimes) {
    int i = 0;
    while (i < numOfTimes) {
        System.out.println("Greetings!");
        i++;
    }
}
```
It has an `int` type parameter called `numOfTimes`
The `greet` method is a parameterized method.

We can call the method with different values.
```
public static void main(String[] args) {
    greet(1);
    System.out.println("");
    greet(3);
}
```

## multiple parameters

A method ca be defined with multiple parameters. When calling, the parameters are passed in the same order.

```
public static void sum(int first, int second) {
    System.out.println("The sum is: " + (first + second));
}
```

Remember: **parameter values are copied in a method call** This means both the main method and the called method can use variables with the same name.
**Changing the value inside the method doesn't change the value in main** 
```
public class Example {
    public static void main(String[] args) {
        int number = 3;
        changeNumber(number);
        System.out.println(number);
    }

    public static void printNumbers(int number) {
        number = number + 5;
    }
}
```
output is
```
3
```

## Methods can return values
**The definition of a method** tells whether that method returns a value of not. If it does, it has to include the type of the returned value, otherwise the keyword `void` is used.

To actual return a value, `return` command is used
```
public static int returnTen(){
    return 10;
}
```
For return value to be used, it must be stored in a variable.
```
public static void main(String[] args) {
    int number = returnTen();
    System.out.print(number);
}
```

## Defining variables inside methods
Defining variable inside methods is done in the same manner as in the "main program"
```java
public static double sum(int number1, int number2) {
    int sum = number1 + number2;
    return sum;
}
```

We can call the method this way:
```
public static void main(String[] args) {
    int a = 3;
    int b = 5;
    System.out.print("The sum is: " + sum(a, b));
}
```
Return value can also be calculated:
```
public static double sum(int number1, int number2) {
    return number1 + number2;
}

```

# Call stack
How does the computer remember where to return after the execution of a method? **The call stack!** 

The environment that executes Java source code keeps track of the method being executed in the call stack. The call stack contains frames, each of which includes information about a specific method's internal variables and their values. When a method is called, a new frame containing its variables is created in the call stack.

We have a main method and a sum method as following:
```
public static void main(String[] args) {
    // code
    sum(3, 6);
}

public static int sum(int number1, int number2) {
    return number1 + number2;
}
```
The call stack of the program looks like this:
```
# First stage
main

# Second stage - when the sum() is called
sum
main

# Third stage - after exec of sum() method
main
```

Once the method `sum()` completes, computer returns to the method that is immediately below the method `sum()` in the call stack - main. `sum()` was removed from the call stack. The execution continues.

## Method calling another method
We can call a method in another method.

For example:
```java
public static int doubleSum(int number1) {
    int doubleSum = 2 * sum(3, 5);
    return doubleSum;
}

public static int sum(int number1, int number2) {
   return number1 + number2; 
}
```
