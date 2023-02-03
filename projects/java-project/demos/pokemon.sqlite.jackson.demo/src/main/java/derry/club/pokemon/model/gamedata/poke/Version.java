package derry.club.pokemon.model.gamedata.poke;

import lombok.Getter;

@Getter
public enum Version {
    RED_BLUE("red_blue", 1),
    YELLOW("yellow", 1),
    GOLD_SILVER("gold-silver", 2),
    CRYSTAL("crystal", 2),
    RUBY_SAPPHIRE("ruby-sapphire", 3),
    EMERALD("emerald", 3),
    FIRERED_LEAFGREEN("firered-leafgreen", 3),
    DIAMOND_PEARL("diamond-pearl", 4),
    PLATINUM("platinum",4),
    HEARTGOLd_soulsilver("heartgold-soulsilver",4 ),
    BLACK_WHite("black-white",5),
    BLACK_2_white_2("black-2-white-2",5),
    X_Y("x-y",6),
    OMEGA_RUby_alpha_sapphire("omega-ruby-alpha-sapphire",6),
    SUN_MOON("sun-moon",7),
    ULTRA_SUn_ultra_moon("ultra-sun-ultra-moon",7),
    LETS_GO_pikachu_lets_go_eevee("lets-go-pikachu-lets-go-eevee",7),
    SWORD_SHield("sword-shield",8);


    private final String gameName;
    private final int generation;

    Version(String name, int generation) {
        this.gameName = name;
        this.generation = generation;
    }

    @Override
    public String toString() {
        return this.gameName;
    }
}
