package com.example.backendadmin.observer_pattern;

import com.example.backendadmin.model.Product;

import java.util.List;

public interface Observer {

    public void handleEvent(String type, Product product);

}
