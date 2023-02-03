package derry.club.jackson.jackson.baeldung.polymorphic.inheritance;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ZooByInheritance {
    private Animal animal;

    public ZooByInheritance(Animal animal) {
        this.animal = animal;
    }


    public static void main(String[] args) throws JsonProcessingException {
        Animal lacy = new Dog("Lacy");

        ObjectMapper mapper = new ObjectMapper();
        var output = mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(lacy);

        System.out.println(output);

    }
}
