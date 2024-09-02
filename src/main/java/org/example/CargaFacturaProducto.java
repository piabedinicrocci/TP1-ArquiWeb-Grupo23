package org.example;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class CargaFacturaProducto extends ConnectionBaseDeDatos {
    public static void cargarDatosCSV() {

        String csvFile = "src/main/resources/csv/facturas-productos.csv";

        try (Connection connection = getConnection();
             CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(csvFile))) {

//            // limpiamos tabla antes de insertar nuevos datos
            String deleteSQL = "DELETE FROM Facturas_Productos";
            try (PreparedStatement deleteStatement = connection.prepareStatement(deleteSQL)) {
                deleteStatement.executeUpdate();
            }


            String insertFacturaProducto = "INSERT INTO Facturas_Productos (idFactura, idProducto, cantidad) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertFacturaProducto);

            for (CSVRecord row : parser) {
                int idFactura = Integer.parseInt(row.get("idFactura"));
                int idProducto = Integer.parseInt(row.get("idProducto"));
                int cantidad = Integer.parseInt(row.get("cantidad"));

                preparedStatement.setInt(1, idFactura);
                preparedStatement.setInt(2, idProducto);
                preparedStatement.setInt(3, cantidad);
                preparedStatement.executeUpdate();
            }

            System.out.println("Datos cargados exitosamente en la tabla facturas-prodctuos");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
