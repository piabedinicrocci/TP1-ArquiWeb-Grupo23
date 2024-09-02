package org.example;

import org.example.entity.ClienteFacturacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ListaClientes extends ConnectionBaseDeDatos {

    public static ArrayList<ClienteFacturacion> listaMayor() {
        ArrayList<ClienteFacturacion> clientesConFacturacion = new ArrayList<>();

        try (Connection connection = getConnection()) {

            String mayorFacturacion = "SELECT c.idCliente, c.nombre, c.email, SUM(fp.cantidad * p.valor) AS Facturacion " +
                    "FROM Cliente c " +
                    "JOIN Facturas f ON c.idCliente = f.idCliente " +
                    "JOIN Facturas_Productos fp ON f.idFactura = fp.idFactura " +
                    "JOIN Productos p ON p.idProducto = fp.idProducto " +
                    "GROUP BY c.idCliente, c.nombre, c.email " +
                    "ORDER BY Facturacion DESC LIMIT 4";
            PreparedStatement preparedStatement = connection.prepareStatement(mayorFacturacion);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Procesar resultados
            while (resultSet.next()) {
                int idCliente = resultSet.getInt("idCliente");
                String nombre = resultSet.getString("nombre");
                String email = resultSet.getString("email");
                float facturacion = resultSet.getFloat("Facturacion");

                // Crear instancia de ClienteFacturacion y agregarla a la lista
                ClienteFacturacion cliente = new ClienteFacturacion(idCliente, nombre, email, facturacion);
                clientesConFacturacion.add(cliente);

                System.out.println("Lista de Clientes, ordenada por cuál facturó más: ");
                System.out.println("ID: " + idCliente);
                System.out.println("Nombre: " + nombre);
                System.out.println("Email: $" + email);
                System.out.println("Facturación: $" + facturacion);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return clientesConFacturacion;
    }
}
