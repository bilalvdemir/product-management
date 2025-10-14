package com.products.code.infrastructure.persistence.repository.jpa;

import com.products.code.infrastructure.persistence.entity.MagazineJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMagazineRepository extends JpaRepository<MagazineJpaEntity, Long> {
}
