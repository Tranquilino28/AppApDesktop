/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.model;

import java.io.Serializable;
import lombok.Data;

/**
 *
 * @author usuario
 */

    // Esta clase es un POJO (Plain Old Java Object)
// que representa la entidad Producto.
// Implementa Serializable para poder pasar los objetos entre componentes si es necesario.
 // Anotación de Lombok para generar getters, setters, toString, equals, hashCode y el constructor.
public class Producto_dto implements Serializable {
    
    private Long id;
    private String codigo;
    private String nombre;
    private String descripcion;
    private Double precio;
    private String categoria;
    private Integer stockDisponible;
    private Long emprId; // Usamos solo el ID de la empresa para simplificar el objeto.

    // Constructor vacío (necesario para algunas librerías como Jackson).
    public Producto_dto() {
    }

  
    
    // Métodos Getter y Setter generados por Lombok (@Data)
    // También puedes escribirlos manualmente si no usas Lombok.
    // ...

    public Producto_dto(Long id, String codigo, String nombre, String descripcion, Double precio, String categoria, Integer stockDisponible, Long emprId) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.categoria = categoria;
        this.stockDisponible = stockDisponible;
        this.emprId = emprId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Integer getStockDisponible() {
        return stockDisponible;
    }

    public void setStockDisponible(Integer stockDisponible) {
        this.stockDisponible = stockDisponible;
    }

    public Long getEmprId() {
        return emprId;
    }

    public void setEmprId(Long emprId) {
        this.emprId = emprId;
    }
    
    
}

