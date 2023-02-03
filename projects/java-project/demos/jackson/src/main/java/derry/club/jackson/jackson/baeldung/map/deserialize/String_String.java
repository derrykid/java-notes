package derry.club.jackson.jackson.baeldung.map.deserialize;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class String_String {
    public static void main(String[] args) throws JsonProcessingException {
        String jsonInput = "{\"key\": \"value\"}";

        TypeReference<Map<String, String>> typeRef = new TypeReference<>() {};

        var map = new ObjectMapper().readValue(jsonInput, typeRef);

        System.out.println(map);

    }
}
