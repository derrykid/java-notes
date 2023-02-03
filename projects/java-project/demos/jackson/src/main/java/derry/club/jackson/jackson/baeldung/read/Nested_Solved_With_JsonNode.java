package derry.club.jackson.jackson.baeldung.read;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import derry.club.jackson.jackson.baeldung.read.custom.deserializer.Nested_Solved_JsonNode_Deserializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This is done in main method.
 * Alternatively, we can create a custom deserializer
 * {@link derry.club.jackson.jackson.baeldung.read.custom.deserializer.Nested_Solved_JsonNode_Deserializer}
 * then annotated the class with it
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonDeserialize(using = Nested_Solved_JsonNode_Deserializer.class)
public class Nested_Solved_With_JsonNode {

    private String id;
    private String name;
    private String brandName;
    private String ownerName;

    public static void main(String[] args) throws JsonProcessingException {
        useCustomDeserialzer();
//        useJsonNode();
    }

    private static void useJsonNode() throws JsonProcessingException {
        String input = """
                {
                    "id": "957c43f2-fa2e-42f9-bf75-6e3d5bb6960a",
                    "name": "The Best Product",
                    "brand": {
                        "id": "9bcd817d-0141-42e6-8f04-e5aaab0980b6",
                        "name": "ACME Products",
                        "owner": {
                            "id": "b21a80b1-0c09-4be3-9ebd-ea3653511c13",
                            "name": "Ultimate Corp, Inc."
                        }
                    }
                }
                """;

        JsonNode node = new ObjectMapper().readTree(input);

        var product = new Nested_Solved_With_JsonNode();
        product.setId(node.get("id").asText());
        product.setName(node.get("name").asText());
        product.setBrandName(node.get("brand").get("name").asText());
        product.setOwnerName(node.get("brand").get("owner").get("name").asText());

        System.out.println(product);

    }

    private static void useCustomDeserialzer() throws JsonProcessingException {
        String input = """
                {
                    "id": "957c43f2-fa2e-42f9-bf75-6e3d5bb6960a",
                    "name": "The Best Product",
                    "brand": {
                        "id": "9bcd817d-0141-42e6-8f04-e5aaab0980b6",
                        "name": "ACME Products",
                        "owner": {
                            "id": "b21a80b1-0c09-4be3-9ebd-ea3653511c13",
                            "name": "Ultimate Corp, Inc."
                        }
                    }
                }
                """;

        ObjectMapper mapper = new ObjectMapper();

        var product = mapper.readValue(input,
                Nested_Solved_With_JsonNode.class);

        System.out.println(product);

    }
}
