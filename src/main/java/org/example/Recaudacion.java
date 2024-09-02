package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Recaudacion extends ConnectionBaseDeDatos{
    public static void mayorRecaudacion() {

        try (Connection connection = getConnection()){

            String mayorRecaudacion = "SELECT p.idProducto, p.nombre, SUM(fp.cantidad * p.valor) AS recaudacion " +
                    "FROM Productos p " +
                    "JOIN Facturas_Productos fp ON p.idProducto = fp.idProducto " +
                    "JOIN Facturas f ON fp.idFactura = f.idFactura " +
                    "GROUP BY p.idProducto, p.nombre " +
                    "ORDER BY recaudacion DESC " +
                    "LIMIT 1";
                 PreparedStatement preparedStatement = connection.prepareStatement(mayorRecaudacion);

            ResultSet resultSet = preparedStatement.executeQuery();


            if (resultSet.next()) {
                int idProducto = resultSet.getInt("idProducto");
                String nombre = resultSet.getString("nombre");
                double recaudacion = resultSet.getDouble("recaudacion");

                System.out.println("Producto con mayor recaudación:");
                System.out.println("ID: " + idProducto);
                System.out.println("Nombre: " + nombre);
                System.out.println("Recaudación: $" + recaudacion);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
}}