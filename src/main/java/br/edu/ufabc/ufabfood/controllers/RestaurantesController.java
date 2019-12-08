package br.edu.ufabc.ufabfood.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufabc.ufabfood.dao.JdbcRestaurantesRespository;
import br.edu.ufabc.ufabfood.dao.RepositoryConfig;
import br.edu.ufabc.ufabfood.models.Restaurante;


/**
 * RestaurantesController
 */
@RestController
public class RestaurantesController {

    @GetMapping(value="/restaurantes")
    public List<Restaurante> getRestaurantes(@RequestParam int localAtendimento) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(RepositoryConfig.class);
        JdbcRestaurantesRespository repositorio = context.getBean(JdbcRestaurantesRespository.class);
 
        List<Restaurante> restaurantes = repositorio.getRestaurantesPorLocal(localAtendimento);
        
        context.close();

        return restaurantes;
    }

    @GetMapping(value="/restaurante")
    public Restaurante getRestaurante(@RequestParam int idRestaurante) {
        
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(RepositoryConfig.class);
        JdbcRestaurantesRespository repositorio = context.getBean(JdbcRestaurantesRespository.class);
 
        Restaurante restaurante = repositorio.getRestaurante(idRestaurante);
        
        context.close();

        return restaurante;

    }
    
    
}