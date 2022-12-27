## Class object

When know the name of class at compile time:
```java
Class myObjectClass = MyObject.class;
```

When know the name at runtime, you must provide full name, including package:
```java
String className = "com.example.util.ObjectClass";

Class class = Class.forName(className);
```

### get super class
```java
Class superClass = clazz.getSuperclass();
```

## Class name

Full name with package
```java
Class clazz = Dog.class;
String fullName = clazz.getName();
```

Only class name:
```java
String simpleName = clazz.getSimpleName();
```

## Modifiers

Each modifier is a flag bit that is either set or cleared:
```java
    Modifier.isAbstract(int modifiers)
    Modifier.isFinal(int modifiers)
    Modifier.isInterface(int modifiers)
    Modifier.isNative(int modifiers)
    Modifier.isPrivate(int modifiers)
    Modifier.isProtected(int modifiers)
    Modifier.isPublic(int modifiers)
    Modifier.isStatic(int modifiers)
    Modifier.isStrict(int modifiers)
    Modifier.isSynchronized(int modifiers)
    Modifier.isTransient(int modifiers)
    Modifier.isVolatile(int modifiers)
```

## Package info

```java
Package package = clazz.getPackage();
```

## Implemented interfaces

Interfaces are also represented by Class objects in Java Reflection.

```java
Class[] interfaces = clazz.getInterfaces();
```

Only the interfaces specifically declared implemented by a given class is returned. Even though a subclass extends a superclass which implements the interface, the subclass will not be in the array.

## Constructors

This will give you all constructors in array
```java
Constructor[] constructors = clazz.getConstructors();
```

If you know the precise parameter types of the constructor you want to access, you can do so rather than obtain the array all constructors.

If the constructor wants a string, you can do this:
```java
Constructor constructor = clazz.getConstructor(new Class[]{String.class});
```

### Get constructor params
```java
Class[] paramTypes = constructor.getParamterTypes();
```

### Instantiate object using constructor object

```java
Constructor constructor = Dog.class.getConstructor(int.class, String.class);
Dog johnDog = (Dog) constructor.newInstance(12, "John");
```

## Methods

```java
Method[] method = clazz.getMethods();
```

### Get the specific method

```java
Method method = clazz.getMethod("setName", new Class[]{String.class});
// or
Method method = clazz.getMethod("setName", String.class);
```

If the method takes no param, pass `null`
```java
Method method = clazz.getMethod("getName", null);
```

### get the params of the method
```java
Class[] parameterTypes = method.getParameterTypes();
```

### Get the return type
```java
Class returnType = method.getReturnType();
```

### Invoke method using method object

```java
//get method that takes a String as argument
Method method = Dog.class.getMethod("setName", String.class);
Object returnValue = method.invoke(null, "parameter-value1");
```
The `null` is the object you want to invoke the method on. If it's `static` method, you supply `null`. If it's not, supply an `object`.

An example of Dog.class:
```java
Method method = Dog.class.getMethod("setName", String.class);
Dog john = new Dog(12, "J");
System.out.println(john.getName());
Object returnValue = method.invoke(john, "parameter-value1");
System.out.println(john.getName());
```

Output:
```
J
parameter-value1
```

## Getters and setters inspection
- Getter - a method starts with 'get', takes 0 param, and return a value
- Setter - a method starts with 'set', takes 1 param, may or may not return a value

```java
public static void printGettersSetters(Class aClass){
  Method[] methods = aClass.getMethods();

  for(Method method : methods){
    if(isGetter(method)) System.out.println("getter: " + method);
    if(isSetter(method)) System.out.println("setter: " + method);
  }
}

public static boolean isGetter(Method method){
  if(!method.getName().startsWith("get"))      return false;
  if(method.getParameterTypes().length != 0)   return false;  
  if(void.class.equals(method.getReturnType()) return false;
  return true;
}

public static boolean isSetter(Method method){
  if(!method.getName().startsWith("set")) return false;
  if(method.getParameterTypes().length != 1) return false;
  return true;
}
```

## Fields

**Get the field that is declared public. If try to get the private field, it throws 'NoSuchFieldException'.** 

```java
Field[] method = aClass.getFields();
```

If you know the name of the field you want to access:
```java
Field field = clazz.getField("name");
```

### Field name
```java
Field field;
String fieldName = field.getName();
```

### Type

```java
Object type = field.getType();
```

### get and set the field values

Set the name from John to Tom. **Note, the field is public.** 
```java
Class clazz = Dog.class;
Field nameField = clazz.getField("name");
Dog dog = new Dog(12, "John");
Object value = nameField.get(dog);
System.out.println("Old dog name: " + value);
nameField.set(dog, "Tom");
System.out.println("New dog name: " + dog.getName());
```

## Annotations

[Annotations](https://jenkov.com/tutorials/java-reflection/annotations.html#pageToc) 

```java
Annotation[] annotations = aClass.getAnnotations();
```

## Access private fields and methods
Despite the common belief it is actually possible to access private fields and methods of other classes via Java Reflection. It is not even that difficult. This can be very handy during unit testing.


### Fields
- `Class.getField(String name)` & `Class.getFields()` only return public ones

Use `Class.getDeclaredField(String name)` or `Class.getDeclaredFields()`

```java
Dog aDog = new Dog(12, "John");

Field privateStringField = Dog.class.getDeclaredField("name");

privateStringField.setAccessible(true);

String fieldValue = (String) privateStringField.get(aDog);
System.out.println("fieldValue = " + fieldValue);
```

Output:
```
fieldValue = John
```

**Note that getDeclaredField("name")** is the call that returns the private field.
By calling Field.setAcessible(true) you turn off the access checks for this particular Field instance, for reflection only.

### Methods

- `Class.getMethod(String name, Class[] parameterTypes)` and `Class.getMethods()` only return public ones

Use `Class.getDeclaredMethod(String name, Class[] parameterTypes)` or `Class.getDeclaredMethods()`

```java
Dog oDog = new Dog(12, "Tom");

Method privateStringMethod = Dog.class.getDeclaredMethod("echo", null);

privateStringMethod.setAccessible(true);

String returnValue = (String)
        privateStringMethod.invoke(oDog, null);

System.out.println("returnValue = " + returnValue);
```
Output:
```
echo // the echo method prints 'echo'
returnValue = null
```

**Note that getDeclaredMethod("echo")** is the call that returns the private method.
By calling Method.setAcessible(true) you turn off the access checks for this particular method, for reflection only.
