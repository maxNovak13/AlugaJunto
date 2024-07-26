package br.csi.model;

public class Perfil {
    private int codperfil;
    private String genero;
    private String idade;
    private String pet;
    private String observacao;

    public Perfil(String pet, String idade, String genero, String observacao) {
        this.pet = pet;
        this.idade = idade;
        this.genero = genero;
        this.observacao = observacao;
    }

    public Perfil(int codperfil, String pet, String idade, String genero, String observacao) {
        this.codperfil = codperfil;
        this.pet = pet;
        this.idade = idade;
        this.genero = genero;
        this.observacao = observacao;
    }

    public int getCodperfil() {
        return codperfil;
    }

    public void setCodperfil(int codperfil) {
        this.codperfil = codperfil;
    }

    public String getPet() {
        return pet;
    }

    public void setPet(String pet) {
        this.pet = pet;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
