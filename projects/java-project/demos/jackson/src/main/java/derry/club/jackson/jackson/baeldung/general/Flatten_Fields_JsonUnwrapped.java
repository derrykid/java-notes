package derry.club.jackson.jackson.baeldung.general;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Flatten_Fields_JsonUnwrapped {

    public int id;

    @JsonUnwrapped
    public Name name;

    @AllArgsConstructor
    public static class Name {
        public String firstName;
        public String lastName;
    }

    public static void main(String[] args) throws JsonProcessingException {
        var object = new Flatten_Fields_JsonUnwrapped();
        object.setId(12);
        object.setName(new Name("John", "Wick"));

        var output = new ObjectMapper().writerWithDefaultPrettyPrinter()
                .writeValueAsString(object);

        System.out.println(output);
    }

}
