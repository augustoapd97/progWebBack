package br.edu.ufabc.ufabfood.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import br.edu.ufabc.ufabfood.models.Pedido;

/**
 * PedidoRepository
 */
public interface PedidoRepository {

    public boolean postPedido(Pedido pedido) throws SQLException;
    public ArrayList<Pedido> getPedidosAndamento(int idUsuario);
    
}