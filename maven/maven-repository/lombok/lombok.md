## Frequently used annotations

- `@Getter/@Setter`
- `@ToString`
- `@EqualsAndHashCode`

Initialisation and constructor related

- `@NoArgsConstructor`
- `@AllArgsConstructor`
- `@RequiredArgsConstructor`

- `@Data`
- `@Value`
- `@Builder`
- `@Slf4j`

### `@Getter/@Setter`

It generates getter and setter methods for us
```java
@Getter
@Setter
public class User {
    private Integer id;
    private String name;
}
```

### `@ToString`

Implement an override `ToString()` method with all variables
```java
@ToString
public class User {
    private Integer id;
    private String name;

    // auto generated
    public String toString(){
        // implementation
    }
}
```

### `@EqualsAndHashCode`

Auto generate `equals()` and `hashCode()` methods
```java
@EqualsAndHashCode
public class User{
    
}
```

### Constructor related

#### `@NoArgsConstructor`

Generate a constructor without any variable
```java
public class User {
    // variables
    public User() {
    
    }
}
```

#### `@AllArgsConstructor`

Generate a constructor with **ALL** variables

Some programs will need a constructor without any param. The best practice is **always add a `@NoArgsConstructor`**.

#### `@RequiredArgsConstructor`

Generate a constructor where the param are those with `final` modifier.

```java
public class User {
    private final Integer id; // a constructor with only this as param
    private String name;
}
```

## `@Data`

It includes:
- `@Getter/@Setter`
- `@ToString`
- `@EqualsAndHashCode`
- `@RequiredArgsConstructor`

It's used for the Object that can be updated. Like DTO or JPA entity. One for most used annotation.

*@Data for mutable class* 

## `@Value`

It includes:  **No setter** 
- `@Getter`
- `@ToString`
- `@EqualsAndHashCode`
- `@RequiredArgsConstructor`

**@Value for immutable class** 

**There's another `@Value` annotation in Spring framework as well. Import the right package.** 

## `@Builder`

Add this annotation to the class:
```java
@Builder
public class User{
    private Integer id;
    private String name;
}
```

Then we can:
```java
public static void main(String[] args){
    User user = User.builder().id(1).name("John").build();
}
```

It's best practice to also add `@Data` annotation with `@Builder`. Spring or other frameworks need getter and setter to do stuff.

```java
@Data
@Builder
public class User{
    // codes
}
```

## `@Slf4j` - log

auto generate the static `Logger`
```java
@Slf4j
public class User {
    
    // this variable is auto generated
    // private static final Logger log = LoggerFactory.getLogger(User.class);
    
    public static void main(String[] args){
        log.info("hello");
    }
}
```
