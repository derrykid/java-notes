package derry.club.pokemon.model.gamedata.poke;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "pokemon")
public class PokemonImpl implements Pokemon {

    @Id
    @JsonProperty("id")
    @Column(name = "pokedexId")
    private int id;
    private String name;

    @JsonProperty("types")
    private PokemonType pokemonType;

    @JsonProperty("moves")
    @OneToMany(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    private Collection<Move> moves;

    @JsonProperty("stats")
    @Embedded
    private Stats stats;
}
