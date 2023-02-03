package derry.club.jackson.jackson.baeldung.enums.serialize.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import derry.club.jackson.jackson.baeldung.enums.entity.Type;

import java.io.IOException;

public class CustomSerializer extends StdSerializer<Type> {

    public CustomSerializer() {
        this(null);
    }

    protected CustomSerializer(Class<Type> t) {
        super(t);
    }

    @Override
    public void serialize(Type type,
                          JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider) throws IOException {

        jsonGenerator.writeStartObject();

        jsonGenerator.writeFieldName("enumName");
        jsonGenerator.writeString(type.name());

        jsonGenerator.writeFieldName("type");
        jsonGenerator.writeString(type.getName());

        jsonGenerator.writeFieldName("order");
        jsonGenerator.writeNumber(type.getOrder());

        jsonGenerator.writeEndObject();

    }
}
