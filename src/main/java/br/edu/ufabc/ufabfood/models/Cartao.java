package br.edu.ufabc.ufabfood.models;

import java.util.Date;

/**
 * Cartao
 */
public class Cartao {

    int id;
    String codigo;
    Date validade;
    String nome;


    public Cartao(int id, String codigo, Date validade, String nome) {
        this.id = id;
        this.codigo = codigo;
        this.validade = validade;
        this.nome = nome;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Date getValidade() {
        return validade;
    }

    public void setValidade(Date validade) {
        this.validade = validade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}