package com.macedo.livrorecomendacao.dtos.alunodto;

import jakarta.validation.constraints.NotBlank;

public record PesquisaMatriculaDTO(
        @NotBlank(message = "Matricula é obrigatória para pesquisa!")
        String matricula
) {
}
