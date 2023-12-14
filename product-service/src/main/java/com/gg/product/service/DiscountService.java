package com.gg.product.service;

import com.gg.grpc.DiscountRequest;
import com.gg.grpc.DiscountResponse;
import com.gg.product.model.Product;
import com.gg.product.model.dto.ServiceResponseDto;
import com.gg.product.service.grpc.DiscountGrpcService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DiscountService {

    private final DiscountGrpcService discountGrpcService;
    private final ProductService productService;

    public DiscountResponse getDiscount(Integer productId, String code){

        ServiceResponseDto serviceResponse = productService.getById(productId);
        Product product = (Product) serviceResponse.getResponseObject();

        DiscountRequest discountRequest = DiscountRequest.newBuilder()
                .setCode(code)
                .setPrice(product.getPrice().floatValue())
                .setExternalCategoryId(product.getCategory().getId())
                .build();
        return discountGrpcService.getDiscount(discountRequest);
    }
}
