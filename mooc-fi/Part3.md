# Guide for Part3
- Discovering errors
- Lists
- ArrayList Using strings
- Summary

Concentrating on a specific part of a study exercise can lead to relevant information being filtered out.

# Commenting the source code
Comments have many purposes, and one of them is explaining how the code works to oneself when searching for bugs.

# Searching for Errors with Print Debugging
When a program is executed multiple times with appropriate inputs the hidden error is often found. Coming up with relevant inputs is a skill in its own right. It's essential to test the so-called corner cases, i.e., circumstances where the program execution could be exceptional.


# Arraylist
Include the command
```
import java.util.ArrayList;
```

Creating a new list by:
ArrayList<type> list = new ArrayList<>();
```
public static void main(String[] args) {
    ArrayList<String> list = new ArrayList<>();
}
```

Arraylist manipulation
```
ArrayList<String> wordList = new ArrayList<>();

// add to array
wordList.add("First");
wordList.add("Second");


// get array value - use index
wordList.get(1);

// get array size
wordList.size();

```
And remove value from an array
Parameter given to remove is the same type as the values in the list, but not integer.
```
// remove by index
list.remove(2); // this will remove the third index in the array

// Arraylist of String type
list.add("First");
list.add("Second");
list.add("Third");

list.remove("First");

System.out.println(list.get(0)); // print second
```
You cannot use **Integer** as parameter to remove the value in arraylist. Instead, you have to use convert the parameter to Integer type, achieved by `valueOf` method.
```
list.add(15);
list.add(293);
list.add(23);
list.add(82);

list.remove(Integer.valueOf(15)); // remove the first element and all keys will be re-ordered.
```
use `contain` method to check the existence of a value. It will return boolean
```
list.add("First");
list.contains("First); // true;
```

# Use arraylist as method parameter
It's the same way we define other methods, we can simply call it by pass the arraylist name as parameter.
**Notice that the name of variable in main and method can be different as well** 
```
public static void print(ArrayList<String> list) {
    for (String value: list) {
        System.out.println(value);
    }
}

public static void main(String[] args) {
   ArrayList<String> strings = new ArrayList<>();
   strings.add("First");
   strings.add("Second");
   
   print(strings);
}
```
Each list is its own separate entity, and the list methods always concern the list that was used to call the method.

# On copying the list to a method parameter
[I don't really understand this part, I will come back later. This article might help later too.](https://stackoverflow.com/questions/40480/is-java-pass-by-reference-or-pass-by-value)
When a list (or any reference-type variable) is copied for a method's use, the method receives the value of the list variable, i.e., a reference. In such a case the method receives a reference to the real value of a reference-type variable, and the method is able to modify the value of the original reference type variable, such as a list. In practice, the list that the method receives as a parameter is the same list that is used in the program that calls the method.
```
ArrayList<Integer> numbers = new ArrayList<>();
numbers.add(3);
numbers.add(2);
numbers.add(6);
numbers.add(-1);

System.out.println(numbers);

removeFirst(numbers);

System.out.println(numbers);

removeFirst(numbers);
removeFirst(numbers);
removeFirst(numbers);

System.out.println(numbers);

// Output
//[3, 2, 6, -1]
//[2, 6, -1]
//[]
```

# For each loop
If you don't need to keep track of the index, you can make use of the **for-each** loop.
The syntax is `for (Type nameOfVariable: nameOfList)`
where the `Type` is the element type of the Arraylist. `nameOfVariable` is the variable that is used to store each value.

Example:
```
// it will literate every index and prints out every name in the array
for (String name: list) {
    System.out.print(name);
}
```

# array    
```    
array[0] = 31; // 0 is the indices, also call index 0    
array[1] = 23;    
```    
    
    
# Creating an array    
```    
// create an array to hold three integers    
int[] numbers = new int[3];    
    
// an array to hold 5 strings    
String[] strings = new String[5];    
```    
# size of an array and iterating    
Use associated variable `length`. It's not a method, so `name.length` will not work
```    
int[] numbers = new int[2];    
numbers[0] = 42;    
numbers[1] = 13;    
    
System.out.println("The array has " + numbers.length + " elements."); // The array has 4 elements.
```    

# Array as a parameter of a method
Use arrays as a parameter of a method like any other variable. **An array is a reference type,** the value of the array is a reference to the information to the information contained in the array.

**Array is an object, so when you change the array inside the method, the changes persist after the execution of the method**.

# Shorter way to create an array
```
int[] numbers = {100, 2, 32};
```
This works for all types.

# Splitting a String
We can split a string to multiple pieces with the `split` method of the String class. The method returns an array of the resulting sub-parts. We can specify the parameter in the method.

It will be very useful when **parsing csv file.** 
```
String text = "Frist, Second, Third";
String[] pieces = text.split(" "); // Space is the delimiter/parameter at play
System.out.println(pieces[0]); // print First
System.out.println(pieces[1]); // print Second
```

Split a string always produces an array of strings

# Search an string in the array
We can use `text.contains("string")` method to check if a string exist:
```
String text = "first, second, third";
text.contains("irs"); // return true
```

# Use method for array element
If we want to use method to specific array index:
```
// check if it contains a string
String[] pieces = input.split(" ");
pieces[i].contains("string"); //  return boolean

// print the numberth character
String text ="Hello!";
char character = text.charAt(0); // charater is "H"
```

# String character length
```
// know the string length of a variable
String text = "ABCDEFG";
text.length(); // 7
```

Also, compare to array length **variable**.
```
String[] list = {1, 2, 3}
int number = list.length; // number is 3
```
