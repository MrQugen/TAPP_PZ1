package com.example.backendadmin.memento;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SaveProduct {
    private final int product_id, amount;
    private final double price;
    private final String name, attractant, type, description, path_to_file;
    private final String date;

    public SaveProduct(int product_id, int amount, double price, String name, String attractant, String type, String description, String path_to_file){
        this.product_id = product_id;
        this.amount = amount;
        this.price = price;
        this.name = name;
        this.attractant = attractant;
        this.type = type;
        this.description = description;
        this.path_to_file = path_to_file;
        this.date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
    }

    public int getProduct_id() { return product_id; }

    public int getAmount() { return amount; }

    public double getPrice() { return price; }

    public String getName() { return name; }

    public String getAttractant() { return attractant; }

    public String getType() { return type; }

    public String getDescription() { return description; }

    public String getPath_to_file() { return path_to_file; }

    public String getDate() { return date; }

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
                ", date='" + date + '\'' +
                '}';
    }
}
