# Java Annotations

Java annotations are used to provide meta data for Java code.

As meta data, it doesn't directly affect the execution of your code, although some can actually be used for that purpose.

It is used for the following purposes:

- Compiler instructions
- Build-time instructions
- Runtime instructions

## Annotation basic

In shortest form:
```java
@Entity
```

The `@` signals to the compiler that this is an annotation.

## Annotation Elements

An annotation can have elements for which you can set values. An element is like an attribute or parameter.

```java
@Entity(tablename = "vehicles")
```

Elements are closed inside the parentheses.

In case an annotation contains just a single element, it's convention to name that element value:

```java
@InsertNew(value = "true")
```

Or even shorter
```java
@InsertNew("true")
```

## Annotation Placement

You can place the annotations on:

- class
- interface
- method
- method parameter
- field
- local variable

The following example is made up:
```java
@Entity
public class Vehicle {

    @Persistent
    protected String vehicleName = null;


    @Getter
    public String getVehicleName() {
        return this.vehicleName;
    }

    public void setVehicleName(@Optional vehicleName) {
        this.vehicleName = vehicleName;
    }

    public List addVehicleNameToList(List names) {

        @Optional
        List localNames = names;

        if(localNames == null) {
            localNames = new ArrayList();
        }
        localNames.add(getVehicleName());

        return localNames;
    }

}
```

## Built-in annotations

Java comes with 3 built-in annotations which are used to give the Java compiler instructions.

- `@Deprecated`
- `@Override`
- `@SuppressWarnings`
- `@Contended`

### @Deprecated

Signals that the method should no longer be used. If you use it, the compiler will give you a warning:
```java
@Deprecated
public class convert() {

}
```

You should also use the corresponding @deprecated JavaDoc symbol to explain why and the alternatives to use:
```java
@Deprecated
/**
  @deprecated Use MyNewComponent instead.
*/
public class MyComponent {

}
```

### @Override

The @Override Java annotation is used above methods that override methods in a superclass. If the method does not match a method in the superclass, the compiler will give you an error.

The benefits using this annotation is that you mark you subclass method that it overrides the superclass method. If you change the method signature, the compiler will tell you the error.

### @SuppressWarnings

It makes the compiler suppress warnings for a given method. For instance, if a method calls a deprecated method, or makes an insecure type cast, the compiler may generate a warning.

```java
@SuppressWarnings
public void methodWithWarning() {
}
```

## Create own Java Annotations

**Notice the `@interface` keyword. It signals to Java compiler that this is a annotation definition.** 

```java
@interface MyAnnotation {
    String value();
    String name();
    int age();
    String[] newNames();
}
```

To use the above annotation, you could use like:
```java
@MyAnnotation(
    value="123",
    name="Derry",
    age=22,
    newNames={"Derry", "Kid"}
)
public class Demo{
}
```

You can set default value as well:
```java
@interface MyAnnotation {
    String value() default "";
    // skip
}
```

By doing so, you can optionally leave the value in parentheses:
```java
@MyAnnotation(
    name="Derry",
    age=22,
    newNames={"Derry", "Kid"}
)
public class MyClass {
}
```

## @Retention

You can specify for your annotation if it should be available at runtime, for inspection via reflection. You can use `@Retention`

```java
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation {
    String value() default "";
}
```

There are other available retention policies. But we won't cover it here.

## @Target

It's used to specify which Java elements your annotation can be used to annotation.

An example that used to target method only:
```java
@Target({ElementType.METHOD})
public @interface MyAnnotation {
    String value();
}
```

There are more available ones:

- `ElementType.ANNOTATION_TYPE`
- `ElementType.CONSTRUCTOR`
- `ElementType.FIELD`
- `ElementType.LOCAL_VARIABLE`
- `ElementType.METHOD`
- `ElementType.PACKAGE`
- `ElementType.PARAMETER`
- `ElementType.TYPE`
- `ElementType.TYPE_PARAMETER`
- `ElementType.TYPE_USE`

## @Inherited

> The @Inherited annotation signals that a custom Java annotation used in a class should be inherited by subclasses;


```java
@Inherited
public @interface MyAnnotation {

}

@MyAnnotation
public class MySuperClass { ... }

public class MySubClass extends MySuperClass { ... }
```

The subclass extends super class. Implicitly, it inherits the `@MyAnnotation` as well.

## @Documented

The @Documented annotation is used to signal to the JavaDoc tool that your custom annotation should be visible in the JavaDoc for classes using your custom annotation.

```java
@Documented
public @interface MyAnnotation {

}

@MyAnnotation
public class MySuperClass { ... }
```

When generating JavaDoc for the MySuperClass class, the @MyAnnotation is now included in the JavaDoc.
