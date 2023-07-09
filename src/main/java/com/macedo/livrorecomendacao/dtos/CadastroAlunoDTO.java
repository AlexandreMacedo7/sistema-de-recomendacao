package com.macedo.livrorecomendacao.dtos;

import com.macedo.livrorecomendacao.enums.Turno;

public record CadastroAlunoDTO(
        String nome,
        String matricula,
        String email,
        String telefne,
        String turma,
        Turno turno
) {
}
