package br.edu.ufabc.ufabfood.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.edu.ufabc.ufabfood.models.Categoria;
import br.edu.ufabc.ufabfood.models.ItemCardapio;
import br.edu.ufabc.ufabfood.models.Prato;

/**
 * JdbcCardapioRepository
 */
@Repository
public class JdbcCardapioRepository implements CardapioRepository{

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<ItemCardapio> getCardapio(int idRestaurante, int idLocalAtendimento) {
        String sql;

        sql = "SELECT ";
        sql += "    T1.ID_PRATO,";
        sql += "    T1.NOME AS NOME_PRATO,";
        sql += "    T1.DESCRICAO,";
        sql += "    T1.VALOR,";
        sql += "    T1.IMAGEM,";
        sql += "    T1.TEMPO_PREPARO,";
        sql += "    T1.QUANTIDADE,";
        sql += "    T3.ID AS ID_CATEGORIA,";
        sql += "    T3.NOME AS NOME_CATEGORIA";
        sql += " FROM";
        sql += "    (";
        sql += "    SELECT * FROM";
        sql += "        PRATOS_DISPONIVEIS AS T1";
        sql += "        LEFT JOIN PRATOS AS T2";
        sql += "        ON T1.ID_PRATO = T2.ID";
        sql += "        LEFT JOIN RESTAURANTES AS T3";
        sql += "        ON T2.CNPJ_RESTAURANTE = T3.CNPJ";
        sql += "    WHERE";
        sql += "        DT_DISPONIVEL = current_date";
        sql += "        AND";
        sql += "        HR_FIM > current_time";
        sql += "    ) AS T1";
        sql += " LEFT JOIN";
        sql += "    (";
        sql += "    SELECT * FROM";
        sql += "        ITENS_PEDIDOS AS T1";
        sql += "        LEFT JOIN";
        sql += "        PEDIDOS AS T2";
        sql += "        ON T1.ID_PEDIDO = T2.ID";
        sql += "    WHERE";
        sql += "        DT_PEDIDO = current_date";
        sql += "    ) AS T2";
        sql += " ON";
        sql += "    T1.ID_PRATO = T2.ID_PRATO";
        sql += " LEFT JOIN CATEGORIAS AS T3";
        sql += " ON T1.ID_CATEGORIA = T3.ID";
        sql += " GROUP BY";
        sql += "    T1.ID_PRATO, ";
        sql += "    T1.ID_LOCAL, ";
        sql += "    T1.ID_USUARIO,";
        sql += "    T1.NOME,";
        sql += "    T1.DESCRICAO,";
        sql += "    T1.VALOR,";
        sql += "    T1.IMAGEM,";
        sql += "    T1.TEMPO_PREPARO,";
        sql += "    T1.QUANTIDADE,";
        sql += "    T3.ID,";
        sql += "    T3.NOME";
        sql += " HAVING";
        sql += "    T1.ID_LOCAL = " + idLocalAtendimento;
        sql += "    AND T1.ID_USUARIO = " + idRestaurante;
        sql += "    AND (SUM(T1.QUANTIDADE) > SUM(T2.QUANTIDADE)";
        sql += "         OR SUM(T2.QUANTIDADE) IS NULL)";

        List<Map<String, Object>> rows = this.jdbcTemplate.queryForList(sql);

        List<ItemCardapio> pratos = new ArrayList<ItemCardapio>();
        for(Map<String, Object> row : rows) {
            Categoria categoria = new Categoria(
                (int)row.get("id_categoria"), 
                (String)row.get("nome_categoria"));
            Prato prato = new Prato(
                (int)row.get("id_prato"), 
                (String)row.get("nome_prato"), 
                (String)row.get("descricao"), 
                (double)row.get("valor"), 
                (String)row.get("imagem"), 
                (int)row.get("tempo_preparo"), 
                categoria);
            ItemCardapio itemCardapio = new ItemCardapio(
                prato, 
                (int)row.get("quantidade"));
            pratos.add(itemCardapio);
        }

        return pratos;
    }
    
}