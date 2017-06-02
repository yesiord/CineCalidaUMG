package com.projects.ron.cinecalidaumg.Modelos;

import java.sql.Blob;

/**
 * Created by Soporte on 01/06/2017.
 */

public class Producto {

    public Producto(){

    }

    public Producto(double precio, String titulo, int idProducto, byte[] img) {
        this.precio = precio;
        this.titulo = titulo;
        this.idProducto = idProducto;
        this.img = img;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    private double precio;
    private String titulo;
    private int idProducto;
    private byte[] img;

}
