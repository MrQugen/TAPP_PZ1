package com.example.backendadmin.model;

import com.example.backendadmin.memento.SaveProduct;

public class Product {
    int product_id, amount;
    double price;
    String name, attractant, type, description, path_to_file;

    public Product(){}

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPath_to_file() {
        return path_to_file;
    }

    public void setPath_to_file(String path_to_file) {
        this.path_to_file = path_to_file;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAttractant() {
        return attractant;
    }

    public void setAttractant(String attractant) {
        this.attractant = attractant;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public SaveProduct save(){
        return new SaveProduct(product_id, amount, price, name, attractant, type, description, path_to_file);
    }
    public void load(SaveProduct save){
        product_id = save.getProduct_id();
        amount = save.getAmount();
        price = save.getPrice();
        name = save.getName();
        attractant = save.getAttractant();
        type = save.getType();
        description = save.getDescription();
        path_to_file = save.getPath_to_file();
    }

    @Override
    public String toString() {
        return "Product{" +
                "product_id=" + product_id +
                ", amount=" + amount +
                ", price=" + price +
                ", name='" + name + '\'' +
                ", attractant='" + attractant + '\'' +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", path_to_file='" + path_to_file + '\'' +
                '}';
    }
}

