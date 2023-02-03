package derry.club.jackson.jackson.baeldung.read;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import derry.club.jackson.jackson.baeldung.read.custom.deserializer.CustomDeserializer;
import lombok.Data;

@Data
public class Use_Custom_Deserializer {

    private int id;
    private String name;

    @JsonDeserialize(using = CustomDeserializer.class)
    private boolean enabled;

    public static void main(String[] args) throws JsonProcessingException {
        String input = """
                {
                    "name":"My bean",
                    "id": 22,
                    "enabled": 1
                }
                """;

        var object = new ObjectMapper()
                .readValue(input, Use_Custom_Deserializer.class);

        System.out.println(object);
    }
}
