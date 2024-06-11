package com.zona_fit.gui;

import com.zona_fit.modelo.Cliente;
import com.zona_fit.servicio.IClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

//Nos permitirá recuperar las demás instancias al crear objetos en Spring
@Component
public class ZonaFitForma extends JFrame{
    private JPanel panelPrincipal;
    private JTable clientesTabla;
    private JTextField nombreTexto;
    private JTextField apellidoTexto;
    private JTextField menbresiaTexto;
    private JPanel panelFormulario;
    private JPanel panelTabla;
    private JButton guardarButton;
    private JButton eliminarButton;
    private JButton limpiarButton;
    private JPanel panelBotones;
    IClienteServicio clienteServicio;
    private DefaultTableModel tablaModeloClientes;
    private Integer idCliente;

    @Autowired
    public ZonaFitForma(IClienteServicio clienteServicio){
        this.clienteServicio = clienteServicio;
        iniciarForma();
        guardarButton.addActionListener(e -> guardarCliente());
        clientesTabla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                //recuperar información de table
                cargarClienteSeleccionado();
            }
        });
        eliminarButton.addActionListener(e -> eliminarCliente());
        limpiarButton.addActionListener(e -> limpiarFormulario());
    }

    private void iniciarForma(){
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700,400);
        setLocationRelativeTo(null);
    }

    //Ejecutará antes que constructo
    private void createUIComponents() {
        // TODO: place custom component creation code here
        //this.tablaModeloClientes = new DefaultTableModel(0,4); //Asignamos la tabla vacía e indicamos 4 cabeceras
        //Crear tabla peo bloqueando la edición
        this.tablaModeloClientes = new DefaultTableModel(0,4){
            @Override
            public boolean isCellEditable(int row, int colum){
                return false;
            }
        };

        String[] cabeceros = {"ID", "NOMBRE","APELLIDO","MEMBRESÍA"}; //Creamos los indicadores de la cabecera
        this.tablaModeloClientes.setColumnIdentifiers(cabeceros); //Pasamos el arreglo con nuestros cabeceros de tabla
        this.clientesTabla = new JTable(tablaModeloClientes); //creamos el objeto de la tabla manualmente
        //Restringimos la selección de la tabla a un solo registro
        this.clientesTabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        //CargarListado de clientes
        listarClientes();
    }

    private void listarClientes(){
        this.tablaModeloClientes.setRowCount(0);
        var clientes = this.clienteServicio.listarClientes();

        clientes.forEach(cliente -> {
            Object[] renglonCliente = {
                    cliente.getId(),
                    cliente.getNombre(),
                    cliente.getApellido(),
                    cliente.getMembresia()
            };

            this.tablaModeloClientes.addRow(renglonCliente);
        });
    }

    private void guardarCliente(){
        if(nombreTexto.getText().equals("")){
            mostrarMensaje("Proporciona un Nombre");
            nombreTexto.requestFocusInWindow();
            return;
        }
        if(apellidoTexto.getText().equals("")){
            mostrarMensaje("Proporciona un Apellido");
            menbresiaTexto.requestFocusInWindow();
            return;
        }
        if(menbresiaTexto.getText().equals("")){
            mostrarMensaje("Proporciona una Membresía");
            menbresiaTexto.requestFocusInWindow();
            return;
        }

        // Recuperar la información del formulario
        var nombre = nombreTexto.getText();
        var apellido = apellidoTexto.getText();
        var membresia = Integer.parseInt(menbresiaTexto.getText());

        var cliente = new Cliente(this.idCliente,nombre,apellido,membresia);
        this.clienteServicio.guardarCliente(cliente); // se guardo datos modificar

        //Mensaje para saber que se realizó
        if(this.idCliente == null){
            mostrarMensaje("Se agrego nuevo cliente a la base de datos");
        }else{
            mostrarMensaje("Se actualizo los datos del cliente");
        }

        //limpiamos el formulario
        limpiarFormulario();

        //lista nuevamente la tabla con el nuevo dato
        listarClientes();
    }

    private void cargarClienteSeleccionado(){
        //Recuperamos la información de in reglón de tabla
        var reglon = clientesTabla.getSelectedRow();

        //Verificar si no se selecciona ni un reglón
        if(reglon != -1){
            var id = clientesTabla.getModel().getValueAt(reglon,0).toString();
            this.idCliente = Integer.parseInt(id);
            var nombre = clientesTabla.getModel().getValueAt(reglon,1).toString();
            this.nombreTexto.setText(nombre);
            var apellido = clientesTabla.getModel().getValueAt(reglon,2).toString();
            this.apellidoTexto.setText(apellido);
            var menbresia = clientesTabla.getModel().getValueAt(reglon,3).toString();
            this.menbresiaTexto.setText(menbresia);
        }

    }

    private void eliminarCliente(){
        //Recuperamos la información de in reglón de tabla
        var reglon = clientesTabla.getSelectedRow();
        //Verificar si no se selecciona ni un reglón
        if(reglon != -1){
            var idCliente = clientesTabla.getModel().getValueAt(reglon,0).toString();
            this.idCliente = Integer.parseInt(idCliente);
            var cliente = new Cliente();
            cliente.setId(this.idCliente);
            clienteServicio.eliminarCliente(cliente);
            mostrarMensaje("Cliente con ID: ["+this.idCliente+"] eliminado");
            limpiarFormulario();
            listarClientes();
        }else{
            mostrarMensaje("Debe seleccionar un Cliente de la Tabla eliminar");
        }
    }

    private void mostrarMensaje(String mensaje){
        JOptionPane.showMessageDialog(this,mensaje);
    }

    private void limpiarFormulario(){
        nombreTexto.setText("");
        apellidoTexto.setText("");
        menbresiaTexto.setText("");

        // Limpiar id del cliente seleccionado
        this.idCliente = null;

        // Deseleccionamos el reglón de la tabla.
        this.clientesTabla.getSelectionModel().clearSelection();
    }
}
