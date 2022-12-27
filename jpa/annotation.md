[toc]

# Entity 

## `@Entity` - marks class as the database entity

```java
@Entity
public class Pokemon{}
```


## `@Table` - table name
```java
@Table(name = "Poke")
public class Pokemon {
}
```

## `@Id` - mark the primary key

Annotate primary key
```java
@Id
private int id;
```

##  `@GeneratedValue` - the strategy for primary key

[GenerationType](https://medium.com/@BalicantaYao/jpa-%E4%B8%AD-generatedvalue-%E7%9A%84%E4%B8%89%E7%A8%AE%E7%AD%96%E7%95%A5-bedebf1c076d) 

```java
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
```

### `GenerationType.IDENTITY`

The database is set AUTO_INCREMENT.

Similar to SQL `id NOT NULL AUTO_INCREMENT`

### `GenerationType.SQUENCE`

### `GenerationType.TABLE`

Use the TABLE in database to set the primary key for us, instead of the DBMS system.

In database, I've created a table named `key_generator`. There are 2 columns in it:

| key_name | next_val |
|----------|----------|
| //       | //       |

```java
@Id
@GeneratedValue(strategy = GenerationType.TABLE, generator = "key_generator")
private int id;
```

**The default column name of the `value` is `next_val`**. 

#### Customize the generator by `@TableGenerator`

To achieve this table:
**key_generator** 
| key_name         | key_value |
|------------------|-----------|
| product_sequence | 150       |

We have to use the `@TableGenerator`:
```java
@Id
@TableGenerator(name = "key_generator",
            table = "key_generator",
            pkColumnName = "key_name",
            pkColumnValue = "product_sequence",
            valueColumnName = "key_value",
            allocationSize = 20)
@GeneratedValue(strategy = GenerationType.TABLE, generator = "key_generator")
private int id;
```

### Generator

For primary key, we can use a custom generator like `@GenericGenerator` provided by hibernate.

#### `@GenericGenerator`

There are several generators available. This one is an example.

```java
@Id
@GenericGenerator( 
                name = "uuid",
                strategy = "org.hibernate.id.UUIDHEXGenerator",
                parameters = @Parameter(name = "separator", value = "-") // this is optional
)
@GeneratedValue(generator = "uuid")
private String id;
```

## `@Column` - name in database

### `@Column(name = "xxx")` - column name
It's used to relate the name in database.

For example, when we create a database in app:
```java
@Entity
@Data
public class Pokemon {

    @Column(name = "Special-Attack")
    private int specialAttack;

}
```

This will set the name of column in database for "Special-Attack", if not set, it will set as the field name;


### `@Column(table = "xxx")` - table name

It can be used at when work with relationships.
```java
@Entity
@Data
@Table(name = "Company")
@SecondaryTable(name = "address",
        pkJoinColumns = @PrimaryKeyJoinColumn(name = "company_fk"))
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @Column(table = "address")
    private String street;

    @Column(table = "address")
    private String road;

}
```

## `@Enumerated` - set the presentation of Enum value in database

At field, we might use enum to set the field. Use the `@Enumerated` annotation
```java
@Entity
@Data
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double amount;

    @Enumerated(value = EnumType.ORDINAL)
    private Currency currency;              // enum type

}

public enum Currency {
    EUR, USD
}
```


`@Enumerated` default sets to ordinal value, however there is alternative:
- `@Enumerated(EnumType.ORDINAL)` - default
- `@Enumerated(EnumType.STRING)`

Different setting to changes the database table:
| id: int | amount:double | currency: (int or varchar)        |
|---------|---------------|--------------------------|
| 1       | 10            | 0  (EUR, if set ordinal) |
| 2       | 30            | USD(set String)          |

## `@Temporal`

For saving the time, JPA has implemented automatically, so we don't have to use this `@Temporal` annotation unless we use the legacy package `Data.class`.

We can just use this, the implementation will help us persist the data:
```java
@Entity
@Data
public class Event {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "event_time")
    private LocalDate eventTime;

    // or ZonedDateTime;
}
```

In database:
| id | event_time: DATE |
|----|------------------|
| 1  | 2020-12-22       |

We can also use timestamp to store the data. **Note that whichever you choose, ZonedDateTime or LocalDateTime, the data saved in database is the same.** 

## `@Embedded`, `@Embeddable` - 1 database table for 2 java object

We can use this to persist an object in our entity. For example we have a company table like this:

| id | name        | no. | street     | city   |
|----|-------------|-----|------------|--------|
| 1  | Water Corp. | 12  | Oak street | London |

We saved the address separately. On the other hand, for OOP point of view, we should save it as an object like this:
```java
@Entity
@Data
public class Company {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    
    @Embedded
    private Address address;
}

@Data
@Embeddable
public class Address {

    private String number;
    private String street;
    private String city;
}
```

This way, when we create an instance of `company`, we can persist it and commit in database in OOP way.

### If the column name doesn't match field name - use `@AttributeOverride`

```java
@Data
@Embeddable
public class Address {

    private String no;
    private String strt;
    private String city;
}
```

For this, the database can't match the field name. We have to use `@AttributeOverride` in company class to specify the change:
```java
public class company {

    // fields

    @Embedded
    @AttributeOverride(name = "no", column = @Column(name = "number"))
    @AttributeOverride(name = "strt", column = @Column(name = "street"))
    private Address address;
}
```

Or use the `@AttributeOverrides` to group these:
```java
@AttributeOverrides{
    @AttributeOverride(name = "no", column = @Column(name = "number")),
    @AttributeOverride(name = "strt", column = @Column(name = "street"))
}
private Address address;
```

### `AssociationOverride` -- need attention
For rare cases, this can used to substitute `@AttributeOverride`. But this increase the complexity.

## Composite primary key - `@IdClass(class)`

Composite key is that there is 2 primary key in the table:

*Department table*
| code:pk | id:pk | name |
|---------|-------|------|
| C132    | S23   | a    |
| C132    | S24   | b    |

We have to create 2 classes to implement this: **note the `@IdClass()`, and 2 `@Id` on the fields.** 
```java
@Entity
@Data
@IdClass(DepartmentPK.class)
@Table(name = "department")
public class Department {

    @Id
    private String code;

    @Id
    private String id;

    private String name;
}

// composite key class

@Data
public class DepartmentPK implements Serializable {

    private String code;
    private String id;
}
```

Probably the `implements Serializable` is not mandatory anymore, cuz when I remove it, the code still works.

### Alternative to `@IdClass()` for composite primary key: `@EmbeddedId`

```java
@Data
@Entity
public class Building {

    @EmbeddedId
    private BuildingPK id;

    // more fileds
}

@Data
@Embeddable
public class BuildingPK implements Serializable {

    private int number;
    private String code;

}
```

### Create a third class and annotate with `@Embeddable` to create composite key

Student table
| student_id | name | grade |
|------------|------|-------|
| 1          | John | 1     |

Subject table
| subject_id | name    |
|------------|---------|
| 101        | English |

marks table
| subject_id | student_id | mark |
|------------|------------|------|
| 101        | 1          | 75   |


The marks table have a composite key made of `subject_id` and `student_id`.

We can create a third class to do it
```java
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long id;

    // fields
}


---------
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subject_id")
    private Long id;

    // fields
}

----------
@Entity
@Data
@Table(name = "student_marks")
public class StudentMark {

    @EmbeddedId
    private StudentMarkId markId;

    private int mark;

}

-----------
@Embeddable
@Data
public class StudentMarkId implements Serializable {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "subject_id")
    private Subject subject;

}
```

Note that `StudentMarkId` is a `@Embeddable`. It works as an composite key. To save it to repository, we have to do an interface as well:
```java
public interface StudentMarkRepository extends JpaRepository<StudentMark, StudentMarkId> {
}
```
**The JpaRepository<T, ID>** ID field is the embedded id.

This is how client calls it:
```java
    @Bean
    public CommandLineRunner run(StudentRepository studentRepository,
                                 SubjectRepository subjectRepository,
                                 StudentMarkRepository studentMarkRepository) {
        return args -> {

            Student jack = new Student();
            jack.setName("jack");
            jack.setGrade(1);

            studentRepository.save(jack);

            Subject english = new Subject();
            english.setName("English");

            subjectRepository.save(english);

            StudentMarkId id = new StudentMarkId();
            id.setStudent(jack);
            id.setSubject(english);

            StudentMark mark = new StudentMark();
            mark.setMark(75);
            mark.setMarkId(id);


            studentMarkRepository.save(mark);

        };
    }
```


## Seldom use - `@Access` to specify access the field or by getter

Default is by field: `@Access(AccessType.FIELD)`

```java
@Data
@Entity
@Access(AccessType.PROPERTY)
public class Person {

    private int id;

    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }
}
```

## Relationships

### `@JoinColumn` to config the default foreign key name

By default, the foreign key naming is `product_id`, in form of `table_id`.

You can change that by:
```java
@JoinColumn(name = "product_fk")
@OneToOne
private Product product;
```

#### Relationship annotation attribute fields

Relationship annotations

- `@OneToOne`
- `@OneToMany`
- `@ManyToOne`
- `@ManyToMany`

##### targetEntity

This is used to specify the target class. This example `Object` is set as field, but it's too vague, we want a more specific class. It works with polymorphism.

```java
@OneToMany(targetEntity = className.class)
private Object field;
```

##### cascade attribute

It's often that when we persist one object in database, it's associated with another object.

The trainer has a pokemon `pikachu`, to persist `trainer` we also have to persist `pikachu`.
```java
pokeMonRepository.save(pikachu);
trainerRepository.save(trainer);
```

**`Cascade` is the way to specify the behaviour of this.**

```java
public class Trainer {
    @OneToOne(cascade = CascadeType.ALL)
    private Pokemon pokemon;
}
public class Pokemon {
    // ...
}
```

- `PERSIST` - The operations cascade to related entities. If only save `trainer`, `pikachu` is also saved
- `REMOVE` - Similar to `PERSIST` but this is delete. If remove the `trainer`, the `pikachu` is also removed!
- `REFRESH` - is to refresh the data in the object. Perhaps there was a change on the database which needs to be synced.
- `MERGE` - propagate the operation from one to another. If `pikachu` changes its data, the trainer whose hold it as the instance variable will also flush the value. It's not in database layer.
- `ALL` - all of above. Set to all isn't always making the program run well. See: [detached entity exception](https://www.baeldung.com/hibernate-detached-entity-passed-to-persist) 

There are more types to select from.

###### Orphan removal vs `CascadeType.REMOVE`

While `REMOVE` remove all related entities. The `orphanRemoval` provides a smart way to remove a record based on relationship.

A trainer can take 6 pokemon on journey. We call it *partyPokemons* here.
```java
Trainer trainer = repository.findById(Trainer.class, 1);
Set<Pokemon> partyPokemons = trainer.getPartyPokemon();
partyPokemons.remove(xxx);    // we remove one pokemon, in collection
```

Now that in the party, trainer have 5 pokemon, and remove 1 from the collection. That removed one is an orphan. That orphan entity will be removed from the database, even though we did not use any delete operation.

##### `fetch` - fetch all related info or not

- `FetchType.EAGER` - fetch all info of the object fields, includes other table
- `FetchType.LAZY`  - fetch the info only when we need it.
Better specify the value, cuz the default depends on the annotations
```java
@OneToOne( fetch = FetchType.EAGER )
```

##### mappedBy
To specify the bidirectional model. It's used at the **owned side.** 
```java
@OneToMany(mappedBy = "fieldNameInAnotherClass")
private Document doc;
```

##### `optional` - default `true`

By default, this allows us to make one field optional. Like in Detail field `product`, we set product, but the `product` detail can be null.

If you want to make sure it always have value:
```java
@OneToOne(optional = false)
```

### OneToOne 

#### By `@OneToOne`: unidirectional - only use this annotation at one class

Unidirectional: in this case, only `Detail` knows the product object.

Product table
| id | name | price |
|----|------|-------|
| 1  | beer | 10    |

Detail table, the foreign key must named this way `product_id`. If want to config this, see `@JoinColumn`
| id  | kcal | product_id |
|-----|------|------------|
| 101 | 200  | 1          |

```java
@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private double price;
}

@Data
@Entity
public class Detail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int kcal;

    @OneToOne
    private Product product;
}
```


#### `@OneToOne` - bidirectional - use this annotations at 2 class

> Not all cases need bidirectional, it will have overheads as well. Think if you really need it.

If you want to map it bidirectional:
```java
public class Detail {
    // some fileds
    @OneToOne
    private Product productField;
}

public class product {
    // some fields
    @OneToOne(mappedBy = "productField")
    private Detail detail;
}
```

In this case, `Detail` class is considered **the owner of the relationship.** So the other side `product` must use `mappedBy` value.

#### By `@SecondaryTable`, no need to use `@OneToOne`

Table company
| id | name   |
|----|--------|
| 5  | A Corp |

Table address
| id | road | street     | company_fk |
|----|------|------------|------------|
| 1  | Oak  | Sky street | 5          |

In table `address`, there's a foreign key `company_fk`. It actually is the constrain key with table `company`.

We can use a class to achieve this:
```java
@Entity
@Data
@Table(name = "Company")
@SecondaryTable(name = "address",
        pkJoinColumns = @PrimaryKeyJoinColumn(name = "company_fk"))
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @Column(table = "address")
    private String street;

    @Column(table = "address")
    private String road;

}
```

There is an annotation `SecondaryTable` on top of the class, specify the name of SecondaryTable. Besides, it points out the `foreign key: company_fk`.

In the field, it also specifies `@Column(table = "address")`.

### OneToMany

#### Create the third table named after other tables' name

A department can have many employees. An employee belongs to one department.

```java
@Entity
@Data
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @OneToMany
    private List<Employee> employee;  // this must match the name
                                      // of column in department_employee
}

---------------------------

@Entity
@Data
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
}
```

For this to work, **we have to create the 3rd table `department_employee`.** 

Table `department_employee`
| department_id | employee_id |
|---------------|-------------|
| //            | //          |

#### don't have to create 3rd table

One person can have many documents

Document table:
| id | person_id |
|----|-----------|
| 1  | 1         |
| 2  | 1         |

```java
@Entity
@Data
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
}

--------------------

@Entity
@Data
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Person person;
}
```

##### Use `Collection<T> ` to store a collection of entities, bidirectional example

Take the Person class for example:
```java
@Entity
@Data
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(mappedBy = "person")
    private Collection<Document> documents;
}
```

This can save multiple documents at a time

### ManyToMany - `@JoinTable`

#### unidirectional - only one knows the other

One professor may have multiple students, and one student may have multiple professors.

- Professor table: id, name
- Student table: id, name

```java
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @ManyToMany
    @JoinTable(name = "professor_student",  // table name, this value here is the default value
            joinColumns = @JoinColumn(name = "professor"),      // column name, suppose to be professor_id
            inverseJoinColumns = @JoinColumn(name = "student")  // the other column name
    )
    private List<Student> students;
}

------------------------

public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
}
```

#### bidirectional

Annotated student class
```java
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @ManyToMany(mappedBy = "students")      // the field name in the Professor class
    private List<Professor> professors;
}

```

### `@ElementCollection` - mark a non-entity object

It's used to mark something that wasn't created as entity.
For example, we have a `person` table, and a `phone` table

#### map a non-entity class

Person
| id | name |
|----|------|
| 1  | John |

phone
| number | person |
|--------|--------|
| 312    | 1      |

We did not create a entity class `phone`, we save it as a String in a list.

The `@ElementCollection` is in used here to mark a non-entity class.

```java
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @ElementCollection
    @CollectionTable(name = "phone", joinColumns = @JoinColumn(name = "person"))
    @Column(name = "number")
    private List<String> phoneNumbers;
}
```

#### map `@Embeddable`
It can also to map an `Embeddable` class

Document table:
| cover | reference | person |
|-------|-----------|--------|
|-------|-----------|--------|

```java
@Entity
public class Person {

    // other fields

    @ElementCollection
    @CollectionTable(name = "document", joinColumns = @JoinColumn(name = "person"))
    private List<Document> documents;
}

--------

@Data
@Embeddable
public class Document {
    private String cover;
    private String reference;
}
```

## Map value

All `@Mapxxx` annotations work on the key. Without the prefix, works on the value. E.g. `@Column` works for value, `@MapKeyColumn` works for key.


A person can have many phone numbers. We can use map to store the value. **Generally speaking, map increases the complexity of code, we can use other way to simplify the code without map.** 

| id | name |
|----|------|
| 1  | John |

`phone` table
| type | number | person |
|------|--------|--------|
| home | 2312   | 1      |
| work | 152    | 1      |

```java
@Entity
@Data
public class Person {
    
    @Id
    private int id;
    private String name;
    
    @ElementCollection
    @CollectionTable(name = "phone", joinColumns = @JoinColumn(name = "person"))
    @MapKeyColumn(name = "type")    // key
    @Column(name = "number")        // value
    private Map<String, String> phoneNumbers;
]
```

### Use enum to map the key

- `@MapKeyEnumerated` for key
- `@Enumerated` for the value

```java
public class Person {
    
    @Id
    private int id;
    private String name;
    
    @ElementCollection
    @CollectionTable(name = "phone", joinColumns = @JoinColumn(name = "person"))
    @MapKeyColumn(name = "type")    // key
    @Column(name = "number")        // value
    @MapKeyEnumerated(EnumType.STRING)
    private Map<PhoneType, String> phoneNumbers;
}

-----------

public enum PhoneType {
    WORK, HOME
}
```

##  Inheritance, hierarchy of class

### `@Inheritance` - InheritanceType

- `SINGLE_TABLE` - default
- `JOINED`
- `TABLE_PER_CLASS` - rare use, not recommend

These classes are based classes for demo:
```java
@Data
@Entity
@Inheritance(strategy = InheritanceType.xxxxxx)
public class Person {

    @Id
    private int id;
    private String name;

}

------------

@Data
@Entity
public class Artist extends Person {
    private String mastery;
}


------------

@Entity
@Data
public class Worker extends Person {
    private int workHour;
}


------main------
Person jack = new Person();    // set id 100

Artist duke = new Artist();    // set id 200

Worker roy = new Worker();     // set id 300
```

#### `SINGLE_TABLE` all classes in single table


We annotated with this value on `Person` class
```java
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Person {

    @Id
    private int id;
    private String name;

}
```

Resulting in only one table in database:
| DTYPE  | id  | name | mastery | workHour |
|--------|-----|------|---------|----------|
| Person | 100 | Jack | null    | null     |
| Artist | 200 | Duke | Drawing | null     |
| Worker | 300 | Roy  | null    | 8        |

As shown above, there are columns set to `null`.

#### `JOINED` - foreign key joined in 1 table

**Joined means the foreign key is joined in one table.** This will actually generate 3 tables.

```java
@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Person {

    @Id
    private int id;
    private String name;
}
```

Person table
| id  | name |
|-----|------|
| 100 | Jack |
| 200 | Duke |
| 300 | Roy  |

Artist table
| id  | mastery |
|-----|---------|
| 200 | Drawing |

Worker table
| id  | workHour |
|-----|----------|
| 300 | 30       |

**Drawback:** The disadvantage of this inheritance mapping method is that retrieving entities requires joins between tables. For high queries, it's more likely to affect the performance.

##### Customize the foreign key column by `@PrimaryKeyJoinColumn(name = "")`

Each table will share the same name `id`. If you want to make, for example, Artist table `id` another name:
```java
@Data
@Entity
@PrimaryKeyJoinColumn(name = "artist_id")
public class Artist extends Person {
    //
}
```

This will set it name as ` artist_id`

Artist table
| artist_id | mastery |
|-----------|---------|
| 200       | Drawing |


#### `TABLE_PER_CLASS` - create table for each class with all the fields

> It maps each entity to its table, which contains all the properties of the entity, including the ones inherited.

```java
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Person {
    //
}
```

Person table
| id  | name |
|-----|------|
| 100 | Jack |

Artist table
| id  | name | mastery |
|-----|------|---------|
| 200 | Duke | Drawing |

Worker table
| id  | name | workHour |
|-----|------|----------|
| 300 | Roy  | 30       |

It's no difference from creating an individual class without inheritance.

### `@MappedSuperclass`

Create a template like class that works like an entity class. It can be used to remove duplicate code.

This class has no separated table in database. The mapping information can be override by `@AttributeOverride` `@AssociationOverride`.


```java
@Data
@MappedSuperclass
public abstract class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String color;
}

----------

@Data
@Entity
public class Car extends Vehicle {
    private String gas;
}


----------
@Data
@Entity
public class Bicycle extends Vehicle {
    private String model;
}
```

Car table
| id | color | gas    |
|----|-------|--------|
| 1  | Blue  | Diesel |

Bicycle table
| id | color | model |
|----|-------|-------|
| 1  | Red   | BMAX  |

This example put `@id` in abstract class. In practice, we should put it in the subclass instead. It's because we can define the desired way of primary key strategy.

# EntityManager

## Get the `EntityManager` by `EntityManagerFactory`

```java
EntityManagerFactory factory = Persistence.createEntityManagerFactory("my-persistence-unit");
EntityManager entityManager = factory.createEntityManager();
entityManager.begin();

// persist, flush, etc operations

entityManager.commit();
entityManager.close();
```

## Available `EntityManager` methods

- `commit()` - commit all changes from JPA context to database. When called, it's where the SQL executed
- `persist()`
- `flush()` - to persist all data in database before `commit()` is called

### `find()`

```java
Product product = entityManager.find(Product.class, 10); // 10 is the primary key id
```

### `getReference()`

The code is similar.

```java
Product product = entityManager.getReference(Product.class, 10); 
```

But the main difference is **if the object is not used later, the query will not be executed.** 
The above code will not execute a `SELECT` query unless we use the object. e.g. print it in the console.

This actually use a proxy to check if the object is used.

### `contains()`

Check if the object is in the JPA context
```java
Product product = entityManager.find(Product.class, 10);
boolean isExist = entityManager.contains(product);
```

### `remove()`

remove record in database
```java
Product product = // get product
entityManager.remove(product);  // execute delete query
```

# JPQL

```
SQL - SELECT * FROM product p (native query)
JPQL - SELECT p FROM Product p          // p is the alias
                     ^ this is the class name
```

## select entities

```java
String jpql = "SELECT p FROM Product p";
TypedQuery<Product> q = entityManager.createQuery(jpql, Product.class); // TypedQuery extends query

List<Product> list = q.getResultList();
```

### Where clause

```java
// sql: SELECT * FROM Employee where Employee.email = xxx
String jpql = "SELECT e FROM Employee e WHERE e.email = :arg"
```

This `:arg` will be replaced by your method parameter.

#### Where ... AND ...

```java
String jpql = "SELECT e FROM Employee e WHERE e.email = :arg1 AND arg2";
```

Example:
```java
String jpql = "SELECT p FROM Product p WHERE p.price > :price";
                                               ^
                                               the field name of the class, not database column name

TypedQuery<Product> q = entityManager.createQuery(jpql, Product.class); // TypedQuery extends query
q.setParameter("price", 10.0);

List<Product> list = q.getResultList();
```

**Note that the parameter must be `:price`.** There's no space in between.

Use number:
```java
String jpql = "SELECT p FROM Product p WHERE p.price > ?1";
TypedQuery<Product> query = entityManager.createQuery(jpql, Product.class);
query.setParameter(1, 10.0);
```

### Like
```java
String jqpl = "SELECT e FROM Employee e WHERE e.name Like :arg1";
```

### Update example with Spring data jpa

Suppose we want to change the `Product` price

```java
public interface Product extends JpaRepository<Product, Long> {

    @Modifying
    @Query("UPDATE Product p SET p.price = :newPrice WHERE p.id = :id")
    void updateProduct(@Param("id") long id,
                       @Param("newPrice" int price)

}
```

## Create and save a query by `@NamedQuery`

Create and save the query at the `@Entity` class
```java
@Entity
@NamedQuery(name = "Product.all", query = "SELECT p FROM Product p")
@NamedQuery(name = "Product.findById", query = "SELECT p FROM Product p WHERE p.id = :id")
public class Product {

    @Id
    private int id;

    private String name;
    private double price;
}



-----main-----
TypedQuery<Product> query = entityManager.createNamedQuery("Product.all", Product.class);
// more
```
