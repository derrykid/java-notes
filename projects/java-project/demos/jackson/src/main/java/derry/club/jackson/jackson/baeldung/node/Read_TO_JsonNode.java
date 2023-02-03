package derry.club.jackson.jackson.baeldung.node;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Read_TO_JsonNode {

    public static void main(String[] args) throws JsonProcessingException {

        String json = "{ \"name\" : \"john\" } ";

        ObjectMapper mapper = new ObjectMapper();

        var node = mapper.readTree(json);

        System.out.println(node.get("name").asText());


    }

}
