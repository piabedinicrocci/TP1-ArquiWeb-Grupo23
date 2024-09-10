package org.example.entity;

public class Producto {
    private int idProducto;
    private String nombre;
    private double valorProducto;

    public Producto(int id, String nombre, double valor) {
        this.idProducto = id;
        this.nombre = nombre;
        this.valorProducto = valor;
    }

    public Producto getProducto() {
        return new Producto(this.idProducto, this.nombre, this.valorProducto);
    }

    public int getIdProducto() {
        return idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public double getValor() {
        return valorProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setValorProducto(int valorProducto) {
        this.valorProducto = valorProducto;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "idProducto=" + idProducto +
                ", nombre='" + nombre + '\'' +
                ", valorProducto=" + valorProducto +
                '}';
    }
}
