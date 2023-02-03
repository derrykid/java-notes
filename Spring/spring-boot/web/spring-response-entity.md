[Tutorial](https://www.baeldung.com/spring-response-entity) 

## `@ResponseBody` - the common way 

Commonly, we might just return `@ResponseBody` like this:
```java
@GetMapping(path = "/")
@ResponseBody
public String hello() {
    return "Hello";
}
```

This will return the text "Hello" in the http response. Spring takes care of it.

## Use `ResponseEntity` to customize 

> At the controller layer, we can return `ResponseEntity`. Spring will take care of it. `ResponseEntity` represents the whole HTTP response: status code, headers, and body.

### Create by constructor
Example:
```java
@GetMapping("/hello")
ResponseEntity<String> hello() {
    return new ResponseEntity<>("Hello World!", HttpStatus.OK);
}
```

The `ResponseEntity` constructor overloads, you can provide optional arguments to it, like:
```java
// there are more than these
new ResponseEntity(Object, headers, HttpStatus);
new ResponseEntity(Object, HttpStatus);
```

The headers are easy to constructed:
```java
HttpHeaders headers = new HttpHeaders();
headers.add("foo", "bar");
return new ResponseEntity<>("Hello", headers, HttpStatus.OK);
```

### Create by Builder

There are a lot to use, these are examples:
```java
// OK
ResponseEntity.ok("Hello);

// not found
ResponseEntity.notFound();
```

With http body:
```java
return ResponseEntity.ok()
                    .header("foo", "bar")
                    .body("Hello World");
```

Pay attention to the builder's return type. Like `notFound()` will not contain any body.

