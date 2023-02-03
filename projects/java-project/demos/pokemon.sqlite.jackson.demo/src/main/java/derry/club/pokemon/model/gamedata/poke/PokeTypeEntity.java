package derry.club.pokemon.model.gamedata.poke;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "PokemonType")
/**
 * This class is only used for saving PokemonType to database.
 */
public class PokeTypeEntity {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private int id;

    @Enumerated(EnumType.STRING)
    private PokemonType pokeType;
}
