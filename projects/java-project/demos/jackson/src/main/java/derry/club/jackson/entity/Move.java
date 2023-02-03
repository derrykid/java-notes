package derry.club.jackson.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using = MoveDeserializer.class)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Move implements Comparable<Move> {

    private LearnMethod learnMethod;
    private String moveName;
    private int levelLearnAt;

    @Override
    public int compareTo(Move move) {
        return Integer.compare(this.levelLearnAt, move.levelLearnAt);
    }


}
