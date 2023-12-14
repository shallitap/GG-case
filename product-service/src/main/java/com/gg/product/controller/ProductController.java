package com.gg.product.controller;

import com.gg.product.model.Product;
import com.gg.product.model.dto.ServiceResponseDto;
import com.gg.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Provider;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/products")
@RequiredArgsConstructor
public class ProductController {

    @Autowired
    private final ProductService productService;

    @PostMapping("create")
    public ResponseEntity<?> create(@Valid @RequestBody Product product, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body(Map.of("message", "Validation error"));
        }

        ServiceResponseDto responseDto = productService.create(product);

        if(responseDto.isSuccess()){
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(responseDto.getResponseObject());
        }else{
            return ResponseEntity.badRequest().body(Map.of("message", responseDto.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        productService.getAll()
                );
    }
}
