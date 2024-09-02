package org.example;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;

    public class CargaCliente extends ConnectionBaseDeDatos {
        public static void cargarDatosCSV() {

            String csvFile = "src/main/resources/csv/clientes.csv";

            try (Connection connection = getConnection();
                 CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(csvFile))) {

//                // limpiamos tabla antes de insertar nuevos datos
                String deleteSQL = "DELETE FROM Cliente";
                try (PreparedStatement deleteStatement = connection.prepareStatement(deleteSQL)) {
                    deleteStatement.executeUpdate();
                }
                //insertar
                String insertCliente = "INSERT INTO Cliente (idCliente, nombre, email) VALUES (?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(insertCliente);

                for (CSVRecord row : parser) {
                    int idCliente = Integer.parseInt(row.get("idCliente"));
                    String nombre = row.get("nombre");
                    String email = row.get("email");

                    preparedStatement.setInt(1, idCliente);
                    preparedStatement.setString(2, nombre);
                    preparedStatement.setString(3, email);

                    preparedStatement.executeUpdate();
                }

                System.out.println("Datos cargados exitosamente en la tabla cliente");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
