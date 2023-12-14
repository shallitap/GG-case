package com.gg.product.service;

import com.gg.product.model.Category;
import com.gg.product.model.Product;
import com.gg.product.model.dto.ServiceResponseDto;
import com.gg.product.repository.CategoryRepository;
import com.gg.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ServiceResponseDto create(Product product){
        Optional<Category> optionalCategory = categoryRepository.findById(product.getCategory().getId());
        if(optionalCategory.isEmpty()){
            return ServiceResponseDto.builder()
                    .message("Category with id:"+product.getCategory().getId()+" does not exists!")
                    .isSuccess(false)
                    .responseObject(null)
                    .build();
        }
        Optional<Product> optionalProduct = productRepository.findByName(product.getName());
        if (optionalProduct.isPresent()){
            return ServiceResponseDto.builder()
                    .message(product.getName()+" already exists!")
                    .isSuccess(false)
                    .responseObject(null)
                    .build();
        }
        return ServiceResponseDto.builder()
                .message(product.getName()+" created successfully!")
                .isSuccess(true)
                .responseObject(productRepository.save(product))
                .build();
    }

    public List<Product> getAll(){
        return productRepository.findAll();
    }

    public ServiceResponseDto getById(Integer id){
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()){
            return ServiceResponseDto.builder()
                    .message("")
                    .isSuccess(true)
                    .responseObject(optionalProduct.get())
                    .build();
        }else{
            return ServiceResponseDto.builder()
                    .message("Cant find product with id:"+id)
                    .isSuccess(false)
                    .responseObject(null)
                    .build();
        }
    }

}
