package derry.club.pokemon.exception;

public class TrainerNotFoundException extends RuntimeException {
    public TrainerNotFoundException(int id) {
        super("Trainer of id: " + id + " not found");
    }
}
