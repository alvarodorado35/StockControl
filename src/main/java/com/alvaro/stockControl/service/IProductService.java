package com.alvaro.stockControl.service;

import com.alvaro.stockControl.dto.ProductResponseDTO;
import com.alvaro.stockControl.model.Product;

public interface IProductService {

    Product createProduct(ProductResponseDTO productCreateDTO);
    ProductResponseDTO getProductByName(String name);
    void deleteProductByName(String name);
}
