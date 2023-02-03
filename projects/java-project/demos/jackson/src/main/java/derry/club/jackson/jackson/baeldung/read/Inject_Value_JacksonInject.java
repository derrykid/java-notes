package derry.club.jackson.jackson.baeldung.read;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Data
@Slf4j
@Configuration
public class Inject_Value_JacksonInject {

    @JsonSetter("theName")
    private String name;
    private int id;

    @JacksonInject
    private int loveRate;

    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        String input = "{  \"id\":1,\n" +
                "    \"theName\":\"My bean\"}";
        InjectableValues inject = new InjectableValues.Std().addValue(int.class, 100);

        var a = mapper
                .reader(inject)
                .readValue(input, Inject_Value_JacksonInject.class);


        System.out.println(a);
    }


}
