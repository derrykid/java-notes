## When to use Singleton pattern?
Usually, you want to get the instance of the Singleton class, the Singleton object will be accessed by `getXXX()` method.

- `java.lang.Runtime#getRuntime()`
- `java.awt.Desktop#getDesktop()`
- `java.lang.System#getSecurityManager()`


**Initialization** 
> There are 2 kinds of initialization when it comes to Singleton class: *Eager initialization* and *lazy initialization.* The differences are: *eager* is instantiated when start the program, while *lazy* is instantiated when call the static `getXXX` method


### Lazy Singleton class:
**Note:** 
*public final class* - Also some examples aren't using `final`. So I think it depends.
*private static Singleton instance* - it's static. `Singleton` is class name, `instance` is variable.
```java
public final class Singleton {
    private static Singleton instance;
    public String value;

    private Singleton(String value) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        this.value = value;
    }

    public static Singleton getInstance(String value) {
        if (instance == null) {
            instance = new Singleton(value);
        }
        return instance;
    }
}
```

### Eager load
**Note:** 
It's not `public final class`
```java
public class GFG
{
  // public instance initialized when loading the class
  private static final GFG instance = new GFG();
 
  private GFG()
  {
    // private constructor
  }
  public static GFG getInstance(){
        return instance;
    }
}
```
