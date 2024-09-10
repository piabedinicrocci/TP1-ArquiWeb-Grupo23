package org.example.dto;

public class ClienteDTO {
    private int idCliente;
    private String nombre;
    private String email;
    private double totalFacturado;

    public ClienteDTO(int id, String nombre, String email, double totalFacturado) {
        this.idCliente = id;
        this.nombre = nombre;
        this.email = email;
        this.totalFacturado = totalFacturado;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public double getTotalFacturado() {
        return totalFacturado;
    }

    @Override
    public String toString() {
        return "ClienteDTO{" +
                "idCliente=" + idCliente +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", totalFacturado=" + totalFacturado +
                '}';
    }
}
