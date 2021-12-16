package com.example.trabalho_n2;

import android.content.res.ColorStateList;

import androidx.core.content.ContextCompat;

public class Alimento {

    public int id;
    public String nome, categoria, categoriaR;

    public Alimento() {

    }

    public Alimento(String nome, String categoria, String categoriaR) {
        this.nome = nome;
        this.categoria = categoria;
        this.categoriaR = categoriaR;

    }

    @Override
    public String toString() {
        return nome +  " - " + categoria + " - " + categoriaR;
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

    public String getCategoriaR() {return categoriaR;}

    public void setCategoriaR(String categoriaR) {this.categoriaR = categoriaR;}
}
