/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.entity.venta.service.interfaces;

import org.softfriascorp.applz.modules.cuenta.service.interfaces.CuentaService;
import org.softfriascorp.applz.entity.maestra.Maestra;
import org.softfriascorp.applz.entity.venta.Venta;
import org.softfriascorp.applz.entity.venta.VentaRequest;
import org.softfriascorp.applz.modules.login.entity.UserPerfilRol;

/**
 *
 * @author usuario
 */
public interface VentaService {
    
    Venta getVenta(String codigoVenta);
    
    Venta saveVenta(CuentaService cuentaService);
    
    
}
