package com.example.backendadmin.DAO;

import com.example.backendadmin.model.Product;
import com.example.backendadmin.model.ProductBuilderIMpl;
import com.example.backendadmin.observer_pattern.Observed;
import com.example.backendadmin.observer_pattern.Observer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CloudscapeProductDAO implements ProductDAO, Observed {

    static private List<Observer> users = new ArrayList<>();

    private final String INDEX_SQL = "select product.product_id,  product.name, product.price, amount, type.name as type, attractant.name as attractant,\n" +
            "description, path_to_file\n" +
            "FROM product\n" +
            "JOIN type ON type.type_id = product.type_id\n" +
            "JOIN attractant on attractant.attractant_id = product.attractant_id\n" +
            "ORDER BY product.product_id;";
    private final String SHOW_SQL = "select product.product_id,  product.name, product.price, amount, type.name as type, attractant.name as attractant,\n" +
            "description, path_to_file\n" +
            "FROM product\n" +
            "JOIN type ON type.type_id = product.type_id\n" +
            "JOIN attractant on attractant.attractant_id = product.attractant_id\n" +
            "WHERE product.product_id = ?;";
    private final String SAVE_SQL = "INSERT INTO product (name, price, type_id, attractant_id, amount, description, path_to_file)\n" +
            "     VALUES (?, ?, (SELECT type_id FROM type WHERE name=?), \n" +
            "     (SELECT attractant_id FROM attractant WHERE name=?), ?, ?, ?);";
    private final String UPDATE_SQL = "UPDATE product SET name=?, price=?, type_id=(SELECT type_id FROM type WHERE name=?),\n" +
            "attractant_id=(SELECT attractant_id FROM attractant WHERE name=?), amount = ?, description=?, path_to_file=? \n" +
            "WHERE product_id = ?;";
    private final String DELETE_SQL = "DELETE FROM product WHERE product_id = ?;";

    public CloudscapeProductDAO(){}

    @Override
    public List<Product> index() {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = CloudscapeDAOFactory.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(INDEX_SQL);

            List<Product> res = new ArrayList<>();

            while (resultSet.next()){

                res.add(new ProductBuilderIMpl().setProduct_id(resultSet.getInt(1)).setName(resultSet.getString(2))
                        .setPrice(resultSet.getDouble(3)).setAmount(resultSet.getInt(4)).setType(resultSet.getString(5))
                        .setAttractant(resultSet.getString(6)).setDescription(resultSet.getString(7)).setPath_to_file(resultSet.getString(8))
                        .build());
            }
            return res;

        }catch (Exception ex){
            System.out.println("Something wet wrong!");
            System.out.println(ex);
        }finally {
            if (statement != null){
                try {
                    statement.close();
                }catch (Exception ex){
                    System.out.println("Something wet wrong!");
                    System.out.println(ex);
                }
            }
            if(connection != null){
                CloudscapeDAOFactory.closeConnection(connection);
            }
            if(resultSet != null){
                try {
                    resultSet.close();
                }catch (Exception ex){
                    System.out.println("Something wet wrong!");
                    System.out.println(ex);
                }
            }
        }
        return null;
    }

    @Override
    public Product show(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = CloudscapeDAOFactory.getConnection();
            preparedStatement = connection.prepareStatement(SHOW_SQL);
            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();
            resultSet.next();

            return new ProductBuilderIMpl().setProduct_id(resultSet.getInt(1)).setName(resultSet.getString(2))
                    .setPrice(resultSet.getDouble(3)).setAmount(resultSet.getInt(4)).setType(resultSet.getString(5))
                    .setAttractant(resultSet.getString(6)).setDescription(resultSet.getString(7)).setPath_to_file(resultSet.getString(8))
                    .build();

        }catch (Exception ex){
            System.out.println("Something wet wrong!");
            System.out.println(ex);
        }finally {
            if (preparedStatement != null){
                try {
                    preparedStatement.close();
                }catch (Exception ex){
                    System.out.println("Something wet wrong!");
                    System.out.println(ex);
                }
            }
            if(connection != null){
                CloudscapeDAOFactory.closeConnection(connection);
            }
            if(resultSet != null){
                try {
                    resultSet.close();
                }catch (Exception ex){
                    System.out.println("Something wet wrong!");
                    System.out.println(ex);
                }
            }
        }
        return null;
    }

    @Override
    public void save(Product product) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = CloudscapeDAOFactory.getConnection();
            preparedStatement = connection.prepareStatement(SAVE_SQL);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setString(3, product.getType());
            preparedStatement.setString(4, product.getAttractant());
            preparedStatement.setInt(5, product.getAmount());
            preparedStatement.setString(6, product.getDescription());
            preparedStatement.setString(7, product.getPath_to_file());

            int rows = preparedStatement.executeUpdate();

            notifyObservers("add", product);

        } catch (Exception ex) {
            System.out.println("Something wet wrong!");
            System.out.println(ex);
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (Exception ex) {
                    System.out.println("Something wet wrong!(preparedStatement)");
                    System.out.println(ex);
                }
            }
            if(connection != null){
                CloudscapeDAOFactory.closeConnection(connection);
            }
        }
    }

    @Override
    public void update(int id, Product updateProduct) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = CloudscapeDAOFactory.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_SQL);
            preparedStatement.setString(1, updateProduct.getName());
            preparedStatement.setDouble(2, updateProduct.getPrice());
            preparedStatement.setString(3, updateProduct.getType());
            preparedStatement.setString(4, updateProduct.getAttractant());
            preparedStatement.setInt(5, updateProduct.getAmount());
            preparedStatement.setString(6, updateProduct.getDescription());
            preparedStatement.setString(7, updateProduct.getPath_to_file());
            preparedStatement.setInt(8, id);

            int rows = preparedStatement.executeUpdate();

            notifyObservers("update", updateProduct);

        } catch (Exception ex) {
            System.out.println("Something wet wrong!");
            System.out.println(ex);
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (Exception ex) {
                    System.out.println("Something wet wrong!(preparedStatement)");
                    System.out.println(ex);
                }
            }
            if(connection != null){
                CloudscapeDAOFactory.closeConnection(connection);
            }
        }
    }

    @Override
    public void delete(int id) {
        Product productDelete = show(id);

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = CloudscapeDAOFactory.getConnection();
            preparedStatement = connection.prepareStatement(DELETE_SQL);
            preparedStatement.setInt(1, id);

            int rows = preparedStatement.executeUpdate();

            notifyObservers("delete",productDelete);

        }catch (Exception ex){
            System.out.println("Something wet wrong!");
            System.out.println(ex);
        }finally {
            if(preparedStatement != null){
                try {
                    preparedStatement.close();
                }catch (Exception ex){
                    System.out.println("Something wet wrong!");
                    System.out.println(ex);
                }
            }
            if(connection != null){
                CloudscapeDAOFactory.closeConnection(connection);
            }
        }
    }

    @Override
    public void addObserved(Observer observer) {
        this.users.add(observer);
    }

    @Override
    public void removeObserved(Observer observer) {
        this.users.remove(observer);
    }

    @Override
    public void notifyObservers(String type, Product product) {
        for(Observer observer: users){
            observer.handleEvent(type, product);
        }
    }
}
