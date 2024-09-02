package org.example;

public class Main {
    public static void main(String[] args) {

        CrearBaseDeDatos crearBaseDeDatos = new CrearBaseDeDatos();
        crearBaseDeDatos.connection();

//        CargaCliente cargaCliente = new CargaCliente();
//        cargaCliente.cargarDatosCSV();
//
//        CargaFactura cargaFactura = new CargaFactura();
//        cargaFactura.cargarDatosCSV();
//
//        CargaProductos cargaProductos = new CargaProductos();
//        cargaProductos.cargarDatosCSV();
//
//        CargaFacturaProducto cargaFacturaProductos = new CargaFacturaProducto();
//        cargaFacturaProductos.cargarDatosCSV();

       // System.out.println("Base de datos creada y datos cargados");

        Recaudacion recaudacion = new Recaudacion();
        recaudacion.mayorRecaudacion();

        ListaClientes lista = new ListaClientes();
        ListaClientes.listaMayor();

    }
}