package com.joaopedro.mini_ecomerce.dto;

public record CreateProductRequest(
        String name,
        String description,
        Integer priceCents,
        Integer stock
) {}