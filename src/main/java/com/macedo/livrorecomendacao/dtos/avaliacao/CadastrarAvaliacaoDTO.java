package com.macedo.livrorecomendacao.dtos.avaliacao;

import jakarta.validation.constraints.NotBlank;
public record CadastrarAvaliacaoDTO(
        @NotBlank
        String matricula,
        @NotBlank
        String isbn,
        @NotBlank
        double nota) {
}
