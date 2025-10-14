package com.products.code.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@DiscriminatorValue("COURSE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class CourseJpaEntity extends ProductJpaEntity {

    @Column(name = "instructor")
    private String instructor;

    @Column(name = "duration_hours")
    private Integer durationHours;

    @Column(name = "level")
    private String level;

    @Override
    public String getDiscriminatorValue() {
        return "COURSE";
    }
}
