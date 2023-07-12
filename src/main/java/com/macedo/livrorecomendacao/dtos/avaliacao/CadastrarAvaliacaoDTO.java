package com.macedo.livrorecomendacao.dtos.avaliacao;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CadastrarAvaliacaoDTO(
        @NotBlank
        String matricula,
        @NotBlank
        String isbn,
        @NotNull
        double nota) {
}
