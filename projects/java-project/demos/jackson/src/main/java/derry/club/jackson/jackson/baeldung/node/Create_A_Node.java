package derry.club.jackson.jackson.baeldung.node;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;

public class Create_A_Node {
    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.createObjectNode();
        System.out.println("Node" + node);
        JsonNode inFactory = JsonNodeFactory.instance.objectNode();
        System.out.printf("factory %s", inFactory);
    }
}
