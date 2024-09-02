package org.example;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class CargaFactura extends ConnectionBaseDeDatos {
    public static void cargarDatosCSV() {

        String csvFile = "src/main/resources/csv/facturas.csv";

        try (Connection connection = getConnection();
             CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(csvFile))) {

//            // limpiamos tabla antes de insertar nuevos datos
            String deleteSQL = "DELETE FROM Facturas";
            try (PreparedStatement deleteStatement = connection.prepareStatement(deleteSQL)) {
                deleteStatement.executeUpdate();
            }

            String insertFactura = "INSERT INTO Facturas (idFactura, idCliente) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertFactura);

            for (CSVRecord row : parser) {
                int idFactura = Integer.parseInt(row.get("idFactura"));
                int idCliente = Integer.parseInt(row.get("idCliente"));

                preparedStatement.setInt(1, idFactura);
                preparedStatement.setInt(2, idCliente);

                preparedStatement.executeUpdate();
            }

            System.out.println("Datos cargados exitosamente en la tabla factura");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
