package com.example.farmBackend.entity.impl;

import com.example.farmBackend.entity.SuperEntity;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = {"field", "staff"})
@Entity
@Table(name = "equipments")
public class Equipment implements SuperEntity {
    @Id
    private String equipmentId;
    private String equipmentName;
    private String equipmentType;
    private String equipmentStatus;

    @ManyToOne
    @JoinColumn(name = "fieldCode", nullable = false)
    private Field field;
    @ManyToOne
    @JoinColumn(name = "staffId", nullable = false)
    private Staff staff;
}