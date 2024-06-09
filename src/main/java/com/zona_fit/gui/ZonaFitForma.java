package com.zona_fit.gui;

import com.zona_fit.servicio.IClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

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
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JPanel panelBotones;
    IClienteServicio clienteServicio;
    private DefaultTableModel tablaModeloClientes;

    @Autowired
    public ZonaFitForma(IClienteServicio clienteServicio){
        this.clienteServicio = clienteServicio;
        iniciarForma();
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
        this.tablaModeloClientes = new DefaultTableModel(0,4); //Asignamos la tabla vacía e indicamos 4 cabeceras
        String[] cabeceros = {"ID", "NOMBRE","APELLIDO","MEMBRESÍA"}; //Creamos los indicadores de la cabecera
        this.tablaModeloClientes.setColumnIdentifiers(cabeceros); //Pasamos el arreglo con nuestros cabeceros de tabla
        this.clientesTabla = new JTable(tablaModeloClientes); //creamos el objeto de la tabla manualmente

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
}
