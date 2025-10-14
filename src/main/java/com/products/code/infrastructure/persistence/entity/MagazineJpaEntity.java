package com.products.code.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@DiscriminatorValue("MAGAZINE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class MagazineJpaEntity extends ProductJpaEntity {

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "publication_month")
    private String publicationMonth;

    @Override
    public String getDiscriminatorValue() {
        return "MAGAZINE";
    }
}
