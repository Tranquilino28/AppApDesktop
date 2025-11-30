/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.modules.pay.controllers;

import com.google.inject.Inject;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Optional;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import org.softfriascorp.applz.Utils.UtilManagerCombobox;
import org.softfriascorp.applz.modules.cuenta.service.interfaces.CuentaService;
import org.softfriascorp.applz.entity.maestra.Maestra;
import org.softfriascorp.applz.entity.maestra.service.interfaces.MaestraService;
import org.softfriascorp.applz.entity.persona.Persona;
import org.softfriascorp.applz.entity.persona.interfaces.PersonaService;
import org.softfriascorp.applz.entity.venta.service.interfaces.VentaService;
import org.softfriascorp.applz.modules.login.entity.AuthResponsePerfil;
import org.softfriascorp.applz.modules.login.entity.UserPerfilRol;
import org.softfriascorp.applz.frameview_manager.views.MainFrameWork;
import org.softfriascorp.applz.modules.pay.views.ClienteValidator;

/**
 *
 * @author usuario
 */
public class ClienteValidatorClontroller implements ActionListener, KeyListener, FocusListener {

    private final String IDENTIFICACION = "IDENTIFICACION";
    private final String NOMBRES = "NOMBRES";
    private final String APELLIDOS = "APELLIDOS";
    private final String DIRECCION = "DIRECCION";

    private final String TX_BTN_FIAR = "FIAR";
    private final String TX_BTN_ACEPTAR = "ACEPTAR";

    private String Btn_select;

    private final ClienteValidator clienteValidator;
    private final MaestraService maestraService;
    private final MainFrameWork mainFrameWork;

    private final PersonaService personaService;
    private JDialog frameModal;
    private final CuentaService cuentaService;

    private Runnable onFiadoFinalizado, onFiadoCancelado;

    private Runnable onEfectivoFinalizado, onEfectivoCancelado;

    Persona personaCliente = null;

    private final VentaService ventaService;

    @Inject
    public ClienteValidatorClontroller(ClienteValidator clienteValidator, MaestraService maestraService, MainFrameWork mainFrameWork,
            PersonaService personaService,
            CuentaService cuentaService,
            VentaService ventaService) {

        this.clienteValidator = clienteValidator;
        this.maestraService = maestraService;
        this.mainFrameWork = mainFrameWork;
        this.personaService = personaService;

        this.cuentaService = cuentaService;
        this.ventaService = ventaService;
    }

