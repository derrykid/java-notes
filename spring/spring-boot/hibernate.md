## Hibernate, connect to database

In application.properties
```
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/db_example
spring.datasource.username=springuser
spring.datasource.password=ThePassword
spring.datasource.driver-class-name =com.mysql.jdbc.Driver
#spring.jpa.show-sql: true
```

For object class:
`@Entity` - this is for hibernate to understand this object class is actually for mapping
`@Talbe` - for database

### JPA interface

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

Then update the `Service` layer
```java
// add instance variable
private StudentRepository studentRepository;

// return rows method should update as well
public List<Student> getStudents() {
    return studentRepository.findAll();
}
```

### JPA config to save entity to database

Create a `StudentConfig` class with `@Configuration` annotation

```java
@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            // create some Student objects
            
            // add it to database
            repository.saveAll(List.of(student1, student2));
        }
    }

}
```

### query to access the database - POST method

Add query in interface
```java
public interface StudentRepository extends JpaRepository<Student, Long> {
    
    // this is the actual query I'm running, we don't have to comment out, but this still works
    // JPQL
    // @Query("SELECT s FROM Student s WHERE s.email = ?1")
    Optional<Student> findStudentByEmail(String email);
}
```

### Transient

In the `Student` entity, mark a object variable `@Transient` so it will use getter to calculate the result. Also, it will not save in the database.

```java

@Transient
private int age;

// getter
public Integer getAge() {
    return Period.between(date1, date).getYears;
}

```
