package derry.club.pokemon.model.gamedata.poke;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import derry.club.pokemon.model.gamedata.pocket.Item;
import derry.club.pokemon.model.gamedata.poke.deserializer.CaughtPokemonDeserializer;
import derry.club.pokemon.model.gamedata.trainer.Trainer;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class CaughtPokemon implements Pokemon {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private int id;

    private int level;
    private String name;

    private int pokedexId;


    @OneToOne(
            cascade = {
                    CascadeType.MERGE,
                    CascadeType.PERSIST
            }
    )
    private Item item;

    @OneToOne(
            cascade = {
                  CascadeType.PERSIST,
                  CascadeType.MERGE
            }
    )
    @JsonIgnore
    private Trainer trainer;

}
