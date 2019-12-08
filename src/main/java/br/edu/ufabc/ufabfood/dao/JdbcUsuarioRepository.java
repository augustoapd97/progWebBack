package br.edu.ufabc.ufabfood.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.edu.ufabc.ufabfood.models.Cartao;
import br.edu.ufabc.ufabfood.models.Usuario;
import br.edu.ufabc.ufabfood.models.UsuarioMapper;

/**
 * JdbcUsuarioRepository
 */
@Repository
public class JdbcUsuarioRepository implements UsuarioRepository {

    
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Usuario getUsuario(String usuario, String senha) {

        String sql;

        sql = "SELECT T1.ID, ";
        sql += "T1.EMAIL, ";
        sql += "T1.ATIVO, ";
        sql += "T2.CPF, ";
        sql += "T2.NOME, ";
        sql += "T3.CNPJ, ";
        sql += "T3.RAZAO_SOCIAL, ";
        sql += "T3.NOME_FANTASIA ";
        sql += "FROM ";
        sql += "USUARIOS AS T1 ";
        sql += "LEFT JOIN ";
        sql += "PESSOAS AS T2 ";
        sql += "ON ";
        sql += "T1.ID = T2.ID_USUARIO ";
        sql += "LEFT JOIN ";
        sql += "RESTAURANTES AS T3 ";
        sql += "ON ";
        sql += "T1.ID = T3.ID_USUARIO ";
        sql += "WHERE ";
        sql += "EMAIL='" + usuario + "' ";
        sql += "AND SENHA='" + senha + "';";

        Usuario user = this.jdbcTemplate.queryForObject(sql, new UsuarioMapper());

        return user;
    
    }

    @Override
    public Usuario getUsuario(int id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Usuario getUsuario(String email) {
        String sql;

        sql = "SELECT T1.ID, ";
        sql += "T1.EMAIL, ";
        sql += "T1.ATIVO, ";
        sql += "T2.CPF, ";
        sql += "T2.NOME, ";
        sql += "T3.CNPJ, ";
        sql += "T3.RAZAO_SOCIAL, ";
        sql += "T3.NOME_FANTASIA ";
        sql += "FROM ";
        sql += "USUARIOS AS T1 ";
        sql += "LEFT JOIN ";
        sql += "PESSOAS AS T2 ";
        sql += "ON ";
        sql += "T1.ID = T2.ID_USUARIO ";
        sql += "LEFT JOIN ";
        sql += "RESTAURANTES AS T3 ";
        sql += "ON ";
        sql += "T1.ID = T3.ID_USUARIO ";
        sql += "WHERE ";
        sql += "EMAIL='" + email + "' ";
        
        Usuario user = this.jdbcTemplate.queryForObject(sql, new UsuarioMapper());

        return user;
    }
    
    @Override
    public void postUsuario(Usuario usuario) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public List<Cartao> getCartoes(String cpfPessoa) {
        
        String sql;

        sql = "SELECT ";
        sql += " ID, ";
        sql += " CODIGO, ";
        sql += " VALIDADE, ";
        sql += " NOME ";
        sql += "FROM ";
        sql += " CARTOES_CREDITO ";
        sql += "WHERE ";
        sql += " CPF_PESSOA = '" + cpfPessoa + "'";

        ArrayList<Cartao> cartoes = new ArrayList<Cartao>();

        List<Map<String, Object>> rows = this.jdbcTemplate.queryForList(sql);
        for(Map<String,Object> row : rows) {
            Cartao cartao = new Cartao(
                (int)row.get("id"), 
                (String)row.get("codigo"), 
                (Date)row.get("validade"), 
                (String)row.get("nome")
            );
            cartoes.add(cartao);
        }

        return cartoes;
    }

}