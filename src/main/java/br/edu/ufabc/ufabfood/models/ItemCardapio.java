package br.edu.ufabc.ufabfood.models;

/**
 * ItemCardapio
 */
public class ItemCardapio {

    private Prato prato;
    private int quantidade;

    public ItemCardapio(Prato prato, int quantidade) {
        this.prato = prato;
        this.quantidade = quantidade;
    }

    public Prato getPrato() {
        return prato;
    }

    public void setPrato(Prato prato) {
        this.prato = prato;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

}