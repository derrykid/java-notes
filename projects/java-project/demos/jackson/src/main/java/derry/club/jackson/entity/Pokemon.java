package derry.club.jackson.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Pokemon {

    @JsonProperty("id")
    private int pokedexId;
    private String name;
    private int weight;

    @JsonProperty("types")
    private List<PokemonType> type;

    @JsonProperty("moves")
    private List<Move> moves;

    @JsonProperty("stats")
    private Stats stats;

    public int getTotalStats() {
        return stats.getTotal();
    }
}
