package derry.club.jackson.jackson.baeldung.node.deserialer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import derry.club.jackson.jackson.baeldung.node.Deserializer_Demo;

import java.io.IOException;

public class MyCustomOne extends StdDeserializer<Deserializer_Demo.Item> {

    public MyCustomOne() {
        this(null);
    }

    protected MyCustomOne(Class<?> vc) {
        super(vc);
    }

    @Override
    public Deserializer_Demo.Item deserialize(JsonParser jsonParser,
                                              DeserializationContext deserializationContext)
            throws IOException, JacksonException {

        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        int id = node.get("id").asInt();
        String itemName = node.get("itemName").asText();
        int userId = node.get("createdBy").asInt();

        return new Deserializer_Demo.Item(id, itemName, new Deserializer_Demo.Owner(userId));
    }
}
