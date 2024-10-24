package com.alvaro.stockControl.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetProductDTO {
    private String name;
    private String description;
    private double price;
    private int quantity;
}
