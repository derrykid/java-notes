> A single-element enum type is often the best way to implement a singleton.

```java
public enum Elvis {
    INSTANCE;
    public void leaveTheBuilding() { ... }
}
```
