package derry.club.jackson.jackson.baeldung.map.entity;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Pair {

    private String first;
    private String second;

    @Override
    @JsonValue
    public String toString() {
        return first + " and " + second;
    }
}
