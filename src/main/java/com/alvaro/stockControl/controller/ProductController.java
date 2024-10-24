package com.alvaro.stockControl.controller;

import com.alvaro.stockControl.dto.ProductResponseDTO;
import com.alvaro.stockControl.model.Product;
import com.alvaro.stockControl.service.IProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
public class ProductController {

    @Autowired
    IProductService productService;

    @PostMapping("/createProduct")
    public ResponseEntity<Object> createProduct(@Valid @RequestBody ProductResponseDTO productCreateDTO, BindingResult bindingResult){

        if(bindingResult.hasErrors()) {
            List<String> errorMessages = bindingResult.getFieldErrors().stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errorMessages);
        }
        Product product = new Product();
        product.setName(productCreateDTO.getName());
        product.setDescription(productCreateDTO.getDescription());
        product.setPrice(productCreateDTO.getPrice());
        product.setQuantity(productCreateDTO.getQuantity());

        Product createdProduct = productService.createProduct(productCreateDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }
    @GetMapping("/product/{name}")
    public ResponseEntity<?> getProductByName(@PathVariable String name) {
        if (name == null || name.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Product name cannot be null or empty");
        }

        ProductResponseDTO product = productService.getProductByName(name);
        return ResponseEntity.ok(product);
    }
    @GetMapping("/product/")
    public ResponseEntity<?> getProductByNameWithoutName(@PathVariable String name) {
            return ResponseEntity.badRequest().body("Product name cannot be null or empty");
    }
    @DeleteMapping("/product/{name:.+}")
    public ResponseEntity<?> deleteProductByName(@PathVariable String name) {
        if (name == null || name.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Product name cannot be null or empty");
        }

        productService.deleteProductByName(name);

        return ResponseEntity.ok("Product " + name + " has been removed succesfully");
    }
    @DeleteMapping("/product/")
    public ResponseEntity<String> deleteProductWithoutName() {
        return ResponseEntity.badRequest().body("Product name cannot be null or empty");
    }

}
