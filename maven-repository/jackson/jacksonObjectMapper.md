[toc]
[Jackson tutorial](https://www.baeldung.com/jackson-object-mapper-tutorial) 

## Jackson Installation

The three JAR files in the Jackson JSON API are:

- Jackson core
- Jackson Annotations
- Jackson Databind

**Note that both `jackson-annotations`, `jackson-databind` have transitive dependencies to `jackson-core`.** It means that you just need to include that as dependency in you Maven POM file.

To read and write CBOR, you have to add `jackson-dataformat-cbor` artifact to classpath too.

### Maven dependencies

```xml
<dependency>
  <groupId>com.fasterxml.jackson.core</groupId>
  <artifactId>jackson-core</artifactId>
  <version>2.9.6</version>
</dependency>

<dependency>
  <groupId>com.fasterxml.jackson.core</groupId>
  <artifactId>jackson-annotations</artifactId>
  <version>2.9.6</version>
</dependency>

<dependency>
  <groupId>com.fasterxml.jackson.core</groupId>
  <artifactId>jackson-databind</artifactId>
  <version>2.9.6</version>
</dependency>
```

### gradle dependencies

Example: build.gradle
```
implementation 'com.fasterxml.jackson.dataformat:jackson-dataformat-csv:2.13.0'
implementation 'com.fasterxml.jackson.core:jackson-databind:2.13.0'
```

## Jackson ObjectMapper

> The Jackson ObjectMapper can parse JSON from a string, stream, or file. It creates Java object or object graph representing the parsed JSON.

It can be another way around. **It can also map Java object to JSON.** It maps JSON into Java Objects(deserialization), or Java Objects into JSON(serialization).


### ObjectMapper example

It can map to object by:

1. string
1. file
1. URL
1. InputStream
1. Byte Array
1. to Object array
1. to List

#### From string

Car class
```java
public class Car {
    private String brnad;
    private int numOfDoors;

    // constructor, getters and setters
}
```

Map to Java object
```java
ObjectMapper objectMapper = new ObjectMapper();
String carJson = "{ \"brand\" : \"Mercedes\", \"doors\" : 5 }";

try {

    Car car = objectMapper.readValue(carJson, Car.class);

    // get car brand and doors
    String brand = car.getBrand;
    int numOfDoors = car.getDoors();

} catch (IOException e) {
     e.printStackTrace();
}
```

#### From a file

```java
Object Mapper objectMapper = new ObjectMapper();

File file = new File("car.json");

Car car = objectMapper.readValue(file, Car.class);
```

#### From URL

```java
URL url = new URL("file:data/car.json");

// or from http
URL url2 = new URL("http://some.com/data.json");

Car car = objectMapper.readValue(url, Car.class);
```

#### To List

**Pay attention to the TypeReference.** 

```java
String jsonArray = "[{\"brand\":\"ford\"}, {\"brand\":\"Fiat\"}]";

List<Car> carList = objectMapper.readValue(jsonArray, new TypeReference<List<Car>>(){});
```

#### To map

```java
String jsonObject = "{\"brand\":\"ford\", \"doors\":5}";

Map<String, Object> jsonMap = objectMapper.readValue(jsonObject, new TypeReference<Map<String, Object>>(){});
```

### How ObjectMapper works?

By default Jackson maps JSON object to fields in a Java object by matching the names of the JSON field to the getter and setter. Jackson removes the "get" and "set" part and converts it to lowercase. E.g. `getBrand` matches `brand`. 

If you need to match JSON object fields in a different way, use *Jackson Annotations*.

### Ignore Unknown JSON fields

At times, there are more fields in JSON than in Java object fields. If you don't take care of extra fields comming from JSON, it will throws an exception: `field not found in java object`.

Here is how to configure the objectMapper to ignore the unknown fields:
```java
objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
```

### Set to fail on NULL JSON values for Primitive types

ObjectMapper by default ignores a `null` value for Primitive. We have to configure it.

```java
objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, true);
```

This will throw the exception we expect. We can use this to log or respond to the request.

The exception thrown may like:
```java
Exception in thread "main" com.fasterxml.jackson.databind.exc.MismatchedInputException:
    Cannot map `null` into type int
    (set DeserializationConfig.DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES to 'false' to allow)
 at [Source: (String)
    "{ "brand":"Toyota", "doors":null }"; line: 1, column: 29] (through reference chain: jackson.Car["doors"])
```

## Custom Deserializer

[custom deserializer example](https://jenkov.com/tutorials/java-json/jackson-objectmapper.html#custom-deserializer) 

## Java Object to JSON

You can use one of the following methods:

- writeValue()
- writeValueAsString()
- writeValueAsBytes()

### FileOutputStream

```java
objectMapper.writeValue(new FileOutputStream("output-2.json"), car);
```

### Write output as a string

```java
Car car = new Car();
car.brand = "BMW";
car.doors = 4;

String json = objectMapper.writeValueAsString(car);
System.out.println(json);
```

Output:
```java
{"brand":"BMW","doors":4}
```

### Write to a file

Example: write the object to a json file
```java
ObjectMapper objectMapper = new ObjectMapper();
Car car = new Car("yellow", "renault");
objectMapper.writeValue(new File("src/main/resources/car.json"), car);
```

### Custom serialization

[Custom serializer example](https://jenkov.com/tutorials/java-json/jackson-objectmapper.html#custom-serializer) 

## Jackson Date formats

By default, Jackson serializes `java.util.Date` object to its long value.

Reminder: How a `date` object usually looks like in Java.
```java
System.out.println(new Date());
```

Output:
```
Wed Jun 29 15:45:29 CST 2022
```

If we write `date` object to JSON:
```java
String output = objectMapper.writeValueAsString(new Date);
System.out.println(output);
```

JSON Output: **note it's long number** 
```java
{"date":1823621827}
```

### Date to String

For readability, you can use `SimpleDateFormat` that is supported by Jackson as well:
```java
SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

objectMapper.setDateFormat(dateFormat);

String output2 = objectMapper.writeValueAsString(new Date());
```

Output:
```
{"date":"2022-01-23"}
```

## JsonNode object 

At times, you don't know what will be the incoming data format, and how many fields you will receive. JsonNode will be a perfect approach to handle this.

JsonNode is Jackson's tree model (object graph model) for JSON. It reads JSON into a JsonNode instance, and write a JsonNode out to JSON.

*JsonNode is immutable.* 

### Read JsonNode from JSON

```java
String json = "{ \"f1\" : \"v1\" } ";

JsonNode jsonNode = objectMapper.readTree(json);

System.out.println(jsonNode.get("f1").asText());
```

Output:
```java
v1
```

### Write JsonNode to JSON
```java
JsonNode jsonNode = objectMapper.readTree(json);

String json = objectMapper.writeValueAsString(jsonNode);
```

### Get JsonNode field

An example json
```json
{
    "field1" : "value1",
    "field2" : 999
}
```

Get the fields
```java
JsonNode jsonNode = objectMapper.readTree(json);

JsonNode field1 = jsonNode.get("field1");
JsonNode field2 = jsonNode.get("field2");
```

### Get JsonNode field at Path

`at()` method can access a JSON field.

For example, there's a json data:
```json
{
  "identification" :  {
        "name" : "James",
        "ssn: "ABC123552"
    }
}
```

To get the "name", you can use the `at()` method:
```java
JsonNode nameNode = jsonNode.at("/identification/name");
```

**Note that the path expression path are Unix-like.** 

### Convert JsonNode field

We can convert the jsonNode field to some common data type. For example, the "f2" node has the value `123`.

```java
String f2 = jsonNode.get("f2").asText();
double f2d = jsonNode.get("f2").asDouble();
int f2i = jsonNode.get("f2").asInt();
long f2l = jsonNode.get("f2").asLong();
```

### Cope with null value when the field is null

```java
String json = "{ \"f1\":\"Hello\", \"f2\":null }";

JsonNode jsonNode = objectMapper.readTree(json);

String f2Value = jsonNode.get("f2").asText("Default");
```
There are also `asInt("")`, `asDouble("")`, `asLong("")` tastes of this. It can set the default value to it.

**If the field doesn't exist at all, you'll get a `NullPointerException`**

### Handle the Null fields

Either the field is set to null, or the field doesn't exist, we have to handle the data.

We can use the `isNull()` to check if the field set to null:
```
boolean isFieldValueNull = fieldNode.isNull();
```

Even better, you can do this:
```java
if (fieldNode == null || fieldNode.isNull()) {
    // do sth
}
```

### Traverse JsonNode graph

A JsonNode that represents a JSON object or JSON array. It can be traversed like any other object graph.

Here is an example of how to do it:
```java
public static void traverse(JsonNode root){
    
    if(root.isObject()){
        Iterator<String> fieldNames = root.fieldNames();

        while(fieldNames.hasNext()) {
            String fieldName = fieldNames.next();
            JsonNode fieldValue = root.get(fieldName);
            traverse(fieldValue);
        }
    } else if(root.isArray()){
        ArrayNode arrayNode = (ArrayNode) root;
        for(int i = 0; i < arrayNode.size(); i++) {
            JsonNode arrayElement = arrayNode.get(i);
            traverse(arrayElement);
        }
    } else {
        // JsonNode root represents a single value field - do something with it.
    }
}
```

### Iterate JsonNode fields

You can obtain all fields from a JsonNode using `fields()` method.
```java
Iterator<Map.Entry<String, JsonNode>> fields = jsonNode.fields();

while(fields.hasNext()) {

    Map.Entry<String, JsonNode> field = fields.next();

    String fieldName = field.getKey();
    JsonNode fieldValue = field.getValue();

    System.out.println(fieldName + " = " + fieldValue.asText());
}
```

### Iterate JsonNode field names

`fieldNames()` returns an Iterator that helps you to iterate all the field names of the JsonNode.

```java
Iterator<String> fieldNames = jsonNode.fieldNames();

while(fileNames.hasNext()) {
    String fieldName = filedNams.next();

    JsonNode field = jsonNode.get(fieldName);
]
```

## ObjectNode

You can create an ObjectNode instance which is a subclass of JsonNode.
```java
ObjectNode objectNode = objectMapper.createObjectNode();
```

### Set ObjectNode field

To set a field on a Jackson ObjectNode you can call its set() method, passing a field name String and JsonNode as parameters.

```java
ObjectNode parentNode = objectMapper.createObjectNode();

JsonNode childNode = objectMapper.readTree();

parentNode.set("child1", childNode);
```

The graph might look like:
```java
{
    child1: {
                // childNode
            },

    child2: {
                // childNode
            }
    // more to add
}
```

### Put ObjectNode field with primitive value

```java
objectNode.put("field1", "value1");
objectNode.put("field2", 123);
objectNode.put("field3", 999.999);
```

### Remove field
```java
objectNode.remove("fieldName");
```

## Reading and writing other data formats with Jackson ObjectMapper

Besides JSON, you can write these as well:

- CBOR
- MessagePack
- YAML

### CBOR

After adding the dependency, we can convert it to cbor byte array:
```java
ObjectMapper objectMapper = new ObjectMapper(new CBORFactory());

Employee employee = new Employee("John", "john@mail.com");

try {
    byte[] cborBytes = objectMapper.writeValueAsBytes(employee);
} catch (JsonProcessingException e) {
    e.printStackTrace();
]
```

If we want to read/deserialize the CBOR bytes back into object:
```java
byte[] cborBytes = null;

try {
    Employee employee = objectMapper.readValue(cborBytes, Employee.class);
} catch (JsonProcessingException e) {
    e.printStackTrace();
}
```

### YAML

[yaml tut](https://jenkov.com/tutorials/java-json/jackson-objectmapper.html#reading-and-writing-yaml-with-the-jackson-objectmapper) 

### MessagePack

[message pack tut](https://jenkov.com/tutorials/java-json/jackson-objectmapper.html#reading-and-writing-messagepack-with-the-jackson-objectmapper) <++>
