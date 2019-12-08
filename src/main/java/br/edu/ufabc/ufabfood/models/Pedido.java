package br.edu.ufabc.ufabfood.models;

import java.util.Date;
import java.util.List;

/**
 * Pedido
 */
public class Pedido {

    private long ID;
    private double troco;
    private int idCartao;
    
    private List<ItemCardapio> pratos;
    private Pessoa cliente;
    private Pessoa entregador;

    private Date date;

    public Pedido(){}

    public Pedido(int id) {
        this.ID = id;
    }

    public long getID() {
        return ID;
    }

    public List<ItemCardapio> getPratos() {
        return pratos;
    }

    public void setPratos(List<ItemCardapio> pratos) {
        this.pratos = pratos;
    }

    public void addPrato(ItemCardapio prato) {
        this.pratos.add(prato);
    }

    
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getTroco() {
        return troco;
    }

    public void setTroco(double troco) {
        this.troco = troco;
    }

    public int getIdCartao() {
        return idCartao;
    }

    public void setIdCartao(int idCartao) {
        this.idCartao = idCartao;
    }

    public void setID(long iD) {
        ID = iD;
    }

    public Pessoa getCliente() {
        return cliente;
    }

    public void setCliente(Pessoa cliente) {
        this.cliente = cliente;
    }

    public Pessoa getEntregador() {
        return entregador;
    }

    public void setEntregador(Pessoa entregador) {
        this.entregador = entregador;
    }

}