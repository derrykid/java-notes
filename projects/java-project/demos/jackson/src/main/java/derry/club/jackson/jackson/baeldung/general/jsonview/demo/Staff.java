package derry.club.jackson.jackson.baeldung.general.jsonview.demo;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Data
public class Staff {
    @JsonView(Expose_Output_Accordingly_JsonView.Normal_Json.class)
    private String name;

    @JsonView(Expose_Output_Accordingly_JsonView.Normal_Json.class)
    private int age;

    // two views
    @JsonView({Expose_Output_Accordingly_JsonView.HR.class,
            Expose_Output_Accordingly_JsonView.Manager.class})
    private String[] position;

    @JsonView(Expose_Output_Accordingly_JsonView.Manager.class)
    private List<String> skills;

    @JsonView(Expose_Output_Accordingly_JsonView.HR.class)
    private Map<String, BigDecimal> salary;
}
