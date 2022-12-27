[toc]
## Context

Dependency:
```
<groupId>org.springframwork</groupId>
<artifactId>spring-context</artifactId>
```

You have to use the annotation to make the Spring framework manage the context. If you don't annotate or create the object that spring wants, the spring will not know. 

For example, you create a bean yourself, and you have a `@PostConstruct` annotation in the bean class, it will not be called because it's not in the spring context.

###  `@Configuration` - the config class

#### `@Bean` and `@Primary`

**`MyBean.class` doesn't have any annotation.** By default, the return bean is *singleton.* 
In the Spring context, spring creates the singleton object, and pass it to the method that asks for it.

In the class, get a bean by `@Bean`. If multiple, use `@Primary`
```java
@Configuration
public class Config {

    @Bean
    @Primary
    public MyBean myBean1() {
        return new MyBean();
    }

    @Bean
    public MyBean myBean2() {
        return new MyBean();
    }
}
```

##### Get the bean by type
In main, get the bean by type:
```java
ApplicationContext context = AnnotationConfigApplicationContext(ProjectConfig.class);

MyBean bean1 = context.getBean(MyBean.class);
MyBean bean2 = context.getBean(MyBean.class);
```

##### Get the bean by method name
Get the bean by name, specify **the method name**, this case, you can safely delete the `@Primary`:
```java
MyBean bean1 = context.getBean("myBean1", MyBean.class);
MyBean bean2 = context.getBean("myBean2", MyBean.class);
```

##### Get the bean by `@Bean(name)`

```java
// give it a name
@Bean("A")
public MyBean myBean2() {
    return new MyBean();
}

MyBean bean = context.getBean("A", MyBean.class);
```

##### Spring shares the bean in the context
In the Config class, we annotated the owner and cat. **See that the owner ask for a cat by calling the method.** Spring will give *the one and the only cat in the context.* It means every time it gives you the same cat "Tom".
```java
@Bean
public Owner owner() {
    Owner owner = new Owner();
    owner.setCat(cat());
    return owner;
}

@Bean
public Cat cat() {
    Cat cat = new Cat();
    cat.setName("Tom");
    return cat;
}
```

### `@Component`, `@ComponentScan` to create a bean

> `@Component` is a stereotype annotation

Interchangeably, if you don't want to use the `@Bean` like the previous section. Annotate the `MyBean.class` with the `@Component`:
```java
@Component
public class MyBean{
    // some codes
}
```

You have to annotation the Config class with `@ComponentScan` with the package name as well:
```java
@Configuration
@ComponentScan(basePackages = "demo.springframework")
public class ProjectConfig{
}
```
Multiple packages:
`@ComponentScan(basePackages = {"package1", "package2"})`

#### `@PostConstruct` calls after object initialization

Note that you didn't mutate the object like in the method in previous section. **You can do so by using `@PostConstruct` in Component class.** 
```java
@Component
public class MyBean{

    private final String text;

    @PostConstruct
    private void init(){
        this.text = "Hello";
    }

}
```

### `@Autowired`

`@Autowired` can be placed at
- variable field
- constructor
- setter method

There are 2 classes: Owner, Cat. Owner owns a cat
```java
@Component
public class Owner {

    private Cat cat;

    // getter, setter, and constructor
}
```

Cat.class
```java
@Componenet
public class Cat {

    private String name;

    // getter, setter
}
```

To wire a cat bean to Owner, place the `@Autowired`.
```java
// at field
@Autowired
private Cat cat;

// at constructor, preferred
@Autowired
public Owner(Cat cat) {
    this.cat = cat;
}

// at setter
@Autowired
public void setCat(Cat cat) {
    this.cat = cat;
}
```

> Annotate at Constructor is preferred. It's because you can add `final` keyword at field while other ways can't

### `@Qualifier`

If you have multiple beans and want to use `@Autowired` to wire the field. You can specify the bean you want by using the `@Qualifier`.
```java
// config class

@Bean
@Qualifier("cat1")
public Cat cat1() {
    return new Cat();
}
@Bean
@Qualifier("cat2")
public Cat cat2() {
    return new Cat();
}


// in own class
@Autowired
@Qualifier("cat2")
private Cat cat;
```
