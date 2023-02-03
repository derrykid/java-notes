package derry.club.jackson.jackson.baeldung.write.custom.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.text.SimpleDateFormat;

public class CustomSerializer extends StdSerializer<Boolean> {


    private static SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");

    public CustomSerializer() {
        this(null);
    }

    protected CustomSerializer(Class<Boolean> t) {
        super(t);
    }

    @Override
    public void serialize(Boolean aBoolean, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (aBoolean) {
            jsonGenerator.writeString("This is true!");
        } else {
            jsonGenerator.writeString("This is false!");
        }
    }
}
