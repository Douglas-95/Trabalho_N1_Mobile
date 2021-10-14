package com.example.trabalho;

public class Filmes {

    public int id;

    public String nome, categoria;

    public Filmes() {

    }

    public Filmes(String nome, String categoria) {
        this.nome = nome;
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return nome + " | " + categoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}

