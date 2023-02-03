package derry.club.pokemon.model.gamedata.poke;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import derry.club.pokemon.model.gamedata.poke.deserializer.StatsDeserializer;
import lombok.Data;

import javax.persistence.*;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using = StatsDeserializer.class)
@Embeddable
public class Stats {

    private int hp;
    private int attack;
    private int defense;

    @Column(name = "special_attack")
    private int specialAttack;

    @Column(name = "special_defense")
    private int specialDefense;
    private int speed;

    @JsonIgnore
    public int getTotal() {
        return hp + attack + defense + specialAttack + specialDefense + speed;
    }
}
