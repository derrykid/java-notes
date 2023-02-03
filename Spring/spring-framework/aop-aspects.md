# Spring aspects 
The methods runs before, after, or intervene the execution.

The most used cases are to log a specific methods. To check the value is true, or is the user have login. Log the information for inspection.

Add dependency:
```
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-aop</artifactId>
    <version>5.3.17</version>
</dependency>
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-aspects</artifactId>
    <version>5.3.17</version>
</dependency>

```

## Configuration, add `@EnableAspectJAutoProxy`

In the Configuration class:
```java
@Configuration
@ComponentScan(basePackages = "demo.springframework")
@EnableAspectJAutoProxy
public class ProjectConfig {

}
```

## `@Aspect` annotates the class



```java
@Component
@Aspect
public class HelloAspect {

    // methods

}
```

## method annotations

- `@Before`
- `@AfterReturning`
- `@AfterThrowing`
- `@Around`

```java
@Component
@Aspect
public class HelloAspect {

    @Before("execution(* demo.springframework.service.HelloService.hello(..))")
    public void before() {
        System.out.println("before the call");
    }

    // more methods


    @Around("execution(* demo.springframework.service.HelloService.hello(..))")
    public Object around(ProceedingJoinPoint joinPoint) {
        // can still proceed the method if you want:

        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return result;
    }

    // or replace the value in the jointPoint
    @Around("execution(* demo.springframework.service.HelloService.hello(..))")
    public Object around(ProceedingJoinPoint joinPoint) {
        // can still proceed the method if you want:

        Object result = null;
        try {
            // originally might pass "Jack", now we pass "Bill"
            result = joinPoint.proceed(new Object[]{"Bill"}); 
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return result;
    }




}
```
