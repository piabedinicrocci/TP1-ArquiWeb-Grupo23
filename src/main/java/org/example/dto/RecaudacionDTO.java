package org.example.dto;

public class RecaudacionDTO {
    private int idProducto;
    private String nombreProducto;
    private double precioUnitario;
    private int cantidadVendida;

    public RecaudacionDTO(int idProducto, String nombreProducto, double precioUnitario, int cantidadVendida) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.precioUnitario = precioUnitario;
        this.cantidadVendida = cantidadVendida;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public int getCantidadVendida() {
        return cantidadVendida;
    }

    public double getImporteTotal() {
        return precioUnitario * cantidadVendida;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public void setCantidadVendida(int cantidadVendida) {
        this.cantidadVendida = cantidadVendida;
    }

    @Override
    public String toString() {
        return "RecaudacionDTO{" +
                "idProducto=" + idProducto +
                ", nombreProducto='" + nombreProducto + '\'' +
                ", precioUnitario=" + precioUnitario +
                ", cantidadVendida=" + cantidadVendida +
                ", importeTotal=" + getImporteTotal() +
                '}';

    }
}
