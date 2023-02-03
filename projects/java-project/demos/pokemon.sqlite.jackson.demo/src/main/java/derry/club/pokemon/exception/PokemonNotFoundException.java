package derry.club.pokemon.exception;

public class PokemonNotFoundException extends RuntimeException {
    public PokemonNotFoundException(int id) {
        super("Pokemon id: " + id + " not found");
    }
}
