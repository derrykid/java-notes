package derry.club.jackson.jackson.baeldung.general.reference;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "ownerName")
public class Owner {
    private String ownerName;
    private int age;

//    @JsonBackReference
    private List<Item> items;

    public static void main(String[] args) throws JsonProcessingException {

        var owner = new Owner();
        owner.setAge(30);
        owner.setOwnerName("John");
        owner.setItems(new ArrayList<>());

        var item1 = new Item("Cup", 20, owner);
        var item2 = new Item("Water", 5, owner);
        owner.getItems().addAll(List.of(item1, item2));

        var mapper = new ObjectMapper();
        var output = mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(owner);

        System.out.println(output);

    }
}
