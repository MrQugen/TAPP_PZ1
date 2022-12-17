package com.example.backendadmin.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CloudscapeDAOFactory extends DAOFactory {
        private static volatile Connection conn;

        public CloudscapeDAOFactory(){}

        public static synchronized Connection getConnection() throws SQLException {
            if (conn == null || conn.isClosed()) {
                synchronized (Connection.class)
                {
                    if(conn == null || conn.isClosed()){
                        try{
                            Class.forName(ConnectionMetaData.Load_MySql_JDBC_DRIVER).getDeclaredConstructor().newInstance();
                            conn = DriverManager.getConnection(ConnectionMetaData.URL, ConnectionMetaData.USER, ConnectionMetaData.PASSWORD);
                        } catch (Exception ex){
                            System.out.println("Connection failed...");
                            System.out.println(ex);
                        }
                    }
                }
            }

            return conn;
        }

    public static void closeConnection(Connection conn) {

        try {

            conn.close();

        } catch (SQLException ex) {
            System.out.println("Connection.close() failed...");
            System.out.println(ex);
        }

    }

    @Override
    public CloudscapeProductDAO getProductDAO() {
        return new CloudscapeProductDAO();
    }

    @Override
    public CloudscapeUserDAO getUserDAO() {
        return new CloudscapeUserDAO();
    }
}