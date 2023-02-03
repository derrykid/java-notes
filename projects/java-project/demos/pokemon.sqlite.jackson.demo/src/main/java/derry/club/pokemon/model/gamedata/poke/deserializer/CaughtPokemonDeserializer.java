package derry.club.pokemon.model.gamedata.poke.deserializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import derry.club.pokemon.model.gamedata.poke.CaughtPokemon;

import java.io.IOException;

public class CaughtPokemonDeserializer extends JsonDeserializer<CaughtPokemon> {
    @Override
    public CaughtPokemon deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        return null;
    }
}
