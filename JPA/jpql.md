[toc]

[jpql, read this first](https://thorben-janssen.com/jpql/#Entity_Model) 
# What is JPQL?

> JPQL is Java Persistence Query Language defined in JPA specification. It is used to create queries against entities to store in a relational database. JPQL is developed based on SQL syntax. But it won’t affect the database directly.

- instead of specifying table name, specify `@Entity` class name in the JPQL query

## Read

### Select

```java
SELECT c.fieldName FROM EntityClass c
[WHERE ...]
[GROUP BY ... [HAVING ...]]
[ORDER BY ...]
```

JPQL Example
```sql
JPQL - SELECT p FROM Product p          // p is the alias
                     ^^^^^^^ 
                    this is the class name

## another example

String jpql = "SELECT p FROM Product p";
TypedQuery<Product> q = entityManager.createQuery(jpql, Product.class); // TypedQuery extends query

List<Product> list = q.getResultList();
```

### Where clause

```java
"Select e from Employee e where e.salary Between 30000 and 40000
```

<++>
```
String jpql = "SELECT e FROM Employee e WHERE e.email = :arg"
```

This `:arg` will be replaced by your method parameter.

#### Where ... AND ...

```java
SELECT e FROM Employee e WHERE e.email = :arg1 AND arg2
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

#### `Where ... Like...`

| char | description   |
|------|---------------|
| _    | one character |
| %    | one or many   |

```java
Select e from Employee e where e.ename LIKE 'M%'
String jqpl = "SELECT e FROM Employee e WHERE e.name Like :arg1";
```

### `ORDER BY`

```java
Select e from Employee e ORDER BY e.ename ASC
```

### JOIN

#### Inner Join

There are 2 approaches:

1. If you define the relationship in the entity class, you don't have to code 2 entity classes in the query
2. Explicitly use `WHERE` to join 2 entity classes, in which they don't know each other

Approach 1:

Syntax:
```java
SELECT c1, e1, FROM Class c JOIN c.myFieldE e1
                                            ^^
                                            The second entity alias
```
Example:
```java
SELECT a, b FROM Author a JOIN a.books b
```

**As long as in the entity class, you specify the relationship between the tables, JPA will join by association.** 

---

Approach 2 Example:
```java
SELECT b, p FROM Book b, Publisher p WHERE b.fk_publisher = p.id
```

#### Left Join

```java
SELECT a, b FROM Author a LEFT JOIN a.books b
```

#### JOIN with condition

```java
	
SELECT a, p FROM Author a JOIN a.publications p ON p.publishingDate > ?1
```

#### Path expressions or implicit joins

```java
SELECT b FROM Book b WHERE b.publisher.name LIKE ‘%es%
```

As you can see, I use the ‘.’ operator to navigate via the publisher attribute of the Book entity b to the related Publisher entities. That creates an implicit join between the Book and Publisher entity which will be translated into an additional join statement in the SQL query.

### Distinct

JPQL
```java
SELECT DISTINCT a.lastName FROM Author a
```

## Polymorphism read - Select super class, all subclasses will be selected

```java
SELECT p FROM Publication p
```

All subclasses entities like BlogPost, Book, will be all selected.

## `@NamedQuery` - predefined query

> Predefined the query with variable, then you replace the variable with the parameters

Syntax: `:variable`. The string that will be placed.

Example:
```java
SELECT p FROM Product p WHERE p.id = :id

// In spring JPA repository
void updateProduct(@Param("id") long id)
```

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

## Constructor read

> Constructor references are a good projection for read-only use cases. They’re more comfortable to use than scalar value projections and avoid the overhead of managed entities.

*What if it uses builder pattern or setters?* 

```java
SELECT new com.example.entity.Author(a.id, a.firstName, a.lastName) FROM Author a
```


# Spring Data JPA

Suppose we want to change the `Product` price

```java
public interface Product extends JpaRepository<Product, Long> {

    @Modifying
    @Query("UPDATE Product p SET p.price = :newPrice WHERE p.id = :id")
    void updateProduct(@Param("id") long id,
                       @Param("newPrice" int price)

}
```

