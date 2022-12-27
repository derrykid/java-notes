### Java Packages & API

Java contains many predefined classes that are grouped into categories of related classes called **packages** . Together, these are known as the Java Application Programming Interface (Java API), or the Java class library.

- Built-in packages (from Java API)
- User-defined packages

------------------

##### Built-in packages with `import`

A library of pre-written classes. Included in JDE.

Divides into **packages** and **classes**. Meaning we can either import a single class, or a whole package that conatin all the classes.

```java
import package.name.Class;   // Import a single class
import package.name.*;   // Import the whole package

// example
import java.util.Scanner;
import java.util.*; // include ArrayList, Scanner, etc.
```

##### User-defined Packages with `package` 

Java uses a file system directory to store them.

└── root
  └── mypack
    └── Main.java

```java
package mypack;
class MyPackageClass {
  public static void main(String[] args) {
    System.out.println("This is my package!");
  }
}
```
