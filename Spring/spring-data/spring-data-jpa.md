[toc]

[Baeldung](https://www.baeldung.com/the-persistence-layer-with-spring-data-jpa) 

## Spring Data JPA interface

create an Interface `StudentRepository` [ClassnameRepository] is the convention.

```java
public interface StudentRepository extends JpaRepository<T, ID> {
}
```

Where in this case, our class is `Student` and primary key is in `long`
```java
public interface StudentRepository extends JpaRepository<Student, Long> {
}
```

Then in the `Service` layer, you can do:
```java
private StudentRepository studentRepository;

public List<Student> getStudents() {
    return studentRepository.findAll();
}
```

### Custom access to database

#### `@Query` - custom query

Add query in interface
```java
public interface StudentRepository extends JpaRepository<Student, Long> {
    
    // JPQL
    @Query("SELECT s FROM Student s WHERE s.email = ?1")
    Optional<Student> findStudentByEmail(String email);
}
```

##### Support native SQL query

Set the value of the `nativeQuery` attribute to true
```java
@Query(
  value = "SELECT * FROM USERS u WHERE u.status = 1", 
  nativeQuery = true)
Collection<User> findAllActiveUsersNative();
```

##### Named Parameter

This works for JPQL and native SQL.

> `@Param` annotation in the method declaration to match parameters

```java
@Query("SELECT u FROM User u WHERE u.status = :status and u.name = :name")
User findUserByUserStatusAndUserName(@Param("status") Integer userStatus, 
                                     @Param("name") String userName);
```

#### Automatic Custom queries by Spring Data 

[Spring Data Doc](https://docs.spring.io/spring-data/data-jpa/docs/current/reference/html/#jpa.query-methods.query-creation) 

## Spring Data JPA and Hibernate, connect to database

In pom.xml, add the dependency:
```xml
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
</dependency>
```

In application.properties
```
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/db_example
spring.datasource.username=springuser
spring.datasource.password=ThePassword
spring.datasource.driver-class-name =com.mysql.jdbc.Driver
#spring.jpa.show-sql: true
```
