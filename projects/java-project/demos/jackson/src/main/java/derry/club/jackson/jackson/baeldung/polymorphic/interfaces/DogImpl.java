package derry.club.jackson.jackson.baeldung.polymorphic.interfaces;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;

@Data
@JsonTypeName("dogValue")
public class DogImpl implements Animal_Interface {

    private String name;
    private int barkVolume;

}
