package com.macedo.livrorecomendacao.dtos.alunodto;

import com.macedo.livrorecomendacao.enums.Turno;

public record AlunoDTO(
        String matricula,
        String nome,
        String turma,
        Turno turno,
        String email,
        String telefone
) {
}
