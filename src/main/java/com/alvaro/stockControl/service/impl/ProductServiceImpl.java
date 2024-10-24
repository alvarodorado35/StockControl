package com.alvaro.stockControl.service.impl;

import com.alvaro.stockControl.dto.ProductResponseDTO;
import com.alvaro.stockControl.exception.ProductAlreadyExistsException;
import com.alvaro.stockControl.exception.ProductNotFoundException;
import com.alvaro.stockControl.model.Product;
import com.alvaro.stockControl.repository.ProductRepository;
import com.alvaro.stockControl.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public Product createProduct(ProductResponseDTO productCreateDTO) {
        Optional<Product> existingProduct = productRepository.findByName(productCreateDTO.getName());
        if(existingProduct.isPresent()){
            throw new ProductAlreadyExistsException("Product with name "
                    + productCreateDTO.getName()
                    + " already exists" );
        }
        Product product = new Product();

        product.setName(productCreateDTO.getName());
        product.setDescription(productCreateDTO.getDescription());
        product.setQuantity(productCreateDTO.getQuantity());
        product.setPrice(productCreateDTO.getPrice());

        return productRepository.save(product);
    }

    @Override
    public ProductResponseDTO getProductByName(String name) {
        Product product = productRepository.findByName(name)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with name: " + name));

        return convertToDto(product);
    }

    @Override
    public void deleteProductByName(String name) {
        Product product = productRepository.findByName(name)
        .orElseThrow(()-> new ProductNotFoundException("Product not found with name: " + name));

        productRepository.delete(product);

    }

    private ProductResponseDTO convertToDto(Product product) {
        return new ProductResponseDTO(
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getQuantity()
        );
    }
}
