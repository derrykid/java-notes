package derry.club.jackson.jackson.baeldung.general.reference;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "itemName")
public class Item {
    private String itemName;
    private int price;

//    @JsonManagedReference
    private Owner owner;
}
