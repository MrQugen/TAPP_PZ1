package com.example.backendadmin.DAO;

import com.example.backendadmin.model.Product;

import java.util.List;

public interface ProductDAO {
    public List<Product> index();
    public Product show(int id);
    public void save(Product product);
    public void update(int id, Product updateProduct);
    public void delete(int id);
}
