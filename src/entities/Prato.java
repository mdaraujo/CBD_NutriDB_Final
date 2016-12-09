/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Miguel
 */
public class Prato {

    private int ID;
    private String nome;
    private String descricao;

    private String cozinha;
    private String dificuldade;
    private String tempo;
    private int doses;
    private String preparacao;

    private String imagem;

    public Prato(int ID, String titulo, String descricao) {
        this.ID = ID;
        this.nome = titulo;
        this.descricao = descricao;
    }

    public Prato(int ID, String cozinha, String dificuldade, String tempo, int doses) {
        this.ID = ID;
        this.cozinha = cozinha;
        this.dificuldade = dificuldade;
        this.tempo = tempo;
        this.doses = doses;
    }

    public Prato(int ID, String nome, String descricao, String cozinha, String dificuldade, String tempo, int doses) {
        this.ID = ID;
        this.nome = nome;
        this.descricao = descricao;
        this.cozinha = cozinha;
        this.dificuldade = dificuldade;
        this.tempo = tempo;
        this.doses = doses;
    }

    public Prato(int id, String cozinha, String dificuldade, String tempo, int doses, String preparacao) {
        this.ID = id;
        this.cozinha = cozinha;
        this.dificuldade = dificuldade;
        this.tempo = tempo;
        this.doses = doses;
        this.preparacao = preparacao;
    }

    @Override
    public String toString() {
        return String.format("%-3d %-70s %-11s %-8s %-5d %-20s", ID, nome, dificuldade, tempo, doses, cozinha);
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getCozinha() {
        return cozinha;
    }

    public void setCozinha(String cozinha) {
        this.cozinha = cozinha;
    }

    public String getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(String dificuldade) {
        this.dificuldade = dificuldade;
    }

    public String getTempo() {
        return tempo;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
    }

    public int getDoses() {
        return doses;
    }

    public void setDoses(int doses) {
        this.doses = doses;
    }

    public String getPreparacao() {
        return preparacao;
    }

    public void setPreparacao(String preparacao) {
        this.preparacao = preparacao;
    }
}
