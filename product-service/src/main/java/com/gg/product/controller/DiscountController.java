package com.gg.product.controller;

import com.gg.grpc.DiscountResponse;
import com.gg.product.model.dto.DiscountResponseDto;
import com.gg.product.service.DiscountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/discounts")
public class DiscountController {

    private final DiscountService discountService;

    @GetMapping
    public ResponseEntity<DiscountResponseDto> getDiscount(@RequestParam("productId") Integer productId,@RequestParam("code") String code){
        DiscountResponse discountResponse = discountService.getDiscount(productId,code);
        return ResponseEntity.ok(
                DiscountResponseDto.builder()
                        .newPrice(discountResponse.getNewPrice())
                        .oldPrice(discountResponse.getOldPrice())
                        .code(discountResponse.getCode())
                        .build()
        );
    }
}
