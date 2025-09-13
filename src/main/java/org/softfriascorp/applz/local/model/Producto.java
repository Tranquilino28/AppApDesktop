/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.local.model;

/**
 *
 * @author TRANQUILINO FRIAS
 */
public class Producto {
     private Integer id;
    private String codigo;
    private String nombre;
    private String descripcion;
    private double precio;
    private String unidadmedida;
    private int maes_ticatpro;
    private int stockDisponible;
    private int emprId;

    public Producto() {
    }

    public Producto(Integer id, String codigo, String nombre, String descripcion, double precio, String unidadmedida, int maes_ticatpro, int stockDisponible, int emprId) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.unidadmedida = unidadmedida;
        this.maes_ticatpro = maes_ticatpro;
        this.stockDisponible = stockDisponible;
        this.emprId = emprId;
    }

    public Producto(String codigo, String nombre, String descripcion, double precio, String unidadmedida, int maes_ticatpro, int stockDisponible, int emprId) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.unidadmedida = unidadmedida;
        this.maes_ticatpro = maes_ticatpro;
        this.stockDisponible = stockDisponible;
        this.emprId = emprId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getUnidadmedida() {
        return unidadmedida;
    }

    public void setUnidadmedida(String unidadmedida) {
        this.unidadmedida = unidadmedida;
    }

    public int getMaes_ticatpro() {
        return maes_ticatpro;
    }

    public void setMaes_ticatpro(int maes_ticatpro) {
        this.maes_ticatpro = maes_ticatpro;
    }

    public int getStockDisponible() {
        return stockDisponible;
    }

    public void setStockDisponible(int stockDisponible) {
        this.stockDisponible = stockDisponible;
    }

    public int getEmprId() {
        return emprId;
    }

    public void setEmprId(int emprId) {
        this.emprId = emprId;
    }

    
}
