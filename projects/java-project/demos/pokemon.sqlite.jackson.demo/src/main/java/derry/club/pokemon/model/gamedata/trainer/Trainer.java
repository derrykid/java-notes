package derry.club.pokemon.model.gamedata.trainer;

import derry.club.pokemon.model.gamedata.poke.CaughtPokemon;
import derry.club.pokemon.model.gamedata.poke.Pokemon;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Trainer {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Setter(AccessLevel.NONE)
    private int id;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String name;

    private int money;
    private ZonedDateTime timeCreated;
    private ZonedDateTime lastLogin;

    public enum Gender {
        MALE, FEMALE;
    }
}
