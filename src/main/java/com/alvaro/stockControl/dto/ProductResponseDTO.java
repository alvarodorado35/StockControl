package com.alvaro.stockControl.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDTO {

    @NotBlank(message = "Name is mandatory")
    private String name;

    private String description;

    @Positive(message = "Price must be positive")
    private double price;

    @Positive(message = "Quantity must be positive")
    private int quantity;
}
