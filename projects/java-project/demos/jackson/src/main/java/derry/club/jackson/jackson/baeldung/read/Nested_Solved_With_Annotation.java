package derry.club.jackson.jackson.baeldung.read;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Nested_Solved_With_Annotation {

    private String id;
    private String name;
    private String brandName;
    private String ownerName;

    @JsonProperty("brand")
    private void unpackedBrand(Map<String, Object> map) {
        this.brandName = (String) map.get("name");
        Map<String , Object> ownerMap = (Map<String, Object>) map.get("owner");
        this.ownerName = (String) ownerMap.get("name");
    }

    public static void main(String[] args) throws JsonProcessingException {
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
        var product = new ObjectMapper().readValue(input, Nested_Solved_With_Annotation.class);
        System.out.println(product);
    }
}
