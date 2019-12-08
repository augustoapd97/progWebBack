package br.edu.ufabc.ufabfood.models;

/**
 * Restaurante
 */
public class Restaurante extends Usuario{

    private String CNPJ;

    private String razaoSocial; 
    private String nomeFantasia;

    public Restaurante(int idUsuario) {
        super(idUsuario);
    }

    public Restaurante(int idUsuario, String CNPJ, String razaoSocial, String nomeFantasia) {
        super(idUsuario);
        this.CNPJ = CNPJ;
        this.razaoSocial = razaoSocial;
        this.nomeFantasia = nomeFantasia;
    }

    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String cNPJ) {
        CNPJ = cNPJ;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

}
