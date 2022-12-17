package com.example.backendadmin.memento;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductSaveHistory {
    private List<SaveProduct> history;

    public ProductSaveHistory(){
        this.history = new ArrayList<SaveProduct>();
    }

    public List<SaveProduct> getSaveAll(){
        return history;
    }
    public List<SaveProduct> getSave(int id){
        return history.stream().filter((p)-> p.getProduct_id() == id).collect(Collectors.toList());
    }
    public SaveProduct getLastSave(int id){
        return history.stream().filter((p)-> p.getProduct_id() == id).reduce((first, second) -> second).get();
    }
    public SaveProduct getFirstSave(int id){
        return history.stream().filter(p -> p.getProduct_id() == id).findFirst().get();
    }
    public void removeSave(int id){
        history.removeAll(history.stream().filter((p)-> p.getProduct_id() == id).collect(Collectors.toList()));
    }
    public SaveProduct getSaveDate(String date){
        return (SaveProduct) history.stream().filter((a) -> a.getDate().equals(date)).findFirst().get();
    }
    public void removeDate(String date){
        history.removeAll(history.stream().filter(p -> p.getDate().equals(date)).collect(Collectors.toList()));
    }

    public void setSave(SaveProduct saveProduct){

        int CountSaveProduct = (int) history.stream().filter((p)-> p.getProduct_id() == saveProduct.getProduct_id()).count();

        if(CountSaveProduct > 4)
        {
            history.remove(history.indexOf(history.stream().filter(p -> p.getProduct_id() == saveProduct.getProduct_id()).findFirst().get()));
            history.add(saveProduct);
        }
        else{
            history.add(saveProduct);
        }
    }
}