package derry.club.jackson.jackson.baeldung.enums.serialize;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import derry.club.jackson.jackson.baeldung.enums.entity.Type;
import lombok.Data;

@Data
public class Serialize_Demo {

    private int id;
    private String name;
    private Type type;

    public static void main(String[] args) throws JsonProcessingException {

        var object = new Serialize_Demo();
        object.setId(1);
        object.setName("Demo");
        object.setType(Type.WIND);

        var mapper = new ObjectMapper();

        var output = mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(object);

        System.out.println(output);


    }

}
