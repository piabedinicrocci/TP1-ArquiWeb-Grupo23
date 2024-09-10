package org.example.utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.example.entity.Cliente;
import org.example.entity.FacturaProducto;
import org.example.entity.Facturas;
import org.example.entity.Producto;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HelperMySQL {
    private Connection conn = null;

    public HelperMySQL() {//Constructor
        String driver = "com.mysql.cj.jdbc.Driver";
        String uri = "jdbc:mysql://localhost:3306/tp1_integrador_arqui";

        try {
            Class.forName(driver).getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                 | NoSuchMethodException | SecurityException | ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }

        try {
            conn = DriverManager.getConnection(uri, "root", "123");
            conn.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        if (conn != null){
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void dropTables() throws SQLException {

        String dropFacturasProductos = "DROP TABLE IF EXISTS Facturas_Productos";
        this.conn.prepareStatement(dropFacturasProductos).execute();
        this.conn.commit();

        String dropFacturas = "DROP TABLE IF EXISTS Facturas";
        this.conn.prepareStatement(dropFacturas).execute();
        this.conn.commit();

        String dropProductos = "DROP TABLE IF EXISTS Productos";
        this.conn.prepareStatement(dropProductos).execute();
        this.conn.commit();

        String dropCliente = "DROP TABLE IF EXISTS Cliente";
        this.conn.prepareStatement(dropCliente).execute();
        this.conn.commit();


    }

    public void createTables() throws SQLException {
        String tablaProductos = "CREATE TABLE IF NOT EXISTS Productos (" +
                "idProducto INT PRIMARY KEY, " +
                "nombre VARCHAR(100),"+
                "valorProducto DOUBLE)";
        this.conn.prepareStatement(tablaProductos).execute();
        this.conn.commit();

        String tablaCliente = "CREATE TABLE IF NOT EXISTS Cliente (" +
                "idCliente INT PRIMARY KEY, " +
                "nombre VARCHAR(100), " +
                "email VARCHAR(100))";
        this.conn.prepareStatement(tablaCliente).execute();
        this.conn.commit();
        String tablaFactura = "CREATE TABLE IF NOT EXISTS Facturas (" +
                "idFactura INT PRIMARY KEY, " +
                "idCliente INT, " +
                "FOREIGN KEY (idCliente) REFERENCES Cliente(idCliente))" ;
        this.conn.prepareStatement(tablaFactura).execute();
        this.conn.commit();
        String tablaFacturasProductos = "CREATE TABLE IF NOT EXISTS  Facturas_Productos (" +
                "idFactura INT NOT NULL , " +
                "idProducto INT NOT NULL , " +
                "cantidad INT NOT NULL , " +
                "PRIMARY KEY (idFactura, idProducto), " +
                "FOREIGN KEY (idFactura) REFERENCES Facturas(idFactura), " +
                "FOREIGN KEY (idProducto) REFERENCES Productos(idProducto))";

        this.conn.prepareStatement(tablaFacturasProductos).execute();
        this.conn.commit();

    }

    private Iterable<CSVRecord> getData(String archivo) throws IOException {
        String path = "src\\main\\resources\\csv\\" + archivo;
        Reader in = new FileReader(path);
        String[] header = {};
        CSVParser csvParser = CSVFormat.EXCEL.withHeader(header).parse(in);

        Iterable<CSVRecord> records = csvParser.getRecords();
        return records;
    }

    public void populateDB() throws Exception {
        try {
            System.out.println("Populating DB...");
            for(CSVRecord row : getData("clientes.csv")) {
                if(row.size() >= 3) { // Verificar que hay al menos 4 campos en el CSVRecord
                    String idString = row.get(0);

                    if(!idString.isEmpty() ) {
                        try {
                            int id = Integer.parseInt(idString);

                            Cliente cliente = new Cliente(id, row.get(1), row.get(2));
                            insertCliente(cliente, conn);
                        } catch (NumberFormatException e) {
                            System.err.println("Error de formato en datos de dirección: " + e.getMessage());
                        }
                    }
                }
            }
            System.out.println("Clientes insertados");

            for (CSVRecord row : getData("facturas.csv")) {
                if (row.size() >= 2) { // Verificar que hay al menos 4 campos en el CSVRecord
                    String idFacturaString = row.get(0);
                    String idClienteString = row.get(1);

                    if (!idFacturaString.isEmpty() && !idClienteString.isEmpty() ) {
                        try {
                            int idFactura = Integer.parseInt(idFacturaString);
                            int idCliente = Integer.parseInt(idClienteString);

                            Facturas factura = new Facturas(idFactura, idCliente);
                            insertFactura(factura, conn);
                        } catch (NumberFormatException e) {
                            System.err.println("Error de formato en datos de factura: " + e.getMessage());
                        }
                    }
                }
            }

            System.out.println("Facturas insertadas");

            for (CSVRecord row : getData("productos.csv")) {
                if (row.size() >= 2) { // Verificar que hay al menos 2 campos en el CSVRecord
                    String idProductoString = row.get(0);
                    String nombre = row.get(1);
                    double valorProducto = Double.parseDouble(row.get("valor"));

                    if (!idProductoString.isEmpty() && !nombre.isEmpty()) {
                        try {
                            int idProducto = Integer.parseInt(idProductoString);

                            Producto producto = new Producto(idProducto, nombre,valorProducto);
                            insertProducto(producto, conn);
                        } catch (NumberFormatException e) {
                            System.err.println("Error de formato en datos de producto: " + e.getMessage());
                        }
                    }
                }
            }

            System.out.println("Productos insertados");
            for (CSVRecord row : getData("facturas-productos.csv")) {
                if (row.size() >= 3) { // Verificar que hay al menos 4 campos en el CSVRecord
                    String idFacturaString = row.get(0);
                    String idProductoString = row.get(1);
                    String cantidadString = row.get(2);

                    if (!idFacturaString.isEmpty() && !idProductoString.isEmpty() ) {
                        try {
                            int idFactura = Integer.parseInt(idFacturaString);
                            int idProducto = Integer.parseInt(idProductoString);
                           int cantidad = Integer.parseInt(cantidadString);

                            FacturaProducto facturaProducto = new FacturaProducto(idFactura, idProducto,cantidad);
                            insertFacturaProducto(facturaProducto, conn);
                        } catch (NumberFormatException e) {
                            System.err.println("Error de formato en datos de factura Producot: " + e.getMessage());
                        }
                    }
                }
            }

            System.out.println("Factura- productos insertadas");



        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int insertProducto(Producto producto, Connection conn) throws Exception {
        String insertProducto = "INSERT INTO Productos (idProducto, nombre, valorProducto) VALUES (?, ?, ?)";
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(insertProducto);
            ps.setInt(1, producto.getIdProducto());
            ps.setString(2, producto.getNombre());
            ps.setDouble(3, producto.getValor());

            if (ps.executeUpdate() == 0) {
                throw new Exception("No se pudo insertar");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closePsAndCommit(conn, ps);
        }
        return 0;
    }

    private int insertCliente (Cliente cliente, Connection conn) throws Exception{
        String insertCliente = "INSERT INTO Cliente (idCliente, nombre, email) VALUES (?, ?, ?)";
        PreparedStatement ps = null;

       try{
            ps=conn.prepareStatement(insertCliente);
            ps.setInt(1, cliente.getIdCliente());
            ps.setString(2, cliente.getNombre());
            ps.setString(3, cliente.getEmail());

            if (ps.executeUpdate() == 0) {
                throw new Exception("No se pudo insertar");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closePsAndCommit(conn, ps);
        }
        return 0;
    }


    private int insertFactura(Facturas factura, Connection conn) throws Exception {

        String insertFactura = "INSERT INTO Facturas (idFactura, idCliente) VALUES (?, ?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(insertFactura);
            ps.setInt(1,factura.getIdFactura());
            ps.setInt(2,factura.getIdCliente());
            if (ps.executeUpdate() == 0) {
                throw new Exception("No se pudo insertar");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closePsAndCommit(conn, ps);
        }
        return 0;
    }

    private int insertFacturaProducto(FacturaProducto facturaProducto, Connection conn) throws Exception {
        String insertFacturaProducto = "INSERT INTO Facturas_Productos (idFactura, idProducto, cantidad)VALUES (?, ?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(insertFacturaProducto);
            ps.setInt(1,facturaProducto.getIdFactura());
            ps.setInt(2,facturaProducto.getIdProducto());
            ps.setInt(3, facturaProducto.getCantidad());
            if (ps.executeUpdate() == 0) {
                throw new Exception("No se pudo insertar");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closePsAndCommit(conn, ps);
        }
        return 0;

    }

    private void closePsAndCommit(Connection conn, PreparedStatement ps) {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar el PreparedStatement: " + e.getMessage());
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.commit();
            } catch (SQLException e) {
                System.err.println("Error al hacer commit en la conexión: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}


