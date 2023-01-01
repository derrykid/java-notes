[Source](https://www.baeldung.com/java-enum-values)
This is a very thorough explained and well-documented examples of enum in Java.

### Brief use case
We often create an enum as a simple list of values. For example, here are the first two rows of the periodic table as a simple enum:
```java
public enum Element {
    H, HE, LI, BE, B, C, N, O, F, NE
}
```

We can add constructors, fields and methods as we do with other classes. Because of this, we can enhance our enum to include the values we need.

### Add a constructor and a final variable

> enums have implicit private constructor

```java
public enum Element {
    
    // special syntax for declaration
    H("Hydrogen"),
    HE("Helium"),
    // ...
    NE("Neon");

    public final String label;

    private Element(String label) {
        this.label = label;
    }
}
```

The special syntax in the declaration `H("Hydrogen")` is how a constructor is invoked for enum types. We use this instead of `new` keyword.

`public final String label;`
* The instance variable is `final`, in most cases we don't want our labels to change. 
* In the spirit of enum values being constant, this makes sense.
* makes it `public` while user can access with `H.label` e.g. `System.out.println(H.label);`

### Add a method
```java
public enum Element {
    // values
    // constructor
    
    public static Element valueOfLabel(String label) {
        // method implementation
    }
}
```

### Implement interface and Value-Specific Class Bodies
Say we have an interface `Command`
```java
public interface Command{
    void exec();
}
```

The enum class can implement an interface, and we have to override the method.
Instead of write a `switch`, we can try *Value-Specific Class Bodies* 
```java
public enum Action implements Command {

    RIGHT(15) {
        @Override
        public void exec() {
            System.out.println("Right");
        }
    };

    private final int value;
    // constructor
     Action(final int value) {
        this.value = value;
    }
    public int getValue(){
         return this.value;
    }

}
```
