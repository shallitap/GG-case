package com.gg.product.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ServiceResponseDto {
    private Object responseObject;
    private boolean isSuccess;
    private String message;
}
