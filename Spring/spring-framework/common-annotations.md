## `@Value` - to inject value from `application.properties` or config

In `resources/application.properties`:
```yml
product.name="Wolf mouse"
```

In class:
```java
@RestController
public class HelloController {

    @Value("${product.name}")
    private String productName;


    @GetMapping(value = "/h")
    public String getProductName() {
        return this.productName;
    }

}
```

When call endpoint, it returns `Wolf mouse`.
