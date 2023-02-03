# Using foreign key

## Unidirectional

> Pokemon table has a foreign key `stat_id`

- Only `Pokemon` table knows `Stats` single direction
- All annotations are marked at `Stats` filed in `Pokemon`

Pokemon table

| pokedexId | name    | stats_id |
|-----------|---------|---------|
| 25        | pikachu | 1       |

---

Stats table
| id | attack | defense |
|----|--------|---------|
| 1  | 30     | 20      |

Application code:
```java
@Data
@Entity
public class Pokemon {

    // fields

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
        name = "stat_id",
        referencedColumnName = "sId"
    )
    private Stats stats;

}

---
@Data
@Entity
public class Stats {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private int sId;
    private int attack;
    private int defense;

}
```

## `@JoinColumn` at `Pokemon` table field `Stats`

### `@JoinColumn(name = "stats_id")`

`@JoinColumn(name = "stats_id")`

The `name` attribute sets the foreign key column name. 

Default is : `stats_id` -> `field_pk`

- `field` being the field name in owner side
- `pk` being the primary key name in owned

### `@JoinColumn(referencedColumnName = "sId")`

The owned side primary key name. In this example, it can be ignored.


## Bidirectional

> Not all cases need bidirectional, it will have overheads as well. Think if you really need it.

For that, add one more field to the class, and add annotations.
```java
public class Stats{

    // fields

    @OneToOne( mappedBy = "stats")
    private Pokemon pokemon;

}
```

`mappedBy` declares this class/field is the owned side. 

# Create a new table

- key point - `@JoinTable`

To achieve this, use `@JoinTable` annotation:
```java
public class Pokemon {

        // other fields

        @JoinTable(
            name = "tableName",
            joinColumns = @JoinColumn(name = "pk1"),
            inverseJoinColumns = @JoinColumn(name = "pk2")
        )
        private Stats stats;
}
```

You can remove `@JoinColumn(mappedBy = "xxx")` in another class.
