package derry.club.jackson.jackson.baeldung.property;

import com.fasterxml.jackson.annotation.JsonIgnoreType;

public class Ignore_Class_JsonIgnoreType {

    private String name;
    private int id;

    private Type type;

    @JsonIgnoreType
    public static enum Type {
        WATER, FIRE;
    }

}

