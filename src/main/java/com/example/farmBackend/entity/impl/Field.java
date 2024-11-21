package com.example.farmBackend.entity.impl;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = {"fieldStaffDetails", "crops"})
@Entity
@Table(name = "fields")
public class Field {
    @Id
    private String fieldCode;
    private String fieldName;
    private String fieldLocation;
    private Double extentSize;
    @Column(columnDefinition = "LONGTEXT")
    private String fieldImage1;
    @Column(columnDefinition = "LONGTEXT")
    private String fieldImage2;
    @OneToMany(mappedBy = "field", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<FieldStaffDetails> fieldStaffDetails = new ArrayList<>();
    @OneToMany(mappedBy = "field")
    private List<Crop> crops = new ArrayList<>();

}
