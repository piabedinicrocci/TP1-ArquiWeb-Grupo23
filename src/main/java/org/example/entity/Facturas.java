package org.example.entity;

public class Facturas {
    private int idFactura;
    private int idCliente;
    public Facturas(int id, int idCliente) {
        this.idFactura = id;
        this.idCliente = idCliente;
    }
    public Facturas getFactura() {
        return new Facturas(this.idFactura, this.idCliente);
    }
    public int getIdFactura() {
        return idFactura;
    }
    public int getIdCliente() {
        return idCliente;
    }
    public void setIdFactura(int id) {
        this.idFactura = id;
    }
    public void setIdCliente(int id) {
        this.idCliente = id;
    }

}
