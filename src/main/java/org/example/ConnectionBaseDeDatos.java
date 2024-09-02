package org.example;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

    public class ConnectionBaseDeDatos {

        private static final String URI = "jdbc:mysql://localhost:3306/arquiweb";
        private static final String USER = "root";
        private static final String PASSWORD = "examplepass";

        public static Connection getConnection() throws SQLException {
            return DriverManager.getConnection(URI, USER, PASSWORD);
        }


        protected void closeConnection(Connection connection) {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
