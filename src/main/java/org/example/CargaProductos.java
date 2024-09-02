package org.example;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class CargaProductos extends ConnectionBaseDeDatos {
    public static void cargarDatosCSV() {

        String csvFile = "src/main/resources/csv/productos.csv";

        try (Connection connection = getConnection();
             CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(csvFile))) {

//            // limpiamos tabla antes de insertar nuevos datos
            String deleteSQL = "DELETE FROM Productos";
            try (PreparedStatement deleteStatement = connection.prepareStatement(deleteSQL)) {
                deleteStatement.executeUpdate();
            }

            String insertProducto = "INSERT INTO Productos (idProducto, nombre, valor) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertProducto);

            for (CSVRecord row : parser) {
                int idProducto = Integer.parseInt(row.get("idProducto"));
                String nombre = row.get("nombre");
                double valor = Double.parseDouble(row.get("valor"));

                preparedStatement.setInt(1, idProducto);
                preparedStatement.setString(2, nombre);
                preparedStatement.setDouble(3, valor);

                preparedStatement.executeUpdate();
            }

            System.out.println("Datos cargados exitosamente en la tabla Productos.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}