package org.example.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Embeddable
@Data
public class DepartmentDetails {

    private String contractNo;

    @ManyToOne
    private Department department;
}
