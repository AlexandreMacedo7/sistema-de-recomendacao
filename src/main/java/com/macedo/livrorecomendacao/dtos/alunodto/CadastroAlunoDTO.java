package com.macedo.livrorecomendacao.dtos.alunodto;

import com.macedo.livrorecomendacao.enums.Turno;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CadastroAlunoDTO(
        @NotBlank
        String nome,
        @NotBlank
        String matricula,
        @NotBlank
        String email,
        @NotBlank
        String telefone,
        @NotBlank
        String turma,
        @NotNull
        Turno turno
) {
}
