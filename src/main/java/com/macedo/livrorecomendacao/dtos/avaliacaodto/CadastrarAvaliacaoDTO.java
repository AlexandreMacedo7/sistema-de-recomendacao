package com.macedo.livrorecomendacao.dtos.avaliacaodto;

import com.macedo.livrorecomendacao.entity.Avaliacao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CadastrarAvaliacaoDTO(
        @NotBlank
        String matricula,
        @NotBlank
        String isbn,
        @NotNull
        double nota) {
    public CadastrarAvaliacaoDTO(Avaliacao avaliacao) {
        this(avaliacao.getAluno().getMatricula(), avaliacao.getLivro().getIsbn(), avaliacao.getNota());
    }
}
