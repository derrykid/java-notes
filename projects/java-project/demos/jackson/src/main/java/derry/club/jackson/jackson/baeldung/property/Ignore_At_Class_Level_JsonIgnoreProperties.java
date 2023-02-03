package derry.club.jackson.jackson.baeldung.property;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"id"})
public class Ignore_At_Class_Level_JsonIgnoreProperties {

    private String name;
    private int id;

    // can work with read and write
}
