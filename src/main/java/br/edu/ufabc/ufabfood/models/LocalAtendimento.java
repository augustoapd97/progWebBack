package br.edu.ufabc.ufabfood.models;

/**
 * LocalAtendimento
 */
public class LocalAtendimento {

    private int ID;
    private String local;

    public LocalAtendimento(int iD, String local) {
        ID = iD;
        this.local = local;
    }

    public int getID() {
        return ID;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    
}