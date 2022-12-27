[toc]
[Jenkov blog](https://jenkov.com/tutorials/java-json/jackson-annotations.html) 

## Jackson general annotations

### @JsonProperty

We can add the @JsonProperty annotation to indicate the property name in JSON.

Let's use @JsonProperty to serialize/deserialize the property name when **we're dealing with non-standard getters and setters**:

```java
public class Person {

    private int id;
    private String name;

    @JsonProperty("name")
    public void setTheName(String name) {
        this.name = name;
    }

    @JsonProperty("name")
    public String getTheName() {
        return name;
    }
}
```

## Read + Write Annotations

### @JsonIgnore

Used to tell Jackson to ignore a certain property/field of a Java object. 

The following example, `personId` will not be read or written from/to JSON.
```java
public class PersonIgnore {
    
    @JsonIgnore
    public long personId = 0;
    public String name = null;
}
```

### @JsonIgnoreProperties

It's used to specify a list of instance fields of a class to ignore. It's placed above a class declaration.

In this example, *firstName and lastName* will be ignored.
```java
@JsonIgnoreProperties({"firstName", "lastName"})
public class PersonIgnoreProperties {
    public long personId = 0;

    public String firstName = null;
    public String lastName = null;
}
```

### @JsonIgnoreType

It is used to mark a whole type (class) to be ignored everywhere that type is used.

The example shows all Address instances will be ignored.
```java
public class PersonIgnoreType {

    @JsonIgnoreType
    public static class Address {
        public String streetName  = null;
        public String houseNumber = null;
        public String zipCode     = null;
        public String city        = null;
        public String country     = null;
    }

    public long    personId = 0;

    public String  name = null;

    public Address address = null;
}
```

### @JsonAutoDetect

It's used to tell Jackson to include properties which are not public, while reading or writing objects.

```java
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY )
public class PersonAutoDetect {

    private long  personId = 123;
    public String name     = null;

}
```

The JsonAutoDetect.Visibility class contains constans matching in Java:

- DEFAULT
- ANY
- NONE
- NON_PRIVATE
- PROTECTED_AND_PRIVATE
- PUBLIC_ONLY

## Read annotations - deserialization

This sections show the annotations that only affect how Jackson parses JSON into Java objects.

### @JsonSetter

This is used to signal that this field matching a JSON field.

A POJO Person class:
```java
public class Person {

    private long   personId = 0;
    private String name     = null;

    // getters and setters
}
```

Note that **id is not named personId.**
```json
{
  "id"   : 1234,
  "name" : "John"
}
```

We can use the `@JsonSetter` to notify jackson:
```java
public class Person {

    private long   personId = 0;
    private String name     = null;

    @JsonSetter("id")
    public void setPersonId(long personId) { this.personId = personId; }
    
    // setters and getters
}
```

### @JsonAnySetter

The Jackson annotation @JsonAnySetter instructs Jackson to call the same setter method for all unrecognized fields in the JSON object.

By "unrecognized" I mean all fields that are not already mapped to a property or setter method in the Java object.

```java
public class Bag {

    private Map<String, Object> properties = new HashMap<>();

    public void set(String fieldName, Object value){
        this.properties.put(fieldName, value);
    }

    public Object get(String fieldName){
        return this.properties.get(fieldName);
    }
}
```

A coming json:
```json
{
  "id"   : 1234,
  "name" : "John"
}
```

While jackson cannot map `id` and `name` to the `Bag` class field because the class contains no public fields or setter methods.

We can use the `@JsonAnySetter` for all unrecognized fields
```java
    @JsonAnySetter
    public void set(String fieldName, Object value){
        this.properties.put(fieldName, value);
    }
```

Now Jackson will call the set() method with the name and value of all unrecognized fields in the JSON object.

Keep in mind that this only has effect on unrecognized fields. If, for instance, you added a public name property or setName(String) method to the Bag Java class, then the name field in the JSON object would be mapped to that property / setter instead.

### @JsonCreator

It's used for constructor. At times you might face an immutable class, a class without setter method. You need to create the object by constructor.

```java
public class PersonImmutable {

    private long   personId   = 0;
    private String name = null;

    public PersonImmutable(long id, String name) {
        this.personId = id;
        this.name = name;
    }

    // getters
}
```

To tell jackson that it should call the **constructor** instead of setter, make use of `@JsonCreator`, also we have to mark the filed by `@JsonProperty` with the corresponding JSON field:
```java
    @JsonCreator
    public PersonImmutable(
            @JsonProperty("id")  long id,
            @JsonProperty("name") String name  ) {

        this.personId = id;
        this.name = name;
    }
```

### @JacksonInject

It is used to inject values into the parsed objects, instead of reading those values from the JSON.

You might want to mark the source where the JSON coming from. You can make use of this annotation.

```java
public class PersonInject {

    public long   id   = 0;
    public String name = null;

    @JacksonInject
    public String source = null;

}
```

In order to inject the value, you also have to make extra effort on objectMapper:
```java
InjectableValues inject = new InjectableValues.Std().addValue(String.class, "derry.club");

PersonInject personInject = new ObjectMapper().reader(inject).forType(PersonInject.class).readValue(new File("data.json"));
```

Notice how the value to inject into the source attribute is set in the InjectableValues addValue() method. Notice also that the value is only tied to the type String - not to any specific field name. It is the @JacksonInject annotation that specifies what field the value is to be injected into.

### @JsonDeserialize

You may want to map `true` or `false` to `1` or `0` sometimes. Or alternatively, you want to map/deserialize an object to another object.

You can make use of `@JsonDeserialize` annotation:
```java
public class PersonDeserialize {

    public long    id      = 0;
    public String  name    = null;

    @JsonDeserialize(using = OptimizedBooleanDeserializer.class)
    public boolean enabled = false;
}
```

We need to create a subclass of `JsonDeserializer<T>` for the annotation element:
```java
public class OptimizedBooleanDeserializer extends JsonDeserializer<Boolean> {

    @Override
    public Boolean deserialize(JsonParser jsonParser,
            DeserializationContext deserializationContext) throws
        IOException, JsonProcessingException {

        String text = jsonParser.getText();
        if("0".equals(text)) return false;
        return true;
    }
}
```

`JsonDeserializer<T>` can specify any type in generic brackets.

At last, you can deserialize an object with a custom deserializer:
```java
PersonDeserialize person = objectMapper
        .reader(PersonDeserialize.class)
        .readValue(new File("data/person-optimized-boolean.json"));
```

## Write Annotations - write to JSON - serialization

This section is used to serialize Java object to JSON.

### @JsonInclude

The Jackson annotation @JsonInclude tells Jackson only to include properties under certain circumstances. For instance, that properties should only be included if they are non-null, non-empty, or have non-default values.

```java
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PersonInclude {

    public long  personId = 0;
    public String name     = null;

}
```

This example will only include the name property if the value set for it is non-empty, meaning is not null and is not an empty string.

A more saying name for the @JsonInclude annotation could have been @JsonIncludeOnlyWhen.

### @JsonGetter

It tells Jackson that a certain field value should be obtained from calling a getters method instead of via direct field access. It's useful if Java class uses jQuery style for getters and setters. E.g. `personId()` instead of `getPersonId()` for getters and setters.

Example:
```java
public class PersonGetter {

    private long  personId = 0;

    @JsonGetter("id")
    public long personId() { return this.personId; }

    @JsonSetter("id")
    public void personId(long personId) { this.personId = personId; }
}
```

The result JSON object:
```java
{"id":0}
```

Also note that the `@JsonSetter` in the class is used to map Java object from JSON.

### @JsonAnyGetter

The @JsonAnyGetter Jackson annotation enables you to use a Map as container for properties that you want to serialize to JSON.

```java
public class PersonAnyGetter {

    private Map<String, Object> properties = new HashMap<>();

    @JsonAnyGetter
    public Map<String, Object> properties() {
        return properties;
    }
}
```

When seeing the @JsonAnyGetter annotation Jackson will obtain the Map returned from the method which @JsonAnyGetter annotates, and will treat each key-value pair in that Map as a property. In other words, all key-value pairs in the Map will be serialized to JSON as part of the PersonAnyGetter object.

### @JsonPropertyOrder

Usually, if you don't specify the order, the output JSON will follow the class filed definition. But you can use this annotation to change that.

```java
@JsonPropertyOrder({"name", "personId"})
public class PersonPropertyOrder {
    public long personId = 0;
    public String name = null;
}
```

### @JsonRawValue

The @JsonRawValue Jackson annotation tells Jackson that this property value should written directly as it is to the JSON output.

Example:
```java
public class PersonRawValue {
    public long   personId = 0;

    @JsonRawValue
    public String address = "{ \"street\" : \"Wall Street\", \"no\":1}";
}
```

This will help us to get this output:
```json
{"personId":0,"address":{ "street" : "Wall Street", "no":1}}
```

Without it:
```json
{"personId":0,"address":"{ \"street\" : \"Wall Street\", \"no\":1}"}
```

### @JsonValue

This annotation tells Jackson that it should not attempt to serialize the object itself. Instead, call the method on the object which serialize the object to a JSON string.

Note that Jackson will escape any quotation marks inside the String returned by the custom serialization, so you cannot return e.g. a full JSON object. For that you should use @JsonRawValue instead.

```java
public class PersonValue {

    public long   personId = 0;
    public String name = null;

    @JsonValue
    public String toJson(){
        return this.personId + "," + this.name;
    }
}
```

Output:
```
"0,null"
```

### @JsonSerialize

It's used to specify a custom serializer for a field in Java object.
```java
public class PersonSerializer {

    public long   personId = 0;
    public String name     = "John";

    @JsonSerialize(using = OptimizedBooleanSerializer.class)
    public boolean enabled = false;
}
```

Similar to `@JsonDeserialize` example:
```java
public class OptimizedBooleanSerializer extends JsonSerializer<Boolean> {

    @Override
    public void serialize(Boolean aBoolean, JsonGenerator jsonGenerator, 
        SerializerProvider serializerProvider) 
    throws IOException, JsonProcessingException {

        if(aBoolean){
            jsonGenerator.writeNumber(1);
        } else {
            jsonGenerator.writeNumber(0);
        }
    }
}
```

