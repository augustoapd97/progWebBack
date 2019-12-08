package br.edu.ufabc.ufabfood.controllers;

import org.springframework.web.bind.annotation.RestController;

import br.edu.ufabc.ufabfood.dao.JdbcUsuarioRepository;
import br.edu.ufabc.ufabfood.dao.RepositoryConfig;
import br.edu.ufabc.ufabfood.models.Cartao;
import br.edu.ufabc.ufabfood.models.Usuario;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * UsuariosController
 */
@RestController
public class UsuariosController {

    @GetMapping(value="/usuarios")
    public Usuario getusuario(@RequestParam String email) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(RepositoryConfig.class);
        JdbcUsuarioRepository repositorio = context.getBean(JdbcUsuarioRepository.class);
 
        Usuario usuario = repositorio.getUsuario(email);
        
        context.close();

        return usuario;
    }
    
    @GetMapping(value="/usuarios/cartoes")
    public List<Cartao> getCartoes(@RequestParam String cpfPessoa) {
        
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(RepositoryConfig.class);
        JdbcUsuarioRepository repositorio = context.getBean(JdbcUsuarioRepository.class);
 
        List<Cartao> cartoes = repositorio.getCartoes(cpfPessoa);
        
        context.close();

        return cartoes;
    }
    
    
}