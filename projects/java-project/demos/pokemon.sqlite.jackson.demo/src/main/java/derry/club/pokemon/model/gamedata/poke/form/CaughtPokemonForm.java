package derry.club.pokemon.model.gamedata.poke.form;

import derry.club.pokemon.model.gamedata.pocket.Item;
import lombok.Data;

@Data
public class CaughtPokemonForm {

    private int level;
    private String name;
    private int pokedexId;
    private Item item;
}
