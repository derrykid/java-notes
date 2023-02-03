package derry.club.jackson.jackson.baeldung.read.custom.deserializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class CustomDeserializer  extends JsonDeserializer<Boolean> {


    @Override
    public Boolean deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        String text = jsonParser.getText();

        if("0".equals("text")) {
            return false;
        }

        return true;
    }
}
