package br.edu.ufabc.ufabfood.models;

/**
 * Prato
 */
public class Prato {

    private int id;
    private String nome;
    private String descricao;
    private double valor;
    private String imagem;
    private int tempoPreparo;
    private Categoria categoria;


    public Prato(int id, String nome, String descricao, double valor, String imagem, int tempoPreparo, Categoria categoria) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.imagem = imagem;
        this.tempoPreparo = tempoPreparo;
        this.categoria = categoria;
    }
    public Prato(int id, String nome, double valor, String descricao, int tempoPreparo) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.imagem = imagem;
        this.tempoPreparo = tempoPreparo;
        this.categoria = categoria;
    }

    public int getID() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public int getTempoPreparo() {
        return tempoPreparo;
    }

    public void setTempoPreparo(int tempoPreparo) {
        this.tempoPreparo = tempoPreparo;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    

}