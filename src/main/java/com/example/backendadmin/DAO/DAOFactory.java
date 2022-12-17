package com.example.backendadmin.DAO;

public abstract class DAOFactory {
    public abstract CloudscapeProductDAO getProductDAO();
    public abstract CloudscapeUserDAO getUserDAO();

}
