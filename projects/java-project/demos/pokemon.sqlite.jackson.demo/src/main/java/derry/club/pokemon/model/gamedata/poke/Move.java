package derry.club.pokemon.model.gamedata.poke;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import derry.club.pokemon.model.gamedata.poke.deserializer.MoveDeserializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using = MoveDeserializer.class)
@AllArgsConstructor
@NoArgsConstructor
public class Move implements Comparable<Move> {

    @Id
    @Column(name = "moveId")
    private int id;

    @Enumerated(EnumType.STRING)
    private LearnMethod learnMethod;
    private String moveName;
    private int levelLearnAt;

    @Override
    public int compareTo(Move move) {
        return Integer.compare(this.levelLearnAt, move.levelLearnAt);
    }

}
