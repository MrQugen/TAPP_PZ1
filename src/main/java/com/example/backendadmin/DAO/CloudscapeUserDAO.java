package com.example.backendadmin.DAO;

import com.example.backendadmin.model.User;
import com.example.backendadmin.model.UserBuilderIMpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CloudscapeUserDAO implements UserDAO{

    private final String INDEX_SQL = "SELECT user_id, user.name, surname, patronymic, city, login, password, role.name as role, telephone \n" +
            "FROM user\n" +
            "JOIN role on role.role_id = user.role_id";
    private final String SHOW_SQL = "SELECT user_id, user.name, surname, patronymic, city, login, password, role.name as role, telephone \n" +
            "FROM user\n" +
            "JOIN role on role.role_id = user.role_id\n" +
            "where login = ?";
    private final String SAVE_SQL = "INSERT INTO user (name, surname, patronymic, city, login, password, role_id, telephone)\n" +
            "     VALUES (?, ?, ?, ?, ?, ?,  \n" +
            "     (SELECT role_id FROM role WHERE name='ROLE_USER'), ?);";
    private final String UPDATE_SQL = "UPDATE user SET name=?, surname=?, patronymic=?, city=?, login=?,\n" +
            "password=?, telephone=?\n" +
            "WHERE login = ?;";
    private final String UPDATE_USER_INFO_SQL = "UPDATE user SET name=?, surname=?, patronymic=?, city=?, telephone=?\n" +
            "WHERE login = ?;";
    private final String UPDATE_USER_SECURITY_SQL = "UPDATE user SET login=?, password=? WHERE login = ?;";
    private final String DELETE_SQL = "DELETE FROM user WHERE user_id=?";

    public CloudscapeUserDAO(){}

    @Override
    public List<User> index() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = CloudscapeDAOFactory.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(INDEX_SQL);

            List<User> res = new ArrayList<>();

            while (resultSet.next()){

                res.add(new UserBuilderIMpl().setUser_id(resultSet.getInt(1)).setName(resultSet.getString(2)).setSurname(resultSet.getString(3))
                        .setPatronymic(resultSet.getString(4)).setCity(resultSet.getString(5)).setLogin(resultSet.getString(6))
                        .setPassword(resultSet.getString(7)).setRole(resultSet.getString(8)).setTelephone(resultSet.getString(9)).build());
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
    public User show(String login) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = CloudscapeDAOFactory.getConnection();
            preparedStatement = connection.prepareStatement(SHOW_SQL);
            preparedStatement.setString(1, login);

            resultSet = preparedStatement.executeQuery();
            resultSet.next();

            return new UserBuilderIMpl().setUser_id(resultSet.getInt(1)).setName(resultSet.getString(2)).setSurname(resultSet.getString(3))
                    .setPatronymic(resultSet.getString(4)).setCity(resultSet.getString(5)).setLogin(resultSet.getString(6))
                    .setPassword(resultSet.getString(7)).setRole(resultSet.getString(8)).setTelephone(resultSet.getString(9)).build();

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
    public void save(User NewUser) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = CloudscapeDAOFactory.getConnection();
            preparedStatement = connection.prepareStatement(SAVE_SQL);
            preparedStatement.setString(1, NewUser.getName());
            preparedStatement.setString(2, NewUser.getSurname());
            preparedStatement.setString(3, NewUser.getPatronymic());
            preparedStatement.setString(4, NewUser.getCity());
            preparedStatement.setString(5, NewUser.getLogin());
            preparedStatement.setString(6, NewUser.getPassword());
            preparedStatement.setString(7, NewUser.getTelephone());

            int rows = preparedStatement.executeUpdate();

            System.out.println(rows + " rows added");

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
    public void update(String login, User updateUser) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = CloudscapeDAOFactory.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_SQL);
            preparedStatement.setString(1, updateUser.getName());
            preparedStatement.setString(2, updateUser.getSurname());
            preparedStatement.setString(3, updateUser.getPatronymic());
            preparedStatement.setString(4, updateUser.getCity());
            preparedStatement.setString(5, updateUser.getLogin());
            preparedStatement.setString(6, updateUser.getPassword());
            preparedStatement.setString(7, updateUser.getTelephone());
            preparedStatement.setString(8, login);

            int rows = preparedStatement.executeUpdate();

            System.out.println(rows + " rows update!");

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
    public void update_user_info(String login, User updateUser) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = CloudscapeDAOFactory.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_USER_INFO_SQL);
            preparedStatement.setString(1, updateUser.getName());
            preparedStatement.setString(2, updateUser.getSurname());
            preparedStatement.setString(3, updateUser.getPatronymic());
            preparedStatement.setString(4, updateUser.getCity());
            preparedStatement.setString(5, updateUser.getTelephone());
            preparedStatement.setString(6, login);

            int rows = preparedStatement.executeUpdate();

            System.out.println(rows + " rows update!");

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
    public void update_user_security(String login, User updateUser) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = CloudscapeDAOFactory.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_USER_SECURITY_SQL);
            preparedStatement.setString(1, updateUser.getLogin());
            preparedStatement.setString(2, updateUser.getPassword());
            preparedStatement.setString(3, login);

            int rows = preparedStatement.executeUpdate();

            System.out.println(rows + " rows update!");

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
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = CloudscapeDAOFactory.getConnection();
            preparedStatement = connection.prepareStatement(DELETE_SQL);
            preparedStatement.setInt(1, id);

            int rows = preparedStatement.executeUpdate();
            System.out.println(rows + " rows delete!");
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
}
