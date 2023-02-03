package derry.club.jackson.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

@Data
@JsonDeserialize(using = StatsDeserializer.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Stats {

    private final int hp;
    private final int attack;
    private final int defense;
    private final int specialAttack;
    private final int specialDefense;
    private final int speed;

    public int getTotal() {
        return hp + attack + defense + specialAttack + specialDefense + speed;
    }
}
