## No need to create `dataSource` in Configuration
As long as you enter the datasouce related properties in the `application.properties`, spring boot will create the Bean of dataSource for you.

Therefore you can inject that bean for your application.
