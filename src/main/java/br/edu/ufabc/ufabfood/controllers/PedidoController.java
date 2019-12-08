package br.edu.ufabc.ufabfood.controllers;

import org.springframework.web.bind.annotation.RestController;

import br.edu.ufabc.ufabfood.dao.JdbcPedidoRepository;
import br.edu.ufabc.ufabfood.dao.RepositoryConfig;
import br.edu.ufabc.ufabfood.models.Pedido;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * PedidoController
 */
@RestController
public class PedidoController {

    @PostMapping(value = "/pedido")
    public boolean postMethodName(@RequestBody Pedido pedido) throws SQLException {
        
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(RepositoryConfig.class);
        JdbcPedidoRepository repositorio = context.getBean(JdbcPedidoRepository.class);
 
    
        repositorio.postPedido(pedido);
        
        context.close();
        
        return true;
    }

    @GetMapping(value="/pedido")
    public ArrayList<Pedido> getPedidosAndamento(@RequestParam int idUsuario) {
        
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(RepositoryConfig.class);
        JdbcPedidoRepository repositorio = context.getBean(JdbcPedidoRepository.class);
 
    
        ArrayList<Pedido> pedidos = repositorio.getPedidosAndamento(idUsuario);
        
        context.close();
        
        return pedidos;
    }
    
    
    
    
}