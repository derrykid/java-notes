package derry.club.jackson.jackson.baeldung.objectmapper;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

@Data
public class Ignore_Unknown_Input {

    private String color;
    private String type;


    public static void main(String[] args) throws JsonProcessingException {
        String jsonString
                = "{ \"color\" : \"Black\", \"type\" : \"Fiat\", \"year\" : \"1970\" }";

        var objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        var demo = objectMapper.readValue(jsonString, Ignore_Unknown_Input.class);
        System.out.println(demo);

    }
}
