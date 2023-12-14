package com.gg.product.service;

import com.gg.product.model.Category;
import com.gg.product.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;

    public Category add(Category category){
//        Example<Category> example = Example.of(new Category(category.getName()));
//        Optional<Category> existingCategory = repository.findOne(example);
//        if (existingCategory.isPresent()){
//            throw new RuntimeException("Category with the same name already exists: " + category.getName());
//        }

        return repository.save(category);
    }
}
