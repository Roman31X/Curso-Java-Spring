package com.zona_fit.servicio;

import com.zona_fit.modelo.Cliente;
import java.util.List;

public interface IClienteServicio {

    public List<Cliente> listarClientes();

    public Cliente buscarClientePorId(Integer idCliente);

    public void guardarCliente(Cliente cliente);

    public void eliminarCliente(Cliente cliente);

}
