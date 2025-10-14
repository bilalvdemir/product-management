package com.products.code.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@DiscriminatorValue("BOOK")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class BookJpaEntity extends ProductJpaEntity {

    @Column(name = "author")
    private String author;

    @Override
    public String getDiscriminatorValue() {
        return "BOOK";
    }
}
