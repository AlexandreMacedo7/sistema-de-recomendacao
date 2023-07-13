package com.macedo.livrorecomendacao.dtos.avaliacaodto;

public record AvaliacaoDadosDTO(
        String nome,
        String isbn,
        double nota
) {
    public AvaliacaoDadosDTO(String nome, String isbn, double nota) {
        this.nome = nome;
        this.isbn = isbn;
        this.nota = nota;
    }
}
