package derry.club.jackson.jackson.baeldung.map.serialize;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import derry.club.jackson.jackson.baeldung.map.entity.Pair;
import derry.club.jackson.jackson.baeldung.map.serialize.serializer.PairSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Data
public class Object_String {

    @JsonSerialize(keyUsing = PairSerializer.class)
    @JsonProperty("root")
    private Map<Pair, String> map;

    public static void main(String[] args) throws JsonProcessingException {

        var object = new Object_String(new HashMap<>());

        Pair pair = new Pair("first", "second");

        object.getMap().put(pair, "myFirstPair");

        String output = new ObjectMapper().writerWithDefaultPrettyPrinter()
                .writeValueAsString(object);

        System.out.println(output);

    }
}
