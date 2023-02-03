package derry.club.jackson.jackson.baeldung.enums.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import derry.club.jackson.jackson.baeldung.enums.serialize.serializer.CustomSerializer;
import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@JsonSerialize(using = CustomSerializer.class)
public enum Type {
    FIRE("fire", 1),
    WATER("water", 2),
    WIND("wind", 3);

    // @JsonValue
    // if I only want this field instead of full graph of this enum, use annotates the field
    private String name;
    private int order;

    Type(String name, int order) {
        this.name = name;
        this.order = order;
    }
}
