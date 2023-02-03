package derry.club.jackson.jackson.baeldung.map.serialize;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Map_Object_JsonAnySetter {

    private int id;
    private String name;
    private Map<String ,String> properties = new HashMap<>();

    @JsonAnySetter
    public void setProperties(String key, String value){
        this.properties.put(key, value);
    }

    public static void main(String[] args) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

        var object = new Map_Object_JsonAnySetter();
        object.setId(1);
        object.setName("john");
        object.getProperties().put("attr", "value1");

        String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        System.out.println(json);
    }

}
