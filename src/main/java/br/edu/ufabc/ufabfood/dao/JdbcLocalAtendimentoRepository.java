package br.edu.ufabc.ufabfood.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.edu.ufabc.ufabfood.models.LocalAtendimento;

/**
 * JdbcLocalAtendimentoRepository
 */
@Repository
public class JdbcLocalAtendimentoRepository implements LocalAtendimentoRepository {

    @Autowired
    JdbcTemplate JdbcTemplate;

    @Override
    public List<LocalAtendimento> getLocais() {
        
        String sql;

        sql = "SELECT ID, NOME ";
        sql += "FROM LOCAIS_ENTREGA ";
        sql += "ORDER BY  NOME;";

        List<Map<String, Object>> rows = this.JdbcTemplate.queryForList(sql);
        
        List<LocalAtendimento> locais = new ArrayList<LocalAtendimento>();
        for(Map<String, Object> row : rows) {
            LocalAtendimento local = new LocalAtendimento( (int)row.get("id"), (String)row.get("nome"));
            locais.add(local);
        }

        return locais;
    }
    
}