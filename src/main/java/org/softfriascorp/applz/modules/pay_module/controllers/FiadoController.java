/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.modules.pay_module.controllers;

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
import org.softfriascorp.applz.modules.cuenta_module.service.interfaces.CuentaService;
import org.softfriascorp.applz.entity.maestra.Maestra;
import org.softfriascorp.applz.entity.maestra.service.interfaces.MaestraService;
import org.softfriascorp.applz.entity.persona.Persona;
import org.softfriascorp.applz.entity.persona.interfaces.PersonaService;
import org.softfriascorp.applz.entity.venta.service.interfaces.VentaService;
import org.softfriascorp.applz.modules.login_module.entity.AuthResponsePerfil;
import org.softfriascorp.applz.modules.login_module.entity.UserPerfilRol;
import org.softfriascorp.applz.mainframework_module.views.MainFrameWork;
import org.softfriascorp.applz.modules.pay_module.views.PFiado;

/**
 *
 * @author usuario
 */
public class FiadoController implements ActionListener, KeyListener, FocusListener {

    private final String IDENTIFICACION = "IDENTIFICACION";
    private final String NOMBRES = "NOMBRES";
    private final String APELLIDOS = "APELLIDOS";
    private final String DIRECCION = "DIRECCION";

    private final PFiado fiado;
    private final MaestraService maestraService;
    private final MainFrameWork mainFrameWork;

    private final PersonaService personaService;
    private JDialog frameModal;
    private final CuentaService cuentaService;

    private Runnable onFiadoFinalizado
            ,onFiadoCancelado;
    
    Persona personaCliente = null;
    
    private final VentaService ventaService;
  
    @Inject
    public FiadoController(PFiado fiado, MaestraService maestraService, MainFrameWork mainFrameWork,
            PersonaService personaService,
            CuentaService cuentaService,
            VentaService ventaService) {

        this.fiado = fiado;
        this.maestraService = maestraService;
        this.mainFrameWork = mainFrameWork;
        this.personaService = personaService;

        this.cuentaService = cuentaService;
        this.ventaService = ventaService;
    }

