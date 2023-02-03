package derry.club.jackson.jackson.baeldung.write;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Setter;

@Setter
public class Change_output_name_by_JsonGetter_or_JsonProperty {

    private int id;
    private String name;

    public int getId() {
        return this.id;
    }

    @com.fasterxml.jackson.annotation.JsonGetter("anotherName")
//    @JsonProperty("orUseThisName")
    public String getTheName() {
        return name;
    }

    public static void main(String[] args) throws JsonProcessingException {
        Change_output_name_by_JsonGetter_or_JsonProperty object = new Change_output_name_by_JsonGetter_or_JsonProperty();
        object.setId(22);
        object.setName("John");

        ObjectMapper mapper = new ObjectMapper();
        var output = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        System.out.println(output);

    }


}
