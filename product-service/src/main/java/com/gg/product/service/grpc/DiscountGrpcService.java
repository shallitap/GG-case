package com.gg.product.service.grpc;

import com.gg.grpc.DiscountRequest;
import com.gg.grpc.DiscountResponse;

public interface DiscountGrpcService {
    DiscountResponse getDiscount(DiscountRequest discountRequest);
}
