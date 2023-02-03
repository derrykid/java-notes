package derry.club.pokemon.model.gamedata.poke.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import derry.club.pokemon.model.gamedata.poke.LearnMethod;
import derry.club.pokemon.model.gamedata.poke.Move;
import derry.club.pokemon.model.gamedata.poke.Version;

import java.io.IOException;

public class MoveDeserializer extends JsonDeserializer<Move> {
    @Override
    public Move deserialize(JsonParser jsonParser,
                            DeserializationContext deserializationContext)
            throws IOException {

        JsonNode rootNode = jsonParser.getCodec().readTree(jsonParser);

        String moveName = getMoveName(rootNode);
        LearnMethod method = getMethod(rootNode);
        int learnLevel = getLevel(rootNode);
        int id = getMoveId(rootNode);

        Move move = new Move();
        move.setId(id);
        move.setMoveName(moveName);
        move.setLearnMethod(method);
        move.setLevelLearnAt(learnLevel);

        return move;
    }

    /**
     * This node doesn't consist of move id. To get move id, parse the move url
     * @param rootNode
     * @return move id
     */
    private int getMoveId(JsonNode rootNode) {
        String moveUrl = rootNode.at("/move/url").asText();

        // in form of :
        // https://pokeapi.co/api/v2/move/14/
        String[] strings = moveUrl.split("/");

        return Integer.parseInt(strings[strings.length - 1]);
    }

    private int getLevel(JsonNode rootNode) {
        JsonNode levelNode = rootNode.at("/version_group_details");
        return levelNode.iterator().next().at("/level_learned_at").asInt();
    }

    private LearnMethod getMethod(JsonNode rootNode) {
        JsonNode methodNode = rootNode.at("/version_group_details");

        for (JsonNode versionDetailNode : methodNode) {
            String method = versionDetailNode.at("/move_learn_method/name").asText();

            for (LearnMethod per : LearnMethod.values()) {
                if (per.toString().equalsIgnoreCase(method)) {
                    return per;
                }
            }

        }
        return LearnMethod.UNKNOWN;
    }

    private String getMoveName(JsonNode rootNode) {
        return rootNode.at("/move/name").asText();
    }

}
