package com.example.backendadmin.observer_pattern;

import com.example.backendadmin.model.Product;
import java.util.List;

public interface Observed {
    public void addObserved(Observer observer);
    public void removeObserved(Observer observer);

    public void notifyObservers(String type, Product product);

}
