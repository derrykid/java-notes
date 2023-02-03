package derry.club.jackson.jackson.baeldung.node;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Path_At_Method {
    public static void main(String[] args) throws JsonProcessingException {
        String input = """
                {
                  "identification" :  {
                        "name" : "James",
                        "ssn": "ABC123552"
                    }
                }
                """;

        var mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(input);
        JsonNode name = root.at("/identification/name");
        System.out.println(name.asText());

        // alternative
        JsonNode test = root.path("identification").path("name");
        System.out.println(test.asText());

    }
}
