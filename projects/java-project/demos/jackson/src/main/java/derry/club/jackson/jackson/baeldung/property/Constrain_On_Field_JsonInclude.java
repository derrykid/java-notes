package derry.club.jackson.jackson.baeldung.property;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Constrain_On_Field_JsonInclude {

    private String name;
}
