package org.example.dao;

import org.example.dto.RecaudacionDTO;
import org.example.entity.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FacturaProductoMysqlDAO {
    private Connection conn;

    public FacturaProductoMysqlDAO(Connection conn) {
        this.conn = conn;
    }

    public int insertProducto(Producto dao) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insertProducto'");
    }
    public RecaudacionDTO getRecaudacion() {
        String query = "SELECT p.idProducto, p.nombre AS nombreProducto, p.valorProducto AS precioUnitario, " +
                "SUM(fp.cantidad) AS cantidadVendida, SUM(fp.cantidad * p.valorProducto) AS recaudacion " +
                "FROM Productos p " +
                "JOIN Facturas_Productos fp ON p.idProducto = fp.idProducto " +
                "GROUP BY p.idProducto, p.nombre, p.valorProducto " +
                "ORDER BY recaudacion DESC " +
                "LIMIT 1";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = conn.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int idProducto = resultSet.getInt("idProducto");
                String nombreProducto = resultSet.getString("nombreProducto");
                double precioUnitario = resultSet.getDouble("precioUnitario");
                int cantidadVendida = resultSet.getInt("cantidadVendida");

                // Crear el objeto RecaudacionDTO con los datos obtenidos
                return new RecaudacionDTO(idProducto, nombreProducto, precioUnitario, cantidadVendida);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar ResultSet y PreparedStatement
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
