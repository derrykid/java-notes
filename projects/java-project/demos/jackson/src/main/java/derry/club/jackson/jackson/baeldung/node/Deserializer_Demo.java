package derry.club.jackson.jackson.baeldung.node;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import derry.club.jackson.jackson.baeldung.node.deserialer.MyCustomOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class Deserializer_Demo {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
//    @JsonDeserialize(using = MyCustomOne.class)
    public static class Item {
        private int id;
        private String itemName;
        @JsonProperty("createdBy")
        private Owner owner;
    }

    @Data
    @AllArgsConstructor
    public static class Owner {
        @JsonSetter("createdBy")
        private int id;
    }


    public static void main(String[] args) throws JsonProcessingException {
        String input = """
                {
                    "id": 1,
                    "itemName": "theItem",
                    "createdBy": 2
                }""";

        var item = new ObjectMapper().readValue(input, Item.class);
        System.out.println(item);
    }
}
