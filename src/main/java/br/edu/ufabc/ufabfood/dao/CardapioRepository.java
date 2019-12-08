package br.edu.ufabc.ufabfood.dao;

import java.util.List;

import br.edu.ufabc.ufabfood.models.ItemCardapio;

/**
 * CardapioRepository
 */
public interface CardapioRepository {

    public List<ItemCardapio> getCardapio(int idRestaurante, int idLocalAtendimento);


}