package derry.club.jackson.jackson.baeldung.polymorphic.interfaces;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;

@JsonTypeName("cat")
@Data
public class CatImpl implements Animal_Interface {

    private int lives;
    private String name;

}
