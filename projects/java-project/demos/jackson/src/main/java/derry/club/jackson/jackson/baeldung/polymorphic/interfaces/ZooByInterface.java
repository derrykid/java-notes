package derry.club.jackson.jackson.baeldung.polymorphic.interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ZooByInterface {

    private Animal_Interface animal;

    public ZooByInterface(Animal_Interface animal) {
        this.animal = animal;
    }

    public static void main(String[] args) throws JsonProcessingException {

        var object = new DogImpl();
        object.setBarkVolume(20);
        object.setName("John");

        ObjectMapper mapper = new ObjectMapper();
        var output = mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(object);

        System.out.println(output);

    }
}
