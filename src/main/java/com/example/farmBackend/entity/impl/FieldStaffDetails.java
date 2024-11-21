package com.example.farmBackend.entity.impl;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "fieldStaffDetails")
public class FieldStaffDetails {
    @Id
    private Long id;
    private String assignedRole;
    private String assignmentDate;
    @ManyToOne
    @JoinColumn(name = "fieldCode")
    private Field field;
    @ManyToOne
    @JoinColumn(name = "staffId")
    private Staff staff;

}
