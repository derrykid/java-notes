[Optional Chinese Articles](https://openhome.cc/Gossip/Java/Optional.html)

### Use `Optional<T>` to deal `null`

Null is ambiguous. It's hard to detect which part has occurred problems regarding the program. We can use `Optional<T>` to deal with this problem. It's *fast fail* concept.

Program without Optional:
```java
    public static void main(String[] args) {
        String nickName = getNickName("Duke");
        if (nickName == null) {
            nickName = "Openhome Reader";
        }
        out.println(nickName);
    }

    static String getNickName(String name) {
        Map<String, String> nickNames = new HashMap<>(); 
        nickNames.put("Justin", "caterpillar");
        nickNames.put("Monica", "momor");
        nickNames.put("Irene", "hamimi");
        return nickNames.get(name);  // will return null
    }
```

We can modify the static method to return Optional<String>
```java
    static Optional<String> getNickName(String name) {
        // implementation
        String nickName = nickName.get(name); // get the value from the hashMap
        return nickName == null ? Optional.empty() : Optional.of(nickName);
    }
```

We can use `orElse()` to return a replacement
```java
Optional<String> nickOptional = getNickName("Duke");
System.out.println(nickOptional.orElse("This is null"));
```

`Optional.ofNullable()` is also a good replacement. It will return `Optional.empty()` if the value is `null`, or it will return the expression.
```java
return Optional.ofNullable(nickNames.get(name));
```