    public void initConfig() {

        fiado.setSize(360, 550);

        fiado.btn_fiar.addKeyListener(this);
        fiado.btn_fiar.addActionListener(this);
        fiado.btn_fiar.setEnabled(false);
        
        fiado.txt_identificacion.addKeyListener(this);
        
        
        fiado.txt_identificacion.addFocusListener(this);
        fiado.txt_nombres.addFocusListener(this);
        fiado.txt_apellidos.addFocusListener(this);
        fiado.txt_direccion.addFocusListener(this);

        cacheService();

        // fiado.setUndecorated(true);
        fiado.btn_exit.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                // La acción se ejecuta al hacer clic con el ratón

                if(onFiadoCancelado != null){
                    onFiadoCancelado.run();
                }
                // Cierra la aplicación Java por completo.
                frameModal.dispose();
            }
        }
        );

        this.frameModal = setframeModal();

       
    }
    
    public void showFormFiado(){
        frameModal.setVisible(true);
    }

    private void cacheService() {

        List<Maestra> listTipoIdentificacion = maestraService.findByPadre("IDE");

        setItemsCmbx(fiado.cmbx_identificacion, listTipoIdentificacion,"TIPO DE IDENTIFICACION");

        List<Maestra> listSexo = maestraService.findByPadre("SEXO");

        setItemsCmbx(fiado.cmbx_sexo, listSexo,"TIPO DE SEXO");

    }

    private void selectItemCmbx(JComboBox cmbBox, Maestra m) {

        cmbBox.removeAllItems();
        cmbBox.addItem(m);
        cmbBox.setSelectedItem(m);

    }

    private void setItemsCmbx(JComboBox cmbBox, List<Maestra> lista, String tipo) {

        removeItemsCmbx(cmbBox,tipo);

        lista.forEach(lis -> {
            cmbBox.addItem(lis);
        });

    }

    private void removeItemsCmbx(JComboBox cmbBox, String tipo) {

        cmbBox.removeAllItems();
        cmbBox.addItem(tipo);

    }

    private JDialog setframeModal() {
        // Asumiendo que 'this.fiado' es la ventana (JFrame o JPanel) que quieres mostrar.

        // 1. Crear el JDialog, pasándole la ventana padre (mainFrameWork?)
        // Nota: Usar 'this.mainFrameWork' como padre si es el JFrame principal.
        JDialog subVentana = new JDialog(this.mainFrameWork, "Subventana Modal", true);
        subVentana.setUndecorated(true);
        // 2. Configuración de la Subventana

        // Si quieres que el modal tenga el mismo tamaño que el contenido de 'fiado':
        subVentana.setSize(this.fiado.getSize().width, this.fiado.getSize().height);

        // Centrar la subventana en relación a la ventana principal
        subVentana.setLocationRelativeTo(this.mainFrameWork);

        // 3. **PASO CLAVE: Agregar el contenido (fiado) al JDialog**
        // Si 'fiado' es un JPanel, simplemente lo añades:
        subVentana.setContentPane(this.fiado);

        return subVentana;
    }

    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == fiado.btn_fiar) {

           
            
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
              
              persona.setIdentificacion(fiado.txt_identificacion.getText().trim());
                
                usuario.setId(UserPerfilRol.getId());
                
                
                cuentaService.setEmpleado(usuario);
                cuentaService.setCliente(persona);
                
                Maestra estado = maestraService.findByTipo("FIDO");
                
                cuentaService.setEstado(estado);
                
               cuentaService.setMetodoPago(maestraService.findByTipo("EFE"));
              
              
            
            try{
                
            ventaService.saveVenta(cuentaService);

            //personaService.save(personaRequest);
            }catch(RuntimeException ex){
                
                
                System.out.println(ex.getMessage());
                return ;
            }
            
            habilitarCampos(true);
            clearCampos();
            
            
            if(onFiadoFinalizado != null){
                onFiadoFinalizado.run();
            }
            
            
            frameModal.dispose();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {

            if (e.getSource() == fiado.txt_identificacion) {
                
                try {
                    personaCliente = personaService.searchPersonaByIdentificacion(fiado.txt_identificacion.getText().trim());

                    llenarCampos(personaCliente);

                    habilitarCampos(false);
                    
                    fiado.btn_fiar.setEnabled(true);

                } catch (RuntimeException ex) {

                    fiado.btn_fiar.setEnabled(false);
                    System.out.println(ex.getMessage());
                }

            }
        }
    }

    public void llenarCampos(Persona p) {

       selectItemCmbx( fiado.cmbx_identificacion,p.getTipoIdentificacion());
        selectItemCmbx(fiado.cmbx_sexo,p.getSexo());
        
        fiado.txt_identificacion.setText(p.getIdentificacion());
        fiado.txt_nombres.setText(p.getNombre());
        fiado.txt_apellidos.setText(p.getApellido());
        fiado.txt_direccion.setText(p.getDireccion());

    }
    public void clearCampos() {

        removeItemsCmbx(fiado.cmbx_identificacion,"TIPO DE IDENTIFICASION");
        removeItemsCmbx(fiado.cmbx_sexo,"TIPO DE SEXO");
        
        fiado.txt_identificacion.setText("");
        fiado.txt_nombres.setText("");
        fiado.txt_apellidos.setText("");
        fiado.txt_direccion.setText("");
        
        

    }

    public void habilitarCampos(Boolean act) {
        
        fiado.cmbx_identificacion.setEnabled(act);
        fiado.cmbx_sexo.setEnabled(act);
        fiado.txt_identificacion.setEnabled(act);
        fiado.txt_nombres.setEnabled(act);
        fiado.txt_apellidos.setEnabled(act);
        fiado.txt_direccion.setEnabled(act);
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void focusGained(FocusEvent e) {
        JComponent comp = (JComponent) e.getSource();

        if (comp == fiado.txt_identificacion) {
            if (fiado.txt_identificacion.getText().equals(IDENTIFICACION)) {
                fiado.txt_identificacion.setText("");
                fiado.txt_identificacion.setForeground(Color.BLACK); // Vuelve a color normal
            }

        }
        if (comp == fiado.txt_nombres) {
            if (fiado.txt_nombres.getText().equals(NOMBRES)) {
                fiado.txt_nombres.setText("");
                fiado.txt_nombres.setForeground(Color.BLACK); // Vuelve a color normal
            }
        }

        if (comp == fiado.txt_apellidos) {
            if (fiado.txt_apellidos.getText().equals(APELLIDOS)) {
                fiado.txt_apellidos.setText("");
                fiado.txt_apellidos.setForeground(Color.BLACK); // Vuelve a color normal
            }
        }
        if (comp == fiado.txt_direccion) {
            if (fiado.txt_direccion.getText().equals(DIRECCION)) {
                fiado.txt_direccion.setText("");
                fiado.txt_direccion.setForeground(Color.BLACK); // Vuelve a color normal
            }
        }

    }

    @Override
    public void focusLost(FocusEvent e) {
        JComponent comp = (JComponent) e.getSource();

        if (comp == fiado.txt_identificacion) {
            if (fiado.txt_identificacion.getText().isEmpty()) {
                fiado.txt_identificacion.setText(IDENTIFICACION);
                fiado.txt_identificacion.setForeground(Color.GRAY); // Color placeholder
            }

        }

        if (comp == fiado.txt_nombres) {
            if (fiado.txt_nombres.getText().isEmpty()) {
                fiado.txt_nombres.setText(NOMBRES);
                fiado.txt_nombres.setForeground(Color.GRAY); // Color placeholder
            }
        }
        if (comp == fiado.txt_apellidos) {
            if (fiado.txt_apellidos.getText().isEmpty()) {
                fiado.txt_apellidos.setText(APELLIDOS);
                fiado.txt_apellidos.setForeground(Color.GRAY); // Color placeholder
            }
        }
        if (comp == fiado.txt_direccion) {
            if (fiado.txt_direccion.getText().isEmpty()) {
                fiado.txt_direccion.setText(DIRECCION);
                fiado.txt_direccion.setForeground(Color.GRAY); // Color placeholder
            }
        }

    }
    
    
    public void setOnFiadoFinalizado(Runnable onFiadoFinalizado){
        
        this.onFiadoFinalizado = onFiadoFinalizado;
    }
    public void setOnFiadoCancelado(Runnable onFiadoCancelado){
        
        this.onFiadoCancelado = onFiadoCancelado;
    }
}
