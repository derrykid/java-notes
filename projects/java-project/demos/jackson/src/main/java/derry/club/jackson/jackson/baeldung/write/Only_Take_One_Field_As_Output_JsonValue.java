package derry.club.jackson.jackson.baeldung.write;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

@Data
public class Only_Take_One_Field_As_Output_JsonValue {

    public static enum Type {
        WATER, FIRE;
    }

    private String name;
    private int id;

    @JsonValue
    private Type type;

    public static void main(String[] args) throws JsonProcessingException {
        var object = new Only_Take_One_Field_As_Output_JsonValue();
        object.setId(12);
        object.setName("John");
        object.setType(Type.WATER);

        String output = new ObjectMapper().writerWithDefaultPrettyPrinter()
                .writeValueAsString(object);

        System.out.println(output);
    }

}
