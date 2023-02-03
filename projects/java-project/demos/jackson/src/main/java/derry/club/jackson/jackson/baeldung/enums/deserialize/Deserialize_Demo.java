package derry.club.jackson.jackson.baeldung.enums.deserialize;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.util.List;

@Data
public class Deserialize_Demo {
    private int id;
    private String name;
    private List<InnerEnum> type;

    public static void main(String[] args) throws JsonProcessingException {

        String input = """
                {
                  "id" : 1,
                  "name" : "Jack",
                  "type" : [
                     "FIRE",
                     "WATER"
                  ]
                }
                """;

        var object = new ObjectMapper().readValue(input, Deserialize_Demo.class);


        System.out.println(object.getType());

    }


    private enum InnerEnum {
        // by default, if prints enum, it prints the name of it, I set the output name here
//        @JsonProperty("flame")
        FIRE, WATER;

//        @JsonValue
//        private String initial;
//        private int value;
//
//        InnerEnum(String initial, int value) {
//            this.initial = initial;
//            this.value = value;
//        }
//
//        @JsonCreator
//        public InnerEnum getValues(
//                @JsonProperty("createInit") String init,
//                @JsonProperty("createValue") int value) {
//
//            for (InnerEnum per : values()) {
//                if (per.initial.equals(init) && per.value == value) {
//                    return per;
//                }
//            }
//
//            return null;
//        }
    }


}
