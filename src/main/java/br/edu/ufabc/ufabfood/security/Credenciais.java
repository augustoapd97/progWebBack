package br.edu.ufabc.ufabfood.security;

/**
 * Credenciais
 */
public class Credenciais {

    private String email;
    private String senha;

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSenha() {
        return senha;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

}