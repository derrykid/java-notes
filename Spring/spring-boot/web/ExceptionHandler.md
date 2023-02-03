# Handling exception

There are a lot of approaches to deal with exception in Spring boot web.

[Article](https://www.baeldung.com/exception-handling-for-rest-with-spring)

## `@ControllerAdvice` and `@ExceptionHandler` to config global exception handling

- `@ResponseBody` - signals this will be rendered straight into the response body
- `@ExceptionHandler` - specify that when the target class exception is thrown, this advice will be thrown. It can be any exception class.
- `@ResponseStatus` - specify that `HttpStatus.NOT_FOUND`, i.e. HTTP 404.

---

`@ExceptionHandler(EmployeeNotFoundException.class)` can pass an array of classes: `@ExceptionHandler({IllegalStateException.class, foo.class, bar.class})`

Example:
```java
@RestController
public class EmployeeController {

    // other methods

    @GetMapping("/employees/{id}")
    public Employee findOne(@PathVariable Long id) {
        return repository
               .findById(id)
               .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

}

//
// custom exception
public class EmployeeNotFoundException extends RuntimeException {

  EmployeeNotFoundException(Long id) {
    super("Could not find employee " + id);
  }
}

// 
// handling exception
@ControllerAdvice
public class EmployeeNotFoundAdvice {

  @ResponseBody
  @ExceptionHandler(EmployeeNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  String employeeNotFoundHandler(EmployeeNotFoundException ex) {
    return ex.getMessage();
  }
}
```

You can also return `ResponseEntity` instead of String:
```java
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { IllegalArgumentException.class, IllegalStateException.class })
    protected ResponseEntity<Object> handleConflict( RuntimeException ex, WebRequest request) {

        String bodyOfResponse = "This should be application specific";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);

    }
}
```

## Spring 5 and above: `ResponseStatusException` at method level

At the catch clause, throw `ResponseStatusException`. 

```java
@GetMapping(value = "/{id}")
public Foo findById(@PathVariable("id") Long id, HttpServletResponse response) {
    try {
        Foo resourceById = RestPreconditions.checkFound(service.findOne(id));

        eventPublisher.publishEvent(new SingleResourceRetrievedEvent(this, response));
        return resourceById;

     } catch (MyResourceNotFoundException exc) {
         throw new ResponseStatusException(
           HttpStatus.NOT_FOUND, "Foo Not Found", exc);
    }
}
```

You can combine `ResponseStatusException` for some methods, and use `@ControllerAdvice` handling other methods. 
