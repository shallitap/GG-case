package com.gg.product.repository;

import com.gg.product.model.Category;
import lombok.NonNull;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
