package com.macedo.livrorecomendacao.dtos.alunodto;

import com.macedo.livrorecomendacao.entity.Aluno;
import com.macedo.livrorecomendacao.enums.Turno;

public record AlunoDTO(
        String matricula,
        String nome,
        String turma,
        Turno turno,
        String email,
        String telefone
) {
    public AlunoDTO(Aluno aluno){
        this(aluno.getMatricula(), aluno.getNome(), aluno.getTurma(), aluno.getTurno(),aluno.getEmail(), aluno.getTelefone());
    }
}
