package derry.club.jackson.jackson.baeldung.write;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

@Data
public class Give_raw_without_wrapper_JsonRawValue {

    private String name;
    private int id;

    @com.fasterxml.jackson.annotation.JsonRawValue
    private double breed;

    public static void main(String[] args) throws JsonProcessingException {
        var object = new Give_raw_without_wrapper_JsonRawValue();
        object.setName("John");
        object.setId(11);
        object.setBreed(23.12);

        String output = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(object);
        System.out.println(output);
    }
}

