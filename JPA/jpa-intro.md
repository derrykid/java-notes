# JPA installation and setup

JPA is an abstraction. There are multiple implementations to use: Hibernate, EclipseLink, etc.

To use JPA, we have to choose one JPA provider as well as database drive:
```xml
# EclipseLink JPA implementation
<dependency>
    <groupId>org.eclipse.persistence</groupId>
    <artifactId>eclipselink</artifactId>
    <version>4.0.0-M3</version>
</dependency>

# MySql connector
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.29</version>
</dependency>
```

Then setup the configuration by `persistence.xml` or by spring

## Spring Data JPA config
Add the spring data jpa dependency and db connector
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
    <version>xxx</version>
</dependency>

# MySql connector
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.29</version>
</dependency>
```

Spring, when require, can inject a `EntityManager` to your class, based on the config we specified.
