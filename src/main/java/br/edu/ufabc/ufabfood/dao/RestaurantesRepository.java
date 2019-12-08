package br.edu.ufabc.ufabfood.dao;

import java.util.List;

import br.edu.ufabc.ufabfood.models.Restaurante;

/**
 * RestaurantesRepository
 */
public interface RestaurantesRepository {

    public Restaurante getRestaurante(int id);
    public List<Restaurante> getRestaurantesPorLocal(int idLocal);
    
}