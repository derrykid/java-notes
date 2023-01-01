[toc]
## datasource
refer to datasource.md file

## entity

Map a java class to database table, if the table in database has:

---
*product* 
| id | name |
|----|------|
| 1  | beer |
---

We can map the it with:
```java
// import javax.persistence...;

@Entity
@Table(name = "product")     
public class Product {
    
    @Id
    private int id;
    private String name;
}
```

- `@Table`  - if the table name matches the class name, this can be ignored
- `@Id`     - It's used to specify the primary key. It's mandatory.

## product repository - to extend of JPA

This is all we need to do at first place to execute simple SQL query. It's a high level implementation which the Spring data will take care of the rest.
```java
                                                        // product is the table, Integer is the primary key
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
```

In the Controller, we can simply:
```java
@RestController
@RequestMapping("/product")
public class ProdcutController{

    @Autowired
    private ProductRepository productRepository

    @PostMapping("/product")
    public void addProduct(@RequestBody Product product) {
        productRepository.save(product);
    }
}
```

And in the request json:
```
{
    "name": "Chocolate"
}
```

### Define your own methods in Jpa

This is all you need to do. The spring will take care of the rest. **However, you have to follow specific naming though.** 
```java
public interface ProductRepository extends JpaRepository<Product, Integer> {

    public Product findProductByName(String name);
}
```

If you want to name your own method, you have to add `@Query` above the method
```java
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT p FROM product p WHERE p.name=:name")
    public Product findProductByName(String name);
}
```

## PagingAndSortingRepository -  superclass of Jpa

Click on the JpaRepository<T, ID> interface, will take you to see more superclasses. You can extends those interfaces instead of JpaRepository. By that, you will have other methods and return types.

For example, PagingAndSortingRepository is the superclass of JpaRepository. The repository can use it.
```java
public interface ProductRepository extends PagingAndSortingRepository<Product, Integer> {
}
```

### Paging
*In spring data domain package* 

In the `ProdcutController` you can use the methods that provide by it, including paging:
```java
@GetMapping("/page/{page}")
public List<Product> getProductsByPage(@PathVariable int page) {
    return productRepository.findAll(PageRequest.of(page, 3)).getContent();
}
```
**Paging is you split the output into different pages.** 

This mapping is telling the application that by accessing `http://localhost/product/page/0`, you will get the first 3 entity in the database.

**Note that it starts with 0.** 

### Sorting
*In spring data domain package* 

You can sort it by the way you want:
```java
@GetMapping
public Interable<Product> getAllSortedDescendingById() {
    return productRepository.findAll(Sort.by("id")).descending();
}
```

In paging, you can also pass the `Sort` to it.
```java
public List<Product> getProductsByPageAndSort(@PathVariable int page) {
    Sort s = Sort.by("id").descending();
    return productRepository.findAll(PageRequest.of(page, 3, s)).getContent();
}
```

### Define your own abstract method

Follow the naming like in Jpa.

In interface:
```java
public interface ProductRepository extends PagingAndSortingRepository<Product, Integer> {

    List<Product> findProductsByName(String name, Pageable p);
}
```

```java
@GetMapping("/name/{name}/{page}")
public List<Product> getproductsByName(@PathVariable String name,
                                        @PathVariable int page) {
    Pageable p = PageRequest.of(page, 2);
    return productRepository.findProductsByName(name, p);
}
```

## supported keywords

[supports key words](https://www.java4coding.com/contents/spring/springdatajpa/spring-data-jpa-method-naming-conventions) 
