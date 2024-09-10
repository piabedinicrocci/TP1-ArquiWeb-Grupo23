package org.example.entity;

public class ClienteFacturacion extends Cliente {
    private float facturacion;

    public ClienteFacturacion(int id, String nombre, String email, float fact) {
        super(id, nombre, email);
        this.facturacion=fact;

    }

    public float getFacturacion() {
        return facturacion;
    }

    public void setFacturacion(float facturacion) {
        this.facturacion = facturacion;
    }

    @Override
    public String toString() {
        return "Id:" + this.getIdCliente() + " " + "Nombre:" + this.getNombre() + " " + "Email:" + this.getEmail()+ ", facturacion=" + facturacion ;
    }
}