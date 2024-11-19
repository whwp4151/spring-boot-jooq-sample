package com.jooq.project.jpa.repository;

import com.jooq.project.jpa.entity.BoardJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardJpaRepository extends JpaRepository<BoardJpaEntity, Long> {
}
