package com.macedo.livrorecomendacao.dtos.avaliacaodto;

public record AvaliacaoDadosDTO(
        String titulo,
        String isbn,
        double nota
) {
    public AvaliacaoDadosDTO(String titulo, String isbn, double nota) {
        this.titulo = titulo;
        this.isbn = isbn;
        this.nota = nota;
    }
}
