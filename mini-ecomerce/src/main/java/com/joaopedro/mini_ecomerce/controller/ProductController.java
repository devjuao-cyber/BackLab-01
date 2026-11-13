

// recebe e responde a requisições HTTP relacionadas a produtos,.




package com.joaopedro.mini_ecomerce.controller;

import com.joaopedro.mini_ecomerce.model.Product;
import com.joaopedro.mini_ecomerce.repository.ProductRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;

import com.joaopedro.mini_ecomerce.dto.CreateProductRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@RestController e uma anotacao Anotações são metadados que podem ser associados a classes, métodos, parâmetros e outros elementos do código.Elas fornecem informações que ferramentas, bibliotecas e frameworks podem interpretar.

@RestController

@CrossOrigin(origins = "http://127.0.0.1:5500")

@RequestMapping("/api/products")

public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }




    @GetMapping
    public List<Product> getAllProducts() {

        System.out.println("Endpoint de produtos foi chamado!");

        List<Product> products = productRepository.findAll();

        System.out.println("Quantidade de produtos encontrados: " + products.size());

        return products;
    }




    @PostMapping
    public ResponseEntity<Void> createProduct(@RequestBody CreateProductRequest request) {

        productRepository.save(
                request.name(),
                request.description(),
                request.priceCents(),
                request.stock()
        );

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {

        int rows = productRepository.deleteById(id);

        if (rows == 0) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }



    @GetMapping("/database")
    public String getDatabasePath() {
        return productRepository.getDatabasePath();
    }


}
