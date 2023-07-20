package com.macedo.livrorecomendacao.dtos.avaliacaodto;

public record AvaliacaoDadosGeralDTO(
        String aluno,
        String matricula,
        String titulo,
        String isbn,
        double nota
) {
    public AvaliacaoDadosGeralDTO(String aluno, String matricula,String titulo, String isbn, double nota) {
        this.aluno = aluno;
        this.matricula = matricula;
        this.titulo = titulo;
        this.isbn = isbn;
        this.nota = nota;
    }
}
