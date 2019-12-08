package br.edu.ufabc.ufabfood.models;

public class Usuario {

    private int idUsuario;
    private String email;
    private boolean ativo;

    public Usuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    public int getIdUsuario() {
        return idUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }


    
}