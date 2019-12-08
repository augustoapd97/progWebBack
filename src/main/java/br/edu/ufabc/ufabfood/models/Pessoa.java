package br.edu.ufabc.ufabfood.models;

/**
 * Pessoa
 */
public class Pessoa extends Usuario{

    public Pessoa(int idUsuario) {
        super(idUsuario);
    }

    public Pessoa(int idUsuario, String cPF, String nome) {
        super(idUsuario);
        CPF = cPF;
        this.nome = nome;
    }

    private String CPF;
    private String nome;
    

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String cPF) {
        CPF = cPF;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}