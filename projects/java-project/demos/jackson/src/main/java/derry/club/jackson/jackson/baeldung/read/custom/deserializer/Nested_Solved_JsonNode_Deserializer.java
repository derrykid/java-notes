package derry.club.jackson.jackson.baeldung.read.custom.deserializer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import derry.club.jackson.jackson.baeldung.read.Nested_Solved_With_JsonNode;

import java.io.IOException;

public class Nested_Solved_JsonNode_Deserializer extends JsonDeserializer<Nested_Solved_With_JsonNode> {

//    public Nested_Solved_JsonNode_Deserializer() {
//        this(null);
//    }
//
//    protected Nested_Solved_JsonNode_Deserializer(Class<?> vc) {
//        super(vc);
//    }
//
    @Override
    public Nested_Solved_With_JsonNode deserialize(JsonParser jsonParser,
                                                   DeserializationContext deserializationContext) throws IOException, JacksonException {

        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        var product = new Nested_Solved_With_JsonNode();

        product.setId(node.get("id").asText());
        product.setName(node.get("name").asText());
        product.setBrandName(node.get("brand").get("name").asText());
        product.setOwnerName(node.get("brand").get("owner").get("name").asText());

        return product;
    }
}
