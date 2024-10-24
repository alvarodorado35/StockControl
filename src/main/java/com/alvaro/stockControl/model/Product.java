package com.alvaro.stockControl.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products") // Indica que esta clase se mapea a la colección "products"
@Data //Genera getters, setters, toString de forma auto
@NoArgsConstructor // Genera un constructor sin param
@AllArgsConstructor //Genera constructor con todos
public class Product {
    @Id //Campo unico que identifica el documento
    private String id;
    @Indexed(unique = true)
    private String name;
    private String description;
    private double price;
    private int quantity;

}
