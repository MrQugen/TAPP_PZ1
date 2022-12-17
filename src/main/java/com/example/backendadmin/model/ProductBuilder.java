package com.example.backendadmin.model;

public interface ProductBuilder {

    ProductBuilder setAmount(int amount);
    ProductBuilder setDescription(String description);
    ProductBuilder setPath_to_file(String path_to_file);
    ProductBuilder setName(String name);
    ProductBuilder setProduct_id(int product_id);
    ProductBuilder setPrice(double price);
    ProductBuilder setAttractant(String attractant);
    ProductBuilder setType(String type);

    Product build();
}
