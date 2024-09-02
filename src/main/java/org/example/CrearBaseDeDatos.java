package org.example;

import java.sql.*;

public class CrearBaseDeDatos {

    public static void connection() {
        String uri = "jdbc:mysql://localhost:3306/arquiweb";
        String user = "root";
        String password = "examplepass";

        try (Connection connection = DriverManager.getConnection(uri, user, password);
             Statement statement = connection.createStatement()) {

            connection.setAutoCommit(false);

            crearTablas(connection);

            connection.commit();
            System.out.println("Base de datos creada.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void crearTablas(Connection connection) throws SQLException {
        // Crear tabla Cliente si no existe
        if (!isTableExists(connection, "Cliente")) {
            String tablaCliente = "CREATE TABLE Cliente (" +
                    "idCliente INT PRIMARY KEY, " +
                    "nombre VARCHAR(100), " +
                    "email VARCHAR(100))";
            connection.prepareStatement(tablaCliente).execute();
            System.out.println("Tabla Cliente creada.");
        }

        // Crear tabla Productos si no existe
        if (!isTableExists(connection, "Productos")) {
            String tablaProductos = "CREATE TABLE Productos (" +
                    "idProducto INT PRIMARY KEY, " +
                    "nombre VARCHAR(100), " +
                    "valor DECIMAL(10,2))";
            connection.prepareStatement(tablaProductos).execute();
            System.out.println("Tabla Productos creada.");
        }

        // Crear tabla Facturas si no existe
        if (!isTableExists(connection, "Facturas")) {
            String tablaFacturas = "CREATE TABLE Facturas (" +
                    "idFactura INT PRIMARY KEY, " +
                    "idCliente INT, " +
                    "FOREIGN KEY (idCliente) REFERENCES Cliente(idCliente))" ;
            connection.prepareStatement(tablaFacturas).execute();
            System.out.println("Tabla Facturas creada.");
        }

        // Crear tabla Facturas_Productos si no existe
        if (!isTableExists(connection, "Facturas_Productos")) {
            String tablaFacturasProductos = "CREATE TABLE Facturas_Productos (" +
                    "idFactura INT, " +
                    "idProducto INT, " +
                    "cantidad INT, " +
                    "PRIMARY KEY (idFactura, idProducto), " +
                    "FOREIGN KEY (idFactura) REFERENCES Facturas(idFactura), " +

                    "FOREIGN KEY (idProducto) REFERENCES Productos(idProducto))" ;
            connection.prepareStatement(tablaFacturasProductos).execute();
            System.out.println("Tabla Facturas_Productos creada.");
        }
    }

    private static boolean isTableExists(Connection connection, String tableName) throws SQLException {
        DatabaseMetaData dbMetaData = connection.getMetaData();
        try (ResultSet rs = dbMetaData.getTables(null, null, tableName, null)) {
            return rs.next();
        }
    }
}
