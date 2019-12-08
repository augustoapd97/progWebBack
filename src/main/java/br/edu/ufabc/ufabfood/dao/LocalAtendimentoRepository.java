package br.edu.ufabc.ufabfood.dao;

import java.util.List;

import br.edu.ufabc.ufabfood.models.LocalAtendimento;

/**
 * LocalAtendimentoRepository
 */
public interface LocalAtendimentoRepository {

    public List<LocalAtendimento> getLocais();
    
}