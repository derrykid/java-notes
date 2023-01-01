Why do we need access modifier to each class? 
It's recommended that we always write **access modifier** for our class. See an example below:

##### public
```java
public class Amount {
    public long amount = 100;
}
```

The variable `amount` is public. People can access it anywhere in the class, subclass, and other packages. We would have no clue that who is modifying this variable. **If there is a bug, it will be hard to debug.** 

----------

##### protected

```java
public class Amount {
    protected long amount = 100;
}
```

It's `protected` where only within the class or sub-classes can access it (e.g. SavingAccount, CurrentAccount, etc.).

----------

##### private

```java
public class Account {   
    private long amount = 100;
}
```
It's **private.** It's strictly accessible only in the current class. No one see the variable out side from it. Only access it by `getter and setter`.

----------

##### default

```java
public class AccessDemo {   
    int instanceCount= 1;   
}
```
Without access modifier - Compiler treats that as a **default modifier aka no modifier**. A default type is **Package Private.** Only with in the class and with in the Package only.

----------


Access Modifier
| Modifier     | Class | Package | Subclass | World |
|--------------|-------|---------|----------|-------|
| public       | Y     | Y       | Y        | Y     |
| protected    | Y     | Y       | Y        | N     |
| no modifier  | Y     | Y       | N        | N     |
| private      | Y     | N       | N        | N     |


[Source article](http://codeinventions.blogspot.com/2014/09/default-access-modifier-in-java-or-no.html)
