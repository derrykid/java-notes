[Rec article](http://tutorials.jenkov.com/java-regex/matcher.html) 

Suppose we have a String
```java
String text = "123Java456";
```


### Create a `Pattern`
Pattern Instance: We can use it as a Regex that can be applied multiple times.

Let's create a pattern that will match `*Java*`.
```java
Pattern pattern = Pattern.compile(".*Java.*");
```

---
### `Matcher`
Matcher Instance: The instance contains the information about the `String` and `Pattern` we passes in.

Use the method in `Pattern` class to create a `Matcher` instance:
```java
Matcher matcher = pattern.matcher(text);
```

#### `Matcher` methods:
- `matches():boolean` - to see if there's occurrence in it;
```java
boolean isContain = matcher.matches();

// example text and pattern
System.out.println(matcher.matches()); // true
```

- `lookingAt():boolean` - match the beginning of the text, don't care about the rear
- `find():boolean` - works like iterator. It points to the first occurrence, then the next.
- `start():int` & `end():int` - return the index of the 'start' & 'end' of found pattern. **end()** will return the index of the next character.

find(), start(), and end() example:
```java
int count = 0;
while(matcher.find()) {
    count++;
    System.out.println("found: " + count + ": " + matcher.start() + " - " + matcher.end());
}
```
Output:
```
found: 1: 3 - 7
```

- `group(int): String` - If we have a regex like `([a-z]*)(1133)(22)` that have multiple parentheses, this method can specify the regex we want by passing the number.

For example
```java
String a = matcher.group(0); // specify the entire regex
String b = matcher.group(1); // ([a-z]*) regex
// ... and so on
```
