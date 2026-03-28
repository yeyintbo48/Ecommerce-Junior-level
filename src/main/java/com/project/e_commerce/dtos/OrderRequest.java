package com.project.e_commerce.dtos;

import java.util.List;
import jakarta.validation.Valid;
import lombok.Data;

@Data
public class OrderRequest {
    @Valid
    private List<OrderItemRequest> items;
}
