package derry.club.jackson.jackson.baeldung.map.serialize.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import derry.club.jackson.jackson.baeldung.map.entity.Pair;

import java.io.IOException;
import java.io.StringWriter;

public class PairSerializer extends JsonSerializer<Pair> {
    @Override
    public void serialize(Pair pair,
                          JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider) throws IOException {

        StringWriter writer = new StringWriter();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(writer, pair);
        jsonGenerator.writeFieldName(writer.toString());
    }
}
