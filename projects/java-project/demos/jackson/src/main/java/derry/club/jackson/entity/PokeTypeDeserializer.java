package derry.club.jackson.entity;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class PokeTypeDeserializer extends JsonDeserializer<PokemonType> {

    @Override
    public PokemonType deserialize(JsonParser jsonParser,
                                   DeserializationContext deserializationContext)
            throws IOException {

        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        String type = node.path("type").path("name").asText();

        for (PokemonType per : PokemonType.values()) {
            if (per.toString().equalsIgnoreCase(type)) {
                return per;
            }
        }
        return null;
    }
}
