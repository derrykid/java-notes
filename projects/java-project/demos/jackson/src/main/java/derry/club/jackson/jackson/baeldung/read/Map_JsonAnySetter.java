package derry.club.jackson.jackson.baeldung.read;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Map_JsonAnySetter {

    private String name;
    private int id;
    private Map<String, String> map = new HashMap<>();

    @JsonAnySetter
    public void add(String key, String value) {
        map.put(key, value);
    }

    public static void main(String[] args) throws JsonProcessingException {
        String input = """
                                {
                    "name":"My bean",
                    "attr2":"val2",
                    "attr1":"val1"
                }
                                """;
        var object = new ObjectMapper()
                .readValue(input, Map_JsonAnySetter.class);
        System.out.println(object);
    }
}
