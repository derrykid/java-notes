package derry.club.jackson.jackson.baeldung.read;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Constructor_JsonCreator {

    private String name;
    private int pokeId;

    @JsonCreator
    public Constructor_JsonCreator(
            @JsonProperty("name") String name,
            @JsonProperty("id") int pokeId) {
        this.name = name;
        this.pokeId = pokeId;
    }
}
