package com.gg.discountservice.repository;

import com.gg.discountservice.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
    Optional<Category> findByExternalId(String id);
}
