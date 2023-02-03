package derry.club.jackson.jackson.baeldung.read;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Read_To_List {

    private String color;
    private String type;

    public static void main(String[] args) throws JsonProcessingException {

        String jsonCarArray =
                "[{ \"color\" : \"Black\", \"type\" : \"BMW\" }, { \"color\" : \"Red\", \"type\" : \"FIAT\" }]";

        var mapper = new ObjectMapper();

        List<Read_To_List> list = mapper.readValue(jsonCarArray,
                new TypeReference<List<Read_To_List>>(){});

        System.out.println(list);
    }
}
