package br.edu.ufabc.ufabfood.controllers;

import org.springframework.web.bind.annotation.RestController;

import br.edu.ufabc.ufabfood.dao.JdbcCardapioRepository;
import br.edu.ufabc.ufabfood.dao.RepositoryConfig;
import br.edu.ufabc.ufabfood.models.ItemCardapio;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * CardapioController
 */
@RestController
public class CardapioController {

    @GetMapping(value="/cardapio")
    public List<ItemCardapio> getItensCardapio(@RequestParam int idRestaurante, @RequestParam int idLocalAtendimento) {
    
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(RepositoryConfig.class);
        JdbcCardapioRepository repositorio = context.getBean(JdbcCardapioRepository.class);
 
        List<ItemCardapio> itens = repositorio.getCardapio(idRestaurante, idLocalAtendimento);
        
        context.close();
        
        return itens;
    }
    
    
}