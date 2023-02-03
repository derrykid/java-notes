package derry.club.pokemon.model.gamedata.poke.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import derry.club.pokemon.model.gamedata.poke.PokemonType;

import java.io.IOException;

public class PokemonTypeDeserializer extends JsonDeserializer<PokemonType> {
    @Override
    public PokemonType deserialize(JsonParser jsonParser,
                                   DeserializationContext deserializationContext)
            throws IOException {

        JsonNode rootNode = jsonParser.getCodec().readTree(jsonParser);

        return rootNode.size() == 1 ? getSingleType(rootNode) : getComboType(rootNode);
    }

    private PokemonType getSingleType(JsonNode rootNode) {
        var node = rootNode.iterator().next();
        String typeName = node.at("/type/name").asText();
        return searchPokemonType(typeName);
    }

    private PokemonType getComboType(JsonNode node) {

        PokemonType[] pokeTypes = new PokemonType[2];

        // get 2 types and save to array
        int i = 0;
        for (JsonNode each : node) {
            String typeName = each.at("/type/name").asText();
            PokemonType found = searchPokemonType(typeName);
            pokeTypes[i] = found;
            i++;
        }
        // determine the type
        String combo = pokeTypes[0].name() + "_" + pokeTypes[1].name();
        return searchPokemonType(combo);
    }

    /**
     * Identify the {@link PokemonType}
     * @param stringName - the string presentation of the PokemonType
     */
    private PokemonType searchPokemonType(String stringName) {
        for (PokemonType per : PokemonType.values()) {
            if (per.name().equalsIgnoreCase(stringName)) {
                return per;
            }
        }
        return PokemonType.UNKNOWN;
    }
}
