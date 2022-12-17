package com.example.backendadmin.model;

public class ProductBuilderIMpl implements ProductBuilder {
    Product product = new Product();

    @Override
    public ProductBuilder setAmount(int amount) {
        product.amount = amount;
        return this;
    }

    @Override
    public ProductBuilder setDescription(String description) {
        product.description = description;
        return this;
    }

    @Override
    public ProductBuilder setPath_to_file(String path_to_file) {
        product.path_to_file = path_to_file;
        return this;
    }

    @Override
    public ProductBuilder setName(String name) {
        product.name = name;
        return this;
    }

    @Override
    public ProductBuilder setProduct_id(int product_id) {
        product.product_id = product_id;
        return this;
    }

    @Override
    public ProductBuilder setPrice(double price) {
        product.price = price;
        return this;
    }

    @Override
    public ProductBuilder setAttractant(String attractant) {
        product.attractant = attractant;
        return this;
    }

    @Override
    public ProductBuilder setType(String type) {
        product.type = type;
        return this;
    }

    @Override
    public Product build() {
        return product;
    }
}
