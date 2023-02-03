package derry.club.jackson.jackson.baeldung.write;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

@Data
@JsonPropertyOrder({"name", "id"})
public class Set_Order_JsonPropertyOrder {
    private int id;
    private String name;

    public static void main(String[] args) throws JsonProcessingException {
        var object = new Set_Order_JsonPropertyOrder();
        object.setId(1);
        object.setName("John");

        var output = new ObjectMapper().writerWithDefaultPrettyPrinter()
                .writeValueAsString(object);

        System.out.println(output);
    }
}
