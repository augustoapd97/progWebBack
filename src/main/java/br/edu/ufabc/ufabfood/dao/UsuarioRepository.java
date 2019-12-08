package br.edu.ufabc.ufabfood.dao;

import java.util.List;

import br.edu.ufabc.ufabfood.models.Cartao;
import br.edu.ufabc.ufabfood.models.Usuario;

/**
 * UsuarioRepository
 */
public interface UsuarioRepository {

    public Usuario getUsuario(int id);
    public Usuario getUsuario(String usuario, String senha);
    public Usuario getUsuario(String email);
    public List<Cartao> getCartoes(String cpfUsuario);
    public void postUsuario(Usuario usuario);

}