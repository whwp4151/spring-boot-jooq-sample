package com.jooq.project.jpa.repository;

import com.jooq.project.jpa.entity.BoardJpaEntity;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.ArrayList;
import java.util.List;

public interface BoardJpaRepository extends JpaRepository<BoardJpaEntity, Long>
        , JpaSpecificationExecutor<BoardJpaEntity> {

    default Page<BoardJpaEntity> searchByKeyword(String searchKeyword, Pageable pageable) {
        return findAll((Specification<BoardJpaEntity>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (searchKeyword != null && !searchKeyword.trim().isEmpty()) {
                Predicate titlePredicate = criteriaBuilder.like(root.get("title"), "%" + searchKeyword + "%");
                Predicate contentPredicate = criteriaBuilder.like(root.get("content"), "%" + searchKeyword + "%");
                predicates.add(criteriaBuilder.or(titlePredicate, contentPredicate));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }, pageable);
    }

}
