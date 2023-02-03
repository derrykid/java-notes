package derry.club.jackson.entity;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class StatsDeserializer extends JsonDeserializer<Stats> {
    @Override
    public Stats deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        JsonNode rootNode = jsonParser.getCodec().readTree(jsonParser);

        int hp = 0;
        int attack = 0;
        int defense = 0;
        int specialAttack = 0;
        int specialDefense = 0;
        int speed = 0;

        for (JsonNode node : rootNode) {
            String statName = node.at("/stat/name").asText();
            int baseValue = node.at("/base_stat").asInt();
            switch (statName) {
                case "hp" -> hp = baseValue;
                case "attack" -> attack = baseValue;
                case "defense" -> defense = baseValue;
                case "special-attack" -> specialAttack = baseValue;
                case "special-defense" -> specialDefense = baseValue;
                case "speed" -> speed = baseValue;
            }
        }
        return new Stats(hp, attack, defense, specialAttack, specialDefense, speed);
    }
}
