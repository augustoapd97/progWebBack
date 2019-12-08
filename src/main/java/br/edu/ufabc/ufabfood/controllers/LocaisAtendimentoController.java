package br.edu.ufabc.ufabfood.controllers;

import org.springframework.web.bind.annotation.RestController;

import br.edu.ufabc.ufabfood.dao.JdbcLocalAtendimentoRepository;
import br.edu.ufabc.ufabfood.dao.RepositoryConfig;
import br.edu.ufabc.ufabfood.models.LocalAtendimento;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * LocaisAtendimentoController
 */
@RestController
public class LocaisAtendimentoController {

    @GetMapping(value="/locaisAtendimento")
    public List<LocalAtendimento> getLocaisAtendimento() {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(RepositoryConfig.class);
        JdbcLocalAtendimentoRepository repositorio = context.getBean(JdbcLocalAtendimentoRepository.class);
 
        List<LocalAtendimento> locais = repositorio.getLocais();
        
        context.close();

        return locais;
    }
    

}