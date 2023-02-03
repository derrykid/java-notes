package derry.club.jackson.jackson.baeldung.polymorphic.interfaces;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
            include = JsonTypeInfo.As.PROPERTY,
            property = "interface Impl")
@JsonSubTypes({
        @JsonSubTypes.Type(value = DogImpl.class, name = "dog"),
        @JsonSubTypes.Type(value = CatImpl.class, name = "cat")
})
public interface Animal_Interface {
}
