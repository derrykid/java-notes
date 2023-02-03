package derry.club.jackson.jackson.baeldung.write;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import derry.club.jackson.jackson.baeldung.write.custom.serializer.CustomSerializer;
import lombok.Data;

@Data
public class Use_Custom_Serializer {

    private String name;

    @JsonSerialize(using = CustomSerializer.class)
    private boolean enabled;

    public static void main(String[] args) throws JsonProcessingException {
        var object = new Use_Custom_Serializer();
        object.setName("Event");
        object.setEnabled(true);

        var output = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(object);
        System.out.println(output);

    }
}
