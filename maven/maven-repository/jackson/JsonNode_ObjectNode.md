## JsonNode vs. ObjectNode

> JsonNode is immutable.

## JsonNode

### Read JsonNode from JSON

```java
String json = "{ \"f1\" : \"v1\" } ";

JsonNode jsonNode = objectMapper.readTree(json);

System.out.println(jsonNode.get("f1").asText());
```

### Write JsonNode to Json

```java
String output = objectMapper.writeValueAsString(jsonNode);
```

### Get JsonNode Field
```json
{
    "field1" : "value1",
    "field2" : 999
}
```

Even though the field is String, we still get the `JsonNode` object
```java
JsonNode jsonNode; // get from objectMapper

JsonNode field1 = jsonNode.get("field1");
JsonNode field2 = jsonNode.get("field2");
```

### Get JsonNode field by `at()` and `path()`

```
{
  "identification" :  {
        "name" : "James",
        "ssn: "ABC123552"
    }
}
```

If we want to access the `name` value - *James*:
```java
JsonNode nameNode = jsonNode.at("/identification/name");

System.out.println(nameNode.asText());  // James
```

--------------

Alternatively, use `path(String path)`

```java
JsonNode nameNode = jsonNode.path("identification").path("name");
System.out.println(nameNode.asText());  // James
```


### Convert JsonNode Field value

```java
var f2Str = jsonNode.get("f2").asText();
var f2Dbl = jsonNode.get("f2").asDouble();
var f2Int = jsonNode.get("f2").asInt();
var f2Lng = jsonNode.get("f2").asLong();
```

### Handling Null Field
Either the field doesn't exist, or the field is set to null
```
// this case, the field2 is set to null
{
    "field1" = "value1",
    "field2" = null
}
```

We can do a null check by : `isNull()` or on the node itself
```java
JsonNode fieldNode = parentNode.get("fieldName");

if(fieldNode == null || fieldNode.isNull()) {
    // the field is either not present in parentNode, or explicitly set to null .
}
```

### Traverse JsonNode Graph

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

### Iterate JsonNode Fields

```java
Iterator<Map.Entry<String, JsonNode>> fields = jsonNode.fields();

while(fields.hasNext()) {
    Map.Entry<String, JsonNode> field = fields.next();
    String   fieldName  = field.getKey();
    JsonNode fieldValue = field.getValue();

    System.out.println(fieldName + " = " + fieldValue.asText());
}
```

## ObjectNode

> To create a JsonNode object graph you must be able to mutate the JsonNode instances in the graph, e.g. setting property values and child JsonNode instances etc. Being immutable, you cannot do that directly with a JsonNode.

> Instead you create an ObjectNode instance which is a subclass of JsonNode.

```java
ObjectMapper objectMapper = new ObjectMapper();

ObjectNode objectNode = objectMapper.createObjectNode();
```

### Set ObjectNode Field

```java
ObjectMapper objectMapper = new ObjectMapper();
ObjectNode parentNode = objectMapper.createObjectNode();

JsonNode childNode = readJsonIntoJsonNode();

parentNode.set("child1", childNode);
```

Set the values by `put`
```java
objectNode.put("field1", "value1");
objectNode.put("field2", 123);
objectNode.put("field3", 999.999);

// remove field
objectNode.remove("fieldName");
```

