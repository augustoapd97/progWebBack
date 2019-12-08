package br.edu.ufabc.ufabfood.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.edu.ufabc.ufabfood.models.ItemCardapio;
import br.edu.ufabc.ufabfood.models.Pedido;
import br.edu.ufabc.ufabfood.models.Pessoa;
import br.edu.ufabc.ufabfood.models.Prato;

/**
 * JdbcPedidoRepository
 */
@Repository
public class JdbcPedidoRepository implements PedidoRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public boolean postPedido(Pedido pedido) throws SQLException {
        
        String sqlPedido;

        sqlPedido = "INSERT INTO ";
        sqlPedido += " PEDIDOS ";
        sqlPedido += "( dt_pedido, ";
        sqlPedido += " hr_inicio, ";
        if (pedido.getTroco() > 0) {
            sqlPedido += " pag_troco, ";
        }
        if (pedido.getIdCartao() > 0) {
            sqlPedido += " pag_id_cartao, ";
        }
        sqlPedido += " cpf_cliente ";
        sqlPedido += " ) ";
        sqlPedido += "VALUES ";
        sqlPedido += "( current_date, ";
        sqlPedido += " current_time, ";
        if (pedido.getTroco() > 0) {
            sqlPedido += " " + pedido.getTroco() + ", ";
        }
        if (pedido.getIdCartao() > 0) {
            sqlPedido += " " + pedido.getIdCartao() + ", ";
        }
        sqlPedido += " '" + ((Pessoa)pedido.getCliente()).getCPF() + "') ";
        sqlPedido += " RETURNING id";

        PreparedStatement statement = jdbcTemplate.getDataSource().getConnection().prepareStatement(sqlPedido);
        ResultSet rs = statement.executeQuery();
        rs.next();
        int idPedido = rs.getInt("id");

        String sqlItem;

        sqlItem = "INSERT INTO";
        sqlItem += " ITENS_PEDIDOS ";
        sqlItem += "( id_pedido, ";
        sqlItem += " id_prato, ";
        sqlItem += " quantidade, ";
        sqlItem += " valor_unitario ) ";
        sqlItem += "VALUES ";

        for(ItemCardapio item : pedido.getPratos()) {
            sqlItem += " ( " + idPedido + ", ";
            sqlItem += " " + item.getPrato().getID() +", ";
            sqlItem += " " + item.getQuantidade() + ", ";
            sqlItem += " " + item.getPrato().getValor() + " ),";
        }

        sqlItem = sqlItem.substring(0, sqlItem.length() - 1 );

        int rows = this.jdbcTemplate.update(sqlItem);

        if (rows != 0) {
            return true;
        } else {
            return false;
        }
        
    }

    @Override
    public ArrayList<Pedido> getPedidosAndamento(int idUsuario) {

        String sql;

        sql = "SELECT";
        sql += " T3.id AS id_pedido,";
        sql += " T3.cpf_cliente,";
        sql += " T3.cpf_entregador,";
        sql += " T3.pag_id_cartao,";
        sql += " T3.pag_troco,";
        sql += " T5.id AS id_prato,";
        sql += " T4.quantidade,";
        sql += " T4.valor_unitario, ";
        sql += " T5.nome AS nome_prato,";
        sql += " T5.descricao AS descricao_prato,";
        sql += " T5.tempo_preparo";
        sql += "FROM";
        sql += " usuarios AS T1 ";
        sql += " LEFT JOIN pessoas AS T2 ";
        sql += " ON T1.id = T2.id_usuario ";
        sql += " LEFT JOIN pedidos AS T3 ";
        sql += " ON T2.cpf = T3.cpf_cliente ";
        sql += " LEFT JOIN itens_pedidos AS T4 ";
        sql += " ON T3.id = T4.id_pedido ";
        sql += " LEFT JOIN pratos AS T5 ";
        sql += " ON T4.id_prato = T5.id ";
        sql += "WHERE";
        sql += " T1.id = " + idUsuario;
        sql += " AND hr_fim ISNULL;";
        
        List<Map<String, Object>> rows = this.jdbcTemplate.queryForList(sql);

        List<Pedido> pedidos = new ArrayList<Pedido>();
        Pedido pedido = new Pedido();
        for(Map<String, Object> row : rows) {
            
            if (pedido.getID() != (long)row.get("id_pedido")) {

            }
            
            Prato prato = new Prato(
                (int)row.get("id_prato"), 
                (String)row.get("nome_prato"), 
                (double)row.get("valor_unitario"), 
                (String)row.get("descricao_prato"), 
                (int)row.get("tempo_preparo")
            );
            ItemCardapio item = new ItemCardapio(
                prato, 
                (int)row.get("quantidade")
                );

            
            
        }

        return null;
    }
    
}