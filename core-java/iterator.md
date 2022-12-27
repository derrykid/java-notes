```java
Set<String> set = new HashSet<String>();

Iterator<String> iterator = set.iterator();

while(iterator.hasNext()) {

    String aString = iterator.next();

}
```


## Implement the Iterator interface
```java
public class MyCollection<T> implements Iterable<T> {

    private final ArrayList<T> list;

    public MyCollection() {
        this.list = new ArrayList<>();
    }

    public void add(T t) {
        this.list.add(t);
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }
}

```