In order to use *Flyway*, first step is to add dependency in *build.gradle*
```
dependencies{
    // flyway dependency
    implementation group: 'org.flywaydb', name: 'flyway-core', version: '8.2.0'

    // jdbc dependency of database, e.g. MySQL
    implementation group: 'mysql', name: 'mysql-connector-java', version: '8.0.18'
}
```

Then add the config below the dependency
```
flyway {
    url = 'jdbc:mysql://localhost:3306/Demo'
    user = 'root'
    password = 'root'
}
```

The migration `sql` file is located at `src/main/resources/db/migration/V1__Create_Person_table.sql`.

**Note that V1 is followed by 2 underscores.** 
