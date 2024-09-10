package org.example.entity;

public class FacturaProducto {
    private int idFactura;
    private int idProducto;
    private int cantidad;

    public FacturaProducto(int idFactura, int idProducto, int cantidad) {
        this.idFactura = idFactura;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
    }

    public FacturaProducto getFacturaProducto() {
        return new FacturaProducto(this.idFactura, this.idProducto, this.cantidad);
    }

    public int getIdFactura() {
        return idFactura;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "FacturaProducto{" +
                "idFactura=" + idFactura +
                ", idProducto=" + idProducto +
                ", cantidad=" + cantidad +
                '}';
    }
}
