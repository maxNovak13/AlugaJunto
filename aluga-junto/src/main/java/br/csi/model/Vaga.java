package br.csi.model;

import java.util.ArrayList;

public class Vaga {
    private int codvaga;
    private String titulo;
    private int dormitorio;
    private int banheiro;
    private String observacao;
    private int area;
    private int garagem;
    private int numinteres;
    private String imagem;
    private Endereco endereco;
    private Perfil perfil;


   // sem img
    // public Vaga(int codvaga, String titulo, int dormitorio, int banheiro, String observacao, int area, int garagem, int numinteres, Endereco endereco, Perfil perfil) {
    //    this.codvaga = codvaga;
    //    this.titulo = titulo;
    //this.dormitorio = dormitorio;
    //    this.banheiro = banheiro;
    //    this.observacao = observacao;
     //   this.area = area;
    //     this.garagem = garagem;
    //     this.numinteres = numinteres;
    //     this.endereco = endereco;
    //     this.perfil = perfil;}

    //com imag
    public Vaga(int codvaga, String titulo, int dormitorio, int banheiro, String observacao, int area, int garagem, int numinteres, String imagem, Endereco endereco, Perfil perfil) {
        this.codvaga = codvaga;
        this.titulo = titulo;
        this.dormitorio = dormitorio;
        this.banheiro = banheiro;
        this.observacao = observacao;
        this.area = area;
        this.garagem = garagem;
        this.numinteres = numinteres;
        this.imagem = imagem;
        this.endereco = endereco;
        this.perfil = perfil;
    }



    //sem img
   // public Vaga(int codvaga, String titulo, int dormitorio, int banheiro, String observacao, int area, int garagem, int numinteres) {
    //     this.codvaga = codvaga;
    //     this.titulo = titulo;
    //     this.dormitorio = dormitorio;
    //     this.banheiro = banheiro;
    //     this.observacao = observacao;
    //     this.area = area;
    //     this.garagem = garagem;
    //     this.numinteres = numinteres;}


    //com img
    public Vaga(int codvaga, String titulo, int dormitorio, int banheiro, String observacao, int area, int garagem, int numinteres, String imagem) {
        this.codvaga = codvaga;
        this.titulo = titulo;
        this.dormitorio = dormitorio;
        this.banheiro = banheiro;
        this.observacao = observacao;
        this.area = area;
        this.garagem = garagem;
        this.numinteres = numinteres;
        this.imagem = imagem;
    }

    ////sem img
//   public Vaga(String titulo, int dormitorio, int banheiro, String observacao, int area, int garagem, int numinteres, Endereco endereco, Perfil perfil) {
//         this.titulo = titulo; // usado pra criar a vaga
    //        this.dormitorio = dormitorio;
    //        this.banheiro = banheiro;
    //     this.observacao = observacao;
    //        this.area = area;
    //        this.garagem = garagem;
    //       this.numinteres = numinteres;
    //        this.endereco = endereco;
    //       this.perfil = perfil;}


    //com imagem
    public Vaga(String titulo, int dormitorio, int banheiro, String observacao, int area, int garagem, int numinteres, String imagem, Endereco endereco, Perfil perfil) {
        this.titulo = titulo;
        this.dormitorio = dormitorio;
        this.banheiro = banheiro;
        this.observacao = observacao;
        this.area = area;
        this.garagem = garagem;
        this.numinteres = numinteres;
        this.imagem = imagem;
        this.endereco = endereco;
        this.perfil = perfil;
    }

    //sem img
    //    public Vaga(String titulo, int dormitorio, int banheiro, String observacao, int area, int garagem, int numinteres) {
    //      this.titulo = titulo;  //usa em getVagas
    //      this.dormitorio = dormitorio;
    //      this.banheiro = banheiro;
    //       this.observacao = observacao;
    //        this.area = area;
    //       this.garagem = garagem;
    //      this.numinteres = numinteres;}

    //com img
    public Vaga(String titulo, int dormitorio, int banheiro, String observacao, int area, int garagem, int numinteres, String imagem) {
        this.titulo = titulo;
        this.dormitorio = dormitorio;
        this.banheiro = banheiro;
        this.observacao = observacao;
        this.area = area;
        this.garagem = garagem;
        this.numinteres = numinteres;
        this.imagem = imagem;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }


    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public int getCodvaga() {
        return codvaga;
    }

    public void setCodvaga(int codvaga) {
        this.codvaga = codvaga;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getDormitorio() {
        return dormitorio;
    }

    public void setDormitorio(int dormitorio) {
        this.dormitorio = dormitorio;
    }

    public int getBanheiro() {
        return banheiro;
    }

    public void setBanheiro(int banheiro) {
        this.banheiro = banheiro;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public int getGaragem() {
        return garagem;
    }

    public void setGaragem(int garagem) {
        this.garagem = garagem;
    }

    public int getNuminteres() {
        return numinteres;
    }

    public void setNuminteres(int numinteres) {
        this.numinteres = numinteres;
    }
}
