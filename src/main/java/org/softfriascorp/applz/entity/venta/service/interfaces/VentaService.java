/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.entity.venta.service.interfaces;

import org.softfriascorp.applz.cuenta_module.submodules.cuenta_module.service.interfaces.CuentaService;
import org.softfriascorp.applz.entity.venta.Venta;

/**
 *
 * @author usuario
 */
public interface VentaService {
    
    Venta getVenta(String codigoVenta);
    Venta saveVenta(CuentaService cuentaService);
    
    
    
}
