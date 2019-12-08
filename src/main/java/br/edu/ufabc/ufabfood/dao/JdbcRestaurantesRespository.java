package br.edu.ufabc.ufabfood.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.edu.ufabc.ufabfood.models.Restaurante;

/**
 * JdbcRestaurantesRespository
 */
@Repository
public class JdbcRestaurantesRespository implements RestaurantesRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Restaurante getRestaurante(int id) {

        String sql;

        sql = "SELECT";
        sql += "    ID_USUARIO,";
        sql += "    CNPJ,";
        sql += "    RAZAO_SOCIAL,";
        sql += "    NOME_FANTASIA ";
        sql += "FROM";
        sql += "    RESTAURANTES ";
        sql += "WHERE";
        sql += "    ID_USUARIO = " + id;

        Restaurante restaurante = this.jdbcTemplate.queryForObject(
            sql, 
            (rs, rowNum) -> 
            new Restaurante(
                rs.getInt("id_usuario"),
                rs.getString("cnpj"),
                rs.getString("razao_social"),
                rs.getString("nome_fantasia")
            )
        );

        return restaurante;
    }

    @Override
    public List<Restaurante> getRestaurantesPorLocal(int idLocal) {
        
        String sql;

        sql = " SELECT";
        sql += "        T1.ID_USUARIO,";
        sql += "        T1.CNPJ,";
        sql += "        T1.RAZAO_SOCIAL,";
        sql += "        T1.NOME_FANTASIA";
        sql += "    FROM RESTAURANTES AS T1";
        sql += "    LEFT JOIN PRATOS AS T2";
        sql += "    ON T1.CNPJ = T2.CNPJ_RESTAURANTE";
        sql += "    LEFT JOIN PRATOS_DISPONIVEIS AS T3";
        sql += "    ON T2.ID = T3.ID_PRATO";
        sql += "    WHERE ID_PRATO IN (";
                
        sql += "        SELECT ";
        sql += "            T1.ID_PRATO";
        sql += "        FROM";
        sql += "            (";
        sql += "            SELECT * FROM";
        sql += "                PRATOS_DISPONIVEIS";
        sql += "            WHERE";
        sql += "                DT_DISPONIVEL = current_date";
        sql += "                AND";
        sql += "                HR_FIM > current_time";
        sql += "            ) AS T1";
        sql += "            LEFT JOIN";
        sql += "            (";
        sql += "            SELECT * FROM";
        sql += "                ITENS_PEDIDOS AS T1";
        sql += "                LEFT JOIN";
        sql += "                PEDIDOS AS T2";
        sql += "                ON T1.ID_PEDIDO = T2.ID";
        sql += "            WHERE";
        sql += "                DT_PEDIDO = current_date";
        sql += "            ) AS T2";
        sql += "            ON";
        sql += "            T1.ID_PRATO = T2.ID_PRATO";
        sql += "        GROUP BY";
        sql += "            T1.ID_PRATO, T1.ID_LOCAL";
        sql += "        HAVING";
        sql += "            T1.ID_LOCAL = " + idLocal;
        sql += "            AND (SUM(T1.QUANTIDADE) > SUM(T2.QUANTIDADE)";
        sql += "                 OR SUM(T2.QUANTIDADE) IS NULL)";
        sql += "    )";
        sql += "    GROUP BY T1.CNPJ";

        List<Map<String, Object>> rows = this.jdbcTemplate.queryForList(sql);

        List<Restaurante> restaurantes = new ArrayList<Restaurante>();
        for(Map<String,Object> row : rows ) {
            Restaurante restaurante = new Restaurante(
                (int)row.get("id_usuario"),
                (String)row.get("CNPJ"),
                (String)row.get("razao_social"),
                (String)row.get("nome_fantasia")
            );
            restaurantes.add(restaurante);
        }

        return restaurantes;
    }
    
}