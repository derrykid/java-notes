[toc]
# `@Transactional` annotation

Execute atomic on the database query.

If a database query execution throws a Runtime exception, the method or the class that annotated `@Transactional` will be roll back.

## Example

Repository
```java
@Repository
public class ProjectRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addOne(Product product) {
        String sql = "INSERT INTO product VALUE (NULL, ?)";
        jdbcTemplate.update(sql, product.getName());
    }
}
```

Service
```java
@Service
public class ProductService  {

    @Autowired
    private ProjectRepository repository;

    public void addProduct(){
        Product product = new Product();
        product.setName("keyboard");
        repository.addOne(product);
    }
}

```

Config class, **Note that `@EnableTransactionManagement` is required.** 
```java
@Configuration
@ComponentScan(basePackages = "demo.springframework")
@EnableTransactionManagement
public class ProjectConfig {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource source = new DriverManagerDataSource();
        source.setUrl("jdbc:mysql://localhost/demo");
        source.setUsername("root");
        source.setPassword("root");
        return source;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}
```

Helper class, **note the `@Transactional` is above the class.** It can annotate method as well.
```java
@Component
@Transactional
public class ProductServiceHelper {

    @Autowired
    private ProductService productService;

    public void testAdd() {
        productService.addProduct();
        throw new RuntimeException(":(");
    }
}
```

In main:
```java
var context = new AnnotationConfigApplicationContext(ProjectConfig.class);
var helper = context.getBean(ProductServiceHelper.class);
helper.testAdd();
```

## Explanation
In the `ProductServiceHelper` class, we throw a RuntimeException, and we annotated the class with `@Transactional`. This annotation will roll back the sql query once the RuntimeException is thrown. We can make it also rolls back when *checked exception* is thrown as well by `@Transactional(rollbackfor = Exception.class)`

### Proxy

We can't use the `@Transactional` at the `ProductService` class. We have to create another class, `ProductServiceHelper` class in this example, to make the transactional happens. It's due to the proxy can't work on the same class for now.

Details at spring doc: [Link](https://docs.spring.io/spring-framework/docs/3.0.0.M3/reference/html/ch08s06.html) 

## propagation attributes

The enums that represent transaction propagation behaviours for use with the `@Transactional`

- `REQUIRED`       - default, this create a new one if none exists.
- `REQUIRES_NEW`   - create a new transaction each time the method invokes
- `MANDATORY`      - there must be a transaction call
- `NEVER`          - must not be a transaction call, and it'll throw exception
- `SUPPORTS`       - support transaction, up to you if you don't want a transaction
- `NOT_SUPPORTED`  - not support transaction, if you use, it will suspend it
- `NESTED`         - execute within a nested transaction if a current transaction exists

How a transaction works?
```java
@Transactional(propagation =  Propagation.REQUIRED)
public void addProducts() {     // create
    repository.addProduct(new Product);
}       // commit
```

A transaction is the method A calling method B, the process from the start of 'create' to 'commit'.

## Isolation level

Concurrency problems of:
- dirty reads           
- repeatable reads     
- phantom reads       

Dirty read:
```
// R means rollback
T1 ---10---------20----------->  $20
T2 ---------20----------R----->  $10 ???
```

Repeatable read:
```
// C means commits
T1 ---10-------------20------->  $20
T2 ---------20----C----------->  $20
```

Phantom reads:
```
// Select products from table
T1 ---100-------------110----->  select 110 products in the end
T2 ---------10----C----------->  
```

With higher level of isolation, the higher consistency, and the performance is compromised.
- `Isolation.READ_UNCOMMITED`
- `Isolation.READ_COMMITED` - default, hence no dirty reads happen
- `Isolation.REPEATABLE_READ` - no repeatable and dirty reads happen
- `Isolation.SERIALIZABLE` - none of reads problems happen
