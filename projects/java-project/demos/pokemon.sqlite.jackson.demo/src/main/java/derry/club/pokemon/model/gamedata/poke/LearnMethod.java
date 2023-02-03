package derry.club.pokemon.model.gamedata.poke;

public enum LearnMethod {
    UNKNOWN("unknown"),
    EGG("egg"),
    MACHINE("machine"),
    LEVEL_UP("level-up"),
    TUTOR("tutor");

    private final String name;

    LearnMethod(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
