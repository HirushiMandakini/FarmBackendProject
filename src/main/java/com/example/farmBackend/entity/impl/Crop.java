package com.example.farmBackend.entity.impl;

import com.example.farmBackend.entity.SuperEntity;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = "field")
@Entity
@Table(name = "crops")
public class Crop implements SuperEntity {
    @Id
    private String cropCode;
    private String cropCommonName;
    private String cropScientificName;
    private String category;
    private String cropSeason;
    @Column(columnDefinition = "LONGTEXT")
    private String cropImage;
    @ManyToOne
    @JoinColumn(name = "fieldCode",nullable = false)
    private Field field;


}
