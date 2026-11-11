package com.joaopedro.mini_ecomerce.model;

public record Product(
        Long id,
        String name,
        String description,
        Integer priceCents,
        Integer stock
) {}