package derry.club.jackson.jackson.baeldung.map.serialize;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import derry.club.jackson.jackson.baeldung.map.entity.Pair;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
public class Object_Object {

    @JsonProperty("root")
    private Map<Pair, Pair> map;

    public static void main(String[] args) throws JsonProcessingException {
        Pair keyPair = new Pair("key1", "value1");
        Pair valuePair = new Pair("key2", "value2");

        var object = new Object_Object(new HashMap<>());
        object.getMap().put(keyPair, valuePair);

        String output = new ObjectMapper().writerWithDefaultPrettyPrinter()
                .writeValueAsString(object);

        System.out.println(output);

    }
}
