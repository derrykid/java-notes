`assert` is a fast and simple way to debug.

```java
public class JavaAssertCheck {
    public static void main (String args[]){
        String[] names = {"John", "Mary", "David"};
        assert names.length == 2;
        System.out.println("There are "+names.length + "  names in an array");
    }
}
```
In this example, the array `names` actually have 3 elements, but we `assert names.length == 2`, it's false;

Therefore, JVM will through `AssertionError` and terminate the program:
```java
Exception in thread "main" java.lang.AssertionError
	at JavaAssertCheck.main(JavaAssertCheck.java:5)
```
