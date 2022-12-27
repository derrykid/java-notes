HttpServletRequest:

##### `getParameter()` get single parameter value
Use it to get the parameter value

```java
String username = request.getParameter("name");
```
If the parameter needs to parse to Integer, use `Integer.parseInt()`.

##### `getParameterValues()` to get the array of parameter
If the parameter is `param=10&param=20&param=30`, we can use this method to get the array

```java
String[] values = request.getParameterValues("param");
```

##### `getParameterNames()` to get the enum of the parameter names
```java
Enumeration<String> e = request.getParameterNames();
while(e.hasMoreElements()) {
    String param = e.nextElement();
}
```

##### `getParameterMap()` get the Map of the parameter
It returns `Map<String, String[]>`. It's used for the time when there are multiple parameters and multiple values.
