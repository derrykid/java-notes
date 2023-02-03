package derry.club.jackson.entity;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

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

        return new Move.MoveBuilder()
                .moveName(moveName)
                .learnMethod(method)
                .levelLearnAt(learnLevel)
                .build();
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
