package com.hildo.costa.library.model;

public class LoanItem {

    private String titulo;      // Título do livro
    private String autor;       // Autor do livro
    private int quantidade;     // Quantidade emprestada

    // Construtor padrão
    public LoanItem() {
    }


    // Construtor completo
    public LoanItem(String titulo, String autor, int quantidade) {
        this.titulo = titulo;
        this.autor = autor;
        this.quantidade = quantidade;
    }

    // Getters e Setters
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
}
