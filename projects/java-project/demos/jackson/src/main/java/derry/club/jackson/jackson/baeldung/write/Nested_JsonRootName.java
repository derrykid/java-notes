package derry.club.jackson.jackson.baeldung.write;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.Data;

@Data
@JsonRootName(value = "dog")
public class Nested_JsonRootName {

    /**
     * Note that you have to enable the objectMapper with {@link SerializationFeature}
     */

    private String name;
    private int id;
    private String color;

    public static void main(String[] args) throws JsonProcessingException {

        var object = new Nested_JsonRootName();
        object.setName("John");
        object.setId(12);
        object.setColor("Yellow");

        var output = new ObjectMapper()
                .enable(SerializationFeature.WRAP_ROOT_VALUE)
                .writerWithDefaultPrettyPrinter().writeValueAsString(object);
        System.out.println(output);

    }
}
