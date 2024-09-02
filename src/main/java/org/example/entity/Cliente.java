package org.example.entity;

public class Cliente {
    private int idCliente;
    private String nombre;
    private String email;

    public Cliente(int id, String nombre, String email) {
        this.idCliente = id;
        this.nombre = nombre;
        this.email = email;
    }

    public Cliente getCliente() {
        return new Cliente(this.idCliente, this.nombre, this.email);
    }

    public String toString() {
        return "Id:" + this.idCliente + " " + "Nombre:" + this.nombre + " " + "Email:" + this.email;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int id) {
        this.idCliente = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}