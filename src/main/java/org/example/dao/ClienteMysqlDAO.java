package org.example.dao;

import org.example.dto.ClienteDTO;
import org.example.entity.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteMysqlDAO {
    private Connection conn;

    public ClienteMysqlDAO(Connection conn) {
        this.conn = conn;
    }

    public int inser(Cliente dao) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insert'");
    }


    public List<Cliente> selectList() {
        String query = "SELECT c.idCliente, c.nombre, c.email, IFNULL(SUM(fp.cantidad * p.valorProducto), 0) AS totalFacturado " +
                      "FROM Cliente c " +
                "LEFT JOIN Facturas f ON c.idCliente = f.idCliente " +
                "LEFT JOIN Facturas_Productos fp ON f.idFactura = fp.idFactura " +
                "LEFT JOIN Productos p ON fp.idProducto = p.idProducto " +
                "GROUP BY c.idCliente, c.nombre, c.email " +
                "ORDER BY totalFacturado DESC";
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Cliente> listado = null;
        try {
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            listado = new ArrayList<Cliente>();
            while (rs.next()) { // Verificar si hay resultados
                int idCliente = rs.getInt("idCliente");
                String nombre = rs.getString("nombre");
                String email = rs.getString("email");
                double totalFacturado = rs.getDouble("totalFacturado");

                Cliente cliente = new Cliente(idCliente,nombre,email);
                listado.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return listado;
    }

}
