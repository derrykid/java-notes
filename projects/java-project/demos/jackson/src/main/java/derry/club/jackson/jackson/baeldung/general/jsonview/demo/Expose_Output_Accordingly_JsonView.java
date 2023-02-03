package derry.club.jackson.jackson.baeldung.general.jsonview.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Expose_Output_Accordingly_JsonView {

    public static class Normal_Json {
    }

    public static class Manager extends Normal_Json {
    }

    public static class HR extends Normal_Json {
    }

    public static void main(String[] args) {

        ObjectMapper mapper = new ObjectMapper();

        Staff staff = createStaff();

        try {
            // to enable pretty print
            mapper.enable(SerializationFeature.INDENT_OUTPUT);

            // normal
            String normalView = mapper
                    .writerWithView(Expose_Output_Accordingly_JsonView.Normal_Json.class)
                    .writeValueAsString(staff);
            System.out.format("Normal views\n%s\n", normalView);

            // manager
            String managerView = mapper.writerWithView(Expose_Output_Accordingly_JsonView.Manager.class)
                    .writeValueAsString(staff);
            System.out.format("Manager views\n%s\n", managerView);

            // hr
            String hrView = mapper.writerWithView(Expose_Output_Accordingly_JsonView.HR.class)
                    .writeValueAsString(staff);
            System.out.format("HR views\n%s\n", hrView);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static Staff createStaff() {

        Staff staff = new Staff();

        staff.setName("mkyong");
        staff.setAge(38);
        staff.setPosition(new String[]{"Founder", "CTO", "Writer"});
        Map<String, BigDecimal> salary = new HashMap() {{
            put("2010", new BigDecimal(10000));
            put("2012", new BigDecimal(12000));
            put("2018", new BigDecimal(14000));
        }};
        staff.setSalary(salary);
        staff.setSkills(Arrays.asList("java", "python", "node", "kotlin"));

        return staff;

    }
}