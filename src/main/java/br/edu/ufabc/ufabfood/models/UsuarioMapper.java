package br.edu.ufabc.ufabfood.models;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * UsuarioMapper
 */
public class UsuarioMapper implements RowMapper<Usuario> {

    @Override
    public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
        
        Usuario usuario;

        if (rs.getString("CPF") == null){
            usuario = new Restaurante(rs.getInt("id"));
            ((Restaurante)usuario).setCNPJ(rs.getString("CNPJ"));
            ((Restaurante)usuario).setNomeFantasia(rs.getString("nome_fantasia"));
            ((Restaurante)usuario).setRazaoSocial(rs.getString("razao_social"));
        } else {
            usuario = new Pessoa(rs.getInt("id"));
            ((Pessoa)usuario).setCPF(rs.getString("CPF"));
            ((Pessoa)usuario).setNome(rs.getString("nome"));
        }
        usuario.setAtivo(rs.getBoolean("ativo"));
        usuario.setEmail(rs.getString("email"));;
        
        
        return usuario;

    }
    
}