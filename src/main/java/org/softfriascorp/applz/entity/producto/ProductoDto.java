/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.entity.producto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.math.BigDecimal;
import org.softfriascorp.applz.entity.maestra.Maestra;

/**
 *
 * @author usuario
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductoDto {
    
   /*
{
    "id": 10,
    "codigoBarras": "770123456010",
    "nombre": "Cerveza Club Colombia Dorada 330ml",
    "descripcion": "Cerveza dorada botella 330ml",
    "precio": 4200.00,
    "descuento": 0,
    "precioFinal": 4200.00,
    "categoria": "CERVEZAS",
    "medida": "UND",
    "stock": "141"
}
*/
    private Long id;
    
    private String codigoBarras;
    
    private String nombre;
    
    private String descripcion;
    
    private BigDecimal precio;
    
    private BigDecimal descuento;
    
    private BigDecimal precioFinal;
    
    private String categoria;
    
    private String medida;
    
    private String stock;
    
    //private String descripcionPromo;
    
    //private Long sucursalId;

    public ProductoDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getMedida() {
        return medida;
    }

    public void setMedida(String medida) {
        this.medida = medida;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public BigDecimal getPrecioFinal() {
        return precioFinal;
    }

    public void setPrecioFinal(BigDecimal precioFinal) {
        this.precioFinal = precioFinal;
    }

    public BigDecimal getDescuento() {
        return descuento;
    }

    public void setDescuento(BigDecimal descuentoAplicado) {
        this.descuento = descuentoAplicado;
    }

    /*public String getDescripcionPromo() {
    return descripcionPromo;
    }
    
    public void setDescripcionPromo(String descripcionPromo) {
    this.descripcionPromo = descripcionPromo;
    }
    
    public Long getSucursalId() {
    return sucursalId;
    }
    
    public void setSucursalId(Long sucursalId) {
    this.sucursalId = sucursalId;
    }
    
    */
    
    
}
