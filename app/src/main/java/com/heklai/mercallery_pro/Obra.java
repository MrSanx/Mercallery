package com.heklai.mercallery_pro;

public class Obra {
    private String idPieza, idPersona, nombre, precio, fechaRealizacion, estado;

    public Obra() {
    }

    public Obra(String idPieza, String idPersona, String nombre, String precio, String fechaRealizacion, String estado) {
        this.idPieza = idPieza;
        this.idPersona = idPersona;
        this.nombre = nombre;
        this.precio = precio;
        this.fechaRealizacion = fechaRealizacion;
        this.estado = estado;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getFechaRealizacion() {
        return fechaRealizacion;
    }

    public void setFechaRealizacion(String fechaRealizacion) {
        this.fechaRealizacion = fechaRealizacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}