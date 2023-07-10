package com.macedo.livrorecomendacao.dtos.alunodto;

import com.macedo.livrorecomendacao.enums.Turno;

public record CadastroAlunoDTO(
        String nome,
        String matricula,
        String email,
        String telefone,
        String turma,
        Turno turno
) {
}