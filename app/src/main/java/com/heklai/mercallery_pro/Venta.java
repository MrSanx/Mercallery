package com.heklai.mercallery_pro;

public class Venta {
    public String idVenta,idPieza,idPersona,fechaVenta;

    public Venta() {
    }

    public Venta(String idVenta, String idPieza, String idPersona, String fechaVenta) {
        this.idVenta = idVenta;
        this.idPieza = idPieza;
        this.idPersona = idPersona;
        this.fechaVenta = fechaVenta;
    }

    public String getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(String idVenta) {
        this.idVenta = idVenta;
    }

    public String getIdPieza() {
        return idPieza;
    }

    public void setIdPieza(String idPieza) {
        this.idPieza = idPieza;
    }

    public String getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(String idPersona) {
        this.idPersona = idPersona;
    }

    public String getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(String fechaVenta) {
        this.fechaVenta = fechaVenta;
    }
}
