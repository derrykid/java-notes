## Handler

- `@ResponseBody` - signals this will be rendered straight into the response body
- `@ExceptionHandler` - tell when which exception is thrown, this advice will be thrown.
- `@ResponseStatus` - specify that `HttpStatus.NOT_FOUND`, i.e. HTTP 404.

```java
@ControllerAdvice
class EmployeeNotFoundAdvice {

  @ResponseBody
  @ExceptionHandler(EmployeeNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  String employeeNotFoundHandler(EmployeeNotFoundException ex) {
    return ex.getMessage();
  }
}
```

### Handler example

Exception is thrown when the request is made and target entity is not found:
```java
@RestController
public class EmployeeController {

    // other methods

    @GetMapping("/employees/{id}")
    public Employee findOne(@PathVariable Long id) {
        return repository.findById(id)
                         .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

}

//

public class EmployeeNotFoundException extends RuntimeException {

  EmployeeNotFoundException(Long id) {
    super("Could not find employee " + id);
  }
}

// 

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


