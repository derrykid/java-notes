[toc]

[Link tut](https://www.youtube.com/watch?v=VE_NRRxc2lw&list=PLEocw3gLFc8Vli5p6rWHnNcYxFRbaIfIJ&index=16) 
## Annotations 

### `@SpringBootApplication`

It consists of annotations like `@ComponentScan` and auto configurations.

## Controller

### `@Controller`, `@ResponseBody`, `@RestController`

- `@Controller` is a stereotype annotation
- `@ResponseBody` tells spring that instead of finding the static web templates, response the content in this method/class directly.
- `@RestController` - it combines `@Controller` and `@ResponseBody`.

### `@RequestMapping` - the parent of mapping annotation
```java
@RequestMapping(mehtod = RequestMethod.GET, path = "/hello")
public String hello() {
    return "Hello World!";
}
```

#### With `@PathVariable`
```java
@RequestMapping(mehtod = RequestMethod.GET, path = "/hello/{name}")
public String hello(@PathVariable("name") String name) {
    return "Hello " + name + "!";
}
```

#### `@GetMapping`

Boiler template codes can be shorter:
```java
@RequestMapping(mehtod = RequestMethod.GET, path = "/hello")
@GetMapping(path = "/hello")
```

### Get the request body by `@PostMapping` and `@RequestBody`

**There's no "request body" in GET method.** Only in other request methods, there's body.

In the request header, in json:
```json
{
    "name" : "Bill"
}
```
The controller
```java
@PostMapping(path = "/goodbye")
public String goodbye(@RequestBody Person p) {
    return "Goodbye, " + p.getName() + "!";
}
```

In browser, the link `http://localhost:8080/hello/john`:
```
Hello john!
```
#### `@PostMapping` with path, header, body, and response
You can access everything in the method
```java
@PostMapping(path = "/test/{name}")
public String test(@PathVariable String name,
                    @RequestHeader String a,
                    @RequestHeader String b,
                    @RequestHeader String c,
                    @RequestBody    String body,
                    HttpServletResponse reponse) {
    response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    return a + b + c + body + name;
}
```

### Different return objects when requests comes in
> You can literally return anything, e.g. byte[], image, set, map, anything

Person class as our object
```java
public class Person{
    private String name;
}
```
#### Return the object
```java
@GetMapping(path = "/get")
public Person goodbye() {
    Person p = new Person("Bill");
    return p;
}
```
**Spring will convert it to json automatically.** 

Output:
```
{
    "name" : "Bill"
}
```

#### Return the List
```java
@GetMapping(path = "/getall")
public List<Person> goodbye() {
    Person p1 = new Person("Bill");
    Person p2 = new Person("John");
    return Arrays.asList(p1, p2);
}
```
Output:
```
[
    {
        "name" : "Bill"
    },
    {
        "name" : "John"
    }
]
```

#### Return a Map
```java
@GetMapping
public Map<String, String> all(@RequestHeader Map<String, String> map) {
    return map;
}
```

### Set the HttpServletResponse status

We can set the status by this, Spring framework will take care of it.
You can also return a body if you wish.
```java
@GetMapping(path = "/status")
public void statusTest(HttpServletResponse reponse) {
    // set the status you preferred
    response.setStatus(HttpServletResponse.SC_NO_CONTENT);
}
```

## Business Layer - Service Layer
`@RestController`

### GetMapping - create a get method endpoint

If we return a `List` instead of a String, it automatically gives a `JSON` format out of box.

```java
@RestController
public class Hello {
    // main method
    
    @GetMapping
    public List<String> hello() {
        return List.of("Hello", "World");
    }
}
```

### RequestMapping

It specifies the path of request
```java
@RestController
@RequestMapping(path = "/api/path")
public class StudentController {

    @GetMapping
    public List<Student> getStudents() {
        return List.of(
            // some student objects
        );
    }

}
```

### PostMapping

In business logic, if we want to set up Post method
```java
@PostMapping
public void registerNewStudent(@RequestBody Student student){
    // implement the method in service layer
    studentService.addNewStudent(student);
}
```

### DeleteMapping - delete method

```java
@DeleteMapping(path = "studentId")
public void deleteStudent(@PathVariable("studentId") Long studentId ){
    // implement the method in service layer
    studentService.deleteStudent(studentId);
}
```
