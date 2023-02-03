package derry.club.jackson.jackson.baeldung.read;

import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;

@Data
public class Change_input_name_by_JsonSetter_JsonProperty {

    public int id;
    @JsonSetter("theName")
    private String name;

}
