/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.softfriascorp.applz.api.services;

import java.util.List;
import org.softfriascorp.applz.api.Response_dtos.VentaResponse;
import org.softfriascorp.applz.api.services.Producto_dto;
import org.softfriascorp.applz.service.venta.service.ServiceVenta;

/**
 *
 * @author usuario
 */
interface Interface_ServiceVentas {
 
   VentaResponse saveVenta(ServiceVenta cuenta);
}
