## Compile spring app

The Maven process compiles and builds a JAR, with dependencies, placing it into your application’s target directory. The spring-boot-maven-plugin in the pom.xml provides this process.

## Compile regular jar file

Add this plugin
```
<plugin>
  <artifactId>maven-assembly-plugin</artifactId>
  <version>3.0.0</version>
  <configuration>
    <descriptorRefs>
      <descriptorRef>jar-with-dependencies</descriptorRef>
    </descriptorRefs>
    <finalName>helloworld</finalName>
  </configuration>
</plugin>
```