    public void initConfig() {

        clienteValidator.setSize(360, 550);

        clienteValidator.btn_fiar.addKeyListener(this);
        clienteValidator.btn_fiar.addActionListener(this);
        clienteValidator.btn_fiar.setEnabled(false);

        clienteValidator.txt_identificacion.addKeyListener(this);

        clienteValidator.txt_identificacion.addFocusListener(this);
        clienteValidator.txt_nombres.addFocusListener(this);
        clienteValidator.txt_apellidos.addFocusListener(this);
        clienteValidator.txt_direccion.addFocusListener(this);

        cacheService();

        // fiado.setUndecorated(true);
        clienteValidator.btn_exit.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                // La acción se ejecuta al hacer clic con el ratón
                if (Btn_select.equals("FIADO")) {

                    if (onFiadoCancelado != null) {
                        onFiadoCancelado.run();
                    }

                }
                if (Btn_select.equals("EFECTIVO")) {

                    if (onEfectivoFinalizado != null) {
                        onEfectivoFinalizado.run();
                    }

                }

                // Cierra la aplicación Java por completo.
                frameModal.dispose();
            }
        }
        );

        this.frameModal = setframeModal();

    }

    public void showFormFiado(String btn_select) {

        validBtnSelect(btn_select);

        frameModal.setVisible(true);
    }

    private void cacheService() {

        List<Maestra> listTipoIdentificacion = maestraService.findByPadre("IDE");

        UtilManagerCombobox.setItemsCmbx(clienteValidator.cmbx_identificacion, listTipoIdentificacion, "TIPO DE IDENTIFICACION");

        List<Maestra> listSexo = maestraService.findByPadre("SEXO");

        UtilManagerCombobox.setItemsCmbx(clienteValidator.cmbx_sexo, listSexo, "TIPO DE SEXO");

    }

    private JDialog setframeModal() {
        // Asumiendo que 'this.fiado' es la ventana (JFrame o JPanel) que quieres mostrar.

        // 1. Crear el JDialog, pasándole la ventana padre (mainFrameWork?)
        // Nota: Usar 'this.mainFrameWork' como padre si es el JFrame principal.
        JDialog subVentana = new JDialog(this.mainFrameWork, "Subventana Modal", true);
        subVentana.setUndecorated(true);
        // 2. Configuración de la Subventana

        // Si quieres que el modal tenga el mismo tamaño que el contenido de 'fiado':
        subVentana.setSize(this.clienteValidator.getSize().width, this.clienteValidator.getSize().height);

        // Centrar la subventana en relación a la ventana principal
        subVentana.setLocationRelativeTo(this.mainFrameWork);

        // 3. **PASO CLAVE: Agregar el contenido (fiado) al JDialog**
        // Si 'fiado' es un JPanel, simplemente lo añades:
        subVentana.setContentPane(this.clienteValidator);

        return subVentana;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == clienteValidator.btn_fiar) {

            

                /*Maestra tipoIdentificasion = (Maestra) fiado.cmbx_identificacion.getSelectedItem();
                
            Maestra sexo = (Maestra) fiado.cmbx_sexo.getSelectedItem();

            
            Persona personaRequest = new Persona();

            personaRequest.setTipoIdentificacion(tipoIdentificasion);
            personaRequest.setIdentificacion(fiado.txt_identificacion.getText().trim());

            personaRequest.setNombre(fiado.txt_nombres.getText().trim());
            personaRequest.setApellido(fiado.txt_apellidos.getText().trim());
            personaRequest.setDireccion(fiado.txt_direccion.getText().trim());
            personaRequest.setSexo(sexo);
            
                 */
                AuthResponsePerfil usuario = new AuthResponsePerfil();

                Persona persona = new Persona();

                persona.setIdentificacion(clienteValidator.txt_identificacion.getText().trim());

                usuario.setId(UserPerfilRol.getId());

                cuentaService.setEmpleado(usuario);
                
                cuentaService.setCliente(persona);

                Maestra estado;
                
                cuentaService.setMetodoPago(maestraService.findByTipo("EFE"));
                
            if ("FIAR".equals(Btn_select)) {
                
                 estado = maestraService.findByTipo("FIDO");
                 
                 cuentaService.setEstado(estado);
                 
                 
                try {

                    ventaService.saveVenta(cuentaService);

                    //personaService.save(personaRequest);
                } catch (RuntimeException ex) {

                    System.out.println(ex.getMessage());
                    return;
                }
                
                
                if (onFiadoFinalizado != null) {
                    
                    onFiadoFinalizado.run();
                }
            }

            if ("EFECTIVO".equals(Btn_select)) {
                
                if (onEfectivoFinalizado != null) {
                    
                    onEfectivoFinalizado.run();
                }
            }
              habilitarCampos(true);
                  
                clearCampos();
            frameModal.dispose();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {

            if (e.getSource() == clienteValidator.txt_identificacion) {

                try {
                    personaCliente = personaService.searchPersonaByIdentificacion(clienteValidator.txt_identificacion.getText().trim());

                    llenarCampos(personaCliente);

                    habilitarCampos(false);

                    clienteValidator.btn_fiar.setEnabled(true);

                } catch (RuntimeException ex) {

                    clienteValidator.btn_fiar.setEnabled(false);
                    System.out.println(ex.getMessage());
                }

            }
        }
    }

    public void llenarCampos(Persona p) {

        UtilManagerCombobox.selectItemCmbx(clienteValidator.cmbx_identificacion, p.getTipoIdentificacion());
        UtilManagerCombobox.selectItemCmbx(clienteValidator.cmbx_sexo, p.getSexo());

        clienteValidator.txt_identificacion.setText(p.getIdentificacion());
        clienteValidator.txt_nombres.setText(p.getNombre());
        clienteValidator.txt_apellidos.setText(p.getApellido());
        clienteValidator.txt_direccion.setText(p.getDireccion());

    }

    public void clearCampos() {

        UtilManagerCombobox.removeItemsCmbx(clienteValidator.cmbx_identificacion, "TIPO DE IDENTIFICASION");
        UtilManagerCombobox.removeItemsCmbx(clienteValidator.cmbx_sexo, "TIPO DE SEXO");

        clienteValidator.txt_identificacion.setText("");
        clienteValidator.txt_nombres.setText("");
        clienteValidator.txt_apellidos.setText("");
        clienteValidator.txt_direccion.setText("");

    }

    public void habilitarCampos(Boolean act) {

        clienteValidator.cmbx_identificacion.setEnabled(act);
        clienteValidator.cmbx_sexo.setEnabled(act);
        clienteValidator.txt_identificacion.setEnabled(act);
        clienteValidator.txt_nombres.setEnabled(act);
        clienteValidator.txt_apellidos.setEnabled(act);
        clienteValidator.txt_direccion.setEnabled(act);
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void focusGained(FocusEvent e) {
        JComponent comp = (JComponent) e.getSource();

        if (comp == clienteValidator.txt_identificacion) {
            if (clienteValidator.txt_identificacion.getText().equals(IDENTIFICACION)) {
                clienteValidator.txt_identificacion.setText("");
                clienteValidator.txt_identificacion.setForeground(Color.BLACK); // Vuelve a color normal
            }

        }
        if (comp == clienteValidator.txt_nombres) {
            if (clienteValidator.txt_nombres.getText().equals(NOMBRES)) {
                clienteValidator.txt_nombres.setText("");
                clienteValidator.txt_nombres.setForeground(Color.BLACK); // Vuelve a color normal
            }
        }

        if (comp == clienteValidator.txt_apellidos) {
            if (clienteValidator.txt_apellidos.getText().equals(APELLIDOS)) {
                clienteValidator.txt_apellidos.setText("");
                clienteValidator.txt_apellidos.setForeground(Color.BLACK); // Vuelve a color normal
            }
        }
        if (comp == clienteValidator.txt_direccion) {
            if (clienteValidator.txt_direccion.getText().equals(DIRECCION)) {
                clienteValidator.txt_direccion.setText("");
                clienteValidator.txt_direccion.setForeground(Color.BLACK); // Vuelve a color normal
            }
        }

    }

    @Override
    public void focusLost(FocusEvent e) {
        JComponent comp = (JComponent) e.getSource();

        if (comp == clienteValidator.txt_identificacion) {
            if (clienteValidator.txt_identificacion.getText().isEmpty()) {
                clienteValidator.txt_identificacion.setText(IDENTIFICACION);
                clienteValidator.txt_identificacion.setForeground(Color.GRAY); // Color placeholder
            }

        }

        if (comp == clienteValidator.txt_nombres) {
            if (clienteValidator.txt_nombres.getText().isEmpty()) {
                clienteValidator.txt_nombres.setText(NOMBRES);
                clienteValidator.txt_nombres.setForeground(Color.GRAY); // Color placeholder
            }
        }
        if (comp == clienteValidator.txt_apellidos) {
            if (clienteValidator.txt_apellidos.getText().isEmpty()) {
                clienteValidator.txt_apellidos.setText(APELLIDOS);
                clienteValidator.txt_apellidos.setForeground(Color.GRAY); // Color placeholder
            }
        }
        if (comp == clienteValidator.txt_direccion) {
            if (clienteValidator.txt_direccion.getText().isEmpty()) {
                clienteValidator.txt_direccion.setText(DIRECCION);
                clienteValidator.txt_direccion.setForeground(Color.GRAY); // Color placeholder
            }
        }

    }

    public void setOnFiadoFinalizado(Runnable onFiadoFinalizado) {

        this.onFiadoFinalizado = onFiadoFinalizado;
    }

    public void setOnFiadoCancelado(Runnable onFiadoCancelado) {

        this.onFiadoCancelado = onFiadoCancelado;
    }

    public void setOnEfectivoIniciado(Runnable onEfectivoFinalizado) {

        this.onEfectivoFinalizado = onEfectivoFinalizado;
    }

    public void setOnEfectivoCancelado(Runnable onEfectivoCancelado) {

        this.onEfectivoCancelado = onEfectivoCancelado;
    }

    private void validBtnSelect(String valid) {

        switch (valid.toUpperCase()) {
            case "FIADO" -> {
                this.clienteValidator.btn_fiar.setText(TX_BTN_FIAR);
                this.Btn_select = valid;
            }
            case "EFECTIVO" -> {
                this.clienteValidator.btn_fiar.setText(TX_BTN_ACEPTAR);
                this.Btn_select = valid;
            }

            default -> {
                throw new RuntimeException("no se puede hacer esta accion");
            }
        }
    }
}
