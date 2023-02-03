# Spring Boot Web
## change the local host port
```
server.port=9090
```



# Spring Data

## datasource

### Set the datasource

Instead of set the configuration in java, you can set it in the file:
```
spring.datasource.url=jdbc:mysql://localhost/demo
spring.datasource.username=root
spring.datasource.password=
```

### No need to create `dataSource` in XML Configuration
As long as you enter the datasouce related properties in the `application.properties`, spring boot will create the Bean of dataSource for you.

Therefore you can inject that bean for your application.
