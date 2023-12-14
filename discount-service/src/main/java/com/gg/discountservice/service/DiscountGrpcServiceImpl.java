package com.gg.discountservice.service;

import com.gg.discountservice.model.Category;
import com.gg.discountservice.model.Discount;
import com.gg.discountservice.repository.CategoryRepository;
import com.gg.discountservice.repository.DiscountRepository;

import com.gg.grpc.DiscountRequest;
import com.gg.grpc.DiscountResponse;
import com.gg.grpc.DiscountServiceGrpc;
import com.gg.grpc.Response;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

import java.math.BigDecimal;
import java.util.Optional;

@GrpcService
@RequiredArgsConstructor
public class DiscountGrpcServiceImpl  extends DiscountServiceGrpc.DiscountServiceImplBase {

    private final DiscountRepository discountRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public void getDiscount(DiscountRequest request, StreamObserver<DiscountResponse> responseObserver) {

        Optional<Category> categoryOptional = categoryRepository.findByExternalId(String.valueOf(request.getExternalCategoryId()));

        if(categoryOptional.isPresent()){

            Category category = categoryOptional.get();

            Optional<Discount> discount = discountRepository.findByCodeAndCategoryId(request.getCode(),category.getId());

            if (discount.isPresent()){

                BigDecimal newPrice = discount.get().getDiscountPrice().subtract(BigDecimal.valueOf(request.getPrice())).abs();

                responseObserver.onNext(DiscountResponse.newBuilder()
                        .setCode(request.getCode())
                        .setOldPrice(request.getPrice())
                        .setNewPrice(newPrice.floatValue())
                        .setResponse(
                                Response.newBuilder()
                                        .setStatusCode(true)
                                        .setMessage("Discount has been applied successfully")
                                        .build())
                        .build()
                );
            }else{
                responseObserver.onNext(DiscountResponse.newBuilder()
                        .setCode(request.getCode())
                        .setOldPrice(request.getPrice())
                        .setNewPrice(request.getPrice())
                        .setResponse(
                                Response.newBuilder()
                                        .setMessage("Code and Category are invalid")
                                        .setStatusCode(false)
                                        .build())
                        .build()
                );
            }
        }else {
            responseObserver.onNext(DiscountResponse.newBuilder()
                    .setCode(request.getCode())
                    .setOldPrice(request.getPrice())
                    .setNewPrice(request.getPrice())
                    .setResponse(Response.newBuilder()
                            .setMessage("Category has not been found")
                            .setStatusCode(false)
                            .build())
                    .build());
        }

        responseObserver.onCompleted();
    }
}
