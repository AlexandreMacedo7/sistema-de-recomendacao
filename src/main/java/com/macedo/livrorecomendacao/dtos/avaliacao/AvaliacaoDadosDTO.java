package com.macedo.livrorecomendacao.dtos.avaliacao;

import com.macedo.livrorecomendacao.entity.Avaliacao;
import com.macedo.livrorecomendacao.entity.Livro;

import java.util.List;

public record AvaliacaoDadosDTO(
        String isbn,
        double nota
) {
    public AvaliacaoDadosDTO(String isbn, double nota) {
        this.isbn = isbn;
        this.nota = nota;
    }
}
