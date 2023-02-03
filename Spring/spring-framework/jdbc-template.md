# Connect to database by jdbc in Spring

- create `DataSource` bean in Config
- create `JdbcTemplate` bean in Config
- create `@Repository` class with field `JdbcTemplate` and it's the class execute the SQL

## ProjectConfig - configuration

DataSource and JdbcTemplate are both implementation of Spring framework

```java
@Configuration
@ComponentScan(basePackages = "demo.springframework")
public class ProjectConfig {

    @Bean
    public DataSource dataSource() {
        var datasource = new DriverManagerDataSource();
        datasource.setUrl("jdbc:mysql://localhost/Student");
        datasource.setUsername("root");
        datasource.setPassword("root");
        return datasource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

}
```

## Repository - execute the SQL query

The `@Repository` annotation is also one of stereotype annotation.

```java
@Repository
public class StudentRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addStudent(Student student) {
        String sql = "INSERT INTO student VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                student.getId(),
                student.getAge(),
                student.getDateOfBirth(),
                student.getEmail(),
                student.getName());
    }

    public List<Student> getStudents() {
        String sql = "SELECT * FROM student";
        return jdbcTemplate.query(sql, new RowMapper<Student>() {
            @Override
            public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setAge(rs.getInt("age"));
                student.setName(rs.getString("name"));
                student.setEmail(rs.getString("email"));
                student.setDateOfBirth(rs.getString("dob"));
                return student;
            }
        });
    }
}
```

## Execution of the program

Save a student in the database:
```java
// get the repository bean codes


Student student = new Student();


// set the fields codes


repository.addStudent(student);
```

Get the list of rows from database
```java
// get the repository bean codes here


List<Student> students = repository.getStudents();
students.forEach(System.out::println);
```
