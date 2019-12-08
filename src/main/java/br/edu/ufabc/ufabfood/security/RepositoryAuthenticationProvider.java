package br.edu.ufabc.ufabfood.security;

import java.util.Collections;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import br.edu.ufabc.ufabfood.dao.JdbcUsuarioRepository;
import br.edu.ufabc.ufabfood.dao.RepositoryConfig;
import br.edu.ufabc.ufabfood.models.Usuario;

/**
 * RepositoryAuthenticationProvider
 */
@Component
public class RepositoryAuthenticationProvider implements AuthenticationProvider {

    
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(RepositoryConfig.class);
        JdbcUsuarioRepository repositorio = context.getBean(JdbcUsuarioRepository.class);
        
        String login = authentication.getName();
        String senha = authentication.getCredentials().toString();
 
        Usuario usuario = repositorio.getUsuario(login, senha);
        
        context.close();

        return new UsernamePasswordAuthenticationToken(usuario.getEmail(), null, Collections.emptyList());
        
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    
}