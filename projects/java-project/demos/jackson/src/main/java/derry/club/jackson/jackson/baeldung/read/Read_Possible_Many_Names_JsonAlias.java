package derry.club.jackson.jackson.baeldung.read;

import com.fasterxml.jackson.annotation.JsonAlias;

public class Read_Possible_Many_Names_JsonAlias {

    @JsonAlias({"id", "pokedexNumber"})
    private int index;

}
