package com.project.e_commerce.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderItemRequest {

    @NotNull(message = "You need to add Product ID")
    private Long productId;

    @NotNull(message = "You need to add Quantity")
    @Min(value=1,message = "You need to buy at least one item!")
    private Integer quantity;
}
