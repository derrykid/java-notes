Add maven dependencies to run hibernate

In order to use the hibernate, I have to add xml file, add annotation, and add factory, etc.


In `hibernate.cfg.xml` file, left in under `src/main/java`

Use the IDE to generate the file and select the driver
```java
        // skip
        
        // If the table does not exist, hibernate will create it for you.
        // Can change it to create, but it will erase the data every time
        <property name="hibernate.hbm2ddl.auto">update</property>
        
        // it shows the SQL query in console
        <property name="show_sql">true</property>
```

### Save an object

```java
    // create an Alien object named "derry"
    
    Configuration conf = new Configuration().configure().addAnnotatedClass(Alien.class);
    
    ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
    
    SessionFactory sf = conf.buildSessionFactory(reg);
    
    Session session = sf.openSession();
    
    Transaction tx = session.beginTransaction();
    
    session.save(derry);
    tx.commit();
```

### Read an object from database

Implement the `toString` in the object class
```java
Alien derry = null;

Configuration conf = new Configuration.configure().addAnnotatedClass(Alien.class);
ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
SessionFactory sf = conf.buildSessionFactory(reg);
Session session = sf.openSession();
Transaction tx = session.beginTransaction();

// use get method and type casting
derry = (Alien) session.get(Alien.class, 102);

tx.commit();

System.out.println(derry);
```

### Annotation

In the object class, you have to specify the `@Entity` and the `@Id` (primary key)
```java
@Entity
public class Alien {

    @Id
    private int aid;
    // more object variables
    
    // getter and setter

}
```

- `@Table(name="tableName")` - to specify the table name
- `@Transient` to skip a column
- `@Column(name="columnName")` - to specify column name
- `@Embeddable` - annotate the object variable class of instance variable "private AlienName name;"

### `@Embeddable` when you create an object variable of an object

We have Alien class with:
```java
public class Alien {
    private int aid;
    private String name;
    
    //skip...
}
```

The name might have first name, last name, and middle name. We can create a class for it and modify the `Alien` class
```java
public class Alien {
    private AlienName name;
}
```

`AlienName` class has 3 instance variables `fname`, `lname`, `mname`. We annotate the `AlienName` class with `Embeddable` so it'll embed in one table.

### One to many, many to many ... annotations

Say we have a `Student` class and `Laptop` class. One student can have multiple laptops, or vise versa. We can use annotations.

- `@OneToMany` 
- `@ManyToMany`

If you want to use foreign key instead of create a new table named `Student_Laptop`, use `@OneToMany(mappedBy="student")`

### Eager and Lazy fetch

By default it's a lazy fetch. It means that when fetching the data, hibernate will only fetch the target table.

However, if it's Eager, it will use `join` query to fetch the data.

Simply add it after `@OneToMany(mappedBy="alien", fetch=FetchType.EAGER)`

### Use cache

When using `session.get(Alien.class, 101)`, you can cache it.

If you want to use cache, that your program won't execute the same query multiple times, you can use cache.
By default, Level 1 cache is executed, while Level 2 is not.

You have to add the dependency to `pom.xml` and also modify the `hibernate.cfg.xml` file.

Also add 2 annotations
- `@Cacheable` - to specify it's cacheable
- `@Cache(usage=CacheConcurrencyStrategy.READ_ONLY)` - to specify the strategy. You can change the `READ_ONLY` to write or other strategies.


Also **cache the query** 
```java
Query q2 = session.createQuery("from Alien where aid=101");
q2.setCacheable(true);
```
