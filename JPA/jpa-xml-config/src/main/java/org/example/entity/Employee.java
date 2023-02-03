package org.example.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Embedded
    @AttributeOverride(name = "contractNo",
            column = @Column(name = "contract_no"))
    @AssociationOverride(name = "department",
            joinColumns = @JoinColumn(name = "dept"))
    private DepartmentDetails departmentDetails;
}
